package mx.pliis.afiliacion.persistencia.hibernate.entity;

import java.io.Serializable;
import java.time.LocalDate;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "observaciones_credito")
@NamedQueries({
    @NamedQuery(name = "ObservacionCreditoEntity.findAll", 						query = "SELECT oc FROM ObservacionCreditoEntity oc"),
    @NamedQuery(name = "ObservacionCreditoEntity.findByIdObservacionCredito", 	query = "SELECT oc FROM ObservacionCreditoEntity oc WHERE oc.idObservacionCredito = :idObservacionCredito"),
//    @NamedQuery(name = "ObservacionCreditoEntity.findByDescripcion", 			query = "SELECT oc FROM ObservacionCreditoEntity oc WHERE oc.descripcion = :descripcion"),
    @NamedQuery(name = "ObservacionCreditoEntity.findByActivo", 				query = "SELECT oc FROM ObservacionCreditoEntity oc WHERE oc.activo = :activo")})
public class ObservacionCreditoEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_observacion_credito")
    private Integer idObservacionCredito;
	@JoinColumn(name = "id_credito", referencedColumnName = "id_credito")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CreditoEntity idCredito;
	@JoinColumn(name = "id_estatus_credito", referencedColumnName = "id_estatus_credito")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private EstatusCreditoEntity idEstatusCredito;
    @Size(min = 1, max = 1000)
    @Column(name = "observacion")
    private String observacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_observacion")
    private LocalDate fechaObservacion;
	@Column(name = "activo")
	private Boolean activo;
	@JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    private UsuarioEntity idUsuario;
	
	public ObservacionCreditoEntity() {
    }
	
	public ObservacionCreditoEntity(Integer idObservacionCredito) {
		this.idObservacionCredito = idObservacionCredito;
    }
	
	public ObservacionCreditoEntity(
		Integer idObservacionCredito, CreditoEntity idCredito, 
		EstatusCreditoEntity idEstatusCredito, String observacion, 
		UsuarioEntity idUsuario, LocalDate fechaObservacion,
		Boolean activo
	) {
		this.idObservacionCredito = idObservacionCredito;
		this.idCredito = idCredito;
		this.idUsuario = idUsuario;
		this.idEstatusCredito = idEstatusCredito;
		this.observacion = observacion;
		this.fechaObservacion = fechaObservacion;
		this.activo = activo;
    }

	public Integer getIdObservacionCredito() {
		return idObservacionCredito;
	}

	public void setIdObservacionCredito(Integer idObservacionCredito) {
		this.idObservacionCredito = idObservacionCredito;
	}

	public CreditoEntity getIdCredito() {
		return idCredito;
	}

	public void setIdCredito(CreditoEntity idCredito) {
		this.idCredito = idCredito;
	}

	public EstatusCreditoEntity getIdEstatusCredito() {
		return idEstatusCredito;
	}

	public void setIdEstatusCredito(EstatusCreditoEntity idEstatusCredito) {
		this.idEstatusCredito = idEstatusCredito;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public LocalDate getFechaObservacion() {
		return fechaObservacion;
	}

	public void setFechaObservacion(LocalDate fechaObservacion) {
		this.fechaObservacion = fechaObservacion;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public UsuarioEntity getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(UsuarioEntity idUsuario) {
		this.idUsuario = idUsuario;
	}
}
