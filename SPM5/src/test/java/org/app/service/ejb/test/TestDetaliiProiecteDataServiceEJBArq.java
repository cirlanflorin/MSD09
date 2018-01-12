package org.app.service.ejb.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.logging.Logger;

import javax.ejb.EJB;

import org.app.service.ejb.DetaliiProiecteDataService;
import org.app.service.ejb.DetaliiProiecteDataServiceEJB;
import org.app.service.ejb.ProiecteService;
import org.app.service.ejb.ProiecteServiceEJB;
import org.app.service.ejb.SuportDataService;
import org.app.service.ejb.SuportDataServiceEJB;
import org.app.service.entities.DetaliiProiecte;
import org.app.service.entities.Proiecte;
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
public class TestDetaliiProiecteDataServiceEJBArq {
	private static Logger logger = Logger.getLogger(TestDetaliiProiecteDataServiceEJBArq.class.getName());

	@EJB
	private static DetaliiProiecteDataService service;
	
	@Deployment
	public static Archive<?> createDeployment(){
		 return ShrinkWrap
                .create(WebArchive.class)
                .addPackage(DetaliiProiecte.class.getPackage())
                .addClass(DetaliiProiecteDataService.class)
                .addClass(DetaliiProiecteDataServiceEJB.class)
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
	/*
	@Test
	public void test2_DeleteDetaliiProiecte() {
		logger.info("DEBUG: Junit TESTING: testDeleteDetaliiProiect ...");
		Collection<DetaliiProiecte> detaliiProiecte= service.getDetaliiProiecte();
		for (DetaliiProiecte dp: detaliiProiecte)
		{
			System.out.println("------------------------------------------------------------------");
			System.out.println(dp.getIdProiect());
			service.removeDetaliiProiecte(dp);
		}
		Collection<DetaliiProiecte> detaliiProiecteAfterDelete = service.getDetaliiProiecte();
		assertTrue("Fail to read detaliiProiecte!", detaliiProiecteAfterDelete.size() == 0);
		
	}
	*/
	
	//@Test
	public void test3_GetDetaliiProiect() {
		logger.info("DEBUG: Junit TESTING: testGetProiecte ...");
		Collection<DetaliiProiecte> detaliiProiecte = service.getDetaliiProiecte();
		assertTrue("Fail to read detaliiProiecte!", detaliiProiecte.size() > 0);
	}
	
	//@Test
	public void test4_AddProiecte() {
		Integer detaliiProiecteToAdd = 2;
		for (int i=1; i <= detaliiProiecteToAdd; i++){
			//service.addFeature(new Feature(100 + i, "Feature_" + (100 + i)));
			service.addDetaliiProiecte(new DetaliiProiecte(null, "Observatie", null, "ore", null,null));
		}
		Collection<DetaliiProiecte> detaliiProiecte = service.getDetaliiProiecte();
		assertTrue("Fail to add detaliiProiecte!", detaliiProiecte.size() == detaliiProiecteToAdd);
	}
	
	
}
