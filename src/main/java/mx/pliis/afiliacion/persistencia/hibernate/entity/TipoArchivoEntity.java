package mx.pliis.afiliacion.persistencia.hibernate.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "tipo_archivo")
@NamedQueries({
    @NamedQuery(name = "TipoArchivoEntity.findAll", 			query = "SELECT ta FROM TipoArchivoEntity ta"),
    @NamedQuery(name = "TipoArchivoEntity.findByIdTipoArchivo",	query = "SELECT ta FROM TipoArchivoEntity ta WHERE ta.idTipoArchivo = :idTipoArchivo"),
//    @NamedQuery(name = "TipoArchivoEntity.findByDescripcion", 	query = "SELECT ta FROM TipoArchivoEntity ta WHERE ta.descripcion = :descripcion"),
    @NamedQuery(name = "TipoArchivoEntity.findByActivo", 		query = "SELECT ta FROM TipoArchivoEntity ta WHERE ta.activo = :activo")})
public class TipoArchivoEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo_archivo")
    private Integer idTipoArchivo;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 50)
	@Column(name = "descripcion")
	private String descripcion;
	@Column(name = "activo")
	private Boolean activo;
	
	public TipoArchivoEntity() {
    }
	
	public TipoArchivoEntity(Integer idTipoArchivo) {
		this.idTipoArchivo = idTipoArchivo;
    }
	
	public TipoArchivoEntity(Integer idTipoArchivo, String descripcion, Boolean activo) {
		this.idTipoArchivo = idTipoArchivo;
		this.descripcion = descripcion;
		this.activo = activo;
    }

	public Integer getIdTipoArchivo() {
		return idTipoArchivo;
	}

	public void setIdTipoArchivo(Integer idTipoArchivo) {
		this.idTipoArchivo = idTipoArchivo;
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
}
