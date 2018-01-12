package org.app.service.ejb;

import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

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
import org.app.service.entities.Personal;
import org.app.service.entities.Suport;

@Path("suports")
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
	@PUT @Path("/{Id}") 
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@Override
	public Suport addSuport(Suport suportAdd) {
		if(suportAdd.getIdSuport() == null || this.getSuportByIdSuport(suportAdd.getIdSuport()) == null)
		{
			em.persist(suportAdd);
			em.flush();
			em.refresh(suportAdd);
		}
		return null;
	}
//read
	@GET @Path("/{id}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Override
	public Suport getSuportByIdSuport(@PathParam("id")Integer IdSuport) {
		logger.info("**** DEBUG REST getSuportByIdSuport(): IdSuport = " + IdSuport);
		return em.find(Suport.class, IdSuport);
	}

	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Override
	public Collection<Suport> getSuport() {
		List<Suport> suport = em.createQuery("SELECT s FROM Suport s", Suport.class)
				.getResultList();
		logger.info("**** DEBUG REST suport.size()= " + suport.size());
		return suport;
	}

	//delete
	@DELETE		
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@Override
	public Collection<Suport> removeSuport(Suport suportToDelete) {
		suportToDelete = em.merge(suportToDelete);
		em.remove(suportToDelete);
		em.flush();
		return this.getSuport();
	}
	
	@DELETE @Path("/{Id}")
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@Override
	public String remove(@PathParam("Id")Integer Id) throws Exception {
		Suport suport = this.getSuportByIdSuport(Id);
		em.remove(suport);
		return "True";
	}
	
	//custom read
	@GET @Path("/{name}")		
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Override
	public Suport getSuportByName(@PathParam("name")String SuportName) {
		return em.createQuery("SELECT s FROM Suport s WHERE s.SuportName = :SuportName", Suport.class)
				.setParameter("SuportName", SuportName)
				.getSingleResult();
	}
	
	@GET @Path("/{TEST}")
	@Produces({ MediaType.TEXT_PLAIN })
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return "SuportDataServiceEJB is ON... ";
	}

}
