/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bicicletas.test.persistence;

import co.edu.uniandes.csw.bicicletas.entities.BicicletaEntity;
import co.edu.uniandes.csw.bicicletas.persistence.BicicletaPersistence;
import co.edu.uniandes.csw.bicicletas.persistence.ResenaPersistence;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import junit.framework.Assert;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author Andrea
 */
@RunWith(Arquillian.class)
public class BicicletaPersistenceTest {
    
    
     @Inject
    private BicicletaPersistence cp;

    @PersistenceContext
    private EntityManager em;
    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(BicicletaEntity.class.getPackage())
                .addPackage(BicicletaPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    @Test
    public void createTest() {
        PodamFactory factory = new PodamFactoryImpl();
        BicicletaEntity newEntity = factory.manufacturePojo(BicicletaEntity.class);
        BicicletaEntity ee = cp.create(newEntity);

        Assert.assertNotNull(ee);
        BicicletaEntity entity = em.find(BicicletaEntity.class, ee.getId());

        Assert.assertEquals(newEntity.getReferencia(), entity.getReferencia());
    }
    
}
