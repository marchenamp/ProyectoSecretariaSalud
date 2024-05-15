/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

/**
 *
 * @author Hiram
 */
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Archivo;

public class ConsultasArchivos extends Conexion {

    public ConsultasArchivos() {
    }

    public boolean registraArchivo(int idExpediente, String nombre, String tipo, InputStream contenido) {
        PreparedStatement pst = null;

        try {
            String consulta = "INSERT INTO archivos (id_expediente, nombre, tipo, contenido) VALUES (?, ?, ?, ?)";
            pst = getConexion().prepareStatement(consulta);
            pst.setInt(1, idExpediente);
            pst.setString(2, nombre);
            pst.setString(3, tipo);
            pst.setBlob(4, contenido);

            if (pst.executeUpdate() == 1) {
                return true;
            }
        } catch (Exception e) {
            System.err.println("Error en: " + e);
        } finally {
//            try {
//                if (getConexion() != null) {
//                    getConexion().close();
//                }
//                if (pst != null) {
//                    pst.close();
//                }
//            } catch (Exception e) {
//                System.err.println("Error en: " + e);
//            }
        }
        return false;
    }

    public boolean eliminarArchivo(int idArchivo) {
        PreparedStatement pst = null;

        try {
            String consulta = "DELETE FROM archivos WHERE id_archivo = ?";
            pst = getConexion().prepareStatement(consulta);
            pst.setInt(1, idArchivo);

            if (pst.executeUpdate() == 1) {
                return true;
            }
        } catch (Exception e) {
            System.err.println("Error en: " + e);
        } finally {
//            try {
//                if (getConexion() != null) {
//                    getConexion().close();
//                }
//                if (pst != null) {
//                    pst.close();
//                }
//            } catch (Exception e) {
//                System.err.println("Error en: " + e);
//            }
        }
        return false;
    }

    public List<Archivo> obtenerArchivosPorExpediente(int idExpediente) throws IOException {
        List<Archivo> archivos = new ArrayList<>();

        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            String consulta = "SELECT id_archivo, nombre, tipo, contenido FROM archivos WHERE id_expediente = ?";
            pst = getConexion().prepareStatement(consulta);
            pst.setInt(1, idExpediente);
            rs = pst.executeQuery();

            while (rs.next()) {
                int idArchivo = rs.getInt("id_archivo");
                String nombre = rs.getString("nombre");
                String tipo = rs.getString("tipo");
                InputStream contenidoStream = rs.getBinaryStream("contenido");

                archivos.add(new Archivo(idArchivo, idExpediente, nombre, tipo, contenidoStream));
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener archivos por expediente: " + e);
        } finally {
//            try {
//                if (getConexion() != null) {
//                    getConexion().close();
//                }
//                if (pst != null) {
//                    pst.close();
//                }
//            } catch (Exception e) {
//                System.err.println("Error en: " + e);
//            }
        }

        return archivos;
    }

    private byte[] obtenerBytesDesdeInputStream(InputStream inputStream) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[4096];
        int bytesRead = -1;

        try {
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            return outputStream.toByteArray();
        } catch (Exception e) {
            System.err.println("Error al obtener bytes desde InputStream: " + e);
        } finally {
            try {
                outputStream.close();
            } catch (Exception e) {
                System.err.println("Error al cerrar outputStream: " + e);
            }
        }

        return null;
    }
    
    public String obtenerTablaArchivos(int idExpediente) throws IOException {
        String htmlcode = "";
        for (Archivo archivo : obtenerArchivosPorExpediente(idExpediente)) {
            htmlcode
                    += "   <tr>\n" 
                    + "       <td>"+archivo.getNombre()+"</td>\n" 
                    + "       <td>"+archivo.getTipo()+"</td>\n" 
                    + "       <td class=\"celda-btn\">\n" 
                    + "            <button id=\"ver-btn\">Ver</button>\n" 
                    + "       </td>\n" 
                    + "       <td>\n"
                    + "            <form id=\"delete\" action=\"ServletExpediente\" method=\"post\">\n"
                    + "                 <input type=\"hidden\" name=\"idArchivoEliminar\" id=\"idArchivoEliminarInput\" value=\"" + archivo.getId() + "\">\n"
                    + "                 <button class=\"eliminar-btn\" name=\"EliminarArchivo\"> \n"
                    + "                     <img src=\"IMG/eliminar.png\" alt=\"eliminar\" id=\"eliminar-icon\"/>\n"
                    + "                 </button>\n"
                    + "            </form>\n"
                    + "       </td>\n"
                    + "    </tr>";

        }
        return htmlcode;
    }
    
//    public String obtenerTablaPermisos(int idMedico) {
//        String htmlcode = "";
//        for (Permiso permiso : obtenerPermisosPorMedico(idMedico)) {
//            Expediente expediente = permiso.getExpediente();
//
//            htmlcode
//                    += "    <tr>"
//                    + "        <td>" + permiso.getCita().getFechaCita().toString() + "</td>"
//                    + "        <td>" + permiso.getCita().getHora() + "</td>"
//                    + "        <td>" + expediente.getPaciente().getNombres() + " " + expediente.getPaciente().getApellidoPaterno() + " " + expediente.getPaciente().getApellidoMaterno() + "</td>"
//                    + "        <td class=\"celda-btn\">"
//                    + "            <form action=\"ServletExpediente\" method=\"post\">\n"
//                    + "                 <input type=\"hidden\" name=\"idExpediente\" id=\"idExpedienteInput\" value=\"" + expediente.getId() + "\">\n"
//                    + "                 <input type=\"hidden\" name=\"VerExpediente\" value=\"true\">"
//                    + "                 <button type=\"button\" id=\"ver-btn\" name=\"VerExpediente\" onclick=\"abrirModal()\">Ver Expediente</button>"
//                    + "            </form>\n"
//                    + "        </td>"
//                    + "    </tr>";
//
//        }
//        return htmlcode;
//    }
    
}
