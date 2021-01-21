package mx.pliis.afiliacion.persistencia.hibernate.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.pliis.afiliacion.enums.PagoEstatus;
import mx.pliis.afiliacion.enums.PagoTipo;
import mx.pliis.afiliacion.persistencia.hibernate.types.PostgreSQLEnumType;

@Entity
@Table(name = "pagos")
@TypeDef(name = "pgsql_enum", typeClass = PostgreSQLEnumType.class)
@Getter
@Setter
@NoArgsConstructor
public class PagosEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pago", nullable = false, unique = true)
    private Integer idPago;
	
	@Column(name = "id_usuario", nullable = false)
	private Integer idUsuario;
	
	@Column(name = "id_plan", nullable = false)
	private Integer idPlan;
	
	@Column(name = "id_pago_openpay", nullable = true)
	private String idPagoOpenpay;
	
	@Column(name = "description", nullable = false, length = 255)
	private String description;

	@Column(name = "monto", nullable = false, precision = 19, scale = 2)
	private BigDecimal monto;
	
	@Enumerated(EnumType.STRING)
	@Type(type = "pgsql_enum")
	@Column(name = "estatus", nullable = false, columnDefinition = "pago_estatus")
	private PagoEstatus status;
	
	@Column(name = "fecha", nullable = false)
	private LocalDateTime fecha;
	
	@Enumerated(EnumType.STRING)
	@Type(type = "pgsql_enum")
	@Column(name = "tipo", nullable = false, columnDefinition = "pago_tipo")
	private PagoTipo tipo;
	
	@Column(name = "autorizacion", nullable = true, length = 15)
	private String authorization;
	
	@Column(name = "codigo_error", nullable = false)
	private Integer errorCode;
	
	@Column(name = "mensaje_error", nullable = false, length = 255)
	private String errorMessage;
	
	@JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private UsuarioEntity usuarioEntity;
}
