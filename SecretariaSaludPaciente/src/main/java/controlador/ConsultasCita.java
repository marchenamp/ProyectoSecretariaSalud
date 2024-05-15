/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Cita;
import modelo.Medico;

/**
 *
 * @author march
 */
public class ConsultasCita extends Conexion {

    public ConsultasCita() {
    }

    public boolean registrarCita(String motivo, Date fechaCita, String horaCita, int idPaciente, String cedulaMedico) {
        PreparedStatement pst = null;
        ConsultasMedico sqlMedico = new ConsultasMedico();
        Medico medico = sqlMedico.buscarMedicoPorCedula(cedulaMedico);

        if (medico != null) {
            try {
                String consulta = "INSERT INTO citas (motivo, fecha, hora, id_paciente, id_medico) VALUES (?, ?, ?, ?, ?)";
                pst = getConexion().prepareStatement(consulta);
                pst.setString(1, motivo);
                pst.setDate(2, fechaCita);
                pst.setString(3, horaCita);
                pst.setInt(4, idPaciente);
                pst.setInt(5, medico.getId());

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
        }
        return false;
    }

    public boolean crearPermiso(int idExpediente, int idMedico, int idCita) {
        PreparedStatement pst = null;

        try {
            String consulta = "INSERT INTO permisos (id_expediente, id_medico, id_cita) VALUES (?, ?, ?)";
            pst = getConexion().prepareStatement(consulta);
            pst.setInt(1, idExpediente);
            pst.setInt(2, idMedico);
            pst.setInt(3, idCita);

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

    public boolean eliminarCita(int idCita) {
        PreparedStatement pst = null;
        try {
            String consulta = "DELETE FROM citas WHERE id_cita = ?";
            pst = getConexion().prepareStatement(consulta);
            pst.setInt(1, idCita);
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
    
    public boolean eliminarPermiso(int idCita) {
        PreparedStatement pst = null;
        try {
            String consulta = "DELETE FROM permisos WHERE id_cita = ?";
            pst = getConexion().prepareStatement(consulta);
            pst.setInt(1, idCita);
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

    public List<Cita> obtenerCitasPorPaciente(int idPaciente) {
        List<Cita> citas = new ArrayList<>();
        PreparedStatement pst = null;
        ConsultasMedico sqlMedico = new ConsultasMedico();
        ConsultasPaciente sqlPaciente = new ConsultasPaciente();

        try {
            String consulta = "SELECT * FROM citas WHERE id_paciente = ?";
            pst = getConexion().prepareStatement(consulta);
            pst.setInt(1, idPaciente);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Cita citaEncontrada = new Cita();
                citaEncontrada.setId(rs.getInt("id_cita"));
                citaEncontrada.setMotivo(rs.getString("motivo"));
                citaEncontrada.setFechaCita(rs.getDate("fecha"));
                citaEncontrada.setHora(rs.getString("hora"));

                int idMedicoCita = rs.getInt("id_medico");
                int idPacienteCita = rs.getInt("id_paciente");

                citaEncontrada.setMedico(sqlMedico.buscarMedico(idMedicoCita));
                citaEncontrada.setPaciente(sqlPaciente.buscarPaciente(idPacienteCita));

                citas.add(citaEncontrada);
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
        return citas;
    }

    public List<Cita> obtenerCitasPorMedico(int idMedico) {
        List<Cita> citas = new ArrayList<>();
        PreparedStatement pst = null;
        ConsultasMedico sqlMedico = new ConsultasMedico();
        ConsultasPaciente sqlPaciente = new ConsultasPaciente();

        try {
            String consulta = "SELECT * FROM citas WHERE id_medico = ?";
            pst = getConexion().prepareStatement(consulta);
            pst.setInt(1, idMedico);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Cita citaEncontrada = new Cita();
                citaEncontrada.setId(rs.getInt("id_cita"));
                citaEncontrada.setMotivo(rs.getString("motivo"));
                citaEncontrada.setFechaCita(rs.getDate("fecha"));
                citaEncontrada.setHora(rs.getString("hora"));

                int idMedicoCita = rs.getInt("id_medico");
                int idPacienteCita = rs.getInt("id_paciente");

                citaEncontrada.setMedico(sqlMedico.buscarMedico(idMedicoCita));
                citaEncontrada.setPaciente(sqlPaciente.buscarPaciente(idPacienteCita));

                citas.add(citaEncontrada);
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
        return citas;
    }

    public String obtenerTablaPacienteCitas(int idPaciente) {
        String htmlcode = "";
        for (Cita cita : obtenerCitasPorPaciente(idPaciente)) {
            htmlcode += "                    <tr>\n"
                    + "                         <td>" + cita.getFechaCita().toString() + "</td>\n"
                    + "                         <td>" + cita.getHora() + "</td>\n"
                    + "                         <td>" + cita.getMedico().getNombres() + " " + cita.getMedico().getApellidoPaterno() + " " + cita.getMedico().getApellidoMaterno() + "</td>\n"
                    + "                         <td>" + cita.getMotivo() + "</td>\n"
                    + "                     </tr>\n";

        }
        return htmlcode;
    }

    public String obtenerTablaMedicoCitas(int idMedico) {
        String htmlcode = "";
        for (Cita cita : obtenerCitasPorMedico(idMedico)) {
            htmlcode
                    += "    <tr>\n"
                    + "        <td>" + cita.getFechaCita().toString() + "</td>\n"
                    + "        <td>" + cita.getHora() + "</td>\n"
                    + "        <td>" + cita.getPaciente().getNombres() + " " + cita.getPaciente().getApellidoPaterno() + " " + cita.getPaciente().getApellidoMaterno() + "</td>\n"
                    + "        <td>" + cita.getMotivo() + "</td>\n"
                    + "        <td>\n"
                    + "            <form id=\"delete\" action=\"ServletCita\" method=\"post\">\n"
                    + "                 <input type=\"hidden\" name=\"idCitaEliminar\" id=\"idCitaEliminarInput\" value=\"" + cita.getId() + "\">\n"
                    + "                 <button class=\"eliminar-btn\"> \n"
                    + "                     <img src=\"IMG/eliminar.png\" alt=\"eliminar\" id=\"eliminar-icon\"/>\n"
                    + "                 </button>\n"
                    + "            </form>\n"
                    + "       </td>\n"
                    + "    </tr>\n";

        }
        return htmlcode;
    }

}
