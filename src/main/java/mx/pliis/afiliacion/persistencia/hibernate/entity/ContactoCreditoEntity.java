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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "contacto_credito")
@NamedQueries({
    @NamedQuery(name = "ContactoCreditoEntity.findAll", 				query = "SELECT cc FROM ContactoCreditoEntity cc"),
    @NamedQuery(name = "ContactoCreditoEntity.findByIdContactoCredito",	query = "SELECT cc FROM ContactoCreditoEntity cc WHERE cc.idContactoCredito = :idContactoCredito"),
    @NamedQuery(name = "ContactoCreditoEntity.findByIdCredito",			query = "SELECT cc FROM ContactoCreditoEntity cc WHERE cc.idContactoCredito = :idContactoCredito"),
    @NamedQuery(name = "ContactoCreditoEntity.findByNombre",			query = "SELECT cc FROM ContactoCreditoEntity cc WHERE cc.nombre = :nombre"),
    @NamedQuery(name = "ContactoCreditoEntity.findByApellidoPaterno", 	query = "SELECT cc FROM ContactoCreditoEntity cc WHERE cc.apellidoPaterno = :apellidoPaterno"),
    @NamedQuery(name = "ContactoCreditoEntity.findByApellidoMaterno", 	query = "SELECT cc FROM ContactoCreditoEntity cc WHERE cc.apellidoMaterno = :apellidoMaterno"),
    @NamedQuery(name = "ContactoCreditoEntity.findByTelefono", 			query = "SELECT cc FROM ContactoCreditoEntity cc WHERE cc.telefono = :telefono"),
    @NamedQuery(name = "ContactoCreditoEntity.findByEmail", 			query = "SELECT cc FROM ContactoCreditoEntity cc WHERE cc.email = :email"),
    @NamedQuery(name = "ContactoCreditoEntity.findByParentesco", 		query = "SELECT cc FROM ContactoCreditoEntity cc WHERE cc.parentesco = :parentesco"),
    @NamedQuery(name = "ContactoCreditoEntity.findByActivo", 			query = "SELECT cc FROM ContactoCreditoEntity cc WHERE cc.activo = :activo")})
public class ContactoCreditoEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_contacto_credito")
    private Integer idContactoCredito;
	@JoinColumn(name = "id_credito", referencedColumnName = "id_credito")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CreditoEntity idCredito;
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
    @Size(min = 1, max = 50)
    @Column(name = "parentesco")
    private String parentesco;
	@Column(name = "activo")
	private Boolean activo;
	
	@JoinColumn(name = "id_credito", referencedColumnName = "id_credito", insertable = false, updatable = false)
	@ManyToOne(fetch = FetchType.LAZY)
	private CreditoEntity creditoEntity;
	
	public ContactoCreditoEntity() {
    }
	
	public ContactoCreditoEntity(Integer idContactoCredito) {
		this.idContactoCredito = idContactoCredito;
    }
	
	public ContactoCreditoEntity(
		Integer idContactoCredito, CreditoEntity idCredito, String nombre,
		String apellidoPaterno, String apellidoMaterno, String telefono,
		String email, String parentesco, Boolean activo
	) {
		this.idContactoCredito = idContactoCredito;
		this.idCredito = idCredito;
		this.nombre = nombre;
		this.apellidoPaterno = apellidoPaterno;
		this.apellidoMaterno = apellidoMaterno;
		this.telefono = telefono;
		this.email = email;
		this.parentesco = parentesco;
		this.activo = activo;
    }

	public Integer getIdContactoCredito() {
		return idContactoCredito;
	}

	public void setIdContactoCredito(Integer idContactoCredito) {
		this.idContactoCredito = idContactoCredito;
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

	public String getParentesco() {
		return parentesco;
	}

	public void setParentesco(String parentesco) {
		this.parentesco = parentesco;
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
