package mx.pliis.afiliacion.persistencia.hibernate.entity;

import java.io.Serializable;

import javax.persistence.Basic;
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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "archivo_credito")
@NamedQueries({
    @NamedQuery(name = "ArchivoCreditoEntity.findAll", 					query = "SELECT ac FROM ArchivoCreditoEntity ac"),
    @NamedQuery(name = "ArchivoCreditoEntity.findByIdArchivoCredito",	query = "SELECT ac FROM ArchivoCreditoEntity ac WHERE ac.idArchivoCredito = :idArchivoCredito"),
    @NamedQuery(name = "ArchivoCreditoEntity.findByNombre", 			query = "SELECT ac FROM ArchivoCreditoEntity ac WHERE ac.nombre = :nombre"),
//    @NamedQuery(name = "ArchivoCreditoEntity.findByExtencion", 			query = "SELECT ac FROM ArchivoCreditoEntity ac WHERE ac.extencion = :extencion"),
    @NamedQuery(name = "ArchivoCreditoEntity.findByActivo", 			query = "SELECT ac FROM ArchivoCreditoEntity ac WHERE ac.activo = :activo")})
public class ArchivoCreditoEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_archivo_credito")
    private Integer idArchivoCredito;
	@JoinColumn(name = "id_credito", referencedColumnName = "id_credito")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CreditoEntity idCredito;
	@Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "nombre")
    private String nombre;
	@Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "extencion")
    private String extencion;
	@JoinColumn(name = "id_tipo_archivo", referencedColumnName = "id_tipo_archivo")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TipoArchivoEntity idTipoArchivo;
	@Column(name = "archivo")
    private byte[] archivo;
	@Column(name = "activo")
	private Boolean activo;
	
	@JoinColumn(name = "id_credito", referencedColumnName = "id_credito", insertable = false, updatable = false)
	@ManyToOne(fetch = FetchType.LAZY)
	private CreditoEntity creditoEntity;
	
	public ArchivoCreditoEntity() {
    }
	
	public ArchivoCreditoEntity(Integer idArchivoCredito) {
		this.idArchivoCredito = idArchivoCredito;
    }
	
	public ArchivoCreditoEntity(
		Integer idArchivoCredito, CreditoEntity idCredito, 
		String nombre, String extencion, 
		TipoArchivoEntity idTipoArchivo, 
		byte[] archivo, Boolean activo
	) {
		this.idArchivoCredito = idArchivoCredito;
		this.idCredito = idCredito;
		this.nombre = nombre;
		this.extencion = extencion;
		this.idTipoArchivo = idTipoArchivo;
		this.archivo = archivo;
		this.activo = activo;
    }

	public Integer getIdArchivoCredito() {
		return idArchivoCredito;
	}

	public void setIdArchivoCredito(Integer idArchivoCredito) {
		this.idArchivoCredito = idArchivoCredito;
	}

	public CreditoEntity getIdCredito() {
		return idCredito;
	}

	public void setIdCredito(CreditoEntity idCredito) {
		this.idCredito = idCredito;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getExtencion() {
		return extencion;
	}

	public void setExtencion(String extencion) {
		this.extencion = extencion;
	}

	public TipoArchivoEntity getIdTipoArchivo() {
		return idTipoArchivo;
	}

	public void setIdTipoArchivo(TipoArchivoEntity idTipoArchivo) {
		this.idTipoArchivo = idTipoArchivo;
	}

	public byte[] getArchivo() {
		return archivo;
	}

	public void setArchivo(byte[] archivo) {
		this.archivo = archivo;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public CreditoEntity getCreditoEntity() {
		return creditoEntity;
	}

	public void setCreditoEntity(CreditoEntity creditoEntity) {
		this.creditoEntity = creditoEntity;
	}
}
