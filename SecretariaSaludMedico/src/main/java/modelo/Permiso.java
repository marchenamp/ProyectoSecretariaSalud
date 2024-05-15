/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author march
 */
public class Permiso {
    
    private int id;
    private Expediente expediente;
    private Medico medico;
    private Cita cita;

    public Permiso() {
    }

    public Permiso(int id, Expediente expediente, Medico medico, Cita cita) {
        this.id = id;
        this.expediente = expediente;
        this.medico = medico;
        this.cita = cita;
    }

    public Permiso(Expediente expediente, Medico medico, Cita cita) {
        this.expediente = expediente;
        this.medico = medico;
        this.cita = cita;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Expediente getExpediente() {
        return expediente;
    }

    public void setExpediente(Expediente expediente) {
        this.expediente = expediente;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Cita getCita() {
        return cita;
    }

    public void setCita(Cita cita) {
        this.cita = cita;
    }
    
}
