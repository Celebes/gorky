package www.wcy.wat.gorky.repositories;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import www.wcy.wat.gorky.model.Avatar;

@Repository
public class AvatarRepository {

	@Autowired
	private SessionFactory sessionFactory;
	
	public Avatar findAvatarByUserId(Integer id)
	{
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Avatar a where a.iduser.iduser = :id");
		query.setParameter("id", id);
		List<Avatar> avatars = query.list();
		if(!avatars.isEmpty())
			return avatars.get(0);
		else 
			return null;		
	}
}
