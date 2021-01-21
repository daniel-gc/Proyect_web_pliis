package mx.pliis.afiliacion.persistencia.hibernate.entity;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "credito")
@NamedQueries({
    @NamedQuery(name = "CreditoEntity.findAll",					query = "SELECT ce FROM CreditoEntity ce WHERE ce.activo = true"),
    @NamedQuery(name = "CreditoEntity.findByIdCredito",			query = "SELECT ce FROM CreditoEntity ce WHERE ce.idCredito = :idCredito"),
////    @NamedQuery(name = "CreditoEntity.findByIdAfiliado", query = "SELECT c FROM CreditoEntity c WHERE c.idAfiliado = :idAfiliado"),
////    @NamedQuery(name = "CreditoEntity.findByIdMiembro", query = "SELECT c FROM CreditoEntity c WHERE c.idMiembro = :idMiembro"),
    @NamedQuery(name = "CreditoEntity.findByNumeroEmpleado",	query = "SELECT ce FROM CreditoEntity ce WHERE ce.numeroEmpleado = :numeroEmpleado and ce.activo = true"),
    @NamedQuery(name = "CreditoEntity.findByFechaIngresoCia",	query = "SELECT ce FROM CreditoEntity ce WHERE ce.fechaIngresoCia = :fechaIngresoCia and ce.activo = true"),
    @NamedQuery(name = "CreditoEntity.findByNombre",			query = "SELECT ce FROM CreditoEntity ce WHERE ce.nombre = :nombre and ce.activo = true"),
    @NamedQuery(name = "CreditoEntity.findByApellidoPaterno",	query = "SELECT ce FROM CreditoEntity ce WHERE ce.apellidoPaterno = :apellidoPaterno and ce.activo = true"),
    @NamedQuery(name = "CreditoEntity.findByApellidoMaterno",	query = "SELECT ce FROM CreditoEntity ce WHERE ce.apellidoMaterno = :apellidoMaterno and ce.activo = true"),
    @NamedQuery(name = "CreditoEntity.findByFechaNacimiento",	query = "SELECT ce FROM CreditoEntity ce WHERE ce.fechaNacimiento = :fechaNacimiento and ce.activo = true"),
    @NamedQuery(name = "CreditoEntity.findByTelefono",			query = "SELECT ce FROM CreditoEntity ce WHERE ce.telefono = :telefono and ce.activo = true"),
    @NamedQuery(name = "CreditoEntity.findByEmail",				query = "SELECT ce FROM CreditoEntity ce WHERE ce.email = :email and ce.activo = true"),
    @NamedQuery(name = "CreditoEntity.findByEmpresa",			query = "SELECT ce FROM CreditoEntity ce WHERE ce.empresa = :empresa and ce.activo = true"),
////    @NamedQuery(name = "CreditoEntity.findByIdMontoCredito", query = "SELECT c FROM CreditoEntity c WHERE c.idMontoCredito = :idMontoCredito"),
////    @NamedQuery(name = "CreditoEntity.findByIdEstatusCredito", query = "SELECT c FROM CreditoEntity c WHERE c.idEstatusCredito = :idEstatusCredito"),
    @NamedQuery(name = "CreditoEntity.findByActivo",			query = "SELECT ce FROM CreditoEntity ce WHERE ce.activo = :activo")})
public class CreditoEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_credito")
    private Integer idCredito;
	@JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    private UsuarioEntity idUsuario;
	@Column(name = "es_afiliado")
	private Boolean esAfiliado;
	@Column(name = "es_miembro")
	private Boolean esMiembro;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "apellido_paterno")
    private String apellidoPaterno;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "apellido_materno")
    private String apellidoMaterno;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "telefono")
    private String telefono;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "empresa")
    private String empresa;
	@Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "numero_empleado")
    private String numeroEmpleado;
	@Basic(optional = false)
    @NotNull
    @Column(name = "fecha_ingreso_cia")
	private LocalDate fechaIngresoCia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "salario_mensual_neto")
	private BigDecimal salarioMensualNeto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "salario_mensual_bruto")
	private BigDecimal salarioMensualBruto;
    @JoinColumn(name = "id_monto_credito", referencedColumnName = "id_monto_credito")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private MontoCreditoEntity idMontoCredito;
    @JoinColumn(name = "id_estatus_credito", referencedColumnName = "id_estatus_credito")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private EstatusCreditoEntity idEstatusCredito;
    @Column(name = "activo")
	private Boolean activo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_solicitud")
    private LocalDate fechaSolicitud;
    @Column(name = "fecha_conclucion")
    private LocalDate fechaConclucion;
    @Column(name = "origen_solicitud")
    private Integer origenSolicitud;
    @Column(name = "enviado")
	private Boolean enviado;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCredito", fetch = FetchType.LAZY)
    private Collection<ObservacionCreditoEntity> observacionCreditoEntity;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCredito", fetch = FetchType.LAZY)
    private Collection<ArchivoCreditoEntity> archivoCreditoEntityCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCredito", fetch = FetchType.LAZY)
    private Collection<ContactoCreditoEntity> contactoCreditoEntityCollection;
    
    public CreditoEntity() {
    }
	
	public CreditoEntity(Integer id_credito) {
		this.idCredito = id_credito;
    }
	
	public CreditoEntity(
		Integer idCredito, UsuarioEntity idUsuario,
		Boolean esAfiliado, Boolean esMiembro,
		String numeroEmpleado, LocalDate fechaIngresoCia, 
		MontoCreditoEntity idMontoCredito, EstatusCreditoEntity idEstatusCredito, 
		String nombre, String apellidoPaterno, 
		String apellidoMaterno, LocalDate fechaNacimiento, 
		String telefono, String email, String empresa, 
		BigDecimal salarioMensualNeto, BigDecimal salarioMensualBruto, 
		LocalDate fechaSolicitud, LocalDate fechaConclucion,
		Boolean activo
	) {
		this.idCredito = idCredito;
		this.idUsuario = idUsuario;
		this.esAfiliado = esAfiliado;
		this.esMiembro = esMiembro;
		this.numeroEmpleado = numeroEmpleado;
		this.fechaIngresoCia = fechaIngresoCia;
		this.idMontoCredito = idMontoCredito;
		this.idEstatusCredito = idEstatusCredito;
		this.nombre = nombre;
		this.apellidoPaterno = apellidoPaterno;
		this.apellidoMaterno = apellidoMaterno;
		this.fechaNacimiento = fechaNacimiento;
		this.telefono = telefono;
		this.email = email;
		this.empresa = empresa;
		this.salarioMensualNeto = salarioMensualNeto;
		this.salarioMensualBruto = salarioMensualBruto;
		this.fechaSolicitud = fechaSolicitud;
		this.fechaConclucion = fechaConclucion;
		this.activo = activo;
    }

	public Integer getIdCredito() {
		return idCredito;
	}

	public void setIdCredito(Integer idCredito) {
		this.idCredito = idCredito;
	}

	public UsuarioEntity getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(UsuarioEntity idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Boolean getEsAfiliado() {
		return esAfiliado;
	}

	public void setEsAfiliado(Boolean esAfiliado) {
		this.esAfiliado = esAfiliado;
	}

	public Boolean getEsMiembro() {
		return esMiembro;
	}

	public void setEsMiembro(Boolean esMiembro) {
		this.esMiembro = esMiembro;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidoPaterno() {
		return apellidoPaterno;
	}

	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}

	public String getApellidoMaterno() {
		return apellidoMaterno;
	}

	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public String getNumeroEmpleado() {
		return numeroEmpleado;
	}

	public void setNumeroEmpleado(String numeroEmpleado) {
		this.numeroEmpleado = numeroEmpleado;
	}

	public LocalDate getFechaIngresoCia() {
		return fechaIngresoCia;
	}

	public void setFechaIngresoCia(LocalDate fechaIngresoCia) {
		this.fechaIngresoCia = fechaIngresoCia;
	}

	public BigDecimal getSalarioMensualNeto() {
		return salarioMensualNeto;
	}

	public void setSalarioMensualNeto(BigDecimal salarioMensualNeto) {
		this.salarioMensualNeto = salarioMensualNeto;
	}

	public BigDecimal getSalarioMensualBruto() {
		return salarioMensualBruto;
	}

	public void setSalarioMensualBruto(BigDecimal salarioMensualBruto) {
		this.salarioMensualBruto = salarioMensualBruto;
	}

	public MontoCreditoEntity getIdMontoCredito() {
		return idMontoCredito;
	}

	public void setIdMontoCredito(MontoCreditoEntity idMontoCredito) {
		this.idMontoCredito = idMontoCredito;
	}

	public EstatusCreditoEntity getIdEstatusCredito() {
		return idEstatusCredito;
	}

	public void setIdEstatusCredito(EstatusCreditoEntity idEstatusCredito) {
		this.idEstatusCredito = idEstatusCredito;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public LocalDate getFechaSolicitud() {
		return fechaSolicitud;
	}

	public void setFechaSolicitud(LocalDate fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}

	public LocalDate getFechaConclucion() {
		return fechaConclucion;
	}

	public void setFechaConclucion(LocalDate fechaConclucion) {
		this.fechaConclucion = fechaConclucion;
	}

	public Integer getOrigenSolicitud() {
		return origenSolicitud;
	}

	public void setOrigenSolicitud(Integer origenSolicitud) {
		this.origenSolicitud = origenSolicitud;
	}

	public Boolean getEnviado() {
		return enviado;
	}

	public void setEnviado(Boolean enviado) {
		this.enviado = enviado;
	}

	public Collection<ObservacionCreditoEntity> getObservacionCreditoEntity() {
		return observacionCreditoEntity;
	}

	public void setObservacionCreditoEntity(Collection<ObservacionCreditoEntity> observacionCreditoEntity) {
		this.observacionCreditoEntity = observacionCreditoEntity;
	}

	public Collection<ArchivoCreditoEntity> getArchivoCreditoEntityCollection() {
		return archivoCreditoEntityCollection;
	}

	public void setArchivoCreditoEntityCollection(Collection<ArchivoCreditoEntity> archivoCreditoEntityCollection) {
		this.archivoCreditoEntityCollection = archivoCreditoEntityCollection;
	}

	public Collection<ContactoCreditoEntity> getContactoCreditoEntityCollection() {
		return contactoCreditoEntityCollection;
	}

	public void setContactoCreditoEntityCollection(Collection<ContactoCreditoEntity> contactoCreditoEntityCollection) {
		this.contactoCreditoEntityCollection = contactoCreditoEntityCollection;
	}
}
