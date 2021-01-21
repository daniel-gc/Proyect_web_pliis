package mx.pliis.afiliacion.persistencia.hibernate.dao.afiliado;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mx.pliis.afiliacion.persistencia.hibernate.entity.UsuarioEntity;

@Repository
public class AfiliadoDAOImpl implements AfiliadoDAO {
	@Autowired
	private EntityManager em;

	@Override
	public UsuarioEntity getUsuarioByAfiliadoId(Integer idAfiliado) {
//		String query = "SELECT usuario ";
		return null;
	}

	@Override
	public boolean existeUsuarioByFacebook(String idFacebook) {
		StringBuilder query = new StringBuilder();
		query.append(" SELECT COUNT(*) ").append(" FROM AfiliadoEntity afil ")
				.append(" WHERE afil.idFacebook = :idFacebook ");

		Integer cantidad = em.createQuery(query.toString(), Integer.class).setParameter("idFacebook", idFacebook)
				.getSingleResult();

		return cantidad == 0 ? false : true;
	}

	@Override
	public boolean existeUsuarioByWhatsapp(String idWhatsapp) {
		StringBuilder query = new StringBuilder();
		query.append(" SELECT COUNT(*) ").append(" FROM AfiliadoEntity afil ")
				.append(" WHERE afil.idWhatsapp = :idWhatsapp ");

		Integer cantidad = em.createQuery(query.toString(), Integer.class).setParameter("idWhatsapp", idWhatsapp)
				.getSingleResult();

		return cantidad == 0 ? false : true;
	}

}
