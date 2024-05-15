/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controlador;

import com.google.gson.Gson;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.GetResponse;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.AccionExpediente;
import modelo.Expediente;

/**
 *
 * @author march
 */
@WebServlet(name = "ServletExpediente", urlPatterns = {"/ServletExpediente"})
public class ServletExpediente extends HttpServlet {

    private static final String EXCHANGE_NAME = "expedientes";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.util.concurrent.TimeoutException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, TimeoutException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        String botonEditar = request.getParameter("EditarExpediente");
        String botonVerExpediente = request.getParameter("VerExpediente");
        String botonVerEditarExpediente = request.getParameter("VerEditarExpediente");

        if (botonVerEditarExpediente != null) {
            int idExpediente = Integer.parseInt(request.getParameter("idExpediente"));
            ConsultasExpediente sqlExpediente = new ConsultasExpediente();

            Expediente expedienteEncontrado = sqlExpediente.buscarExpediente(idExpediente);

            if (expedienteEncontrado != null) {
                request.setAttribute("expedienteEncontrado", expedienteEncontrado);
                RequestDispatcher rd = request.getRequestDispatcher("editarExpediente.jsp");
                rd.forward(request, response);
            } else {
                request.setAttribute("txt-advertencia", "No se encontró ningún expediente");
                RequestDispatcher rd = request.getRequestDispatcher("expediente.jsp");
                rd.forward(request, response);
            }

        }  else if (botonVerExpediente == null) {
            String tipoSangre = request.getParameter("tipoSangre");
            String estatura = request.getParameter("estatura");
            float peso = Float.parseFloat(request.getParameter("peso"));
            String alergias = request.getParameter("alergias");
            String frecuenciaCardiaca = request.getParameter("frecuenciaCardiaca");
            String padecimientosPersonales = request.getParameter("padecimientosPersonales");
            String antecedentesHereditarios = request.getParameter("antecedentesHereditarios");
            String nombreContactoEmergencia = request.getParameter("nombreContactoEmergencia");
            String telefonoContactoEmergencia = request.getParameter("numeroContactoEmergencia");

            Expediente expediente = null;

            try (Connection connection = factory.newConnection(); Channel channel = connection.createChannel()) {
                // Declara el intercambio si aún no existe
                channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.FANOUT);

                // Crear una cola temporal exclusiva para recibir la confirmación
                String confirmationQueueName = channel.queueDeclare().getQueue();

                AccionExpediente accionExpediente = null;

                if (botonEditar != null) {

                    expediente = new Expediente();

                    expediente.setId(Integer.parseInt(request.getParameter("idExpediente")));
                    expediente.setAntecedentesHereditarios(antecedentesHereditarios);
                    expediente.setPadecimientoPersonales(padecimientosPersonales);
                    expediente.setAlergias(alergias);
                    expediente.setEstatura(estatura);
                    expediente.setPeso(peso);
                    expediente.setFrecuenciaCardiaca(frecuenciaCardiaca);
                    expediente.setTipoSangre(tipoSangre);

                    accionExpediente = new AccionExpediente("editar", expediente);

                }

                Gson serializer = new Gson();
                String mensaje = serializer.toJson(accionExpediente);

                // Configurar las propiedades del mensaje para recibir la confirmación
                AMQP.BasicProperties props = new AMQP.BasicProperties.Builder()
                        .replyTo(confirmationQueueName) // Establecer la cola de respuesta
                        .correlationId("1") // ID de correlación para identificar la respuesta
                        .build();

                // Publica el mensaje en el intercambio
                channel.basicPublish(EXCHANGE_NAME, "", props, mensaje.getBytes("UTF-8"));
                System.out.println(" [x] Sent '" + mensaje + "'");

                // Esperar hasta recibir la confirmación
                boolean confirmed = false;
                String confirmationMessage = null;
                while (!confirmed) {
                    GetResponse responseConsumer = channel.basicGet(confirmationQueueName, true);
                    if (responseConsumer != null) {
                        confirmationMessage = new String(responseConsumer.getBody(), "UTF-8");
                        System.out.println(" [x] Received confirmation: " + confirmationMessage);
                        confirmed = true;
                    }
                }

                if (confirmationMessage.equalsIgnoreCase("Exito")) {
                    request.setAttribute("txt-exito", "Edición de expediente exitoso");
                } else {
                    request.setAttribute("txt-exito", "Edición de expediente fallido");
                }
                RequestDispatcher rd = request.getRequestDispatcher("expediente.jsp");
                rd.forward(request, response);
            }
        } else {
            int idExpediente = Integer.parseInt(request.getParameter("idExpediente"));
            ConsultasExpediente sqlExpediente = new ConsultasExpediente();

            Expediente expedienteEncontrado = sqlExpediente.buscarExpediente(idExpediente);

            if (expedienteEncontrado != null) {
                request.setAttribute("expedienteEncontrado", expedienteEncontrado);
                RequestDispatcher rd = request.getRequestDispatcher("expediente.jsp");
                rd.forward(request, response);
            } else {
                request.setAttribute("txt-advertencia", "No se encontró ningún expediente");
                RequestDispatcher rd = request.getRequestDispatcher("expediente.jsp");
                rd.forward(request, response);
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (TimeoutException ex) {
            Logger.getLogger(ServletExpediente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (TimeoutException ex) {
            Logger.getLogger(ServletExpediente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
