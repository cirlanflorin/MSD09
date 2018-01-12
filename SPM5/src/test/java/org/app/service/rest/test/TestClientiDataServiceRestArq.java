package org.app.service.rest.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.logging.Logger;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import org.app.service.ejb.ClientiDataService;
import org.app.service.entities.Clienti;
import org.app.service.rest.ApplicationConfig;
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
public class TestClientiDataServiceRestArq {
	private static Logger logger = Logger.getLogger(TestClientiDataServiceRestArq.class.getName());
	
	private static String serviceURL = "http://localhost:8080/SPM5/data/clientic";
	
	@Deployment
	public static Archive<?> createDeployment(){
		 return ShrinkWrap
                .create(WebArchive.class, "SPM5.war")
                .addPackage(Clienti.class.getPackage())
                .addPackage(ClientiDataService.class.getPackage())
                .addPackage(ApplicationConfig.class.getPackage())
                .addAsResource("META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
	}
	

	@Test
	public void test1_GetMessage() {
		String resourceURL = serviceURL + "/test";
		logger.info("Debug: Junit Testing:getMessage...");
		String response = ClientBuilder.newClient().target(resourceURL).request().get().readEntity(String.class);
		assertNotNull("Data Service failed!", response);
		logger.info("DEBUG: EJB Response ..." + response);
		
	}
	
	@Test
	public void test2_DeleteClient() {
		String resourceURL = serviceURL + "/";
		logger.info("DEBUG: Junit TESTING: test2_DeleteProject ...");
		Client client = ClientBuilder.newClient();
		Collection<Clienti> clienti = client.target(serviceURL)
				.request().get()
				.readEntity(new GenericType<Collection<Clienti>>(){});		
		
		for (Clienti c: clienti) {
			client.target(resourceURL + c.getIdClient()).request().delete();
		}
		
		Collection<Clienti> clientiAfterDelete = client.target(serviceURL)
				.request().get()
				.readEntity(new GenericType<Collection<Clienti>>(){});	
		assertTrue("Fail to read Projects!", clientiAfterDelete.size() == 0);
	}
	@Test
	public void test3_AddClient() throws Exception {
		logger.info("DEBUG: Junit TESTING: test3_AddProject ...");
		Client client = ClientBuilder.newClient();
		Collection<Clienti> clientiCol;
		Integer clientToAdd = 3;
		Clienti clientc;
		for (int i=1; i <= clientToAdd; i++){
			clientc = new Clienti(null, "test4", null, "Strada Lalelelor", "telefon", "lalea@gmail");
			clientiCol = client.target(serviceURL)
				.request().accept(MediaType.APPLICATION_JSON)
				.post(Entity.entity(clientc, MediaType.APPLICATION_JSON))
				.readEntity(new GenericType<Collection<Clienti>>(){});
			assertTrue("Fail to read Projects!", clientiCol.size() > 0);
		}
		clientiCol = client.target(serviceURL)
				.request().get()
				.readEntity(new GenericType<Collection<Clienti>>(){});
		assertTrue("Fail to add Clienti!", clientiCol.size() >= clientToAdd);
		clientiCol.stream().forEach(System.out::println);
	}
	
	
	@Test
	public void test4_GetClient() {
		logger.info("DEBUG: Junit TESTING: testGetClient ...");
		Collection<Clienti> clientiCol = ClientBuilder.newClient().target(serviceURL)
				.request().get()
				.readEntity(new GenericType<Collection<Clienti>>(){});
		assertTrue("Fail to read client!", clientiCol.size() > 0);
		clientiCol.stream().forEach(System.out::println);
	}
	
	@Test
	public void test5_getClientByID() {
		String resourceURL = serviceURL + "/1";
		logger.info("DEBUG: Junit TESTING: test1_GetMessage ...");
		
		Clienti clienti = ClientBuilder.newClient().target(resourceURL)
				.request().accept(MediaType.APPLICATION_JSON)
				.get().readEntity(Clienti.class);
		
		assertNotNull("REST Data Service failed!", clienti);
		logger.info(">>>>>> DEBUG: REST Response ..." + clienti);
	}	
	
	@Test
	public void test6_UpdateClienti() {
		String resourceURL = serviceURL + "/1";
		logger.info("************* DEBUG: Junit TESTING: test5_UpdateClienti ... :" + resourceURL);
		Client client = ClientBuilder.newClient();
		
		Clienti clientC = client.target(resourceURL)
				.request().accept(MediaType.APPLICATION_JSON)
				.get().readEntity(Clienti.class);
		
		assertNotNull("REST Data Service failed!", clientC);
		//logger.info(">>> Initial Project: " + clientC);
		
		// update and save project
		clientC.setDenSoc(clientC.getDenSoc() + "_UPD_JSON");
		clientC = client.target(resourceURL)
				.request().accept(MediaType.APPLICATION_XML).header("Content-Type", "application/xml")
				//.request().accept(MediaType.APPLICATION_JSON)
				.put(Entity.entity(clientC, MediaType.APPLICATION_JSON))
				.readEntity(Clienti.class);
		
		//logger.info(">>> Updated Project: " + clientC);
		
		assertNotNull("REST Data Service failed!", clientC);
	}	
	//@Test
	public void test7_CreateProject() {
		String resourceURL = serviceURL + "/new/"; //createNewProject
		logger.info("DEBUG: Junit TESTING: test3_CreateProject ...");
		Client client = ClientBuilder.newClient();
		
		Integer clientiToAdd = 3;
		Clienti clientC;
		for (int i=1; i <= clientiToAdd; i++){
			clientC = ClientBuilder.newClient().target(resourceURL + i)
					.request().accept(MediaType.APPLICATION_JSON)
					.post(null).readEntity(Clienti.class);
			System.out.println(">>> Created/posted Project: " + clientC);
		}

		Collection<Clienti> clienticol = client.target(serviceURL)
				.request().get()
				.readEntity(new GenericType<Collection<Clienti>>(){});
		
		assertTrue("Fail to add Projects!", clienticol.size() >= clientiToAdd);
	}	

}
