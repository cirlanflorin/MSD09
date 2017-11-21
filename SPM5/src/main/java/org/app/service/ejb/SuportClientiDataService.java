package org.app.service.ejb;

import org.app.patterns.EntityRepository;
import org.app.service.entities.Clienti;
import org.app.service.entities.Suport;

public interface SuportClientiDataService {
	Clienti createNewClienti(Integer id);
	Suport getSuportById(Integer IdSuport);
	String getMessage();

}
