package mx.pliis.afiliacion.persistencia.hibernate.dao.afiliado;

import mx.pliis.afiliacion.persistencia.hibernate.entity.UsuarioEntity;

public interface AfiliadoDAO {

	UsuarioEntity getUsuarioByAfiliadoId(Integer idAfiliado);

	boolean existeUsuarioByFacebook(String idFacebook);

	boolean existeUsuarioByWhatsapp(String idWhatsapp);
}
