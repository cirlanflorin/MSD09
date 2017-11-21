package org.app.service.ejb.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.EJB;
import org.app.service.ejb.SuportClientiDataService;
import org.app.service.ejb.SuportClientiDataServiceEJB;
import org.app.service.entities.Clienti;
import org.app.service.entities.Suport;
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
public class TestSuportClientiDataServiceEJBAgg {
	private static Logger logger = 
			Logger.getLogger(TestSuportClientiDataServiceEJBAgg.class.getName());
	
	@EJB
	private static SuportClientiDataService service;
	
	@Deployment
	public static Archive<?> createDeployment(){
		 return ShrinkWrap
                .create(WebArchive.class, "msd-test.war")
                .addPackage(Clienti.class.getPackage())
                .addClass(SuportClientiDataService.class)
                .addClass(SuportClientiDataServiceEJB.class)
                .addAsResource("META-INF/persistence.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	}
	
	@Test
	public void test1_GetMessage() {
		logger.info("DEBUG: Junit TESTING: testGetMessage ...");
		String response = service.getMessage();
		assertNotNull("Data Service failed!", response);
		logger.info("DEBUG: EJB Response ..." + response);
	}	

	@Test
	public void test2_GetSuport() {
		logger.info("DEBUG: Junit TESTING: testGetProject 7002 ...");
		Suport suport = service.getSuportById(7002);
		assertNotNull("Fail to Get Project 7002!", suport);
	}
	/* CREATE Test 2: create aggregate*/
	@Test
	public void test3_CreateNewClienti(){
		Integer clientToAdd = 4;
		Clienti clienti = service.createNewClienti(null);
		for (int i=1; i <= clientToAdd; i++){
			//service.addFeature(new Feature(100 + i, "Feature_" + (100 + i)));
			clienti = service.createNewClienti(1002);
		}
		//Suport suport = service.getSuportById(1458);
		assertNotNull("Fail to save new project in repository!", clienti);

		logger.info("DEBUG createNewProject: project changed: " + clienti);
		
}
	/*@Test
	public void test2_DeleteSuport() {
		logger.info("DEBUG: Junit TESTING: testDeleteProject 7002...");
		Suport suport = service.getSuportById(7002);  // !!!
		if (suport != null)
			service.remove(suport);
		suport = service.getSuportById(7002);  // !!!
		assertNull("Fail to delete Project 7002!", suport);
	}	*/
	
}
