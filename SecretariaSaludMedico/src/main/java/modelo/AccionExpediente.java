/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author march
 */
public class AccionExpediente {

    private String accion;
    private Expediente expediente;
    private int idPaciente;

    public AccionExpediente(String accion, Expediente expediente, int idPaciente) {
        this.accion = accion;
        this.expediente = expediente;
        this.idPaciente = idPaciente;
    }

    public AccionExpediente(String accion, Expediente expediente) {
        this.accion = accion;
        this.expediente = expediente;
    }
    
    public AccionExpediente() {
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public Expediente getExpediente() {
        return expediente;
    }

    public void setExpediente(Expediente expediente) {
        this.expediente = expediente;
    }

    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }

}
