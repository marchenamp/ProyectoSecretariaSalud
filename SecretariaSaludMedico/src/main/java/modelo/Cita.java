/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.Date;

/**
 *
 * @author march
 */
public class Cita {

    private int id;
    private String motivo;
    private Date fechaCita;
    private String hora;
    private Paciente paciente;
    private Medico medico;

    public Cita() {
    }

    public Cita(String motivo, Date fechaCita, String hora, Paciente paciente, Medico medico) {
        this.motivo = motivo;
        this.fechaCita = fechaCita;
        this.hora = hora;
        this.paciente = paciente;
        this.medico = medico;
    }

    public Cita(int id, String motivo, Date fechaCita, String hora, Paciente paciente, Medico medico) {
        this.id = id;
        this.motivo = motivo;
        this.fechaCita = fechaCita;
        this.hora = hora;
        this.paciente = paciente;
        this.medico = medico;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Date getFechaCita() {
        return fechaCita;
    }

    public void setFechaCita(Date fechaCita) {
        this.fechaCita = fechaCita;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

}
