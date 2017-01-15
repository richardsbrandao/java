package studi.hibernate.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class DaoAbstratto<T> {

	private SessionFactory sessionFactory;
	
	public DaoAbstratto() {
		Configuration configuration = new Configuration().configure();
		ServiceRegistry buildServiceRegistry = new ServiceRegistryBuilder().applySettings( configuration.getProperties() ).buildServiceRegistry();
		sessionFactory = configuration.buildSessionFactory( buildServiceRegistry );
	}
	
	protected void salva(T dominio) {
		Session sessione = session();
		Transaction transazione = null;
		try {
			transazione = sessione.beginTransaction();
			sessione.save(dominio);
			transazione.commit();
		} catch (Exception e) {
			if( transazione != null ) {
				transazione.rollback();
			}
		}
	}
	
	protected Session session() {
		return sessionFactory.getCurrentSession();
	}
	
}
