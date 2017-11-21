package org.app.service.ejb;

import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.app.service.entities.Personal;

@Stateless @LocalBean
public class PersonalDataServiceEJB implements PersonalDataService {

	private static Logger logger = Logger.getLogger(PersonalDataServiceEJB.class.getName());
	
	@PersistenceContext(unitName="MSD")
	private EntityManager em;
	
	public PersonalDataServiceEJB() {
	
	}
	
// Constructor
	@PostConstruct
	public void init() {
		logger.info("POSTCONSTRUCT-INIT injected EntityManager: " + this.em);
	}
	
	//add
	
	@Override
	public Personal addPersonal(Personal personalAdd) {
		em.persist(personalAdd);
		em.flush();
		em.refresh(personalAdd);
		return null;
	}

	//read
	@Override
	public Personal getPersonalByIdPersonal(Integer IdPersonal) {
		// TODO Auto-generated method stub
		return em.find(Personal.class, IdPersonal);
	}

	@Override
	public Collection<Personal> getPersonal() {
		List<Personal> personal = em.createQuery("SELECT p FROM Personal p", Personal.class)
				.getResultList();
		return personal;
	}
	
	//delete

	@Override
	public String removePersonal(Personal personalToDelete) {
		personalToDelete = em.merge(personalToDelete);
		em.remove(personalToDelete);
		em.flush();
		return "True";
	}

	//custom read
	@Override
	public Personal getPersonalByName(String PersonalName) {
		return em.createQuery("SELECT p FROM Personal p WHERE p.PersonalName = :PersonalName", Personal.class)
				.setParameter("PersonalName", PersonalName)
				.getSingleResult();
	}

	//others
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return "PersonalDataServiceEJB is ON... ";
	}

}
