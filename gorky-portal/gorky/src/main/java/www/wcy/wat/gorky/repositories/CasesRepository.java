/*package www.wcy.wat.gorky.repositories;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
//TODO stworzenie modeli
public class CasesRepository
{
    @Autowired
    private SessionFactory sessionFactory;

//    @Transactional(readOnly = true)
//    public List getCases()
//    {
//
//    }

    @Transactional(readOnly = true)
    public Long getCasesCount()
    {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = this.createCasesCriteria( session );

        criteria.setProjection( Projections.rowCount() );

        return (Long) criteria.uniqueResult();
    }

    private Criteria createCasesCriteria( Session session )
    {
        Criteria criteria = session.createCriteria( Object.class );

        return criteria;
    }
}
*/