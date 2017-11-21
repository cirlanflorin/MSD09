package org.app.service.ejb;

import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.app.service.entities.Personal;
import org.app.service.entities.Suport;

@Stateless @LocalBean
public class SuportDataServiceEJB implements SuportDataService {
	
	private static Logger logger = Logger.getLogger(SuportDataServiceEJB.class.getName());
	
	@PersistenceContext(unitName="MSD")
	private EntityManager em;
	
	public SuportDataServiceEJB() {
	
	}
	
// Constructor
	@PostConstruct
	public void init() {
		logger.info("POSTCONSTRUCT-INIT injected EntityManager: " + this.em);
	}
	
//add
	@Override
	public Suport addSuport(Suport suportAdd) {
		em.persist(suportAdd);
		em.flush();
		em.refresh(suportAdd);
		return null;
	}
//read
	@Override
	public Suport getSuportByIdSuport(Integer IdSuport) {
		// TODO Auto-generated method stub
		return em.find(Suport.class, IdSuport);
	}

	@Override
	public Collection<Suport> getSuport() {
		List<Suport> suport = em.createQuery("SELECT s FROM Suport s", Suport.class)
				.getResultList();
		return suport;
	}

	//delete
	@Override
	public String removeSuport(Suport suportToDelete) {
		suportToDelete = em.merge(suportToDelete);
		em.remove(suportToDelete);
		em.flush();
		return "True";
	}
	//custom read

	@Override
	public Suport getSuportByName(String SuportName) {
		return em.createQuery("SELECT s FROM Suport s WHERE s.SuportName = :SuportName", Suport.class)
				.setParameter("SuportName", SuportName)
				.getSingleResult();
	}

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return "SuportDataServiceEJB is ON... ";
	}

}
