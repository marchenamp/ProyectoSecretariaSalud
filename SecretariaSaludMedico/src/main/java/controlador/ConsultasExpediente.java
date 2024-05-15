/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.Expediente;
import modelo.Genero;
import modelo.Medico;

/**
 *
 * @author march
 */
public class ConsultasExpediente extends Conexion{

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
    
    public Expediente buscarExpediente(int idExpediente) {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        Expediente expedienteEncontrado = null;
        ConsultasPaciente sqlPaciente = new ConsultasPaciente();

        try {
            con = getConexion();
            String consulta = "SELECT * FROM expedientes WHERE id_expediente = ?";
            pst = con.prepareStatement(consulta);
            pst.setInt(1, idExpediente);

            rs = pst.executeQuery();

            if (rs.next()) {
                expedienteEncontrado = new Expediente();
                expedienteEncontrado.setId(rs.getInt("id_expediente"));
                expedienteEncontrado.setTipoSangre(rs.getString("tipo_sangre"));
                expedienteEncontrado.setEstatura(rs.getString("estatura"));
                expedienteEncontrado.setPeso(rs.getFloat("peso"));
                expedienteEncontrado.setAlergias(rs.getString("alergias"));
                expedienteEncontrado.setFrecuenciaCardiaca(rs.getString("frecuencia_cardiaca"));
                expedienteEncontrado.setPadecimientoPersonales(rs.getString("padecimientos_personales"));
                expedienteEncontrado.setAntecedentesHereditarios(rs.getString("antecedentes_hereditarios"));
                expedienteEncontrado.setNombreContactoEmergencia(rs.getString("nombre_contacto_emergencia"));
                expedienteEncontrado.setTelefonoContactoEmergencia(rs.getString("telefono_contacto_emergencia"));
                expedienteEncontrado.setPaciente(sqlPaciente.buscarPaciente(rs.getInt("id_paciente")));
            }
        } catch (SQLException e) {
            System.err.println("Error en: " + e);
        } finally {
//            try {
//                if (rs != null) {
//                    rs.close();
//                }
//                if (pst != null) {
//                    pst.close();
//                }
//                if (con != null) {
//                    con.close();
//                }
//            } catch (SQLException e) {
//                System.err.println("Error en: " + e);
//            }
        }
        return expedienteEncontrado;
    }
}
