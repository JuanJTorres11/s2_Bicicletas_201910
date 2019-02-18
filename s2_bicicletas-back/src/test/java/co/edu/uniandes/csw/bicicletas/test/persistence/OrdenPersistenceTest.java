/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bicicletas.test.persistence;

import co.edu.uniandes.csw.bicicletas.entities.OrdenEntity;
import co.edu.uniandes.csw.bicicletas.persistence.OrdenPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Before;
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

    private List<OrdenEntity> data;

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(OrdenEntity.class.getPackage())
                .addPackage(OrdenPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    @Before
    public void insertData() {

        data = new ArrayList<OrdenEntity>();
        PodamFactory factory = new PodamFactoryImpl();
        OrdenEntity en1 = factory.manufacturePojo(OrdenEntity.class);
        data.add(en1);
        OrdenEntity en2 = factory.manufacturePojo(OrdenEntity.class);
        data.add(en2);
        OrdenEntity en3 = factory.manufacturePojo(OrdenEntity.class);
        data.add(en3);

    }

    @Test
    public void createTest() {

        PodamFactory factory = new PodamFactoryImpl();
        OrdenEntity newEntity = factory.manufacturePojo(OrdenEntity.class);
        OrdenEntity result = ordenPersistence.create(newEntity);

        Assert.assertNotNull(result);
    }

    public void findAllTest() {
        List<OrdenEntity> list = ordenPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());

        for (OrdenEntity ent : list) {
            boolean found = false;
            for (OrdenEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    public void findTest() {
        OrdenEntity entity = data.get(0);
        OrdenEntity newEntity = ordenPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        
    }
}
