/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bicicletas.test.persistence;

import co.edu.uniandes.csw.bicicletas.entities.MedioPagoEntity;
import co.edu.uniandes.csw.bicicletas.persistence.MedioPagoPersistence;
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
 * @author Andres Donoso
 */
@RunWith(Arquillian.class)
public class MedioPagoPersistenceTest {
    @Inject private MedioPagoPersistence mpp;
    @PersistenceContext private EntityManager em;
    @Inject UserTransaction utx;
    private List<MedioPagoEntity> data = new ArrayList<>();
    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(MedioPagoEntity.class.getPackage())
                .addPackage(MedioPagoPersistence.class.getPackage())
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
        em.createQuery("delete from MedioPagoEntity").executeUpdate();
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

            MedioPagoEntity entity = factory.manufacturePojo(MedioPagoEntity.class);

            em.persist(entity);

            data.add(entity);
        }
    }
    
    @Test
    public void createMedioPagoTest() {
        PodamFactory factory = new PodamFactoryImpl();
        MedioPagoEntity newEntity = factory.manufacturePojo(MedioPagoEntity.class);
        MedioPagoEntity resultado = mpp.create(newEntity);
        
        Assert.assertNotNull(resultado);
        MedioPagoEntity entity = em.find(MedioPagoEntity.class, resultado.getId());

        Assert.assertEquals(newEntity.getNumeroTarjeta(), entity.getNumeroTarjeta());
    }
    
    @Test
    public void deleteMedioPagoTest() {
        MedioPagoEntity medioPago = data.get(0);
        mpp.delete(medioPago.getId());
        MedioPagoEntity borrado = em.find(MedioPagoEntity.class, medioPago.getId());
        Assert.assertNull(borrado);
    }
    
    @Test
    public void findByNumberTest() {
        MedioPagoEntity medioPago = data.get(0);
        MedioPagoEntity newEntity = mpp.findByNumber(medioPago.getNumeroTarjeta());
        
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(newEntity.getNumeroTarjeta(), medioPago.getNumeroTarjeta());
    }
    
    @Test
    public void updateTest() {
        MedioPagoEntity medioPago = data.get(0);
        int mes = (int) (Math.random() * 13);
        int anio = (int) (Math.random() * 100);
        String fecha = mes + "/" + anio;
        medioPago.setFechaVencimiento(fecha);
        
        mpp.update(medioPago);
        
        Assert.assertEquals(fecha, mpp.find(data.get(0).getId()).getFechaVencimiento());
    }
}
