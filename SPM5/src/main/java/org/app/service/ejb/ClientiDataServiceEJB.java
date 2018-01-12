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

@Path("clientic")
@Stateless @LocalBean
public class ClientiDataServiceEJB implements ClientiDataService{

	private static Logger logger = Logger.getLogger(ClientiDataServiceEJB.class.getName());
	
	@PersistenceContext(unitName ="MSD")
	private EntityManager em;

	//Constructor
	@PostConstruct
	public void init(){
		logger.info("POSTCONSTRUCT-INIT : " + this.em);
	}	
	
	/******** REST MAPPING IMPLEMENTATION ************************************************/
	//CRUD OPERTAION
	//CREATE OR Update
	
	@PUT @Path("/{Id}") 
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@Override
	public Clienti addClient(Clienti clientToAdd) throws Exception{
		if(clientToAdd.getIdClient() == null || this.getClientByID(clientToAdd.getIdClient()) == null)
		{
			em.persist(clientToAdd);
			em.flush();
			em.refresh(clientToAdd);
		}
		else
		{
			em.merge(clientToAdd);
		}
		return clientToAdd;
	}

	//remove
	@DELETE		
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@Override
	public Collection<Clienti> removeClient(Clienti clientToDelete) {
		clientToDelete = em.merge(clientToDelete);
		em.remove(clientToDelete);
		em.flush();
		return this.getClient();
	}

	//Read
	@GET @Path("/{Id}")		/* SCRUM/data/clienti		REST-resource: Clienti-entity */
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })	
	@Override
	public Clienti getClientByID(@PathParam("Id")Integer clientID) {
		logger.info("**** DEBUG REST getClientByID(): clientID = " + clientID);
		return em.find(Clienti.class, clientID);
	}

	@GET 					/* SCRUM/data/clienti		REST-resource: Clienti-collection */
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })	
	@Override
	public Collection<Clienti> getClient() {
		List<Clienti> client = em.createQuery("Select c FROM Clienti c", Clienti.class).getResultList();
		logger.info("**** DEBUG REST features.size()= " + client.size());
		return client;
	}

	//custom read
	//@GET @Path("/{name}")		/* SCRUM/data/features 		REST-resource: Feature-entity */
	//@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })		
	@Override
	public Clienti getClientByName(@PathParam("name")String clientName) {
		// TODO Auto-generated method stub
		return em.createQuery("Select c FROM Clienti c where c.clientName = :clientName", Clienti.class).setParameter("clientName", clientName).getSingleResult();
		
	}

	//others
	//@GET @Path("/{TEST}")
	//@Produces({ MediaType.TEXT_PLAIN })
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return "ClientiDataServiceEJB is on..";
	}

	@DELETE @Path("/{Id}")
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@Override
	public String remove(@PathParam("Id")Integer Id) throws Exception {
		Clienti client = this.getClientByID(Id);
		em.remove(client);
		return "True";
	}

}
