/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.pliis.afiliacion.persistencia.hibernate.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author DELL
 */
@Entity
@Table(name = "beneficio_vinculo_familiar")
@NamedQueries({
    @NamedQuery(name = "BeneficioVinculoFamiliarEntity.findAll", query = "SELECT b FROM BeneficioVinculoFamiliarEntity b"),
    @NamedQuery(name = "BeneficioVinculoFamiliarEntity.findByIdVinculoFamiliar", query = "SELECT b FROM BeneficioVinculoFamiliarEntity b WHERE b.beneficioVinculoFamiliarEntityPK.idVinculoFamiliar = :idVinculoFamiliar"),
    @NamedQuery(name = "BeneficioVinculoFamiliarEntity.findByIdBeneficio", query = "SELECT b FROM BeneficioVinculoFamiliarEntity b WHERE b.beneficioVinculoFamiliarEntityPK.idBeneficio = :idBeneficio"),
    @NamedQuery(name = "BeneficioVinculoFamiliarEntity.findByActivo", query = "SELECT b FROM BeneficioVinculoFamiliarEntity b WHERE b.activo = :activo")})
public class BeneficioVinculoFamiliarEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected BeneficioVinculoFamiliarEntityPK beneficioVinculoFamiliarEntityPK;
    @Column(name = "activo")
    private Boolean activo;
    @JoinColumn(name = "id_beneficio", referencedColumnName = "id_beneficio", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private BeneficioEntity beneficioEntity;
    @JoinColumn(name = "id_vinculo_familiar", referencedColumnName = "id_vinculo_famiiliar", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private VinculoFamiliarEntity vinculoFamiliarEntity;

    public BeneficioVinculoFamiliarEntity() {
    }

    public BeneficioVinculoFamiliarEntity(BeneficioVinculoFamiliarEntityPK beneficioVinculoFamiliarEntityPK) {
        this.beneficioVinculoFamiliarEntityPK = beneficioVinculoFamiliarEntityPK;
    }

    public BeneficioVinculoFamiliarEntity(int idVinculoFamiliar, int idBeneficio) {
        this.beneficioVinculoFamiliarEntityPK = new BeneficioVinculoFamiliarEntityPK(idVinculoFamiliar, idBeneficio);
    }

    public BeneficioVinculoFamiliarEntityPK getBeneficioVinculoFamiliarEntityPK() {
        return beneficioVinculoFamiliarEntityPK;
    }

    public void setBeneficioVinculoFamiliarEntityPK(BeneficioVinculoFamiliarEntityPK beneficioVinculoFamiliarEntityPK) {
        this.beneficioVinculoFamiliarEntityPK = beneficioVinculoFamiliarEntityPK;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public BeneficioEntity getBeneficioEntity() {
        return beneficioEntity;
    }

    public void setBeneficioEntity(BeneficioEntity beneficioEntity) {
        this.beneficioEntity = beneficioEntity;
    }

    public VinculoFamiliarEntity getVinculoFamiliarEntity() {
        return vinculoFamiliarEntity;
    }

    public void setVinculoFamiliarEntity(VinculoFamiliarEntity vinculoFamiliarEntity) {
        this.vinculoFamiliarEntity = vinculoFamiliarEntity;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (beneficioVinculoFamiliarEntityPK != null ? beneficioVinculoFamiliarEntityPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BeneficioVinculoFamiliarEntity)) {
            return false;
        }
        BeneficioVinculoFamiliarEntity other = (BeneficioVinculoFamiliarEntity) object;
        if ((this.beneficioVinculoFamiliarEntityPK == null && other.beneficioVinculoFamiliarEntityPK != null) || (this.beneficioVinculoFamiliarEntityPK != null && !this.beneficioVinculoFamiliarEntityPK.equals(other.beneficioVinculoFamiliarEntityPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.pliis.afiliacion.persistencia.hibernate.entities.BeneficioVinculoFamiliarEntity[ beneficioVinculoFamiliarEntityPK=" + beneficioVinculoFamiliarEntityPK + " ]";
    }
    
}
