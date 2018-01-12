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

import org.app.service.entities.Personal;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

@RunWith(Arquillian.class) 
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestPersonalDataServiceRESTArqRemote {
	
	private static Logger logger = Logger.getLogger(TestPersonalDataServiceRESTArqRemote.class.getName());
	
	private static String serviceURL = "http://localhost:8080/SPM5/data/personalp";
	
	@Deployment 
	public static Archive<?> createDeployment() {
	        return ShrinkWrap
	                .create(WebArchive.class, "SPM5.war")
	                .addPackage(Personal.class.getPackage()); // all mode by default
	}	
	
	@Test
	public void test1_GetMessage() {
		String resourceURL = serviceURL + "/test";
		logger.info("DEBUG: Junit TESTING: test1_GetMessage ...");
		String response = ClientBuilder.newClient().target(resourceURL)
				.request().get()
				.readEntity(String.class);
		assertNotNull("Data Service failed!", response);
		logger.info("DEBUG: EJB Response ..." + response);
	}

	@Test
	public void test4_GetPersonal() {
		logger.info("DEBUG: Junit TESTING: test4_GetProjects ...");
		Collection<Personal> personal = ClientBuilder.newClient()
				.target(serviceURL)
				.request().get()
				.readEntity(new GenericType<Collection<Personal>>(){});
		assertTrue("Fail to read Projects!", personal.size() > 0);
		personal.stream().forEach(System.out::println);
	}

	//@Test
	public void test3_AddPersonal() {
		// addIntoCollection
		logger.info("DEBUG: Junit TESTING: test3_AddProject ...");
		Client client = ClientBuilder.newClient();
		Collection<Personal> personalcol;
		Integer personalToAdd = 3;
		Personal personal;
		for (int i=1; i <= personalToAdd; i++){
			personal = new Personal(null, "Ionescu", "Vasile", null, "Tehnician", "Numar", "vasile@gmail");
			personalcol = client.target(serviceURL)
				.request().accept(MediaType.APPLICATION_JSON)
				.post(Entity.entity(personal, MediaType.APPLICATION_JSON))
				.readEntity(new GenericType<Collection<Personal>>(){});
			assertTrue("Fail to read Projects!", personalcol.size() > 0);
		}
		personalcol = client.target(serviceURL)
				.request().get()
				.readEntity(new GenericType<Collection<Personal>>(){});
		assertTrue("Fail to add Projects!", personalcol.size() >= personalToAdd);
		personalcol.stream().forEach(System.out::println);
	}

	//@Test
	public void test3_CreatePersonal() {
		String resourceURL = serviceURL + "/new/"; //createNewProject
		logger.info("DEBUG: Junit TESTING: test3_CreateProject ...");
		Client client = ClientBuilder.newClient();
		
		Integer personalToAdd = 3;
		Personal personal;
		for (int i=1; i <= personalToAdd; i++){
			personal = ClientBuilder.newClient().target(resourceURL + i)
					.request().accept(MediaType.APPLICATION_JSON)
					.post(null).readEntity(Personal.class);
			System.out.println(">>> Created/posted Project: " + personal);
		}

		Collection<Personal> personalcol = client.target(serviceURL)
				.request().get()
				.readEntity(new GenericType<Collection<Personal>>(){});
		
		assertTrue("Fail to add Projects!", personalcol.size() >= personalToAdd);
	}	
	
	//@Test
	public void test2_DeletePersonal() {
		String resourceURL = serviceURL + "/";
		logger.info("DEBUG: Junit TESTING: test2_DeleteProject ...");
		Client client = ClientBuilder.newClient();
		Collection<Personal> personal = client.target(serviceURL)
				.request().get()
				.readEntity(new GenericType<Collection<Personal>>(){});		
		
		for (Personal p: personal) {
			client.target(resourceURL + p.getIdTrainer()).request().delete();
		}
		
		Collection<Personal> personalAfterDelete = client.target(serviceURL)
				.request().get()
				.readEntity(new GenericType<Collection<Personal>>(){});	
		assertTrue("Fail to read Projects!", personalAfterDelete.size() == 0);
	}
	
	@Test
	public void test1_GetByID() {
		String resourceURL = serviceURL + "/1";
		logger.info("DEBUG: Junit TESTING: test1_GetMessage ...");
		
		Personal personal = ClientBuilder.newClient().target(resourceURL)
				.request().accept(MediaType.APPLICATION_JSON)
				.get().readEntity(Personal.class);
		
		assertNotNull("REST Data Service failed!", personal);
		logger.info(">>>>>> DEBUG: REST Response ..." + personal);
	}	
	
	//@Test
	public void test5_UpdatePersonal() {
		String resourceURL = serviceURL + "/1"; //createNewProject
		logger.info("************* DEBUG: Junit TESTING: test5_UpdateProject ... :" + resourceURL);
		Client client = ClientBuilder.newClient();
		// Get project
		Personal personal = client.target(resourceURL)
				.request().accept(MediaType.APPLICATION_JSON)
				.get().readEntity(Personal.class);
		
		assertNotNull("REST Data Service failed!", personal);
		logger.info(">>> Initial Project: " + personal);
		
		// update and save project
		personal.setNume(personal.getNume() + "_UPD_JSON_REMOTE");
		personal = client.target(resourceURL)
				//.request().accept(MediaType.APPLICATION_XML).header("Content-Type", "application/xml")
				.request().accept(MediaType.APPLICATION_JSON)
				.put(Entity.entity(personal, MediaType.APPLICATION_JSON))
				.readEntity(Personal.class);
		
		logger.info(">>> Updated Project: " + personal);
		
		assertNotNull("REST Data Service failed!", personal);
	}	
	


}
