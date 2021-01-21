/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.pliis.afiliacion.persistencia.hibernate.entity;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author DELL
 */
@Entity
@Table(name = "usuario")
@NamedQueries({
    @NamedQuery(name = "UsuarioEntity.findAll", query = "SELECT u FROM UsuarioEntity u"),
    @NamedQuery(name = "UsuarioEntity.findByIdUsuario", query = "SELECT u FROM UsuarioEntity u WHERE u.idUsuario = :idUsuario"),
    @NamedQuery(name = "UsuarioEntity.findByNombres", query = "SELECT u FROM UsuarioEntity u WHERE u.nombres = :nombres"),
    @NamedQuery(name = "UsuarioEntity.findByTelefono", query = "SELECT u FROM UsuarioEntity u WHERE u.telefono = :telefono"),
    @NamedQuery(name = "UsuarioEntity.findByArqIdUsuario", query = "SELECT u FROM UsuarioEntity u WHERE u.arqIdUsuario = :arqIdUsuario")})
@Getter
@Setter
@NoArgsConstructor
public class UsuarioEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_usuario")
    private Integer idUsuario;
    
    @Size(max = 500)
    @Column(name = "nombres")
    private String nombres;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "telefono")
    private String telefono;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "arq_id_usuario")
    private int arqIdUsuario;
	
    @Column(name = "activo")
	private Boolean activo;
	
    @Size(max = 25)
    @Column(name = "id_usuario_openpay", length = 25, nullable = true)
    private String idUsuarioOpenpay;
	
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "idUsuario", fetch = FetchType.LAZY)
    private AfiliadoEntity afiliadoEntity;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "idUsuario", fetch = FetchType.LAZY)
    private FamiliarEntity familiarEntity;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "idUsuario", fetch = FetchType.LAZY)
    private MiembroEntity miembroEntity;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuarioEntity", fetch = FetchType.LAZY)
    private Collection<UsuarioRedSocialEntity> usuarioRedSocialEntityCollection;

    /*public UsuarioEntity() {
    }*/
    
    public UsuarioEntity(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public UsuarioEntity(Integer idUsuario, String telefono, int arqIdUsuario) {
        this.idUsuario = idUsuario;
        this.telefono = telefono;
        this.arqIdUsuario = arqIdUsuario;
    }

    /*public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getArqIdUsuario() {
        return arqIdUsuario;
    }

    public void setArqIdUsuario(int arqIdUsuario) {
        this.arqIdUsuario = arqIdUsuario;
    }

    public AfiliadoEntity getAfiliadoEntity() {
        return afiliadoEntity;
    }

    public void setAfiliadoEntity(AfiliadoEntity afiliadoEntity) {
        this.afiliadoEntity = afiliadoEntity;
    }

    public FamiliarEntity getFamiliarEntity() {
        return familiarEntity;
    }

    public void setFamiliarEntity(FamiliarEntity familiarEntity) {
        this.familiarEntity = familiarEntity;
    }

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public Collection<UsuarioRedSocialEntity> getUsuarioRedSocialEntityCollection() {
        return usuarioRedSocialEntityCollection;
    }

    public void setUsuarioRedSocialEntityCollection(Collection<UsuarioRedSocialEntity> usuarioRedSocialEntityCollection) {
        this.usuarioRedSocialEntityCollection = usuarioRedSocialEntityCollection;
    }*/

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUsuario != null ? idUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsuarioEntity)) {
            return false;
        }
        UsuarioEntity other = (UsuarioEntity) object;
        if ((this.idUsuario == null && other.idUsuario != null) || (this.idUsuario != null && !this.idUsuario.equals(other.idUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.pliis.afiliacion.persistencia.hibernate.entities.UsuarioEntity[ idUsuario=" + idUsuario + " ]";
    }
    
}
