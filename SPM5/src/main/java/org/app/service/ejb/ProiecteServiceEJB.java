package org.app.service.ejb;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.app.service.entities.Proiecte;

import java.util.Collection;
import java.util.List;
//import org.jboss.resteasy.logging.Logger;
import java.util.logging.Logger;

@Stateless @LocalBean
public class ProiecteServiceEJB implements ProiecteService{
	
	private static Logger logger = Logger.getLogger(ProiecteServiceEJB.class.getName());
	
	//ProiecteService initialization
	
	@PersistenceContext(unitName="MSD")
	private EntityManager em;
	
	//constructor
	public ProiecteServiceEJB() {
		//init after construct
	}
	
	@PostConstruct
	public void init() {
		logger.info("POSTCONSTRUCT-INIT :" + this.em );
	}
	
	//CRUD operation implementation
	//Create or Update
	@Override
	public Proiecte addProiecte(Proiecte proiecteAdd) {
		// TODO Auto-generated method stub
		em.persist(proiecteAdd);
		em.flush();
		em.refresh(proiecteAdd);
		return proiecteAdd;
	}
	
	//remove

	@Override
	public String removeProiecte(Proiecte proiecteToDelete) {
		// TODO Auto-generated method stub
		proiecteToDelete = em.merge(proiecteToDelete);
		em.remove(proiecteToDelete);
		em.flush();
		return "True";
	}

	//read
	
	@Override
	public Proiecte getProiecteByIdProiect(Integer IdProiecte) {
		// TODO Auto-generated method stub
		return em.find(Proiecte.class, IdProiecte);
	}
	public Collection<Proiecte> getProiecte() {
		// TODO Auto-generated method stub
		List<Proiecte>  proiecte= em.createQuery("Select p FROM Proiecte p", Proiecte.class).getResultList();
		return proiecte;
	}

	//custom read
	@Override
	public Proiecte getProiecteByName(String ProiecteName) {
		// TODO Auto-generated method stub
		return em.createQuery("Select p from Proiecte p where p.ProiecteName = :ProiecteName", Proiecte.class).setParameter("ProiecteName", ProiecteName).getSingleResult();
	}

	//others

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return "ProiecteServiceEJB IS ON..";
	}

}
