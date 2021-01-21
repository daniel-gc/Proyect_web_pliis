/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

/**
 *
 * @author DELL
 */
@Entity
@Table(name = "afiliado")
@NamedQueries({
    @NamedQuery(name = "AfiliadoEntity.findAll", query = "SELECT a FROM AfiliadoEntity a"),
    @NamedQuery(name = "AfiliadoEntity.findByIdAfiliado", query = "SELECT a FROM AfiliadoEntity a WHERE a.idAfiliado = :idAfiliado"),
    @NamedQuery(name = "AfiliadoEntity.findByNombres", query = "SELECT a FROM AfiliadoEntity a WHERE a.nombres = :nombres"),
    @NamedQuery(name = "AfiliadoEntity.findByEsIdCentroTrabajo", query = "SELECT a FROM AfiliadoEntity a WHERE a.esIdCentroTrabajo = :esIdCentroTrabajo"),
    @NamedQuery(name = "AfiliadoEntity.findByEsIdSindicato", query = "SELECT a FROM AfiliadoEntity a WHERE a.esIdSindicato = :esIdSindicato"),
    @NamedQuery(name = "AfiliadoEntity.findByApellidoPaterno", query = "SELECT a FROM AfiliadoEntity a WHERE a.apellidoPaterno = :apellidoPaterno"),
    @NamedQuery(name = "AfiliadoEntity.findByApellidoMaterno", query = "SELECT a FROM AfiliadoEntity a WHERE a.apellidoMaterno = :apellidoMaterno"),
    @NamedQuery(name = "AfiliadoEntity.findByDireccionDomicilio", query = "SELECT a FROM AfiliadoEntity a WHERE a.direccionDomicilio = :direccionDomicilio"),
    @NamedQuery(name = "AfiliadoEntity.findByRfc", query = "SELECT a FROM AfiliadoEntity a WHERE a.rfc = :rfc"),
    @NamedQuery(name = "AfiliadoEntity.findByCurp", query = "SELECT a FROM AfiliadoEntity a WHERE a.curp = :curp"),
    @NamedQuery(name = "AfiliadoEntity.findByLugarNacimiento", query = "SELECT a FROM AfiliadoEntity a WHERE a.lugarNacimiento = :lugarNacimiento"),
    @NamedQuery(name = "AfiliadoEntity.findByFechaAfiliacion", query = "SELECT a FROM AfiliadoEntity a WHERE a.fechaAfiliacion = :fechaAfiliacion"),
    @NamedQuery(name = "AfiliadoEntity.findByFechaNacimiento", query = "SELECT a FROM AfiliadoEntity a WHERE a.fechaNacimiento = :fechaNacimiento"),
    @NamedQuery(name = "AfiliadoEntity.findByContrato", query = "SELECT a FROM AfiliadoEntity a WHERE a.contrato = :contrato"),
    @NamedQuery(name = "AfiliadoEntity.findByFechaRegistro", query = "SELECT a FROM AfiliadoEntity a WHERE a.fechaRegistro = :fechaRegistro"),
    @NamedQuery(name = "AfiliadoEntity.findByFechaBaja", query = "SELECT a FROM AfiliadoEntity a WHERE a.fechaBaja = :fechaBaja"),
    @NamedQuery(name = "AfiliadoEntity.findByNombreSindicato", query = "SELECT a FROM AfiliadoEntity a WHERE a.nombreSindicato = :nombreSindicato"),
    @NamedQuery(name = "AfiliadoEntity.findByNombrePuestoTrabajo", query = "SELECT a FROM AfiliadoEntity a WHERE a.nombrePuestoTrabajo = :nombrePuestoTrabajo"),
    @NamedQuery(name = "AfiliadoEntity.findBySalarioMensualNeto", query = "SELECT a FROM AfiliadoEntity a WHERE a.salarioMensualNeto = :salarioMensualNeto"),
    @NamedQuery(name = "AfiliadoEntity.findBySalarioMensualBruto", query = "SELECT a FROM AfiliadoEntity a WHERE a.salarioMensualBruto = :salarioMensualBruto")})
public class AfiliadoEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_afiliado")
    private Integer idAfiliado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "nombres")
    private String nombres;
    @Column(name = "es_id_centro_trabajo")
    private Integer esIdCentroTrabajo;
    @Column(name = "es_id_sindicato")
    private Integer esIdSindicato;
//    @Lob
    @Column(name = "foto_credencial")
    private byte[] fotoCredencial;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "apellido_paterno")
    private String apellidoPaterno;
    @Size(max = 100)
    @Column(name = "apellido_materno")
    private String apellidoMaterno;
    @Size(max = 500)
    @Column(name = "direccion_domicilio")
    private String direccionDomicilio;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "rfc")
    private String rfc;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "curp")
    private String curp;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "lugar_nacimiento")
    private String lugarNacimiento;
    @Column(name = "fecha_afiliacion")
	private LocalDate fechaAfiliacion;
//    @Lob
    @Column(name = "foto_afiliado")
    private byte[] fotoAfiliado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_nacimiento")
	private LocalDate fechaNacimiento;
    @Size(max = 100)
    @Column(name = "contrato")
    private String contrato;
    @Column(name = "fecha_registro")
    
	private LocalDate fechaRegistro;
    @Column(name = "fecha_baja")
    
	private LocalDate fechaBaja;
    @Size(max = 100)
    @Column(name = "nombre_sindicato")
    private String nombreSindicato;
    @Size(max = 100)
    @Column(name = "nombre_puesto_trabajo")
    private String nombrePuestoTrabajo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "salario_mensual_neto")
	private BigDecimal salarioMensualNeto;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "salario_mensual_bruto")
	private BigDecimal salarioMensualBruto;
	@Column(name = "desea_afiliarse")
	private Boolean deseaAfiliarse;
	@Column(name = "fecha_ingreso_empresa")
	private LocalDate fechaIngresoEmpresa;
	@Column(name = "id_facebook")
	private String idFacebook;
	@Column(name = "id_whatsapp")
	private String idWhatsapp;
	@Column(name = "calle")
	private String calle;
	@Column(name = "numero")
	private String numero;
	@Column(name = "colonia")
	private String colonia;
	@Column(name = "alcaldia")
	private String alcaldia;
	@Column(name = "ciudad")
	private String ciudad;
	@Column(name = "pais")
	private String pais;

    @JoinColumn(name = "id_estado_civil", referencedColumnName = "id_estado_civil")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private EstadoCivilEntity idEstadoCivil;
    @JoinColumn(name = "id_nacionalidad", referencedColumnName = "id_nacionalidad")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private NacionalidadEntity idNacionalidad;
    @JoinColumn(name = "id_sexo", referencedColumnName = "id_sexo")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private SexoEntity idSexo;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    private UsuarioEntity idUsuario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idAfiliado", fetch = FetchType.LAZY)
    private Collection<VinculoFamiliarEntity> vinculoFamiliarEntityCollection;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idAfiliado", fetch = FetchType.LAZY)
//    private Collection<CreditoEntity> creditoEntityCollection;
    
    public AfiliadoEntity() {
    }

    public AfiliadoEntity(Integer idAfiliado) {
        this.idAfiliado = idAfiliado;
    }

	public AfiliadoEntity(Integer idAfiliado, String nombres, String apellidoPaterno, String rfc, String curp,
			String lugarNacimiento, LocalDate fechaAfiliacion, LocalDate fechaNacimiento,
			BigDecimal salarioMensualNeto) {
        this.idAfiliado = idAfiliado;
        this.nombres = nombres;
        this.apellidoPaterno = apellidoPaterno;
        this.rfc = rfc;
        this.curp = curp;
        this.lugarNacimiento = lugarNacimiento;
        this.fechaAfiliacion = fechaAfiliacion;
        this.fechaNacimiento = fechaNacimiento;
        this.salarioMensualNeto = salarioMensualNeto;
    }

    public Integer getIdAfiliado() {
        return idAfiliado;
    }

    public void setIdAfiliado(Integer idAfiliado) {
        this.idAfiliado = idAfiliado;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public Integer getEsIdCentroTrabajo() {
        return esIdCentroTrabajo;
    }

    public void setEsIdCentroTrabajo(Integer esIdCentroTrabajo) {
        this.esIdCentroTrabajo = esIdCentroTrabajo;
    }

    public Integer getEsIdSindicato() {
        return esIdSindicato;
    }

    public void setEsIdSindicato(Integer esIdSindicato) {
        this.esIdSindicato = esIdSindicato;
    }

    public byte[] getFotoCredencial() {
        return fotoCredencial;
    }

    public void setFotoCredencial(byte[] fotoCredencial) {
        this.fotoCredencial = fotoCredencial;
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

    public String getDireccionDomicilio() {
        return direccionDomicilio;
    }

    public void setDireccionDomicilio(String direccionDomicilio) {
        this.direccionDomicilio = direccionDomicilio;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public String getLugarNacimiento() {
        return lugarNacimiento;
    }

    public void setLugarNacimiento(String lugarNacimiento) {
        this.lugarNacimiento = lugarNacimiento;
    }

	public LocalDate getFechaAfiliacion() {
        return fechaAfiliacion;
    }

	public void setFechaAfiliacion(LocalDate fechaAfiliacion) {
        this.fechaAfiliacion = fechaAfiliacion;
    }

    public byte[] getFotoAfiliado() {
        return fotoAfiliado;
    }

    public void setFotoAfiliado(byte[] fotoAfiliado) {
        this.fotoAfiliado = fotoAfiliado;
    }

	public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getContrato() {
        return contrato;
    }

    public void setContrato(String contrato) {
        this.contrato = contrato;
    }

	public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

	public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

	public LocalDate getFechaBaja() {
        return fechaBaja;
    }

	public void setFechaBaja(LocalDate fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

    public String getNombreSindicato() {
        return nombreSindicato;
    }

    public void setNombreSindicato(String nombreSindicato) {
        this.nombreSindicato = nombreSindicato;
    }

    public String getNombrePuestoTrabajo() {
        return nombrePuestoTrabajo;
    }

    public void setNombrePuestoTrabajo(String nombrePuestoTrabajo) {
        this.nombrePuestoTrabajo = nombrePuestoTrabajo;
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

    public EstadoCivilEntity getIdEstadoCivil() {
        return idEstadoCivil;
    }

    public void setIdEstadoCivil(EstadoCivilEntity idEstadoCivil) {
        this.idEstadoCivil = idEstadoCivil;
    }

    public NacionalidadEntity getIdNacionalidad() {
        return idNacionalidad;
    }

    public void setIdNacionalidad(NacionalidadEntity idNacionalidad) {
        this.idNacionalidad = idNacionalidad;
    }

    public SexoEntity getIdSexo() {
        return idSexo;
    }

    public void setIdSexo(SexoEntity idSexo) {
        this.idSexo = idSexo;
    }

    public UsuarioEntity getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(UsuarioEntity idUsuario) {
        this.idUsuario = idUsuario;
    }

	public Boolean getDeseaAfiliarse() {
		return deseaAfiliarse;
	}

	public void setDeseaAfiliarse(Boolean deseaAfiliarse) {
		this.deseaAfiliarse = deseaAfiliarse;
	}

	public LocalDate getFechaIngresoEmpresa() {
		return fechaIngresoEmpresa;
	}

	public void setFechaIngresoEmpresa(LocalDate fechaIngresoEmpresa) {
		this.fechaIngresoEmpresa = fechaIngresoEmpresa;
	}

	public String getIdFacebook() {
		return idFacebook;
	}

	public String getIdWhatsapp() {
		return idWhatsapp;
	}

	public String getCalle() {
		return calle;
	}

	public String getNumero() {
		return numero;
	}

	public String getColonia() {
		return colonia;
	}

	public String getAlcaldia() {
		return alcaldia;
	}

	public String getCiudad() {
		return ciudad;
	}

	public String getPais() {
		return pais;
	}

	public void setIdFacebook(String idFacebook) {
		this.idFacebook = idFacebook;
	}

	public void setIdWhatsapp(String idWhatsapp) {
		this.idWhatsapp = idWhatsapp;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public void setColonia(String colonia) {
		this.colonia = colonia;
	}

	public void setAlcaldia(String alcaldia) {
		this.alcaldia = alcaldia;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public void setPais(String pais) {
		this.pais = pais;
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
        hash += (idAfiliado != null ? idAfiliado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AfiliadoEntity)) {
            return false;
        }
        AfiliadoEntity other = (AfiliadoEntity) object;
        if ((this.idAfiliado == null && other.idAfiliado != null) || (this.idAfiliado != null && !this.idAfiliado.equals(other.idAfiliado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.pliis.afiliacion.persistencia.hibernate.entities.AfiliadoEntity[ idAfiliado=" + idAfiliado + " ]";
    }
    
}
