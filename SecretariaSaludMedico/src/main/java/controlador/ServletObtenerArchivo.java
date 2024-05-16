/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controlador;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Base64;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Archivo;

/**
 *
 * @author march
 */
@WebServlet(name = "ServletObtenerArchivo", urlPatterns = {"/ServletObtenerArchivo"})
public class ServletObtenerArchivo extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idArchivo = request.getParameter("idArchivo");

        ConsultasArchivos sqlArchivos = new ConsultasArchivos();
        Archivo archivo = sqlArchivos.obtenerArchivoPorId(Integer.parseInt(idArchivo));

        if (archivo != null) {
            // Obtener el contenido del archivo como un arreglo de bytes
            byte[] contenido = archivo.getContenido();

            // Convertir el contenido a una cadena base64
            String contenidoBase64 = Base64.getEncoder().encodeToString(contenido);

            // Establecer el tipo de contenido de la respuesta
            response.setContentType(archivo.getTipo());

            // Configurar cabeceras de la respuesta
            response.setHeader("Content-Disposition", "inline; filename=" + archivo.getNombre());

            // Escribir el contenido base64 en el cuerpo de la respuesta
            try (PrintWriter out = response.getWriter()) {
                out.print(contenidoBase64);
            }
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            response.getWriter().write("Archivo no encontrado");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
