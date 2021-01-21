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
@Table(name = "plan")
@NamedQueries({
    @NamedQuery(name = "PlanEntity.findAll", query = "SELECT p FROM PlanEntity p WHERE p.activo = true ORDER BY p.orden ASC"),
    @NamedQuery(name = "PlanEntity.findByIdPlan", query = "SELECT p FROM PlanEntity p WHERE p.activo = true AND p.idPlan = :idPlan"),
    @NamedQuery(name = "PlanEntity.findByNombre", query = "SELECT p FROM PlanEntity p WHERE p.activo = true AND p.nombre = :nombre")})
public class PlanEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_plan")
	private Integer idPlan;
	@Column(name = "id_plan_openpay")
	private String idPlanOpenpay;
	@Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nombre")
    private String nombre;
	@Column(name = "monto")
	private Double monto;
	@Column(name = "descripcion")
	private String descripcion;
	@Column(name = "moneda")
	private String moneda;
	@Column(name = "duracion")
	private Integer duracion;
	@Column(name = "unidad_duracion")
	private String unidad_duracion;
	@Column(name = "tiempo_prueba")
	private Integer tiempo_prueba;
	@Column(name = "orden")
	private Integer orden;
	@Column(name = "activo")
	private Boolean activo;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "planEntity", fetch = FetchType.LAZY)
    private Collection<ColorPlanEntity> colorPlanEntityCollection;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "planEntity", fetch = FetchType.LAZY)
    private Collection<SuscripcionEntity> suscripcionEntityCollection;
	
	public PlanEntity() {
    }

    public PlanEntity(Integer idSexo) {
        this.idPlan = idSexo;
    }

    public PlanEntity(Integer idPlan, String nombre) {
        this.idPlan = idPlan;
        this.nombre = nombre;
    }

	public Integer getIdPlan() {
		return idPlan;
	}

	public void setIdPlan(Integer idPlan) {
		this.idPlan = idPlan;
	}

	public String getIdPlanOpenpay() {
		return idPlanOpenpay;
	}

	public void setIdPlanOpenpay(String idPlanOpenpay) {
		this.idPlanOpenpay = idPlanOpenpay;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Double getMonto() {
		return monto;
	}

	public void setMonto(Double monto) {
		this.monto = monto;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getMoneda() {
		return moneda;
	}

	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	public Integer getDuracion() {
		return duracion;
	}

	public void setDuracion(Integer duracion) {
		this.duracion = duracion;
	}

	public String getUnidad_duracion() {
		return unidad_duracion;
	}

	public void setUnidad_duracion(String unidad_duracion) {
		this.unidad_duracion = unidad_duracion;
	}

	public Integer getTiempo_prueba() {
		return tiempo_prueba;
	}

	public void setTiempo_prueba(Integer tiempo_prueba) {
		this.tiempo_prueba = tiempo_prueba;
	}

	public Integer getOrden() {
		return orden;
	}

	public void setOrden(Integer orden) {
		this.orden = orden;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public Collection<ColorPlanEntity> getColorPlanEntityCollection() {
		return colorPlanEntityCollection;
	}

	public void setColorPlanEntityCollection(Collection<ColorPlanEntity> colorPlanEntityCollection) {
		this.colorPlanEntityCollection = colorPlanEntityCollection;
	}

	public Collection<SuscripcionEntity> getSuscripcionEntityCollection() {
		return suscripcionEntityCollection;
	}

	public void setSuscripcionEntityCollection(Collection<SuscripcionEntity> suscripcionEntityCollection) {
		this.suscripcionEntityCollection = suscripcionEntityCollection;
	}
}
