package org.app.service.ejb;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.app.service.entities.Clienti;
import org.app.service.entities.Suport;

@Stateless @LocalBean
public abstract class SuportClientiDataServiceEJB implements SuportClientiDataService {

	private static Logger logger = Logger.getLogger(SuportClientiDataServiceEJB.class.getName());

	@PersistenceContext(unitName="MSD")
	private EntityManager em;
	
	@EJB
	private Suport suportDataService;
	
	
	@PostConstruct
	public void init() {
		logger.info("POSTCONSTRUCT-INIT injected EntityManager: " + this.em);
	}
	
	@Override
	public Clienti createNewClienti(Integer id) {
		Clienti clienti = new Clienti(id, "Test1", null, "Str Lalele", "Telefon","test1@gmail");
		List<Suport> suportClient = new ArrayList<>();
		int numar=3;
		for(int i=1; i<=numar; i++) {
			suportClient.add(new Suport(null,"Interventie", new Date(), 23.4, "OBS", null, null, null));
		}
		clienti.setSuport(suportClient);
		em.persist(clienti);
		em.flush();
		em.refresh(clienti);
		
		return clienti;
	}
	

	public Suport getSuportById(Integer IdSuport) {
		// TODO Auto-generated method stub
		return em.find(Suport.class, IdSuport);
		
	}
	
	public String getMessage() {
		// TODO Auto-generated method stub
		return "SuportClienti is working.";
	}

}
