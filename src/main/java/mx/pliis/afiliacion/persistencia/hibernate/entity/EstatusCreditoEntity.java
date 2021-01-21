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

@Entity
@Table(name = "estatus_credito")
@NamedQueries({
    @NamedQuery(name = "EstatusCreditoEntity.findAll", 					query = "SELECT ec FROM EstatusCreditoEntity ec"),
    @NamedQuery(name = "EstatusCreditoEntity.findByIdEstatusCredito",	query = "SELECT ec FROM EstatusCreditoEntity ec WHERE ec.idEstatusCredito = :idEstatusCredito"),
    @NamedQuery(name = "EstatusCreditoEntity.findByDescripcion",		query = "SELECT ec FROM EstatusCreditoEntity ec WHERE ec.descripcion = :descripcion"),
    @NamedQuery(name = "EstatusCreditoEntity.findByActivo", 			query = "SELECT ec FROM EstatusCreditoEntity ec WHERE ec.activo = :activo")})
public class EstatusCreditoEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_estatus_credito")
    private Integer idEstatusCredito;
	@Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "descripcion")
    private String descripcion;
	@Column(name = "activo")
	private Boolean activo;
//	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idEstatusCredito", fetch = FetchType.LAZY)
//    private Collection<CreditoEntity> creditoEntityCollection;
//	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idEstatusCredito", fetch = FetchType.LAZY)
//    private Collection<ObservacionCreditoEntity> observacionCreditoEntityCollection;
    
	public EstatusCreditoEntity() {
    }
	
	public EstatusCreditoEntity(Integer idEstatusCredito) {
		this.idEstatusCredito = idEstatusCredito;
    }
	
	public EstatusCreditoEntity(Integer idEstatusCredito, String descripcion, Boolean activo) {
		this.idEstatusCredito = idEstatusCredito;
		this.descripcion = descripcion;
		this.activo = activo;
    }

	public Integer getIdEstatusCredito() {
		return idEstatusCredito;
	}

	public void setIdEstatusCredito(Integer idEstatusCredito) {
		this.idEstatusCredito = idEstatusCredito;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}
//
//	public Collection<CreditoEntity> getCreditoEntityCollection() {
//		return creditoEntityCollection;
//	}
//
//	public void setCreditoEntityCollection(Collection<CreditoEntity> creditoEntityCollection) {
//		this.creditoEntityCollection = creditoEntityCollection;
//	}
//
//	public Collection<ObservacionCreditoEntity> getObservacionCreditoEntityCollection() {
//		return observacionCreditoEntityCollection;
//	}
//
//	public void setObservacionCreditoEntityCollection(
//			Collection<ObservacionCreditoEntity> observacionCreditoEntityCollection) {
//		this.observacionCreditoEntityCollection = observacionCreditoEntityCollection;
//	}
}
