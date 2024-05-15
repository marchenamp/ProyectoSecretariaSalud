/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

/**
 *
 * @author Hiram
 */
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConsultasArchivos extends Conexion {

    public ConsultasArchivos() {
    }

    // Método para registrar un archivo adjunto
    public boolean registrarArchivo(int idExpediente, String nombreArchivo, String tipoArchivo, byte[] datos) {
        PreparedStatement pst = null;
        try {
            String consulta = "INSERT INTO archivos_adjuntos (id_expediente, nombre_archivo, tipo_archivo, datos) VALUES (?, ?, ?, ?)";
            pst = getConexion().prepareStatement(consulta);
            pst.setInt(1, idExpediente);
            pst.setString(2, nombreArchivo);
            pst.setString(3, tipoArchivo);
            pst.setBytes(4, datos);

            return pst.executeUpdate() == 1;

        } catch (SQLException e) {
            System.err.println("Error al registrar archivo: " + e);
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
            } catch (SQLException e) {
                System.err.println("Error al cerrar PreparedStatement: " + e);
            }
        }
        return false;
    }

    // Método para eliminar un archivo adjunto de un expediente en la base de datos
    public boolean eliminarArchivo(int idExpediente, String nombreArchivo) {
        PreparedStatement pst = null;
        try {
            String consulta = "DELETE FROM archivos_adjuntos WHERE id_expediente = ? AND nombre_archivo = ?";
            pst = getConexion().prepareStatement(consulta);
            pst.setInt(1, idExpediente);
            pst.setString(2, nombreArchivo);

            return pst.executeUpdate() == 1; // Retorna true si se eliminó un registro (archivo)

        } catch (SQLException e) {
            System.err.println("Error al eliminar el archivo del expediente: " + e);
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
            } catch (SQLException e) {
                System.err.println("Error al cerrar PreparedStatement: " + e);
            }
        }
        return false;
    }
    // Método para verificar si un archivo ya está asociado a un expediente en la base de datos
    public boolean archivoExistente(int idExpediente, String nombreArchivo) {
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            String consulta = "SELECT id_archivo FROM archivos_adjuntos WHERE id_expediente = ? AND nombre_archivo = ?";
            pst = getConexion().prepareStatement(consulta);
            pst.setInt(1, idExpediente);
            pst.setString(2, nombreArchivo);
            rs = pst.executeQuery();

            return rs.next(); // Retorna true si hay al menos un resultado (archivo encontrado)

        } catch (SQLException e) {
            System.err.println("Error al verificar si el archivo ya existe en el expediente: " + e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pst != null) {
                    pst.close();
                }
            } catch (SQLException e) {
                System.err.println("Error al cerrar ResultSet o PreparedStatement: " + e);
            }
        }
        return false;
    }
    
}

