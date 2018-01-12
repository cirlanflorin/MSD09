package org.app.service.ejb;

import java.util.Collection;

import javax.ejb.Remote;

import org.app.service.entities.Clienti;
import org.app.service.entities.Proiecte;

@Remote
public interface ProiecteService {
	//create/update
	Proiecte addProiecte(Proiecte proiecteAdd);
	
	//delete
	Collection<Proiecte> removeProiecte(Proiecte proiecteToDelete);
	String remove(Integer Id) throws Exception;
	
	//read
	Proiecte getProiecteByIdProiect(Integer IdProiecte);
	Collection<Proiecte> getProiecte();
	
	//custom read/query
	Proiecte getProiecteByName(String ProiecteName);
	
	//others
	String getMessage();
}
