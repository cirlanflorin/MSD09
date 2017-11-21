package org.app.service.ejb;

import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.app.service.entities.DetaliiProiecte;



@Stateless @LocalBean
public class DetaliiProiecteDataServiceEJB implements DetaliiProiecteDataService {
	
	private static Logger logger = Logger.getLogger(DetaliiProiecteDataServiceEJB.class.getName());
	
	@PersistenceContext(unitName="MSD")
	private EntityManager em;
	
	public DetaliiProiecteDataServiceEJB() {
	
	}
	
// Constructor
	@PostConstruct
	public void init() {
		logger.info("POSTCONSTRUCT-INIT injected EntityManager: " + this.em);
	}
	
	//create

	@Override
	public DetaliiProiecte addDetaliiProiecte(DetaliiProiecte detaliiProiecteAdd) {
		em.persist(detaliiProiecteAdd);
		em.flush();
		em.refresh(detaliiProiecteAdd);
		return null;
	}

	//read
	@Override
	public DetaliiProiecte getDetaliiProiecteByIdProiect(Integer IdProiect) {
		// TODO Auto-generated method stub
			return em.find(DetaliiProiecte.class, IdProiect);
	}

	@Override
	public Collection<DetaliiProiecte> getDetaliiProiecte() {
		List<DetaliiProiecte> detaliiProiect = em.createQuery("SELECT dp FROM DetaliiProiecte dp", DetaliiProiecte.class)
				.getResultList();
		return detaliiProiect;
	}

	//delete
	@Override
	public String removeDetaliiProiecte(DetaliiProiecte detaliiProiecteToDelete) {
		detaliiProiecteToDelete = em.merge(detaliiProiecteToDelete);
		em.remove(detaliiProiecteToDelete);
		em.flush();
		return "True";
	}
//custom read
	@Override
	public DetaliiProiecte getDetaliiProiecteByName(String detaliiProiecteName) {
		// TODO Auto-generated method stub
				return em.createQuery("Select dp from DetaliiProiecte dp where dp.detaliiProiecteName = :detaliiProiecteName", DetaliiProiecte.class).setParameter("detaliiProiecteName", detaliiProiecteName).getSingleResult();
		}

//other
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return "SuportDataServiceEJB is ON... ";
	}

}
