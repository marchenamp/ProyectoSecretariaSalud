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
@Table(name = "medicos")
@NamedQueries({
    @NamedQuery(name = "Medicos.findAll", query = "SELECT m FROM Medicos m"),
    @NamedQuery(name = "Medicos.findByIdMedico", query = "SELECT m FROM Medicos m WHERE m.idMedico = :idMedico"),
    @NamedQuery(name = "Medicos.findByNombres", query = "SELECT m FROM Medicos m WHERE m.nombres = :nombres"),
    @NamedQuery(name = "Medicos.findByApellidopaterno", query = "SELECT m FROM Medicos m WHERE m.apellidopaterno = :apellidopaterno"),
    @NamedQuery(name = "Medicos.findByApellidomaterno", query = "SELECT m FROM Medicos m WHERE m.apellidomaterno = :apellidomaterno"),
    @NamedQuery(name = "Medicos.findByCorreo", query = "SELECT m FROM Medicos m WHERE m.correo = :correo"),
    @NamedQuery(name = "Medicos.findByPassword", query = "SELECT m FROM Medicos m WHERE m.password = :password"),
    @NamedQuery(name = "Medicos.findByFechaNacimiento", query = "SELECT m FROM Medicos m WHERE m.fechaNacimiento = :fechaNacimiento"),
    @NamedQuery(name = "Medicos.findByTelefono", query = "SELECT m FROM Medicos m WHERE m.telefono = :telefono"),
    @NamedQuery(name = "Medicos.findByGenero", query = "SELECT m FROM Medicos m WHERE m.genero = :genero"),
    @NamedQuery(name = "Medicos.findByCedulaProfesional", query = "SELECT m FROM Medicos m WHERE m.cedulaProfesional = :cedulaProfesional"),
    @NamedQuery(name = "Medicos.findByEspecialidadMedica", query = "SELECT m FROM Medicos m WHERE m.especialidadMedica = :especialidadMedica"),
    @NamedQuery(name = "Medicos.findByLugarTrabajoActual", query = "SELECT m FROM Medicos m WHERE m.lugarTrabajoActual = :lugarTrabajoActual")})
public class Medicos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_medico")
    private Integer idMedico;
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
    @Column(name = "correo")
    private String correo;
    @Basic(optional = false)
    @Column(name = "password")
    private String password;
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
    @Column(name = "cedula_profesional")
    private String cedulaProfesional;
    @Basic(optional = false)
    @Column(name = "especialidad_medica")
    private String especialidadMedica;
    @Basic(optional = false)
    @Column(name = "lugar_trabajo_actual")
    private String lugarTrabajoActual;

    public Medicos() {
    }

    public Medicos(Integer idMedico) {
        this.idMedico = idMedico;
    }

    public Medicos(Integer idMedico, String nombres, String apellidopaterno, String apellidomaterno, String correo, String password, Date fechaNacimiento, String telefono, String genero, String cedulaProfesional, String especialidadMedica, String lugarTrabajoActual) {
        this.idMedico = idMedico;
        this.nombres = nombres;
        this.apellidopaterno = apellidopaterno;
        this.apellidomaterno = apellidomaterno;
        this.correo = correo;
        this.password = password;
        this.fechaNacimiento = fechaNacimiento;
        this.telefono = telefono;
        this.genero = genero;
        this.cedulaProfesional = cedulaProfesional;
        this.especialidadMedica = especialidadMedica;
        this.lugarTrabajoActual = lugarTrabajoActual;
    }

    public Integer getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(Integer idMedico) {
        this.idMedico = idMedico;
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

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getCedulaProfesional() {
        return cedulaProfesional;
    }

    public void setCedulaProfesional(String cedulaProfesional) {
        this.cedulaProfesional = cedulaProfesional;
    }

    public String getEspecialidadMedica() {
        return especialidadMedica;
    }

    public void setEspecialidadMedica(String especialidadMedica) {
        this.especialidadMedica = especialidadMedica;
    }

    public String getLugarTrabajoActual() {
        return lugarTrabajoActual;
    }

    public void setLugarTrabajoActual(String lugarTrabajoActual) {
        this.lugarTrabajoActual = lugarTrabajoActual;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMedico != null ? idMedico.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Medicos)) {
            return false;
        }
        Medicos other = (Medicos) object;
        if ((this.idMedico == null && other.idMedico != null) || (this.idMedico != null && !this.idMedico.equals(other.idMedico))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "controlador.Medicos[ idMedico=" + idMedico + " ]";
    }
    
}
