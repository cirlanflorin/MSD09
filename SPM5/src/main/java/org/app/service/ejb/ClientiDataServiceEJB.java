package org.app.service.ejb;

import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.app.service.entities.Clienti;

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
	
	//CRUD OPERTAION
	//CREATE OR Update
	
	@Override
	public Clienti addClient(Clienti clientToAdd) {
		em.persist(clientToAdd);
		em.flush();
		em.refresh(clientToAdd);
		return clientToAdd;
	}

	//remove
	@Override
	public String removeClient(Clienti clientToDelete) {
		clientToDelete = em.merge(clientToDelete);
		em.remove(clientToDelete);
		em.flush();
		return "True";
	}

	//Read
	@Override
	public Clienti getClientByID(Integer clientID) {
		// TODO Auto-generated method stub
		return em.find(Clienti.class, clientID);
	}

	@Override
	public Collection<Clienti> getClient() {
		List<Clienti> client = em.createQuery("Select c FROM Clienti c", Clienti.class).getResultList();
		return client;
	}

	//custom read
	@Override
	public Clienti getClientByName(String clientName) {
		// TODO Auto-generated method stub
		return em.createQuery("Select c FROM Clienti c where c.clientName = :clientName", Clienti.class).setParameter("clientName", clientName).getSingleResult();
		
	}

	//others
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return "ClientiDataServiceEJB is on..";
	}

}
