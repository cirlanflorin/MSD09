package org.app.service.ejb;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.app.service.entities.Clienti;
import org.app.service.entities.Suport;

@Path("suportclienti")
@Stateless @LocalBean
public class SuportClientiDataServiceEJB implements SuportClientiDataService {

	private static Logger logger = Logger.getLogger(SuportClientiDataServiceEJB.class.getName());

	@PersistenceContext(unitName="MSD")
	private EntityManager em;
	
	/*
	@EJB
	private Suport suportDataService;
	*/
	public SuportClientiDataServiceEJB() {
	
	}
	@PostConstruct
	public void init() {
		logger.info("POSTCONSTRUCT-INIT injected EntityManager: " + this.em);
	}
	
	@POST @Path("/new/{id}")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@Override
	public Clienti createNewClienti(@PathParam("id") Integer id) {
		Clienti clienti = new Clienti(null, "Test1", null, "Str Lalele", "Telefon","test1@gmail");
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
	
	@GET @Path("/{IdSuport}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Override
	public Suport getSuportById(@PathParam("IdSuport")Integer IdSuport) {
		// TODO Auto-generated method stub
		return em.find(Suport.class, IdSuport);
		
	}
	
	@GET @Path("/{TEST}")
	@Produces({ MediaType.TEXT_PLAIN })
	public String getMessage() {
		// TODO Auto-generated method stub
		return "SuportClienti is working.";
	}

}
