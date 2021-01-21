/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.pliis.afiliacion.persistencia.hibernate.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author DELL
 */
@Embeddable
public class BeneficioVinculoFamiliarEntityPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 101624477655432246L;
	@Basic(optional = false)
    @NotNull
    @Column(name = "id_vinculo_familiar")
	private int idVinculoFamiliar;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_beneficio")
    private int idBeneficio;

    public BeneficioVinculoFamiliarEntityPK() {
    }

    public BeneficioVinculoFamiliarEntityPK(int idVinculoFamiliar, int idBeneficio) {
        this.idVinculoFamiliar = idVinculoFamiliar;
        this.idBeneficio = idBeneficio;
    }

    public int getIdVinculoFamiliar() {
        return idVinculoFamiliar;
    }

    public void setIdVinculoFamiliar(int idVinculoFamiliar) {
        this.idVinculoFamiliar = idVinculoFamiliar;
    }

	public int getIdBeneficio() {
        return idBeneficio;
    }

    public void setIdBeneficio(int idBeneficio) {
        this.idBeneficio = idBeneficio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idVinculoFamiliar;
        hash += (int) idBeneficio;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BeneficioVinculoFamiliarEntityPK)) {
            return false;
        }
        BeneficioVinculoFamiliarEntityPK other = (BeneficioVinculoFamiliarEntityPK) object;
        if (this.idVinculoFamiliar != other.idVinculoFamiliar) {
            return false;
        }
        if (this.idBeneficio != other.idBeneficio) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.pliis.afiliacion.persistencia.hibernate.entities.BeneficioVinculoFamiliarEntityPK[ idVinculoFamiliar=" + idVinculoFamiliar + ", idBeneficio=" + idBeneficio + " ]";
    }
    
}
