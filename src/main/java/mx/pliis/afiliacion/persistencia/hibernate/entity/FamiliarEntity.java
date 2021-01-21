/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.pliis.afiliacion.persistencia.hibernate.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

/**
 *
 * @author DELL
 */
@Entity
@Table(name = "familiar")
@NamedQueries({
    @NamedQuery(name = "FamiliarEntity.findAll", query = "SELECT f FROM FamiliarEntity f"),
    @NamedQuery(name = "FamiliarEntity.findByIdFamiliar", query = "SELECT f FROM FamiliarEntity f WHERE f.idFamiliar = :idFamiliar"),
    @NamedQuery(name = "FamiliarEntity.findByNombres", query = "SELECT f FROM FamiliarEntity f WHERE f.nombres = :nombres"),
    @NamedQuery(name = "FamiliarEntity.findByApellidoPaterno", query = "SELECT f FROM FamiliarEntity f WHERE f.apellidoPaterno = :apellidoPaterno"),
    @NamedQuery(name = "FamiliarEntity.findByApellidoMaterno", query = "SELECT f FROM FamiliarEntity f WHERE f.apellidoMaterno = :apellidoMaterno"),
    @NamedQuery(name = "FamiliarEntity.findByEmail", query = "SELECT f FROM FamiliarEntity f WHERE f.email = :email"),
    @NamedQuery(name = "FamiliarEntity.findByFechaNacimiento", query = "SELECT f FROM FamiliarEntity f WHERE f.fechaNacimiento = :fechaNacimiento")})
public class FamiliarEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_familiar")
    private Integer idFamiliar;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "nombres")
    private String nombres;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "apellido_paterno")
    private String apellidoPaterno;
    @Basic(optional = false)
    @Size(min = 1, max = 100)
    @Column(name = "apellido_materno")
    private String apellidoMaterno;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 100)
    @Column(name = "email")
    private String email;
    @Column(name = "fecha_nacimiento")
	private LocalDate fechaNacimiento;
    @JoinColumn(name = "id_sexo", referencedColumnName = "id_sexo")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private SexoEntity idSexo;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    private UsuarioEntity idUsuario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idFamiliar", fetch = FetchType.LAZY)
    private Collection<VinculoFamiliarEntity> vinculoFamiliarEntityCollection;

    public FamiliarEntity() {
    }

    public FamiliarEntity(Integer idFamiliar) {
        this.idFamiliar = idFamiliar;
    }

    public FamiliarEntity(Integer idFamiliar, String nombres, String apellidoPaterno, String apellidoMaterno) {
        this.idFamiliar = idFamiliar;
        this.nombres = nombres;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
    }

    public Integer getIdFamiliar() {
        return idFamiliar;
    }

    public void setIdFamiliar(Integer idFamiliar) {
        this.idFamiliar = idFamiliar;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

	public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public SexoEntity getIdSexo() {
        return idSexo;
    }

    public void setIdSexo(SexoEntity idSexo) {
        this.idSexo = idSexo;
    }

    public UsuarioEntity getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(UsuarioEntity idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Collection<VinculoFamiliarEntity> getVinculoFamiliarEntityCollection() {
        return vinculoFamiliarEntityCollection;
    }

    public void setVinculoFamiliarEntityCollection(Collection<VinculoFamiliarEntity> vinculoFamiliarEntityCollection) {
        this.vinculoFamiliarEntityCollection = vinculoFamiliarEntityCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFamiliar != null ? idFamiliar.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FamiliarEntity)) {
            return false;
        }
        FamiliarEntity other = (FamiliarEntity) object;
        if ((this.idFamiliar == null && other.idFamiliar != null) || (this.idFamiliar != null && !this.idFamiliar.equals(other.idFamiliar))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.pliis.afiliacion.persistencia.hibernate.entities.FamiliarEntity[ idFamiliar=" + idFamiliar + " ]";
    }
    
}
