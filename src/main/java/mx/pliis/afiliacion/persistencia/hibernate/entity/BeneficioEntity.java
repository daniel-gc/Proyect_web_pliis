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
@Table(name = "beneficio")
@NamedQueries({
    @NamedQuery(name = "BeneficioEntity.findAll", query = "SELECT b FROM BeneficioEntity b"),
    @NamedQuery(name = "BeneficioEntity.findByIdBeneficio", query = "SELECT b FROM BeneficioEntity b WHERE b.idBeneficio = :idBeneficio"),
    @NamedQuery(name = "BeneficioEntity.findByNombre", query = "SELECT b FROM BeneficioEntity b WHERE b.nombre = :nombre")})
public class BeneficioEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_beneficio")
    private Integer idBeneficio;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nombre")
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "beneficioEntity", fetch = FetchType.LAZY)
    private Collection<BeneficioVinculoFamiliarEntity> beneficioVinculoFamiliarEntityCollection;

    public BeneficioEntity() {
    }

    public BeneficioEntity(Integer idBeneficio) {
        this.idBeneficio = idBeneficio;
    }

    public BeneficioEntity(Integer idBeneficio, String nombre) {
        this.idBeneficio = idBeneficio;
        this.nombre = nombre;
    }

    public Integer getIdBeneficio() {
        return idBeneficio;
    }

    public void setIdBeneficio(Integer idBeneficio) {
        this.idBeneficio = idBeneficio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Collection<BeneficioVinculoFamiliarEntity> getBeneficioVinculoFamiliarEntityCollection() {
        return beneficioVinculoFamiliarEntityCollection;
    }

    public void setBeneficioVinculoFamiliarEntityCollection(Collection<BeneficioVinculoFamiliarEntity> beneficioVinculoFamiliarEntityCollection) {
        this.beneficioVinculoFamiliarEntityCollection = beneficioVinculoFamiliarEntityCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idBeneficio != null ? idBeneficio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BeneficioEntity)) {
            return false;
        }
        BeneficioEntity other = (BeneficioEntity) object;
        if ((this.idBeneficio == null && other.idBeneficio != null) || (this.idBeneficio != null && !this.idBeneficio.equals(other.idBeneficio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.pliis.afiliacion.persistencia.hibernate.entities.BeneficioEntity[ idBeneficio=" + idBeneficio + " ]";
    }
    
}
