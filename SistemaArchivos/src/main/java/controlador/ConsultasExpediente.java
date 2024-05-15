/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author march
 */
public class ConsultasExpediente extends Conexion {

    public ConsultasExpediente() {
    }

    public boolean registrarExpediente(String tipoSangre, String estatura, float peso, String alergias, String frecuenciaCardiaca, String padecimientosPersonales, String antecedentesHereditarios, String nombreContactoEmergencia, String telefonoContactoEmergencia, int idPaciente) {
        PreparedStatement pst = null;
        try {
            String consulta = "INSERT INTO expedientes (tipo_sangre, estatura, peso, alergias, frecuencia_cardiaca, padecimientos_personales, antecedentes_hereditarios, nombre_contacto_emergencia, telefono_contacto_emergencia, id_paciente) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            pst = getConexion().prepareStatement(consulta);
            pst.setString(1, tipoSangre);
            pst.setString(2, estatura);
            pst.setFloat(3, peso);
            pst.setString(4, alergias);
            pst.setString(5, frecuenciaCardiaca);
            pst.setString(6, padecimientosPersonales);
            pst.setString(7, antecedentesHereditarios);
            pst.setString(8, nombreContactoEmergencia);
            pst.setString(9, telefonoContactoEmergencia);
            pst.setInt(10, idPaciente);

            if (pst.executeUpdate() == 1) {
                return true;
            }
        } catch (SQLException e) {
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

    public boolean editarExpediente(int idExpediente, String tipoSangre, String estatura, float peso, String alergias, String frecuenciaCardiaca, String padecimientosPersonales, String antecedentesHereditarios) {
        PreparedStatement pst = null;
        try {
            String consulta = "UPDATE expedientes SET tipo_sangre = ?, estatura = ?, peso = ?, alergias = ?, frecuencia_cardiaca = ?, padecimientos_personales = ?, antecedentes_hereditarios = ? WHERE id_expediente = ?";
            pst = getConexion().prepareStatement(consulta);
            pst.setString(1, tipoSangre);
            pst.setString(2, estatura);
            pst.setFloat(3, peso);
            pst.setString(4, alergias);
            pst.setString(5, frecuenciaCardiaca);
            pst.setString(6, padecimientosPersonales);
            pst.setString(7, antecedentesHereditarios);
            pst.setInt(8, idExpediente);

            return pst.executeUpdate() == 1;
        } catch (SQLException e) {
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

}
