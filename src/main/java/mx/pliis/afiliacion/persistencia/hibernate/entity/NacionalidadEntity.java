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
@Table(name = "nacionalidad")
@NamedQueries({
    @NamedQuery(name = "NacionalidadEntity.findAll", query = "SELECT n FROM NacionalidadEntity n"),
    @NamedQuery(name = "NacionalidadEntity.findByIdNacionalidad", query = "SELECT n FROM NacionalidadEntity n WHERE n.idNacionalidad = :idNacionalidad"),
    @NamedQuery(name = "NacionalidadEntity.findByNombre", query = "SELECT n FROM NacionalidadEntity n WHERE n.nombre = :nombre")})
public class NacionalidadEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_nacionalidad")
    private Integer idNacionalidad;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nombre")
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idNacionalidad", fetch = FetchType.LAZY)
    private Collection<AfiliadoEntity> afiliadoEntityCollection;

    public NacionalidadEntity() {
    }

    public NacionalidadEntity(Integer idNacionalidad) {
        this.idNacionalidad = idNacionalidad;
    }

    public NacionalidadEntity(Integer idNacionalidad, String nombre) {
        this.idNacionalidad = idNacionalidad;
        this.nombre = nombre;
    }

    public Integer getIdNacionalidad() {
        return idNacionalidad;
    }

    public void setIdNacionalidad(Integer idNacionalidad) {
        this.idNacionalidad = idNacionalidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Collection<AfiliadoEntity> getAfiliadoEntityCollection() {
        return afiliadoEntityCollection;
    }

    public void setAfiliadoEntityCollection(Collection<AfiliadoEntity> afiliadoEntityCollection) {
        this.afiliadoEntityCollection = afiliadoEntityCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idNacionalidad != null ? idNacionalidad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NacionalidadEntity)) {
            return false;
        }
        NacionalidadEntity other = (NacionalidadEntity) object;
        if ((this.idNacionalidad == null && other.idNacionalidad != null) || (this.idNacionalidad != null && !this.idNacionalidad.equals(other.idNacionalidad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.pliis.afiliacion.persistencia.hibernate.entities.NacionalidadEntity[ idNacionalidad=" + idNacionalidad + " ]";
    }
    
}
