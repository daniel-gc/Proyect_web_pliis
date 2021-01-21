/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.pliis.afiliacion.persistencia.hibernate.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author DELL
 */
@Entity
@Table(name = "usuario_red_social")
@NamedQueries({
    @NamedQuery(name = "UsuarioRedSocialEntity.findAll", query = "SELECT u FROM UsuarioRedSocialEntity u"),
    @NamedQuery(name = "UsuarioRedSocialEntity.findByIdentificador", query = "SELECT u FROM UsuarioRedSocialEntity u WHERE u.identificador = :identificador"),
    @NamedQuery(name = "UsuarioRedSocialEntity.findByIdUsuario", query = "SELECT u FROM UsuarioRedSocialEntity u WHERE u.usuarioRedSocialEntityPK.idUsuario = :idUsuario"),
    @NamedQuery(name = "UsuarioRedSocialEntity.findByIdRedSocial", query = "SELECT u FROM UsuarioRedSocialEntity u WHERE u.usuarioRedSocialEntityPK.idRedSocial = :idRedSocial")})
public class UsuarioRedSocialEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected UsuarioRedSocialEntityPK usuarioRedSocialEntityPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "identificador")
    private String identificador;
    @JoinColumn(name = "id_red_social", referencedColumnName = "id_red_social", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private RedSocialEntity redSocialEntity;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private UsuarioEntity usuarioEntity;

    public UsuarioRedSocialEntity() {
    }

    public UsuarioRedSocialEntity(UsuarioRedSocialEntityPK usuarioRedSocialEntityPK) {
        this.usuarioRedSocialEntityPK = usuarioRedSocialEntityPK;
    }

    public UsuarioRedSocialEntity(UsuarioRedSocialEntityPK usuarioRedSocialEntityPK, String identificador) {
        this.usuarioRedSocialEntityPK = usuarioRedSocialEntityPK;
        this.identificador = identificador;
    }

    public UsuarioRedSocialEntity(int idUsuario, int idRedSocial) {
        this.usuarioRedSocialEntityPK = new UsuarioRedSocialEntityPK(idUsuario, idRedSocial);
    }

    public UsuarioRedSocialEntityPK getUsuarioRedSocialEntityPK() {
        return usuarioRedSocialEntityPK;
    }

    public void setUsuarioRedSocialEntityPK(UsuarioRedSocialEntityPK usuarioRedSocialEntityPK) {
        this.usuarioRedSocialEntityPK = usuarioRedSocialEntityPK;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public RedSocialEntity getRedSocialEntity() {
        return redSocialEntity;
    }

    public void setRedSocialEntity(RedSocialEntity redSocialEntity) {
        this.redSocialEntity = redSocialEntity;
    }

    public UsuarioEntity getUsuarioEntity() {
        return usuarioEntity;
    }

    public void setUsuarioEntity(UsuarioEntity usuarioEntity) {
        this.usuarioEntity = usuarioEntity;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usuarioRedSocialEntityPK != null ? usuarioRedSocialEntityPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsuarioRedSocialEntity)) {
            return false;
        }
        UsuarioRedSocialEntity other = (UsuarioRedSocialEntity) object;
        if ((this.usuarioRedSocialEntityPK == null && other.usuarioRedSocialEntityPK != null) || (this.usuarioRedSocialEntityPK != null && !this.usuarioRedSocialEntityPK.equals(other.usuarioRedSocialEntityPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.pliis.afiliacion.persistencia.hibernate.entities.UsuarioRedSocialEntity[ usuarioRedSocialEntityPK=" + usuarioRedSocialEntityPK + " ]";
    }
    
}
