//package www.wcy.wat.gorky.repositories;
//
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//
//import www.wcy.wat.gorky.model.Role;
//
//@Repository
//public class RoleRepository {
//	
//    @Autowired
//    private SessionFactory sessionFactory;
//    
//    public Role getRole(int id)
//    {
//    	Session session = sessionFactory.getCurrentSession();
//        Role role = (Role) session.load(Role.class, id);
//        return role;
//    }
//
//}
