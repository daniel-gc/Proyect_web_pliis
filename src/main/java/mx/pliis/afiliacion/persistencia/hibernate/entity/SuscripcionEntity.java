package mx.pliis.afiliacion.persistencia.hibernate.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "suscripcion")
@NamedQueries({
    @NamedQuery(name = "SuscripcionEntity.findAll", query = "SELECT s FROM SuscripcionEntity s"),
    @NamedQuery(name = "SuscripcionEntity.findByidPlan", query = "SELECT s FROM SuscripcionEntity s WHERE s.suscripcionEntityPK.idPlan = :idPlan"),
//    @NamedQuery(name = "SuscripcionEntity.findByidMiembro", query = "SELECT s FROM SuscripcionEntity s WHERE s.suscripcionEntityPK.idMiembro = :idMiembro"),
    @NamedQuery(name = "SuscripcionEntity.findByidMiembroAndidPlanAndidPagoAndactivo", query = "SELECT s FROM SuscripcionEntity s WHERE s.suscripcionEntityPK.idMiembro = :idMiembro and s.suscripcionEntityPK.idPlan = :idPlan and s.suscripcionEntityPK.idPago = :idPago and s.activo = true"),
    @NamedQuery(name = "SuscripcionEntity.findByactivo", query = "SELECT s FROM SuscripcionEntity s WHERE s.activo = true"),
    @NamedQuery(name = "SuscripcionEntity.findByidMiembroAndActivo", query = "SELECT s FROM SuscripcionEntity s WHERE s.suscripcionEntityPK.idMiembro = :idMiembro and s.activo = :activo")
    })
public class SuscripcionEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EmbeddedId
    protected SuscripcionEntityPK suscripcionEntityPK;
	@JoinColumn(name = "id_plan", referencedColumnName = "id_plan", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private PlanEntity planEntity;
	@JoinColumn(name = "id_miembro", referencedColumnName = "id_miembro", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private MiembroEntity miembroEntity;
	@OneToOne
	@JoinColumn(name = "id_pago", referencedColumnName = "id_pago", insertable = false, updatable = false)
    private PagosEntity pagoEntity;
	@Basic(optional = false)
    @NotNull
    @Column(name = "fecha_inicio")
	private LocalDate fechaInicio;
	@Basic(optional = false)
    @NotNull
    @Column(name = "fecha_fin")
	private LocalDate fechaFin;
	@Column(name = "activo")
    private Boolean activo;
	
	public SuscripcionEntity() {
		
	}
	public SuscripcionEntity(SuscripcionEntityPK suscripcionEntityPK) {
		this.suscripcionEntityPK = suscripcionEntityPK;
	}
	public SuscripcionEntity(SuscripcionEntityPK suscripcionEntityPK, LocalDate fechaInicio, LocalDate fechaFin, Boolean activo) {
		this.suscripcionEntityPK = suscripcionEntityPK;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.activo = activo;
	}
	public SuscripcionEntity(int idPlan, int idMiembro, int idPago) {
		this.suscripcionEntityPK = new SuscripcionEntityPK(idPlan, idMiembro, idPago);
	}
	public SuscripcionEntityPK getSuscripcionEntityPK() {
		return suscripcionEntityPK;
	}
	public void setSuscripcionEntityPK(SuscripcionEntityPK suscripcionEntityPK) {
		this.suscripcionEntityPK = suscripcionEntityPK;
	}
	public PlanEntity getPlanEntity() {
		return planEntity;
	}
	public void setPlanEntity(PlanEntity planEntity) {
		this.planEntity = planEntity;
	}
	public MiembroEntity getMiembroEntity() {
		return miembroEntity;
	}
	public void setMiembroEntity(MiembroEntity miembroEntity) {
		this.miembroEntity = miembroEntity;
	}
	public PagosEntity getPagoEntity() {
		return pagoEntity;
	}
	public void setPagoEntity(PagosEntity pagoEntity) {
		this.pagoEntity = pagoEntity;
	}
	public LocalDate getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(LocalDate fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public LocalDate getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(LocalDate fechaFin) {
		this.fechaFin = fechaFin;
	}
	public Boolean getActivo() {
		return activo;
	}
	public void setActivo(Boolean activo) {
		this.activo = activo;
	}
}
