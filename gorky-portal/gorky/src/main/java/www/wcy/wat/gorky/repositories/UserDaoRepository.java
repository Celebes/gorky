//package www.wcy.wat.gorky.repositories;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.hibernate.Query;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//
//import www.wcy.wat.gorky.model.Users;
//
//@Repository
//public class UserDaoRepository {
//
//    @Autowired
//    private SessionFactory sessionFactory;
//     
// 
//    public Users getUser(String login) {
//        List<Users> userList = new ArrayList<Users>();
//        Session session = sessionFactory.getCurrentSession();
//        Query query = session.createQuery("from Users u where u.login = :login");
//        query.setParameter("login", login);
//        userList = query.list();
//        if (userList.size() > 0)
//            return userList.get(0);
//        else
//            return null;    
//    }
//}
