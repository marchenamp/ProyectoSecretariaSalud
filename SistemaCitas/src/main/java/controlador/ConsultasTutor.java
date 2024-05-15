/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.EstadoCivil;
import modelo.Genero;
import modelo.Tutor;

/**
 *
 * @author march
 */
public class ConsultasTutor extends Conexion{

    public ConsultasTutor() {
    }
    
    public boolean registrarTutor(String nombres, String apellidoPaterno, String apellidoMaterno, Date fechaNacimiento, String telefono, Genero genero, String parentesco) {
        PreparedStatement pst = null;
        try {
            String consulta = "INSERT INTO tutores (nombres, apellidopaterno, apellidomaterno, fecha_nacimiento, telefono, genero, parentesco) VALUES (?, ?, ?, ?, ?, ?, ?)";
            pst = getConexion().prepareStatement(consulta);
            pst.setString(1, nombres);
            pst.setString(2, apellidoPaterno);
            pst.setString(3, apellidoMaterno);
            pst.setDate(4, fechaNacimiento);
            pst.setString(5, telefono);
            pst.setString(6, genero.name());
            pst.setString(7, parentesco);

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
    
    public Tutor buscarTutor(int id) {
        PreparedStatement pst = null;
        ResultSet rs = null;
        Tutor tutorEncontrado = null;

        try {
            String consulta = "SELECT * FROM tutores WHERE id_tutor = ?";
            pst = getConexion().prepareStatement(consulta);
            pst.setInt(1, id);
            rs = pst.executeQuery();

            if (rs.next()) {
                tutorEncontrado = new Tutor();
                tutorEncontrado.setId(rs.getInt("id_tutor"));
                tutorEncontrado.setNombres(rs.getString("nombres"));
                tutorEncontrado.setApellidoPaterno(rs.getString("apellidopaterno"));
                tutorEncontrado.setApellidoMaterno(rs.getString("apellidomaterno"));
                tutorEncontrado.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
                tutorEncontrado.setTelefono(rs.getString("telefono"));
                tutorEncontrado.setGenero(Genero.valueOf(rs.getString("genero")));
                tutorEncontrado.setParentesco(rs.getString("parentesco"));

            }
        } catch (Exception e) {
            System.err.println("Error en: " + e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pst != null) {
                    pst.close();
                }
                if (getConexion() != null) {
                    getConexion().close();
                }
            } catch (Exception e) {
                System.err.println("Error en: " + e);
            }
        }
        return tutorEncontrado;
    }
    
    public Tutor buscarUltimoTutorRegistrado() {
        PreparedStatement pst = null;
        ResultSet rs = null;
        Tutor ultimoTutorRegistrado = null;

        try {
            String consulta = "SELECT * FROM tutores ORDER BY id_tutor DESC LIMIT 1";
            pst = getConexion().prepareStatement(consulta);
            rs = pst.executeQuery();

            if (rs.next()) {
                ultimoTutorRegistrado = new Tutor();
                ultimoTutorRegistrado.setId(rs.getInt("id_tutor"));
                ultimoTutorRegistrado.setNombres(rs.getString("nombres"));
                ultimoTutorRegistrado.setApellidoPaterno(rs.getString("apellidopaterno"));
                ultimoTutorRegistrado.setApellidoMaterno(rs.getString("apellidomaterno"));
                ultimoTutorRegistrado.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
                ultimoTutorRegistrado.setTelefono(rs.getString("telefono"));
                ultimoTutorRegistrado.setGenero(Genero.valueOf(rs.getString("genero")));
                ultimoTutorRegistrado.setParentesco(rs.getString("parentesco"));

            }
        } catch (Exception e) {
            System.err.println("Error en: " + e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pst != null) {
                    pst.close();
                }
                if (getConexion() != null) {
                    getConexion().close();
                }
            } catch (Exception e) {
                System.err.println("Error en: " + e);
            }
        }
        return ultimoTutorRegistrado;
    }
}
