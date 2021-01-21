package mx.pliis.afiliacion.persistencia.hibernate.entity;

import java.io.Serializable;
import java.util.Collection;

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

@Entity
@Table(name = "color_plan")
@NamedQueries({
    @NamedQuery(name = "ColorPlanEntity.findAll", query = "SELECT c FROM ColorPlanEntity c"),
    @NamedQuery(name = "ColorPlanEntity.findByIdPlan", query = "SELECT c FROM ColorPlanEntity c WHERE c.idPlan = :idPlan")})
public class ColorPlanEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_color_plan")
    private Integer idColorPlan;
	@Column(name = "id_plan")
    private Integer idPlan;
	@Column(name = "color_hex")
    private String colorHex;
	@Column(name = "color_rgba")
    private String colorRGBA;
	@Column(name = "uso_para")
    private String usoPara;
	@Column(name = "orden")
    private Integer orden;
	@JoinColumn(name = "id_plan", referencedColumnName = "id_plan", insertable = false, updatable = false)
	@ManyToOne(fetch = FetchType.LAZY)
	private PlanEntity planEntity;
	
	public Integer getIdColorPlan() {
		return idColorPlan;
	}
	public void setIdColorPlan(Integer idColorPlan) {
		this.idColorPlan = idColorPlan;
	}
	public Integer getIdPlan() {
		return idPlan;
	}
	public void setIdPlan(Integer idPlan) {
		this.idPlan = idPlan;
	}
	public String getColorHex() {
		return colorHex;
	}
	public void setColorHex(String colorHex) {
		this.colorHex = colorHex;
	}
	public String getColorRGBA() {
		return colorRGBA;
	}
	public void setColorRGBA(String colorRGBA) {
		this.colorRGBA = colorRGBA;
	}
	public String getUsoPara() {
		return usoPara;
	}
	public void setUsoPara(String usoPara) {
		this.usoPara = usoPara;
	}
	public Integer getOrden() {
		return orden;
	}
	public void setOrden(Integer orden) {
		this.orden = orden;
	}
	public PlanEntity getPlanEntity() {
		return planEntity;
	}
	public void setPlanEntity(PlanEntity planEntity) {
		this.planEntity = planEntity;
	}
}
