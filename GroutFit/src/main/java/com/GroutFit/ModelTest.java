package com.GroutFit;

import com.GroutFit.Model.ClothingType;
import org.apache.lucene.search.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.QueryBuilder;

import java.util.List;

public class ModelTest {
    public static void main(String[] args) {
        SessionFactory sf = new Configuration().configure().buildSessionFactory(); // Hibernate
        Session session = sf.openSession();

        /*try {
            FullTextSession fullTextSession = Search.getFullTextSession(session);
            fullTextSession.createIndexer().startAndWait();
        } catch(Exception e) {}
        session.close();
        System.out.println("riverrun\n");
        System.exit(0);*/

        FullTextSession fullTextSession = Search.getFullTextSession(session);
        Transaction tx = fullTextSession.beginTransaction();
        QueryBuilder qb = fullTextSession.getSearchFactory().buildQueryBuilder().forEntity(ClothingType.class).get();
        org.apache.lucene.search.Query query = qb.keyword().onField("description").matching("The GroutFit staple.").createQuery();

        javax.persistence.Query jpaQuery = fullTextSession.createFullTextQuery(query, ClothingType.class);

        List results = jpaQuery.getResultList();
        for(int i = 0; i < results.size(); i++) {
            System.out.println(results.get(i).toString());
        }
        session.close();
    }
}
