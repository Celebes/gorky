package www.wcy.wat.gorky.repositories;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import www.wcy.wat.gorky.dto.UserDTO;
import www.wcy.wat.gorky.model.User;

@Repository
public class UserRepository {

	@Autowired
	private SessionFactory sessionFactory;

	@Transactional(readOnly = false, rollbackFor = DataAccessException.class)
	public void saveUser(UserDTO userDto) {
		User user = mapDtoUserToEntity(userDto);
		sessionFactory.getCurrentSession().persist(user);
	}
	
	public List<User> findAll()
	{
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from User");
		
		return query.list();
		
	}
	
	public User findUserById(Integer id)
	{
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from User u where u.iduser = :id");
		query.setParameter("id", id);
		List<User> users = query.list();
		if(!users.isEmpty())
			return users.get(0);
		else 
			return null;		
	}
	
	public User findUserByLogin(String login)
	{
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(User.class)
				.add(Restrictions.like("login", login));
		List<User> users = criteria.list();
		if(!users.isEmpty())
			return users.get(0);
		else
			return null;
	}
	
	public User updateUser(String login)
	{
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(User.class)
				.add(Restrictions.like("login", login));
		List<User> users = criteria.list();
		if(!users.isEmpty())
			return users.get(0);
		else
			return null;
	}
	
	public User mapDtoUserToEntity(UserDTO userDTO)
	{
		User user = new User();
		user.setLogin(userDTO.getLogin());
		user.setPassword(userDTO.getPassword());
		user.setName(userDTO.getName());
		user.setSurname(userDTO.getSurname());
		user.setEmail(userDTO.getEmail());
		user.setOld(userDTO.getOld());
		user.setCountry(userDTO.getCountry());
		user.setCity(userDTO.getCity());
		user.setDescription(userDTO.getDescription());
		return user;
	}

}
