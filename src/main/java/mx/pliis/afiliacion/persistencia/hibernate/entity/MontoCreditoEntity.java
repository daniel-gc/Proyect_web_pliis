package mx.pliis.afiliacion.persistencia.hibernate.entity;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "monto_credito")
@NamedQueries({
    @NamedQuery(name = "MontoCreditoEntity.findAll",				query = "SELECT mc FROM MontoCreditoEntity mc"),
//    @NamedQuery(name = "MontoCreditoEntity.findByIdMontoCredito",	query = "SELECT mc FROM MontoCreditoEntity mc WHERE mc.idMontoCredito = :idMontoCredito"),
    @NamedQuery(name = "MontoCreditoEntity.findByActivo",			query = "SELECT mc FROM MontoCreditoEntity mc WHERE mc.activo = :activo")})
public class MontoCreditoEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_monto_credito")
    private Integer idMontoCredito;
	@Basic(optional = false)
    @NotNull
	@Column(name = "monto")
	private BigDecimal monto;
	@Basic(optional = false)
    @NotNull
	@Column(name = "cuota_recuperacion")
	private BigDecimal cuotaRecuperacion;
	@Basic(optional = false)
    @NotNull
	@Column(name = "total")
	private BigDecimal total;
	@Column(name = "valido_desde")
    private Integer validoDesde;
	@Column(name = "valido_hasta")
    private Integer validoHasta;
	@Basic(optional = false)
    @NotNull
	@Column(name = "ingreso_neto")
	private BigDecimal ingresoNeto;
	@Basic(optional = false)
    @NotNull
	@Column(name = "descuento")
	private BigDecimal descuento;
	@Column(name = "activo")
	private Boolean activo;
//	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idMontoCredito", fetch = FetchType.LAZY)
//    private Collection<CreditoEntity> creditoEntityCollection;
    
	public MontoCreditoEntity() {
    }
	
	public MontoCreditoEntity(Integer idMontoCredito) {
		this.idMontoCredito = idMontoCredito;
    }
	
	public MontoCreditoEntity(
		Integer idMontoCredito, BigDecimal monto, BigDecimal cuotaRecuperacion,
		BigDecimal total, Integer validoDesde, Integer validoHasta,
		BigDecimal ingresoNeto, BigDecimal descuento, Boolean activo
	) {
		this.idMontoCredito = idMontoCredito;
		this.monto = monto;
		this.cuotaRecuperacion = cuotaRecuperacion;
		this.total = total;
		this.validoDesde = validoDesde;
		this.validoHasta = validoHasta;
		this.ingresoNeto = ingresoNeto;
		this.descuento = descuento;
		this.activo = activo;
    }

	public Integer getIdMontoCredito() {
		return idMontoCredito;
	}

	public void setIdMontoCredito(Integer idMontoCredito) {
		this.idMontoCredito = idMontoCredito;
	}

	public BigDecimal getMonto() {
		return monto;
	}

	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}

	public BigDecimal getCuotaRecuperacion() {
		return cuotaRecuperacion;
	}

	public void setCuotaRecuperacion(BigDecimal cuotaRecuperacion) {
		this.cuotaRecuperacion = cuotaRecuperacion;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public Integer getValidoDesde() {
		return validoDesde;
	}

	public void setValidoDesde(Integer validoDesde) {
		this.validoDesde = validoDesde;
	}

	public Integer getValidoHasta() {
		return validoHasta;
	}

	public void setValidoHasta(Integer validoHasta) {
		this.validoHasta = validoHasta;
	}

	public BigDecimal getIngresoNeto() {
		return ingresoNeto;
	}

	public void setIngresoNeto(BigDecimal ingresoNeto) {
		this.ingresoNeto = ingresoNeto;
	}

	public BigDecimal getDescuento() {
		return descuento;
	}

	public void setDescuento(BigDecimal descuento) {
		this.descuento = descuento;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

//	public Collection<CreditoEntity> getCreditoEntityCollection() {
//		return creditoEntityCollection;
//	}
//
//	public void setCreditoEntityCollection(Collection<CreditoEntity> creditoEntityCollection) {
//		this.creditoEntityCollection = creditoEntityCollection;
//	}
}
