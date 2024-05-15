/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import java.util.List;
import java.util.ArrayList;
/**
 *
 * @author march
 */
public class Expediente {
    private int id;
    private String tipoSangre;
    private String estatura;
    private float peso;
    private String alergias;
    private String frecuenciaCardiaca;
    private String padecimientoPersonales;
    private String antecedentesHereditarios;
    private String nombreContactoEmergencia;
    private String telefonoContactoEmergencia;
    private Paciente paciente;
    

    public Expediente(int id, String tipoSangre, String estatura, float peso, String alergias, String frecuenciaCardiaca, String padecimientoPersonales, String antecedentesHereditarios, String nombreContactoEmergencia, String telefonoContactoEmergencia, Paciente paciente) {
        this.id = id;
        this.tipoSangre = tipoSangre;
        this.estatura = estatura;
        this.peso = peso;
        this.alergias = alergias;
        this.frecuenciaCardiaca = frecuenciaCardiaca;
        this.padecimientoPersonales = padecimientoPersonales;
        this.antecedentesHereditarios = antecedentesHereditarios;
        this.nombreContactoEmergencia = nombreContactoEmergencia;
        this.telefonoContactoEmergencia = telefonoContactoEmergencia;
        this.paciente = paciente;
        
    }

    public Expediente(String tipoSangre, String estatura, float peso, String alergias, String frecuenciaCardiaca, String padecimientoPersonales, String antecedentesHereditarios, String nombreContactoEmergencia, String telefonoContactoEmergencia, Paciente paciente) {
        this.tipoSangre = tipoSangre;
        this.estatura = estatura;
        this.peso = peso;
        this.alergias = alergias;
        this.frecuenciaCardiaca = frecuenciaCardiaca;
        this.padecimientoPersonales = padecimientoPersonales;
        this.antecedentesHereditarios = antecedentesHereditarios;
        this.nombreContactoEmergencia = nombreContactoEmergencia;
        this.telefonoContactoEmergencia = telefonoContactoEmergencia;
        this.paciente = paciente;
        
    }
    
    public Expediente(String tipoSangre, String estatura, float peso, String alergias, String frecuenciaCardiaca, String padecimientoPersonales, String antecedentesHereditarios, String nombreContactoEmergencia, String telefonoContactoEmergencia) {
        this.tipoSangre = tipoSangre;
        this.estatura = estatura;
        this.peso = peso;
        this.alergias = alergias;
        this.frecuenciaCardiaca = frecuenciaCardiaca;
        this.padecimientoPersonales = padecimientoPersonales;
        this.antecedentesHereditarios = antecedentesHereditarios;
        this.nombreContactoEmergencia = nombreContactoEmergencia;
        this.telefonoContactoEmergencia = telefonoContactoEmergencia;
    }

    public Expediente() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipoSangre() {
        return tipoSangre;
    }

    public void setTipoSangre(String tipoSangre) {
        this.tipoSangre = tipoSangre;
    }

    public String getEstatura() {
        return estatura;
    }

    public void setEstatura(String estatura) {
        this.estatura = estatura;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public String getAlergias() {
        return alergias;
    }

    public void setAlergias(String alergias) {
        this.alergias = alergias;
    }

    public String getFrecuenciaCardiaca() {
        return frecuenciaCardiaca;
    }

    public void setFrecuenciaCardiaca(String frecuenciaCardiaca) {
        this.frecuenciaCardiaca = frecuenciaCardiaca;
    }

    public String getPadecimientoPersonales() {
        return padecimientoPersonales;
    }

    public void setPadecimientoPersonales(String padecimientoPersonales) {
        this.padecimientoPersonales = padecimientoPersonales;
    }

    public String getAntecedentesHereditarios() {
        return antecedentesHereditarios;
    }

    public void setAntecedentesHereditarios(String antecedentesHereditarios) {
        this.antecedentesHereditarios = antecedentesHereditarios;
    }

    public String getNombreContactoEmergencia() {
        return nombreContactoEmergencia;
    }

    public void setNombreContactoEmergencia(String nombreContactoEmergencia) {
        this.nombreContactoEmergencia = nombreContactoEmergencia;
    }

    public String getTelefonoContactoEmergencia() {
        return telefonoContactoEmergencia;
    }

    public void setTelefonoContactoEmergencia(String telefonoContactoEmergencia) {
        this.telefonoContactoEmergencia = telefonoContactoEmergencia;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
    
}
