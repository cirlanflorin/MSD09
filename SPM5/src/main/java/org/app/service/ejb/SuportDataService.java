package org.app.service.ejb;

import java.util.Collection;

import javax.ejb.Remote;

import org.app.service.entities.Suport;


@Remote
public interface SuportDataService {

		//create/update
		Suport addSuport(Suport suportAdd);
		
		//read
		Suport getSuportByIdSuport(Integer IdSuport);
		Collection<Suport> getSuport();
		
		//delete
		String removeSuport(Suport suportToDelete);
		
		//custom read/query
		Suport getSuportByName(String SuportName);
		
		//others
		String getMessage();
}
