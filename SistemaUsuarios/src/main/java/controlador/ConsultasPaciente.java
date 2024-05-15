/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneId;
import modelo.EstadoCivil;
import modelo.Genero;
import modelo.Paciente;
import modelo.Tutor;

/**
 *
 * @author march
 */
public class ConsultasPaciente extends Conexion {

    public ConsultasPaciente() {
    }

    private boolean pacienteExistente(int id) {
        try {
            String consulta = "SELECT id_paciente FROM pacientes WHERE id_paciente = ?";
            PreparedStatement pst = getConexion().prepareStatement(consulta);
            pst.setInt(1, id);

            ResultSet rs = pst.executeQuery();

            return rs.next();

        } catch (SQLException e) {
            System.err.println("Error al verificar el id del paciente existente: " + e);
        }
        return false;
    }

    public boolean registrarPaciente(String nombres, String apellidoPaterno, String apellidoMaterno, String correo, String password, Date fechaNacimiento, String telefono, EstadoCivil estadoCivil, Genero genero) {
        PreparedStatement pst = null;
        try {
            String consulta = "INSERT INTO pacientes (nombres, apellidopaterno, apellidomaterno, correo, password, fecha_nacimiento, telefono, estado_civil, genero) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            pst = getConexion().prepareStatement(consulta);
            pst.setString(1, nombres);
            pst.setString(2, apellidoPaterno);
            pst.setString(3, apellidoMaterno);
            pst.setString(4, correo);
            pst.setString(5, password);
            pst.setDate(6, fechaNacimiento);
            pst.setString(7, telefono);
            pst.setString(8, estadoCivil.name());
            pst.setString(9, genero.name());

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

    public boolean registrarPacienteConTutor(String nombres, String apellidoPaterno, String apellidoMaterno, String correo, String password, Date fechaNacimiento, String telefono, EstadoCivil estadoCivil, Genero genero, Tutor tutor) {
        PreparedStatement pst = null;
        try {
            String consulta = "INSERT INTO pacientes (nombres, apellidopaterno, apellidomaterno, correo, password, fecha_nacimiento, telefono, estado_civil, genero, id_tutor) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            pst = getConexion().prepareStatement(consulta);
            pst.setString(1, nombres);
            pst.setString(2, apellidoPaterno);
            pst.setString(3, apellidoMaterno);
            pst.setString(4, correo);
            pst.setString(5, password);
            pst.setDate(6, fechaNacimiento);
            pst.setString(7, telefono);
            pst.setString(8, estadoCivil.name());
            pst.setString(9, genero.name());
            pst.setInt(10, tutor.getId());

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

    public boolean correoExistente(String correo) {
        try {
            String consulta = "SELECT correo FROM pacientes WHERE correo = ?";
            PreparedStatement pst = getConexion().prepareStatement(consulta);
            pst.setString(1, correo);

            ResultSet rs = pst.executeQuery();

            return rs.next();

        } catch (SQLException e) {
            System.err.println("Error al verificar el correo existente: " + e);
        }
        return false;
    }

    public boolean autenticacion(String correo, String password) {
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            String consulta = "SELECT * FROM pacientes WHERE correo = ? AND password = ?";
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
    
    public Paciente obtenerPacientePorCorreo(String correo, String password) {
        PreparedStatement pst = null;
        ResultSet rs = null;
        Paciente pacienteEncontrado = null;
        ConsultasTutor sqlTutor = new ConsultasTutor();
        try {
            String consulta = "SELECT * FROM pacientes WHERE correo = ? AND password = ?";
            pst = getConexion().prepareStatement(consulta);
            pst.setString(1, correo);
            pst.setString(2, password);
            rs = pst.executeQuery();

            if (rs.next()) {
                pacienteEncontrado = new Paciente();
                pacienteEncontrado.setId(rs.getInt("id_paciente"));
                pacienteEncontrado.setNombres(rs.getString("nombres"));
                pacienteEncontrado.setApellidoPaterno(rs.getString("apellidopaterno"));
                pacienteEncontrado.setApellidoMaterno(rs.getString("apellidomaterno"));
                pacienteEncontrado.setCorreo(rs.getString("correo"));
                pacienteEncontrado.setPassword(rs.getString("password"));
                pacienteEncontrado.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
                pacienteEncontrado.setTelefono(rs.getString("telefono"));
                pacienteEncontrado.setEstadoCivil(EstadoCivil.valueOf(rs.getString("estado_civil")));
                pacienteEncontrado.setGenero(Genero.valueOf(rs.getString("genero")));
                pacienteEncontrado.setTutor(sqlTutor.buscarTutor(rs.getInt("id_tutor")));
                
                return pacienteEncontrado;
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
        return pacienteEncontrado;
    }

    public Paciente buscarPaciente(int id) {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        Paciente pacienteEncontrado = null;
        ConsultasTutor sqlTutor = new ConsultasTutor();

        try {
            con = getConexion();
            String consulta = "SELECT * FROM pacientes WHERE id_paciente = ?";
            pst = con.prepareStatement(consulta);
            pst.setInt(1, id);

            rs = pst.executeQuery();

            if (rs.next()) {
                pacienteEncontrado = new Paciente();
                pacienteEncontrado.setId(rs.getInt("id_paciente"));
                pacienteEncontrado.setNombres(rs.getString("nombres"));
                pacienteEncontrado.setApellidoPaterno(rs.getString("apellidopaterno"));
                pacienteEncontrado.setApellidoMaterno(rs.getString("apellidomaterno"));
                pacienteEncontrado.setCorreo(rs.getString("correo"));
                pacienteEncontrado.setPassword(rs.getString("password"));
                pacienteEncontrado.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
                pacienteEncontrado.setTelefono(rs.getString("telefono"));
                pacienteEncontrado.setEstadoCivil(EstadoCivil.valueOf(rs.getString("estado_civil")));
                pacienteEncontrado.setGenero(Genero.valueOf(rs.getString("genero")));
                pacienteEncontrado.setTutor(sqlTutor.buscarTutor(rs.getInt("id_tutor")));
            }
        } catch (SQLException e) {
            System.err.println("Error en: " + e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                System.err.println("Error en: " + e);
            }
        }
        return pacienteEncontrado;
    }

}
