package org.app.service.ejb.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.EJB;

import org.app.service.ejb.ClientiDataService;
import org.app.service.ejb.ClientiDataServiceEJB;
import org.app.service.entities.Clienti;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

@RunWith(Arquillian.class) 
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestClientiDataServiceEJBArq {
	private static Logger logger = 
			Logger.getLogger(TestClientiDataServiceEJBArq.class.getName());
	
	@EJB
	private static ClientiDataService service;
	
	@Deployment
	public static Archive<?> createDeployment(){
		 return ShrinkWrap
                .create(WebArchive.class, "msd-test.war")
                .addPackage(Clienti.class.getPackage())
                .addClass(ClientiDataService.class)
                .addClass(ClientiDataServiceEJB.class)
                .addAsResource("META-INF/persistence.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	}
	
	@Test
	public void test1_GetMessage() {
		logger.info("Debug: Junit Testing:getMessage...");
		String response = service.getMessage();
		assertNotNull("Data Service failed!", response);
		logger.info("DEBUG: EJB Response ..." + response);
		
	}
	
	@Test
	public void test2_DeleteClient() {
		logger.info("DEBUG: Junit TESTING: testDeleteClient ...");
		Collection<Clienti> client = service.getClient();
		for (Clienti c: client)
			service.removeClient(c);
		Collection<Clienti> clientAfterDelete = service.getClient();
		assertTrue("Fail to read client!", clientAfterDelete.size() == 0);
		
	}
	@Test
	public void test3_AddClient() {
		Integer clientToAdd = 4;
		for (int i=1; i <= clientToAdd; i++){
			//service.addFeature(new Feature(100 + i, "Feature_" + (100 + i)));
			service.addClient(new Clienti(null, "test", null, "Strada Paci", "Numar Telefon", "test@gmail"));
		}
		Collection<Clienti> client = service.getClient();
		assertTrue("Fail to add client!", client.size() ==clientToAdd);
	}
	
	@Test
	public void test4_GetClient() {
		logger.info("DEBUG: Junit TESTING: testGetClient ...");
		Collection<Clienti> client = service.getClient();
		assertTrue("Fail to read client!", client.size() > 0);
	}
}
