/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controlador;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import modelo.Paciente;

/**
 *
 * @author magda
 */
@WebServlet(name = "IniciarSesion", urlPatterns = {"/IniciarSesion"})
public class IniciarSesion extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String correo = request.getParameter("correo");
        String password = request.getParameter("password");

        ConsultasPaciente sql = new ConsultasPaciente();

        if (sql.autenticacion(correo, password)) {
            Paciente paciente = sql.obtenerPacientePorCorreo(correo, password);

            // Generar token JWT
            String token = Jwts.builder()
                    .setSubject(correo)
                    .claim("correo", paciente.getCorreo())
                    .claim("nombres", paciente.getNombres())
                    .claim("id", paciente.getId())
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + 300000)) // 5 minutos de validez
                    .signWith(SignatureAlgorithm.HS512, "2bb80a5ff92fefff0d3fc3e46fd8f5e2a63d78377757c713f4a3e6e11e78c9a5a553891de4f598a6f0f6a2854e23bcb8b286e069e7c5d7d60c03441c0e285383") // Cambia "claveSecreta" por tu propia clave secreta
                    .compact();
            
            
            // Establecer el token en una sesión para acceder desde el JSP
            HttpSession session = request.getSession();
            session.setAttribute("token", token);

            // Redirigir al usuario al menúPaciente.jsp
            RequestDispatcher rd = request.getRequestDispatcher("menuPaciente.jsp");
            rd.forward(request, response);
        } else {
            request.setAttribute("txt-advertencia", "Credenciales incorrectas");
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
        processRequest(request, response);
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
        processRequest(request, response);
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
