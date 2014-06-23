package www.wcy.wat.gorky.repositories;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import www.wcy.wat.gorky.model.Avatar;
import www.wcy.wat.gorky.model.Characterattributes;

@Repository
public class CharacterAttributesRepository {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public Characterattributes findCharacterattributesByAvatarId(Integer id)
	{
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Characterattributes c where c.idavatar.idavatar = :id");
		query.setParameter("id", id);
		List<Characterattributes> characterattributesList = query.list();
		if(!characterattributesList.isEmpty())
			return characterattributesList.get(0);
		else 
			return null;		
	}

}
