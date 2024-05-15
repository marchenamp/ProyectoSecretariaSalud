/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.Genero;

/**
 *
 * @author march
 */
public class ConsultasMedico extends Conexion {

    public ConsultasMedico() {
    }

    public boolean registrarMedico(String nombres, String apellidoPaterno, String apellidoMaterno, String correo, String password, Date fechaNacimiento, String telefono, Genero genero, String cedulaProfesional, String especialidadMedica, String lugarTrabajoActual) {
        PreparedStatement pst = null;
        try {
            String consulta = "INSERT INTO medicos (nombres, apellidopaterno, apellidomaterno, correo, password, fecha_nacimiento, telefono, genero, cedula_profesional, especialidad_medica, lugar_trabajo_actual) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            pst = getConexion().prepareStatement(consulta);
            pst.setString(1, nombres);
            pst.setString(2, apellidoPaterno);
            pst.setString(3, apellidoMaterno);
            pst.setString(4, correo);
            pst.setString(5, password);
            pst.setDate(6, fechaNacimiento);
            pst.setString(7, telefono);
            pst.setString(8, genero.name());
            pst.setString(9, cedulaProfesional);
            pst.setString(10, especialidadMedica);
            pst.setString(11, lugarTrabajoActual);

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

    public boolean autenticacion(String correo, String password) {
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            String consulta = "SELECT * FROM medicos WHERE correo = ? AND password = ?";
            pst = getConexion().prepareStatement(consulta);
            pst.setString(1, correo);
            pst.setString(2, password);
            rs = pst.executeQuery();

            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            System.err.println("Error en: " + e);
        } finally {
            try {
                if (getConexion() != null) {
//                    getConexion().close();
                }
                if (pst != null) {
                    pst.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {
                System.err.println("Error en: " + e);
            }
        }
        return false;
    }

    public boolean correoExistente(String correo) {
        try {
            String consulta = "SELECT correo FROM medicos WHERE correo = ?";
            PreparedStatement pst = getConexion().prepareStatement(consulta);
            pst.setString(1, correo);

            ResultSet rs = pst.executeQuery();

            return rs.next();

        } catch (SQLException e) {
            System.err.println("Error al verificar el correo existente: " + e);
        }
        return false;
    }
}
