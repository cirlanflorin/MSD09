package org.app.service.ejb.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.logging.Logger;

import javax.ejb.EJB;

import org.app.service.ejb.PersonalDataService;
import org.app.service.ejb.PersonalDataServiceEJB;
import org.app.service.entities.Clienti;
import org.app.service.entities.Personal;
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
public class TestPersonalDataServiceEJBArq {
	private static Logger logger = Logger.getLogger(TestPersonalDataServiceEJBArq.class.getName());
	
	@EJB
	private static PersonalDataService service;
	
	@Deployment
	public static Archive<?> createDeployment(){
		 return ShrinkWrap
                .create(WebArchive.class, "msd-test.war")
                .addPackage(Personal.class.getPackage())
                .addClass(PersonalDataService.class)
                .addClass(PersonalDataServiceEJB.class)
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
	public void test2_DeletePersonal() {
		logger.info("DEBUG: Junit TESTING: testDeleteClient ...");
		Collection<Personal> personal = service.getPersonal();
		for (Personal p: personal)
			service.removePersonal(p);
		Collection<Personal> personalAfterDelete = service.getPersonal();
		assertTrue("Fail to read client!", personalAfterDelete.size() == 0);
		
	}
	
	@Test
	public void test3_AddPersonal() {
		Integer personalToAdd = 4;
		for (int i=1; i <= personalToAdd; i++){
			//service.addFeature(new Feature(100 + i, "Feature_" + (1200 + i)));
			service.addPersonal(new Personal(null, "Ionescu", "Vasile", null, "Tehnician", "Numar", "vasile@gmail"));
		}
		Collection<Personal> persona = service.getPersonal();
		assertTrue("Fail to add persona!", persona.size() == personalToAdd);
	}
	
	@Test
	public void test4_GetPersonal() {
		logger.info("DEBUG: Junit TESTING: testGetPersonal ...");
		Collection<Personal> persona = service.getPersonal();
		assertTrue("Fail to read client!", persona.size() > 0);
	}
	

}
