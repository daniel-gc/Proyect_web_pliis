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
@Table(name = "estado_civil")
@NamedQueries({
    @NamedQuery(name = "EstadoCivilEntity.findAll", query = "SELECT e FROM EstadoCivilEntity e"),
    @NamedQuery(name = "EstadoCivilEntity.findByIdEstadoCivil", query = "SELECT e FROM EstadoCivilEntity e WHERE e.idEstadoCivil = :idEstadoCivil"),
    @NamedQuery(name = "EstadoCivilEntity.findByNombre", query = "SELECT e FROM EstadoCivilEntity e WHERE e.nombre = :nombre")})
public class EstadoCivilEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_estado_civil")
    private Integer idEstadoCivil;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nombre")
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEstadoCivil", fetch = FetchType.LAZY)
    private Collection<AfiliadoEntity> afiliadoEntityCollection;

    public EstadoCivilEntity() {
    }

    public EstadoCivilEntity(Integer idEstadoCivil) {
        this.idEstadoCivil = idEstadoCivil;
    }

    public EstadoCivilEntity(Integer idEstadoCivil, String nombre) {
        this.idEstadoCivil = idEstadoCivil;
        this.nombre = nombre;
    }

    public Integer getIdEstadoCivil() {
        return idEstadoCivil;
    }

    public void setIdEstadoCivil(Integer idEstadoCivil) {
        this.idEstadoCivil = idEstadoCivil;
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
        hash += (idEstadoCivil != null ? idEstadoCivil.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstadoCivilEntity)) {
            return false;
        }
        EstadoCivilEntity other = (EstadoCivilEntity) object;
        if ((this.idEstadoCivil == null && other.idEstadoCivil != null) || (this.idEstadoCivil != null && !this.idEstadoCivil.equals(other.idEstadoCivil))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.pliis.afiliacion.persistencia.hibernate.entities.EstadoCivilEntity[ idEstadoCivil=" + idEstadoCivil + " ]";
    }
    
}
