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
@Table(name = "relacion_familiar")
@NamedQueries({
    @NamedQuery(name = "RelacionFamiliarEntity.findAll", query = "SELECT r FROM RelacionFamiliarEntity r"),
    @NamedQuery(name = "RelacionFamiliarEntity.findByIdRelacionFamiliar", query = "SELECT r FROM RelacionFamiliarEntity r WHERE r.idRelacionFamiliar = :idRelacionFamiliar"),
    @NamedQuery(name = "RelacionFamiliarEntity.findByNombre", query = "SELECT r FROM RelacionFamiliarEntity r WHERE r.nombre = :nombre")})
public class RelacionFamiliarEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_relacion_familiar")
    private Integer idRelacionFamiliar;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nombre")
    private String nombre;
	@Basic(optional = false)
	@NotNull
	@Column(name = "incluida", columnDefinition = "boolean default true", nullable = false)
	private Boolean incluida;
	@Column(name = "cantidad_maxima")
	private Integer cantidadMaxima;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idRelacionFamiliar", fetch = FetchType.LAZY)
    private Collection<VinculoFamiliarEntity> vinculoFamiliarEntityCollection;

    public RelacionFamiliarEntity() {
    }

    public RelacionFamiliarEntity(Integer idRelacionFamiliar) {
        this.idRelacionFamiliar = idRelacionFamiliar;
    }

    public RelacionFamiliarEntity(Integer idRelacionFamiliar, String nombre) {
        this.idRelacionFamiliar = idRelacionFamiliar;
        this.nombre = nombre;
    }

    public Integer getIdRelacionFamiliar() {
        return idRelacionFamiliar;
    }

    public void setIdRelacionFamiliar(Integer idRelacionFamiliar) {
        this.idRelacionFamiliar = idRelacionFamiliar;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

	public Boolean getIncluida() {
		return incluida;
	}

	public Integer getCantidadMaxima() {
		return cantidadMaxima;
	}

	public void setIncluida(Boolean incluida) {
		this.incluida = incluida;
	}

	public void setCantidadMaxima(Integer cantidadMaxima) {
		this.cantidadMaxima = cantidadMaxima;
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
        hash += (idRelacionFamiliar != null ? idRelacionFamiliar.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RelacionFamiliarEntity)) {
            return false;
        }
        RelacionFamiliarEntity other = (RelacionFamiliarEntity) object;
        if ((this.idRelacionFamiliar == null && other.idRelacionFamiliar != null) || (this.idRelacionFamiliar != null && !this.idRelacionFamiliar.equals(other.idRelacionFamiliar))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.pliis.afiliacion.persistencia.hibernate.entities.RelacionFamiliarEntity[ idRelacionFamiliar=" + idRelacionFamiliar + " ]";
    }
    
}
