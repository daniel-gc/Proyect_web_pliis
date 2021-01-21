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
@Table(name = "sexo")
@NamedQueries({
    @NamedQuery(name = "SexoEntity.findAll", query = "SELECT s FROM SexoEntity s"),
    @NamedQuery(name = "SexoEntity.findByIdSexo", query = "SELECT s FROM SexoEntity s WHERE s.idSexo = :idSexo"),
    @NamedQuery(name = "SexoEntity.findByNombre", query = "SELECT s FROM SexoEntity s WHERE s.nombre = :nombre")})
public class SexoEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_sexo")
    private Integer idSexo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nombre")
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idSexo", fetch = FetchType.LAZY)
    private Collection<AfiliadoEntity> afiliadoEntityCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idSexo", fetch = FetchType.LAZY)
    private Collection<FamiliarEntity> familiarEntityCollection;

    public SexoEntity() {
    }

    public SexoEntity(Integer idSexo) {
        this.idSexo = idSexo;
    }

    public SexoEntity(Integer idSexo, String nombre) {
        this.idSexo = idSexo;
        this.nombre = nombre;
    }

    public Integer getIdSexo() {
        return idSexo;
    }

    public void setIdSexo(Integer idSexo) {
        this.idSexo = idSexo;
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

    public Collection<FamiliarEntity> getFamiliarEntityCollection() {
        return familiarEntityCollection;
    }

    public void setFamiliarEntityCollection(Collection<FamiliarEntity> familiarEntityCollection) {
        this.familiarEntityCollection = familiarEntityCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSexo != null ? idSexo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SexoEntity)) {
            return false;
        }
        SexoEntity other = (SexoEntity) object;
        if ((this.idSexo == null && other.idSexo != null) || (this.idSexo != null && !this.idSexo.equals(other.idSexo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.pliis.afiliacion.persistencia.hibernate.entities.SexoEntity[ idSexo=" + idSexo + " ]";
    }
    
}
