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
@Table(name = "permisos")
@NamedQueries({
    @NamedQuery(name = "Permisos.findAll", query = "SELECT p FROM Permisos p"),
    @NamedQuery(name = "Permisos.findByIdPermiso", query = "SELECT p FROM Permisos p WHERE p.idPermiso = :idPermiso"),
    @NamedQuery(name = "Permisos.findByIdExpediente", query = "SELECT p FROM Permisos p WHERE p.idExpediente = :idExpediente"),
    @NamedQuery(name = "Permisos.findByIdMedico", query = "SELECT p FROM Permisos p WHERE p.idMedico = :idMedico"),
    @NamedQuery(name = "Permisos.findByIdCita", query = "SELECT p FROM Permisos p WHERE p.idCita = :idCita")})
public class Permisos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_permiso")
    private Integer idPermiso;
    @Basic(optional = false)
    @Column(name = "id_expediente")
    private int idExpediente;
    @Basic(optional = false)
    @Column(name = "id_medico")
    private int idMedico;
    @Basic(optional = false)
    @Column(name = "id_cita")
    private int idCita;

    public Permisos() {
    }

    public Permisos(Integer idPermiso) {
        this.idPermiso = idPermiso;
    }

    public Permisos(Integer idPermiso, int idExpediente, int idMedico, int idCita) {
        this.idPermiso = idPermiso;
        this.idExpediente = idExpediente;
        this.idMedico = idMedico;
        this.idCita = idCita;
    }

    public Integer getIdPermiso() {
        return idPermiso;
    }

    public void setIdPermiso(Integer idPermiso) {
        this.idPermiso = idPermiso;
    }

    public int getIdExpediente() {
        return idExpediente;
    }

    public void setIdExpediente(int idExpediente) {
        this.idExpediente = idExpediente;
    }

    public int getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(int idMedico) {
        this.idMedico = idMedico;
    }

    public int getIdCita() {
        return idCita;
    }

    public void setIdCita(int idCita) {
        this.idCita = idCita;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPermiso != null ? idPermiso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Permisos)) {
            return false;
        }
        Permisos other = (Permisos) object;
        if ((this.idPermiso == null && other.idPermiso != null) || (this.idPermiso != null && !this.idPermiso.equals(other.idPermiso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "controlador.Permisos[ idPermiso=" + idPermiso + " ]";
    }
    
}
