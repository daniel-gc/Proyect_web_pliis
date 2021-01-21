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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author DELL
 */
@Entity
@Table(name = "vinculo_familiar")
@NamedQueries({
    @NamedQuery(name = "VinculoFamiliarEntity.findAll", query = "SELECT v FROM VinculoFamiliarEntity v"),
    @NamedQuery(name = "VinculoFamiliarEntity.findByActivo", query = "SELECT v FROM VinculoFamiliarEntity v WHERE v.activo = :activo"),
    @NamedQuery(name = "VinculoFamiliarEntity.findByIdVinculoFamiiliar", query = "SELECT v FROM VinculoFamiliarEntity v WHERE v.idVinculoFamiiliar = :idVinculoFamiiliar")})
public class VinculoFamiliarEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "activo")
    private Boolean activo;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_vinculo_famiiliar")
    private Integer idVinculoFamiiliar;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vinculoFamiliarEntity", fetch = FetchType.LAZY)
    private Collection<BeneficioVinculoFamiliarEntity> beneficioVinculoFamiliarEntityCollection;
    @JoinColumn(name = "id_afiliado", referencedColumnName = "id_afiliado")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private AfiliadoEntity idAfiliado;
    @JoinColumn(name = "id_familiar", referencedColumnName = "id_familiar")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private FamiliarEntity idFamiliar;
    @JoinColumn(name = "id_relacion_familiar", referencedColumnName = "id_relacion_familiar")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private RelacionFamiliarEntity idRelacionFamiliar;

    public VinculoFamiliarEntity() {
    }

    public VinculoFamiliarEntity(Integer idVinculoFamiiliar) {
        this.idVinculoFamiiliar = idVinculoFamiiliar;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public Integer getIdVinculoFamiiliar() {
        return idVinculoFamiiliar;
    }

    public void setIdVinculoFamiiliar(Integer idVinculoFamiiliar) {
        this.idVinculoFamiiliar = idVinculoFamiiliar;
    }

    public Collection<BeneficioVinculoFamiliarEntity> getBeneficioVinculoFamiliarEntityCollection() {
        return beneficioVinculoFamiliarEntityCollection;
    }

    public void setBeneficioVinculoFamiliarEntityCollection(Collection<BeneficioVinculoFamiliarEntity> beneficioVinculoFamiliarEntityCollection) {
        this.beneficioVinculoFamiliarEntityCollection = beneficioVinculoFamiliarEntityCollection;
    }

    public AfiliadoEntity getIdAfiliado() {
        return idAfiliado;
    }

    public void setIdAfiliado(AfiliadoEntity idAfiliado) {
        this.idAfiliado = idAfiliado;
    }

    public FamiliarEntity getIdFamiliar() {
        return idFamiliar;
    }

    public void setIdFamiliar(FamiliarEntity idFamiliar) {
        this.idFamiliar = idFamiliar;
    }

    public RelacionFamiliarEntity getIdRelacionFamiliar() {
        return idRelacionFamiliar;
    }

    public void setIdRelacionFamiliar(RelacionFamiliarEntity idRelacionFamiliar) {
        this.idRelacionFamiliar = idRelacionFamiliar;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idVinculoFamiiliar != null ? idVinculoFamiiliar.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VinculoFamiliarEntity)) {
            return false;
        }
        VinculoFamiliarEntity other = (VinculoFamiliarEntity) object;
        if ((this.idVinculoFamiiliar == null && other.idVinculoFamiiliar != null) || (this.idVinculoFamiiliar != null && !this.idVinculoFamiiliar.equals(other.idVinculoFamiiliar))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.pliis.afiliacion.persistencia.hibernate.entities.VinculoFamiliarEntity[ idVinculoFamiiliar=" + idVinculoFamiiliar + " ]";
    }
    
}
