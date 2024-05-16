package controlador.service;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author chaly
 */
@Entity
@Table(name = "tutores")
@NamedQueries({
    @NamedQuery(name = "Tutores.findAll", query = "SELECT t FROM Tutores t"),
    @NamedQuery(name = "Tutores.findByIdTutor", query = "SELECT t FROM Tutores t WHERE t.idTutor = :idTutor"),
    @NamedQuery(name = "Tutores.findByNombres", query = "SELECT t FROM Tutores t WHERE t.nombres = :nombres"),
    @NamedQuery(name = "Tutores.findByApellidopaterno", query = "SELECT t FROM Tutores t WHERE t.apellidopaterno = :apellidopaterno"),
    @NamedQuery(name = "Tutores.findByApellidomaterno", query = "SELECT t FROM Tutores t WHERE t.apellidomaterno = :apellidomaterno"),
    @NamedQuery(name = "Tutores.findByFechaNacimiento", query = "SELECT t FROM Tutores t WHERE t.fechaNacimiento = :fechaNacimiento"),
    @NamedQuery(name = "Tutores.findByTelefono", query = "SELECT t FROM Tutores t WHERE t.telefono = :telefono"),
    @NamedQuery(name = "Tutores.findByGenero", query = "SELECT t FROM Tutores t WHERE t.genero = :genero"),
    @NamedQuery(name = "Tutores.findByParentesco", query = "SELECT t FROM Tutores t WHERE t.parentesco = :parentesco")})
public class Tutores implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tutor")
    private Integer idTutor;
    @Basic(optional = false)
    @Column(name = "nombres")
    private String nombres;
    @Basic(optional = false)
    @Column(name = "apellidopaterno")
    private String apellidopaterno;
    @Basic(optional = false)
    @Column(name = "apellidomaterno")
    private String apellidomaterno;
    @Basic(optional = false)
    @Column(name = "fecha_nacimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;
    @Basic(optional = false)
    @Column(name = "telefono")
    private String telefono;
    @Basic(optional = false)
    @Column(name = "genero")
    private String genero;
    @Basic(optional = false)
    @Column(name = "parentesco")
    private String parentesco;

    public Tutores() {
    }

    public Tutores(Integer idTutor) {
        this.idTutor = idTutor;
    }

    public Tutores(Integer idTutor, String nombres, String apellidopaterno, String apellidomaterno, Date fechaNacimiento, String telefono, String genero, String parentesco) {
        this.idTutor = idTutor;
        this.nombres = nombres;
        this.apellidopaterno = apellidopaterno;
        this.apellidomaterno = apellidomaterno;
        this.fechaNacimiento = fechaNacimiento;
        this.telefono = telefono;
        this.genero = genero;
        this.parentesco = parentesco;
    }

    public Integer getIdTutor() {
        return idTutor;
    }

    public void setIdTutor(Integer idTutor) {
        this.idTutor = idTutor;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidopaterno() {
        return apellidopaterno;
    }

    public void setApellidopaterno(String apellidopaterno) {
        this.apellidopaterno = apellidopaterno;
    }

    public String getApellidomaterno() {
        return apellidomaterno;
    }

    public void setApellidomaterno(String apellidomaterno) {
        this.apellidomaterno = apellidomaterno;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getParentesco() {
        return parentesco;
    }

    public void setParentesco(String parentesco) {
        this.parentesco = parentesco;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTutor != null ? idTutor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tutores)) {
            return false;
        }
        Tutores other = (Tutores) object;
        if ((this.idTutor == null && other.idTutor != null) || (this.idTutor != null && !this.idTutor.equals(other.idTutor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "controlador.Tutores[ idTutor=" + idTutor + " ]";
    }
    
}
