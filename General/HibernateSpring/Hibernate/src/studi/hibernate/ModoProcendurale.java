package studi.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import studi.hibernate.dominio.Studente;

public class ModoProcendurale {

	public static void main(String[] args) {
		Configuration configuration = new Configuration().configure();
		
		ServiceRegistry buildServiceRegistry = new ServiceRegistryBuilder().applySettings( configuration.getProperties() ).buildServiceRegistry();
		SessionFactory sessionFactory = configuration.buildSessionFactory( buildServiceRegistry );
		
		Studente studente = new Studente();
		studente.setId(1L);
		studente.setNome("Abc");
		
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		session.saveOrUpdate( studente );
		session.getTransaction().commit();
	}
	
}
