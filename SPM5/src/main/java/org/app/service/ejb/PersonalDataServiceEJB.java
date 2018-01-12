package org.app.service.ejb;

import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.app.service.entities.Clienti;
import org.app.service.entities.Personal;

@Path("personalp")
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
	@PUT @Path("/{personalAdd}") 	/* SCRUM/data/features/{id} 	REST-resource: Feature-entity */	
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@Override
	public Personal addPersonal(Personal personalAdd) {
		em.persist(personalAdd);
		em.flush();
		em.refresh(personalAdd);
		return null;
	}

	//read
	@GET @Path("/{IdPersonal}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Override
	public Personal getPersonalByIdPersonal(@PathParam("IdPersonal")Integer IdPersonal) {
		logger.info("Debug test getPersonalByIdPersonal(): " + IdPersonal);
		// TODO Auto-generated method stub
		return em.find(Personal.class, IdPersonal);
	}
	
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Override
	public Collection<Personal> getPersonal() {
		List<Personal> personal = em.createQuery("SELECT p FROM Personal p", Personal.class)
				.getResultList();
		logger.info("**** DEBUG REST personal.size()= " + personal.size());
		return personal;
	}
	
	//delete

	@DELETE		
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@Override
	public Collection<Personal> removePersonal(Personal personalToDelete) {
		personalToDelete = em.merge(personalToDelete);
		em.remove(personalToDelete);
		em.flush();
		return this.getPersonal();
	}
	

	@DELETE @Path("/{Id}")
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@Override
	public String remove(@PathParam("Id")Integer Id) throws Exception {
		Personal personal = this.getPersonalByIdPersonal(Id);
		em.remove(personal);
		return "True";
	}

	//custom read
	@GET @Path("/{PersonalName}")		
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })		
	@Override
	public Personal getPersonalByName(@PathParam("PersonalName")String PersonalName) {
		return em.createQuery("SELECT p FROM Personal p WHERE p.PersonalName = :PersonalName", Personal.class)
				.setParameter("PersonalName", PersonalName)
				.getSingleResult();
	}

	//others
	@GET @Path("/{TEST}")
	@Produces({ MediaType.TEXT_PLAIN })
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return "PersonalDataServiceEJB is ON... ";
	}

}
