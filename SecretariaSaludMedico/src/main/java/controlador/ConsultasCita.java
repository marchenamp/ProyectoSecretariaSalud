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
import modelo.Expediente;
import modelo.Medico;
import modelo.Permiso;

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

    public Cita obtenerCitaPorId(int idCita) {
        Cita cita = null;
        PreparedStatement pst = null;
        ConsultasMedico sqlMedico = new ConsultasMedico();
        ConsultasPaciente sqlPaciente = new ConsultasPaciente();

        try {
            String consulta = "SELECT * FROM citas WHERE id_cita = ?";
            pst = getConexion().prepareStatement(consulta);
            pst.setInt(1, idCita);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                cita = new Cita();
                cita.setId(rs.getInt("id_cita"));
                cita.setMotivo(rs.getString("motivo"));
                cita.setFechaCita(rs.getDate("fecha"));
                cita.setHora(rs.getString("hora"));

                int idMedicoCita = rs.getInt("id_medico");
                int idPacienteCita = rs.getInt("id_paciente");

                cita.setMedico(sqlMedico.buscarMedico(idMedicoCita));
                cita.setPaciente(sqlPaciente.buscarPaciente(idPacienteCita));
            }
        } catch (SQLException e) {
            System.err.println("Error en: " + e);
        } finally {
            // Aquí deberías cerrar los recursos como en los otros métodos
        }
        return cita;
    }

    public Cita obtenerUltimaCitaPorPaciente(int idPaciente) {
        Cita ultimaCita = null;
        PreparedStatement pst = null;
        ConsultasMedico sqlMedico = new ConsultasMedico();
        ConsultasPaciente sqlPaciente = new ConsultasPaciente();

        try {
            String consulta = "SELECT * FROM citas WHERE id_paciente = ? ORDER BY id_cita DESC LIMIT 1";
            pst = getConexion().prepareStatement(consulta);
            pst.setInt(1, idPaciente);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                ultimaCita = new Cita();
                ultimaCita.setId(rs.getInt("id_cita"));
                ultimaCita.setMotivo(rs.getString("motivo"));
                ultimaCita.setFechaCita(rs.getDate("fecha"));
                ultimaCita.setHora(rs.getString("hora"));

                int idMedicoCita = rs.getInt("id_medico");
                int idPacienteCita = rs.getInt("id_paciente");

                ultimaCita.setMedico(sqlMedico.buscarMedico(idMedicoCita));
                ultimaCita.setPaciente(sqlPaciente.buscarPaciente(idPacienteCita));
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
        return ultimaCita;
    }

    public List<Permiso> obtenerPermisosPorMedico(int idMedico) {
        List<Permiso> permisos = new ArrayList<>();
        PreparedStatement pst = null;
        ConsultasExpediente sqlExpediente = new ConsultasExpediente();
        ConsultasMedico sqlMedico = new ConsultasMedico();

        try {
            String consulta = "SELECT * FROM permisos WHERE id_medico = ?";
            pst = getConexion().prepareStatement(consulta);
            pst.setInt(1, idMedico);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Permiso permiso = new Permiso();
                permiso.setId(rs.getInt("id_permiso"));
                permiso.setExpediente(sqlExpediente.buscarExpediente(rs.getInt("id_expediente")));
                permiso.setMedico(sqlMedico.buscarMedico(rs.getInt("id_medico")));
                permiso.setCita(obtenerCitaPorId(rs.getInt("id_cita")));

                permisos.add(permiso);
            }
        } catch (SQLException e) {
            System.err.println("Error en: " + e);
        } finally {
            // Aquí deberías cerrar los recursos como en el método crearPermiso
        }
        return permisos;
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
                    + "                 <button name=\"EliminarCita\" class=\"eliminar-btn\"> \n"
                    + "                     <img src=\"IMG/eliminar.png\" alt=\"eliminar\" id=\"eliminar-icon\"/>\n"
                    + "                 </button>\n"
                    + "            </form>\n"
                    + "       </td>\n"
                    + "    </tr>\n";

        }
        return htmlcode;
    }

    public String obtenerTablaPermisos(int idMedico) {
        String htmlcode = "";
        for (Permiso permiso : obtenerPermisosPorMedico(idMedico)) {
            Expediente expediente = permiso.getExpediente();

            htmlcode
                    += "    <tr>"
                    + "        <td>" + permiso.getCita().getFechaCita().toString() + "</td>"
                    + "        <td>" + permiso.getCita().getHora() + "</td>"
                    + "        <td>" + expediente.getPaciente().getNombres() + " " + expediente.getPaciente().getApellidoPaterno() + " " + expediente.getPaciente().getApellidoMaterno() + "</td>"
                    + "        <td class=\"celda-btn\">"
                    + "            <form action=\"ServletExpediente\" method=\"post\">\n"
                    + "                 <input type=\"hidden\" name=\"idExpediente\" id=\"idExpedienteInput\" value=\"" + expediente.getId() + "\">\n"
                    + "                 <input type=\"hidden\" name=\"VerExpediente\" value=\"true\">"
                    + "                 <button type=\"button\" id=\"ver-btn\" name=\"VerExpediente\" onclick=\"abrirModal()\">Ver Expediente</button>"
                    + "            </form>\n"
                    + "        </td>"
                    + "    </tr>";

        }
        return htmlcode;
    }

}
