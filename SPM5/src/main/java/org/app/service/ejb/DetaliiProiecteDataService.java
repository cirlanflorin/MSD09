package org.app.service.ejb;

import java.util.Collection;

import javax.ejb.Remote;

import org.app.service.entities.DetaliiProiecte;


@Remote
public interface DetaliiProiecteDataService {

	    //create/update
		DetaliiProiecte addDetaliiProiecte(DetaliiProiecte detaliiProiecteAdd);
		
		//read
		DetaliiProiecte getDetaliiProiecteByIdProiect(Integer IdProiect);
		Collection<DetaliiProiecte> getDetaliiProiecte();
		
		
		//delete
		String removeDetaliiProiecte(DetaliiProiecte detaliiProiecteToDelete);
		//custom read/query
		DetaliiProiecte getDetaliiProiecteByName(String detaliiProiecteName);
		
		//others
		String getMessage();
}
