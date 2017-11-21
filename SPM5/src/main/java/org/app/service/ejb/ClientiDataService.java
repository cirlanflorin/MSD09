package org.app.service.ejb;

import java.util.Collection;

import javax.ejb.Remote;

import org.app.service.entities.Clienti;

@Remote
public interface ClientiDataService {

	//Create or Update
	
	Clienti addClient(Clienti clientToAdd);
	
	//Delete
	String removeClient(Clienti clientToDelete);
	
	//Read
	Clienti getClientByID(Integer clientID);
	Collection<Clienti> getClient();
	
	//Custom read:custom query
	Clienti getClientByName(String clientName);
	
	//Others
	String getMessage();
	
	
}
