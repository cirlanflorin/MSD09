package org.app.service.ejb.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.logging.Logger;

import javax.ejb.EJB;

import org.app.service.ejb.ClientiDataService;
import org.app.service.ejb.ClientiDataServiceEJB;
import org.app.service.ejb.SuportDataService;
import org.app.service.ejb.SuportDataServiceEJB;
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
public class TestSuportDataServiceEJBArq {

	private static Logger logger = 
			Logger.getLogger(TestSuportDataServiceEJBArq.class.getName());
	
	@EJB
	private static SuportDataService service;
	
	@Deployment
	public static Archive<?> createDeployment(){
		 return ShrinkWrap
                .create(WebArchive.class, "msd-test.war")
                .addPackage(Suport.class.getPackage())
                .addClass(SuportDataService.class)
                .addClass(SuportDataServiceEJB.class)
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
	public void test2_DeleteSuport() {
		logger.info("DEBUG: Junit TESTING: testDeleteClient ...");
		Collection<Suport> suport= service.getSuport();
		for (Suport s: suport)
			service.removeSuport(s);
		Collection<Suport> suportAfterDelete = service.getSuport();
		assertTrue("Fail to read suport!", suportAfterDelete.size() == 0);
		
	}
	@Test
	public void test3_AddSuport() {
		Integer suportToAdd = 2;
		for (int i=1; i <= suportToAdd; i++){
			//service.addFeature(new Feature(100 + i, "Feature_" + (100 + i)));
			service.addSuport(new Suport(null, "Modificare meniu", null, null, "Modificare complexa", null, null, null));
		}
		Collection<Suport> suport = service.getSuport();
		assertTrue("Fail to add suport!", suport.size() == suportToAdd);
	}
	
	@Test
	public void test4_GetSuport() {
		logger.info("DEBUG: Junit TESTING: testGetSuport ...");
		Collection<Suport> suport = service.getSuport();
		assertTrue("Fail to read client!", suport.size() > 0);
	}
}
