/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bicicletas.test.persistence;

import co.edu.uniandes.csw.bicicletas.entities.OrdenEntity;
import co.edu.uniandes.csw.bicicletas.persistence.OrdenPersistence;
import javax.inject.Inject;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author Mateo
 */

@RunWith(Arquillian.class)
public class OrdenPersistenceTest {
    
    @Inject
    private OrdenPersistence ordenPersistence;
    
    @Deployment
    public static JavaArchive createDeployment(){
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(OrdenEntity.class.getPackage())
                .addPackage(OrdenPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
     @Test
    public void createTest(){
        
        PodamFactory factory = new PodamFactoryImpl();
        OrdenEntity newEntity = factory.manufacturePojo(OrdenEntity.class);
        OrdenEntity result = ordenPersistence.create(newEntity);
        
        Assert.assertNotNull(result);
    }
    
}
