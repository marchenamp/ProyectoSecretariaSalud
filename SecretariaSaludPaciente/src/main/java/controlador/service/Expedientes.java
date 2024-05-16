package controlador.service;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author chaly
 */
@Entity
@Table(name = "expedientes")
@NamedQueries({
    @NamedQuery(name = "Expedientes.findAll", query = "SELECT e FROM Expedientes e"),
    @NamedQuery(name = "Expedientes.findByIdExpediente", query = "SELECT e FROM Expedientes e WHERE e.idExpediente = :idExpediente"),
    @NamedQuery(name = "Expedientes.findByTipoSangre", query = "SELECT e FROM Expedientes e WHERE e.tipoSangre = :tipoSangre"),
    @NamedQuery(name = "Expedientes.findByEstatura", query = "SELECT e FROM Expedientes e WHERE e.estatura = :estatura"),
    @NamedQuery(name = "Expedientes.findByPeso", query = "SELECT e FROM Expedientes e WHERE e.peso = :peso"),
    @NamedQuery(name = "Expedientes.findByAlergias", query = "SELECT e FROM Expedientes e WHERE e.alergias = :alergias"),
    @NamedQuery(name = "Expedientes.findByFrecuenciaCardiaca", query = "SELECT e FROM Expedientes e WHERE e.frecuenciaCardiaca = :frecuenciaCardiaca"),
    @NamedQuery(name = "Expedientes.findByPadecimientosPersonales", query = "SELECT e FROM Expedientes e WHERE e.padecimientosPersonales = :padecimientosPersonales"),
    @NamedQuery(name = "Expedientes.findByAntecedentesHereditarios", query = "SELECT e FROM Expedientes e WHERE e.antecedentesHereditarios = :antecedentesHereditarios"),
    @NamedQuery(name = "Expedientes.findByNombreContactoEmergencia", query = "SELECT e FROM Expedientes e WHERE e.nombreContactoEmergencia = :nombreContactoEmergencia"),
    @NamedQuery(name = "Expedientes.findByTelefonoContactoEmergencia", query = "SELECT e FROM Expedientes e WHERE e.telefonoContactoEmergencia = :telefonoContactoEmergencia"),
    @NamedQuery(name = "Expedientes.findByIdPaciente", query = "SELECT e FROM Expedientes e WHERE e.idPaciente = :idPaciente")})
public class Expedientes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_expediente")
    private Integer idExpediente;
    @Basic(optional = false)
    @Column(name = "tipo_sangre")
    private String tipoSangre;
    @Basic(optional = false)
    @Column(name = "estatura")
    private String estatura;
    @Basic(optional = false)
    @Column(name = "peso")
    private float peso;
    @Basic(optional = false)
    @Column(name = "alergias")
    private String alergias;
    @Basic(optional = false)
    @Column(name = "frecuencia_cardiaca")
    private String frecuenciaCardiaca;
    @Basic(optional = false)
    @Column(name = "padecimientos_personales")
    private String padecimientosPersonales;
    @Basic(optional = false)
    @Column(name = "antecedentes_hereditarios")
    private String antecedentesHereditarios;
    @Basic(optional = false)
    @Column(name = "nombre_contacto_emergencia")
    private String nombreContactoEmergencia;
    @Basic(optional = false)
    @Column(name = "telefono_contacto_emergencia")
    private String telefonoContactoEmergencia;
    @Basic(optional = false)
    @Column(name = "id_paciente")
    private int idPaciente;

    public Expedientes() {
    }

    public Expedientes(Integer idExpediente) {
        this.idExpediente = idExpediente;
    }

    public Expedientes(Integer idExpediente, String tipoSangre, String estatura, float peso, String alergias, String frecuenciaCardiaca, String padecimientosPersonales, String antecedentesHereditarios, String nombreContactoEmergencia, String telefonoContactoEmergencia, int idPaciente) {
        this.idExpediente = idExpediente;
        this.tipoSangre = tipoSangre;
        this.estatura = estatura;
        this.peso = peso;
        this.alergias = alergias;
        this.frecuenciaCardiaca = frecuenciaCardiaca;
        this.padecimientosPersonales = padecimientosPersonales;
        this.antecedentesHereditarios = antecedentesHereditarios;
        this.nombreContactoEmergencia = nombreContactoEmergencia;
        this.telefonoContactoEmergencia = telefonoContactoEmergencia;
        this.idPaciente = idPaciente;
    }

    public Integer getIdExpediente() {
        return idExpediente;
    }

    public void setIdExpediente(Integer idExpediente) {
        this.idExpediente = idExpediente;
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

    public String getPadecimientosPersonales() {
        return padecimientosPersonales;
    }

    public void setPadecimientosPersonales(String padecimientosPersonales) {
        this.padecimientosPersonales = padecimientosPersonales;
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

    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idExpediente != null ? idExpediente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Expedientes)) {
            return false;
        }
        Expedientes other = (Expedientes) object;
        if ((this.idExpediente == null && other.idExpediente != null) || (this.idExpediente != null && !this.idExpediente.equals(other.idExpediente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "controlador.Expedientes[ idExpediente=" + idExpediente + " ]";
    }
    
}
