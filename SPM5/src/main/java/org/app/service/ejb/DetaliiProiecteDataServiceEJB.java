package org.app.service.ejb;

import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
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

import org.app.service.entities.DetaliiProiecte;


@Path("detaliiproiectedp")
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

	@PUT @Path("/{id}") 	
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Override
	public DetaliiProiecte addDetaliiProiecte(DetaliiProiecte detaliiProiecteAdd) {
		em.persist(detaliiProiecteAdd);
		em.flush();
		em.refresh(detaliiProiecteAdd);
		return null;
	}

	//read
	@GET @Path("/{id}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Override
	public DetaliiProiecte getDetaliiProiecteByIdProiect(@PathParam("id")Integer IdProiect) {
		logger.info("**** DEBUG REST getClientByID(): clientID = " + IdProiect);
			return em.find(DetaliiProiecte.class, IdProiect);
	}
	
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Override
	public Collection<DetaliiProiecte> getDetaliiProiecte() {
		List<DetaliiProiecte> detaliiProiect = em.createQuery("SELECT dp FROM DetaliiProiecte dp", DetaliiProiecte.class)
				.getResultList();
		logger.info("**** DEBUG REST detaliiProiect.size()= " + detaliiProiect.size());
		return detaliiProiect;
	}

	//delete
	@DELETE 					
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Override
	public String removeDetaliiProiecte(DetaliiProiecte detaliiProiecteToDelete) {
		detaliiProiecteToDelete = em.merge(detaliiProiecteToDelete);
		em.remove(detaliiProiecteToDelete);
		em.flush();
		return "True";
	}
//custom read
	@GET @Path("/{detaliiProiecteName}")		
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Override
	public DetaliiProiecte getDetaliiProiecteByName(@PathParam("detaliiProiecteName")String detaliiProiecteName) {
		// TODO Auto-generated method stub
				return em.createQuery("Select dp from DetaliiProiecte dp where dp.detaliiProiecteName = :detaliiProiecteName", DetaliiProiecte.class).setParameter("detaliiProiecteName", detaliiProiecteName).getSingleResult();
		}

//other
	@GET @Path("/{TEST}")
	@Produces({ MediaType.TEXT_PLAIN })
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return "SuportDataServiceEJB is ON... ";
	}

}
