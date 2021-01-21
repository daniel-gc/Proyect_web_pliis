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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author DELL
 */
@Entity
@Table(name = "red_social")
@NamedQueries({
    @NamedQuery(name = "RedSocialEntity.findAll", query = "SELECT r FROM RedSocialEntity r"),
    @NamedQuery(name = "RedSocialEntity.findByIdRedSocial", query = "SELECT r FROM RedSocialEntity r WHERE r.idRedSocial = :idRedSocial"),
    @NamedQuery(name = "RedSocialEntity.findByNombre", query = "SELECT r FROM RedSocialEntity r WHERE r.nombre = :nombre")})
public class RedSocialEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_red_social")
    private Integer idRedSocial;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nombre")
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "redSocialEntity", fetch = FetchType.LAZY)
    private Collection<UsuarioRedSocialEntity> usuarioRedSocialEntityCollection;

    public RedSocialEntity() {
    }

    public RedSocialEntity(Integer idRedSocial) {
        this.idRedSocial = idRedSocial;
    }

    public RedSocialEntity(Integer idRedSocial, String nombre) {
        this.idRedSocial = idRedSocial;
        this.nombre = nombre;
    }

    public Integer getIdRedSocial() {
        return idRedSocial;
    }

    public void setIdRedSocial(Integer idRedSocial) {
        this.idRedSocial = idRedSocial;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Collection<UsuarioRedSocialEntity> getUsuarioRedSocialEntityCollection() {
        return usuarioRedSocialEntityCollection;
    }

    public void setUsuarioRedSocialEntityCollection(Collection<UsuarioRedSocialEntity> usuarioRedSocialEntityCollection) {
        this.usuarioRedSocialEntityCollection = usuarioRedSocialEntityCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRedSocial != null ? idRedSocial.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RedSocialEntity)) {
            return false;
        }
        RedSocialEntity other = (RedSocialEntity) object;
        if ((this.idRedSocial == null && other.idRedSocial != null) || (this.idRedSocial != null && !this.idRedSocial.equals(other.idRedSocial))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.pliis.afiliacion.persistencia.hibernate.entities.RedSocialEntity[ idRedSocial=" + idRedSocial + " ]";
    }
    
}
