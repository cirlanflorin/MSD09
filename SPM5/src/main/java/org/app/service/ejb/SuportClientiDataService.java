package org.app.service.ejb;

import javax.ejb.Remote;

import org.app.patterns.EntityRepository;
import org.app.service.entities.Clienti;
import org.app.service.entities.Suport;

@Remote
public interface SuportClientiDataService {
	Clienti createNewClienti(Integer id);
	Suport getSuportById(Integer IdSuport);
	String getMessage();

}
