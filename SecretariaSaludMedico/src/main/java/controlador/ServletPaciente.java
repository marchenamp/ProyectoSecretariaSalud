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
import modelo.AccionPaciente;
import modelo.EstadoCivil;
import modelo.Genero;
import modelo.Paciente;
import modelo.Tutor;

/**
 *
 * @author march
 */
@WebServlet(name = "ServletPaciente", urlPatterns = {"/ServletPaciente"})
public class ServletPaciente extends HttpServlet {

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

        String botonRegistrar = request.getParameter("RegistrarPaciente");

        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");

        String nombresTutor = request.getParameter("nombreTutor");

        Paciente paciente = null;
        Tutor tutorPaciente = null;

        if (nombresTutor.equalsIgnoreCase("")) {
            String nombresPaciente = request.getParameter("nombrePaciente");
            String apellidoPaternoPaciente = request.getParameter("apellidoPaternoPaciente");
            String apellidoMaternoPaciente = request.getParameter("apellidoMaternoPaciente");
            String correoPaciente = request.getParameter("emailPaciente");
            String passwordPaciente = request.getParameter("passwordPaciente");

            String fechaNacimientoPacienteString = request.getParameter("fechaNacimientoPaciente");
            java.util.Date fechaUtilPaciente = formatoFecha.parse(fechaNacimientoPacienteString);
            Date fechaNacimientoPaciente = new Date(fechaUtilPaciente.getTime());

            String telefonoPaciente = request.getParameter("telefonoPaciente");
            EstadoCivil estadoCivilPaciente = EstadoCivil.valueOf(request.getParameter("estadoCivilPaciente"));
            Genero generoPaciente = Genero.valueOf(request.getParameter("generoPaciente"));

            paciente = new Paciente(nombresPaciente, apellidoPaternoPaciente, apellidoMaternoPaciente, correoPaciente, passwordPaciente, fechaNacimientoPaciente, telefonoPaciente, estadoCivilPaciente, generoPaciente);

        } else {
            String apellidoPaternoTutor = request.getParameter("apellidoPaternoTutor");
            String apellidoMaternoTutor = request.getParameter("apellidoMaternoTutor");

            String telefonoTutor = request.getParameter("telefonoTutor");
            Genero generoTutor = Genero.valueOf(request.getParameter("generoTutor"));
            String parentescoTutor = request.getParameter("parentescoTutor");

            String fechaNacimientoTutorString = request.getParameter("fechaNacimientoTutor");
            java.util.Date fechaUtilTutor = formatoFecha.parse(fechaNacimientoTutorString);
            Date fechaNacimientoTutor = new Date(fechaUtilTutor.getTime());

            String nombresPaciente = request.getParameter("nombrePaciente");
            String apellidoPaternoPaciente = request.getParameter("apellidoPaternoPaciente");
            String apellidoMaternoPaciente = request.getParameter("apellidoMaternoPaciente");
            String correoPaciente = request.getParameter("emailPaciente");
            String passwordPaciente = request.getParameter("passwordPaciente");

            String fechaNacimientoPacienteString = request.getParameter("fechaNacimientoPaciente");
            java.util.Date fechaUtilPaciente = formatoFecha.parse(fechaNacimientoPacienteString);
            Date fechaNacimientoPaciente = new Date(fechaUtilPaciente.getTime());

            String telefonoPaciente = request.getParameter("telefonoPaciente");
            EstadoCivil estadoCivilPaciente = EstadoCivil.valueOf(request.getParameter("estadoCivilPaciente"));
            Genero generoPaciente = Genero.valueOf(request.getParameter("generoPaciente"));

            tutorPaciente = new Tutor(nombresTutor, apellidoPaternoTutor, apellidoMaternoTutor, fechaNacimientoTutor, telefonoTutor, generoTutor, parentescoTutor);

            paciente = new Paciente(nombresPaciente, apellidoPaternoPaciente, apellidoMaternoPaciente, correoPaciente, passwordPaciente, fechaNacimientoPaciente, telefonoPaciente, estadoCivilPaciente, generoPaciente);

        }

        try (Connection connection = factory.newConnection(); Channel channel = connection.createChannel()) {
            // Declara el intercambio si aún no existe
            channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.FANOUT);

            // Crear una cola temporal exclusiva para recibir la confirmación
            String confirmationQueueName = channel.queueDeclare().getQueue();

            AccionPaciente accionPaciente = null;

            if (botonRegistrar != null) {

                if (nombresTutor.equalsIgnoreCase("")) {
                    accionPaciente = new AccionPaciente("registrar_sin_tutor", paciente);
                } else {
                    accionPaciente = new AccionPaciente("registrar_con_tutor", paciente, tutorPaciente);
                }
            }
            Gson serializer = new Gson();
            String mensaje = serializer.toJson(accionPaciente);

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
                request.setAttribute("txt-exito", "Registro de paciente exitoso");
            } else {
                request.setAttribute("txt-exito", "Registro de paciente fallido");
            }
            RequestDispatcher rd = request.getRequestDispatcher("indexPaciente.jsp");
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

        } catch (ParseException | TimeoutException ex) {
            Logger.getLogger(ServletPaciente.class
                    .getName()).log(Level.SEVERE, null, ex);

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

        } catch (ParseException | TimeoutException ex) {
            Logger.getLogger(ServletPaciente.class
                    .getName()).log(Level.SEVERE, null, ex);

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
