/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author march
 */
public class Archivo {
    
    private int id;
    private int idExpediente;
    private String nombre;
    private String tipo;
    private byte[] contenido; 

    public Archivo(int id, int idExpediente, String nombre, String tipo, byte[] contenido) {
        this.id = id;
        this.idExpediente = idExpediente;
        this.nombre = nombre;
        this.tipo = tipo;
        this.contenido = contenido;
    }

    public Archivo() {
    }

    public int getIdExpediente() {
        return idExpediente;
    }

    public void setIdExpediente(int idExpediente) {
        this.idExpediente = idExpediente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public byte[] getContenido() {
        return contenido;
    }

    public void setContenido(byte[] contenido) {
        this.contenido = contenido;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
}
