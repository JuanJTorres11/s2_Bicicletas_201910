/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bicicletas.test.persistence;

import co.edu.uniandes.csw.bicicletas.entities.MarcaEntity;
import co.edu.uniandes.csw.bicicletas.persistence.MarcaPersistence;
import co.edu.uniandes.csw.bicicletas.persistence.MarcaPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
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
public class MarcaPersistenceTest {

    @Inject
    private MarcaPersistence marcaPersistence;


    @PersistenceContext
    private EntityManager em;

    @Inject
    UserTransaction utx;

    private List<MarcaEntity> data = new ArrayList<MarcaEntity>();

    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(MarcaEntity.class.getPackage())
                .addPackage(MarcaPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }


    /**
     * Configuración inicial de la prueba.
     */
    @Before
    public void configTest() {
        try {
            utx.begin();
            em.joinTransaction();
            clearData();
            insertData();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }
    
    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();

        for (int i = 0; i < 3; i++) {
            MarcaEntity entity = factory.manufacturePojo(MarcaEntity.class);
            entity.setNombre("marca"+i);
            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * Limpia las tablas que están implicadas en la prueba.
     */
    private void clearData() {
        em.createQuery("delete from MarcaEntity").executeUpdate();
    }
    
    /**
     * Prueba para crear un Marca.
     */
    @Test
    public void createMarcaTest() {
        PodamFactory factory = new PodamFactoryImpl();
        MarcaEntity newEntity = factory.manufacturePojo(MarcaEntity.class);

        MarcaEntity result = marcaPersistence.create(newEntity);

        Assert.assertNotNull(result);

        MarcaEntity entity = em.find(MarcaEntity.class, result.getId());

        Assert.assertEquals(newEntity.getId(), entity.getId());
    }

    /**
     * Prueba para consultar la lista de premios.
     */
    @Test
    public void getMarcasTest() {
        List<MarcaEntity> list = marcaPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (MarcaEntity ent : list) {
            boolean found = false;
            for (MarcaEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un Marca.
     */
    @Test
    public void getMarcaTest() {
        MarcaEntity entity = data.get(0);
        MarcaEntity newEntity = marcaPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getId(), newEntity.getId());
    }

    /**
     * Prueba para eliminar un Marca.
     */
    @Test
    public void deleteMarcaTest() {
        MarcaEntity entity = data.get(0);
        marcaPersistence.delete(entity.getId());
        MarcaEntity deleted = em.find(MarcaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }


    /**
     * Prueba para actualizar un Marca.
     */
    @Test
    public void updateMarcaTest() {
        MarcaEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        MarcaEntity newEntity = factory.manufacturePojo(MarcaEntity.class);

        newEntity.setId(entity.getId());

        marcaPersistence.update(newEntity);

        MarcaEntity resp = em.find(MarcaEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getId(), resp.getId());
    }
    
    /**
     * Prueba para consultar una Marca por nombre.
     */
    @Test
    public void findMarcaByNameTest() {
        MarcaEntity entity = data.get(0);
        MarcaEntity newEntity = marcaPersistence.findByName(entity.getNombre());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getNombre(), newEntity.getNombre());

        newEntity = marcaPersistence.findByName(null);
        Assert.assertNull(newEntity);
}

}

    