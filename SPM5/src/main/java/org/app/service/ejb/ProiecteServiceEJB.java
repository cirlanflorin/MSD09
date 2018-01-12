package org.app.service.ejb;

import javax.annotation.PostConstruct;
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
import org.app.service.entities.Proiecte;

import java.util.Collection;
import java.util.List;
//import org.jboss.resteasy.logging.Logger;
import java.util.logging.Logger;

@Path("proiectep")
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
	@PUT @Path("/{id}") 	/* SCRUM/data/features/{id} 	REST-resource: Feature-entity */	
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })	
	@Override
	public Proiecte addProiecte(Proiecte proiecteAdd) {
		if(proiecteAdd.getIdProiect() == null || this.getProiecteByIdProiect(proiecteAdd.getIdProiect()) == null)
		{
			em.persist(proiecteAdd);
			em.flush();
			em.refresh(proiecteAdd);
		}
		else
		{
			em.merge(proiecteAdd);
		}
		return proiecteAdd;
	}
	
	//remove

	@DELETE		
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@Override
	public Collection<Proiecte> removeProiecte(Proiecte proiecteToDelete) {
		// TODO Auto-generated method stub
		proiecteToDelete = em.merge(proiecteToDelete);
		em.remove(proiecteToDelete);
		em.flush();
		return this.getProiecte();
	}
	
	@DELETE @Path("/{Id}")
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@Override
	public String remove(@PathParam("Id")Integer Id) throws Exception {
		Proiecte proiecte = this.getProiecteByIdProiect(Id);
		em.remove(proiecte);
		return "True";
	}

	//read
	@GET @Path("/{id}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Override
	public Proiecte getProiecteByIdProiect(@PathParam("id")Integer IdProiecte) {
		logger.info("**** DEBUG REST getClientByID(): IdProiecte = " + IdProiecte);
		return em.find(Proiecte.class, IdProiecte);
	}
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Override
	public Collection<Proiecte> getProiecte() {
		// TODO Auto-generated method stub
		List<Proiecte>  proiecte= em.createQuery("Select p FROM Proiecte p", Proiecte.class).getResultList();
		logger.info("**** DEBUG REST proiecte.size()= " + proiecte.size());
		return proiecte;
	}

	//custom read
	@GET @Path("/{id}")		
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })	
	@Override
	public Proiecte getProiecteByName(@PathParam("id")String ProiecteName) {
		// TODO Auto-generated method stub
		return em.createQuery("Select p from Proiecte p where p.ProiecteName = :ProiecteName", Proiecte.class).setParameter("ProiecteName", ProiecteName).getSingleResult();
	}

	//others

	@GET @Path("/{TEST}")
	@Produces({ MediaType.TEXT_PLAIN })
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return "ProiecteServiceEJB IS ON..";
	}

}
