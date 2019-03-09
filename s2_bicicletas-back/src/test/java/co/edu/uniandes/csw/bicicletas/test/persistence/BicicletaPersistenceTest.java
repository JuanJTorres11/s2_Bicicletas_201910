/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bicicletas.test.persistence;

import co.edu.uniandes.csw.bicicletas.entities.BicicletaEntity;
import co.edu.uniandes.csw.bicicletas.persistence.BicicletaPersistence;
import co.edu.uniandes.csw.bicicletas.persistence.ResenaPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import junit.framework.Assert;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Before;
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
    private BicicletaPersistence persistence;

    @PersistenceContext
    private EntityManager em;

    @Inject
    UserTransaction utx;

    private List<BicicletaEntity> data = new ArrayList<>();

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(BicicletaEntity.class.getPackage())
                .addPackage(BicicletaPersistence.class.getPackage())
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
     * Limpia las tablas que están implicadas en la prueba.
     *
     *
     */
    private void clearData() {
        em.createQuery("delete from BicicletaEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     *
     *
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {

            BicicletaEntity entity = factory.manufacturePojo(BicicletaEntity.class);

            em.persist(entity);

            data.add(entity);
        }
    }

    @Test
    public void createTest() {
        PodamFactory factory = new PodamFactoryImpl();
        BicicletaEntity newEntity = factory.manufacturePojo(BicicletaEntity.class);
        BicicletaEntity ee = persistence.create(newEntity);

        Assert.assertNotNull(ee);
        BicicletaEntity entity = em.find(BicicletaEntity.class, ee.getId());

        Assert.assertEquals(newEntity.getReferencia(), entity.getReferencia());
    }

    @Test
    public void deleteTest() {

        BicicletaEntity eliminar = data.get(0);
        persistence.delete(eliminar.getId());
        BicicletaEntity eliminado = persistence.find(eliminar.getId());
        Assert.assertNull(eliminado);

    }

    @Test
    public void updateTest() {

        PodamFactory factory = new PodamFactoryImpl();

        Long idActualizar = data.get(0).getId();
        BicicletaEntity nuevaB = factory.manufacturePojo(BicicletaEntity.class);
        nuevaB.setId(idActualizar);
        persistence.update(nuevaB);

        BicicletaEntity recuperada = persistence.find(idActualizar);

        Assert.assertEquals(nuevaB.getPrecio(), recuperada.getPrecio());
        Assert.assertEquals(nuevaB.getReferencia(), recuperada.getReferencia());

    }

    @Test
    public void findByReferenciaTest() {

        String refBuscada = data.get(0).getReferencia();

        BicicletaEntity encontrado = persistence.findByReferencia(refBuscada);
        Assert.assertNotNull(encontrado);
        Assert.assertEquals(refBuscada, encontrado.getReferencia());

    }

}
