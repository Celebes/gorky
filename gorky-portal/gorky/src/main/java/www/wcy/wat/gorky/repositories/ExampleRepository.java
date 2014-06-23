//package www.wcy.wat.gorky.repositories;
//
//import java.util.List;
//
//import org.hibernate.Criteria;
//import org.hibernate.Query;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.criterion.Order;
//import org.hibernate.criterion.Projections;
//import org.hibernate.criterion.Restrictions;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.dao.DataAccessException;
//import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;
//
//import www.wcy.wat.gorky.model.Example;
//
//@Repository
//public class ExampleRepository
//{
//    @Autowired
//    private SessionFactory sessionFactory;
//
//    @Transactional(readOnly = true)
//    @SuppressWarnings("unchecked")
//    public List<Example> findExampleById( Long id )
//    {
//        Session session = sessionFactory.getCurrentSession();
//        Criteria criteria = session.createCriteria( Example.class )
//                .add( Restrictions.eq( "id", id ) )
//                .add( Restrictions.le( "age", 40 ) );
//
//        criteria.setProjection( Projections.projectionList()
//                .add( Projections.rowCount() )
//                .add( Projections.max( "age" ) ) );
//
//        criteria.addOrder( Order.asc( "id" ) );
//        criteria.addOrder( Order.desc( "age" ) );
//
//        criteria.setFirstResult( 5 * 25 );
//        criteria.setMaxResults( 25 );
//
//        return criteria.list();
//    }
//
//    public List<Example> findExampleHQL()
//    {
//        Session session = sessionFactory.getCurrentSession();
//        Query query = session.createQuery( "from Example e where e.id = :id" );
//
//        query.setParameter( "id", 123 );
//
//        return null;
//    }
//    
//    public List<Example> findAll()
//    {
//    	Session session = sessionFactory.getCurrentSession();
//    	Query query = session.createQuery("from Example");
//    	
//    	return query.list();
//    }
//
//    @Transactional(readOnly = false, rollbackFor = DataAccessException.class)
//    public void updateExample( Example example )
//    {
//        sessionFactory.getCurrentSession().merge( example );
//    }
//
//    @Transactional(readOnly = false, rollbackFor = DataAccessException.class)
//    public void saveExample( Example example )
//    {
//        sessionFactory.getCurrentSession().persist( example );
//    }
//}
