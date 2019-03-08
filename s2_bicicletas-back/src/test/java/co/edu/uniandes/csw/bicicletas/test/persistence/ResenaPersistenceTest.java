/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bicicletas.test.persistence;

import co.edu.uniandes.csw.bicicletas.entities.BicicletaEntity;
import co.edu.uniandes.csw.bicicletas.entities.ResenaEntity;
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
public class ResenaPersistenceTest {

    @Inject
    private ResenaPersistence persistence;

    @PersistenceContext
    private EntityManager em;
    
    @Inject
    UserTransaction utx;

    private List<ResenaEntity> data = new ArrayList<>();
    private List<BicicletaEntity> dataBikes = new ArrayList<>();


    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ResenaEntity.class.getPackage())
                .addPackage(ResenaPersistence.class.getPackage())
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
        em.createQuery("delete from ResenaEntity").executeUpdate();
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
            BicicletaEntity entityB = factory.manufacturePojo(BicicletaEntity.class);
            em.persist(entityB);
            dataBikes.add(entityB);
        }
        for (int i = 0; i < 3; i++) {

            ResenaEntity entity = factory.manufacturePojo(ResenaEntity.class);
            if(i == 0)
                entity.setBicicleta(dataBikes.get(0));
            em.persist(entity);
          data.add(entity);
        }
        
        
    }

    @Test
    public void createTest() {
        PodamFactory factory = new PodamFactoryImpl();
        ResenaEntity newEntity = factory.manufacturePojo(ResenaEntity.class);
        ResenaEntity ee = persistence.create(newEntity);

        Assert.assertNotNull(ee);
        ResenaEntity entity = em.find(ResenaEntity.class, ee.getId());

        Assert.assertEquals(newEntity.getTitulo(), entity.getTitulo());
    }
    
    
    @Test
    public void findTest(){
        ResenaEntity encontrado = persistence.find(dataBikes.get(0).getId(), data.get(0).getId());
        Assert.assertNotNull(encontrado);
        Assert.assertEquals(data.get(0).getId(), encontrado.getId());

    }
    
    @Test
    public void updateTest(){
        PodamFactory factory = new PodamFactoryImpl();

        Long idActualizar = data.get(0).getId();
        ResenaEntity nuevaR = factory.manufacturePojo(ResenaEntity.class);
        nuevaR.setId(idActualizar);
        persistence.update(nuevaR);

        ResenaEntity recuperada = em.find(ResenaEntity.class, idActualizar);

        Assert.assertEquals(nuevaR.getId(), recuperada.getId());
        Assert.assertEquals(nuevaR.getDescripcion(), recuperada.getDescripcion());
    }
    
    @Test
    public void deleteTest(){
        ResenaEntity eliminar = data.get(0);
        persistence.delete(eliminar.getId());
        ResenaEntity eliminado = em.find(ResenaEntity.class, eliminar.getId());
        Assert.assertNull(eliminado);

    }

}
