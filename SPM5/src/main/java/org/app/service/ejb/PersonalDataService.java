package org.app.service.ejb;

import java.util.Collection;

import javax.ejb.Remote;

import org.app.service.entities.Personal;

@Remote
public interface PersonalDataService {
	//create/update
	Personal addPersonal(Personal personalAdd);
	
	//read
	Personal getPersonalByIdPersonal(Integer IdPersonal);
	Collection<Personal> getPersonal();
	
	//delete
	Collection<Personal> removePersonal(Personal personalToDelete);
	String remove(Integer Id) throws Exception;
	
	//custom read/query
	Personal getPersonalByName(String PersonalName);
	
	//others
	String getMessage();
	
	

}
