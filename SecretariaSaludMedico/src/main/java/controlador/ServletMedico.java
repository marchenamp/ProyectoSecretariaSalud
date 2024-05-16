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
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.AccionMedico;
import modelo.Genero;
import modelo.Medico;

/**
 *
 * @author march
 */
@WebServlet(name = "ServletMedico", urlPatterns = {"/ServletMedico"})
public class ServletMedico extends HttpServlet {

    private static final String EXCHANGE_NAME = "usuarios";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.util.concurrent.TimeoutException
     * @throws java.text.ParseException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, TimeoutException, ParseException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        String botonRegistrar = request.getParameter("RegistrarMedico");

        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");

        String nombresMedico = request.getParameter("nombreMedico");
        String apellidoPaternoMedico = request.getParameter("apellidoPaternoMedico");
        String apellidoMaternoMedico = request.getParameter("apellidoMaternoMedico");
        String correoMedico = request.getParameter("emailMedico");
        String passwordMedico = request.getParameter("passwordMedico");

        String fechaNacimientoMedicoString = request.getParameter("fechaNacimientoMedico");
        java.util.Date fechaUtilMedico = formatoFecha.parse(fechaNacimientoMedicoString);
        Date fechaNacimientoMedico = new Date(fechaUtilMedico.getTime());

        String telefonoMedico = request.getParameter("telefonoMedico");
        Genero generoMedico = Genero.valueOf(request.getParameter("generoMedico"));
        String cedulaProfesionalMedico = request.getParameter("cedulaProfesionalMedico");
        String especialidadMedica = request.getParameter("especialidadMedica");
        String lugarTrabajoActual = request.getParameter("lugarTrabajoActual");
        
        Medico medico = new Medico(nombresMedico, apellidoPaternoMedico, apellidoMaternoMedico, correoMedico, passwordMedico, fechaNacimientoMedico, telefonoMedico, generoMedico, cedulaProfesionalMedico, especialidadMedica, lugarTrabajoActual);

        try (Connection connection = factory.newConnection(); Channel channel = connection.createChannel()) {
            // Declara el intercambio si aún no existe
            channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.FANOUT);

            // Crear una cola temporal exclusiva para recibir la confirmación
            String confirmationQueueName = channel.queueDeclare().getQueue();

            AccionMedico accionMedico = null;

            if (botonRegistrar != null) {

                accionMedico = new AccionMedico("registrar", medico);

            }
            Gson serializer = new Gson();
            String mensaje = serializer.toJson(accionMedico);

            // Configurar las propiedades del mensaje para recibir la confirmación
            AMQP.BasicProperties props = new AMQP.BasicProperties.Builder()
                    .replyTo(confirmationQueueName) // Establecer la cola de respuesta
                    .correlationId("1") // ID de correlación para identificar la respuesta
                    .expiration("60000") // Caducidad de 60 segundos
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
                request.setAttribute("txt-exito", "Registro de medico exitoso");
            } else {
                request.setAttribute("txt-exito", "Registro de medico fallido");
            }
            RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
            rd.forward(request, response);
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
        } catch (TimeoutException | ParseException ex) {
            Logger.getLogger(ServletMedico.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (TimeoutException | ParseException ex) {
            Logger.getLogger(ServletMedico.class.getName()).log(Level.SEVERE, null, ex);
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
