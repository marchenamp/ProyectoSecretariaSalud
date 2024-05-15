/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

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

    public Archivo(int id, int idExpediente, String nombre, String tipo, InputStream contenidoStream) throws IOException {
        this.id = id;
        this.idExpediente = idExpediente;
        this.nombre = nombre;
        this.tipo = tipo;
        this.contenido = convertirInputStreamABytes(contenidoStream);
    }

    public Archivo(int idExpediente, String nombre, String tipo, InputStream contenidoStream) throws IOException {
        this.idExpediente = idExpediente;
        this.nombre = nombre;
        this.tipo = tipo;
        this.contenido = convertirInputStreamABytes(contenidoStream);
    }

    public Archivo() {
    }

    private byte[] convertirInputStreamABytes(InputStream inputStream) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[4096];
        int bytesRead;

        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }

        return outputStream.toByteArray();
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
