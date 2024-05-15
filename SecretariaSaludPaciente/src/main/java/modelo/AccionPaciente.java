/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author march
 */
public class AccionPaciente {

    private String accion;
    private Paciente paciente;
    private Tutor tutor;

    public AccionPaciente(String accion, Paciente paciente) {
        this.accion = accion;
        this.paciente = paciente;
    }

    public AccionPaciente(String accion, Paciente paciente, Tutor tutor) {
        this.accion = accion;
        this.paciente = paciente;
        this.tutor = tutor;
    }
    
    public AccionPaciente() {
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Tutor getTutor() {
        return tutor;
    }

    public void setTutor(Tutor tutor) {
        this.tutor = tutor;
    }

}
