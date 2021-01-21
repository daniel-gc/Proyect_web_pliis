/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.pliis.afiliacion.persistencia.hibernate.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author DELL
 */
@Embeddable
public class UsuarioRedSocialEntityPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "id_usuario")
    private int idUsuario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_red_social")
    private int idRedSocial;

    public UsuarioRedSocialEntityPK() {
    }

    public UsuarioRedSocialEntityPK(int idUsuario, int idRedSocial) {
        this.idUsuario = idUsuario;
        this.idRedSocial = idRedSocial;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdRedSocial() {
        return idRedSocial;
    }

    public void setIdRedSocial(int idRedSocial) {
        this.idRedSocial = idRedSocial;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idUsuario;
        hash += (int) idRedSocial;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsuarioRedSocialEntityPK)) {
            return false;
        }
        UsuarioRedSocialEntityPK other = (UsuarioRedSocialEntityPK) object;
        if (this.idUsuario != other.idUsuario) {
            return false;
        }
        if (this.idRedSocial != other.idRedSocial) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.pliis.afiliacion.persistencia.hibernate.entities.UsuarioRedSocialEntityPK[ idUsuario=" + idUsuario + ", idRedSocial=" + idRedSocial + " ]";
    }
    
}
