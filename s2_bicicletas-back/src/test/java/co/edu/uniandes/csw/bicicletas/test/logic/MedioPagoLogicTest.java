/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bicicletas.test.logic;

import co.edu.uniandes.csw.bicicletas.ejb.MedioPagoLogic;
import co.edu.uniandes.csw.bicicletas.entities.MedioPagoEntity;
import co.edu.uniandes.csw.bicicletas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.bicicletas.persistence.MedioPagoPersistence;
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
 * @author Andres Donoso
 */
@RunWith(Arquillian.class)
public class MedioPagoLogicTest {
    private PodamFactory factory = new PodamFactoryImpl();
    
    @Inject
    private MedioPagoLogic mpl;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    private UserTransaction utx;
    
    private List<MedioPagoEntity> data = new ArrayList<>();
    
    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(MedioPagoEntity.class.getPackage())
                .addPackage(MedioPagoLogic.class.getPackage())
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
     */
    private void clearData() {
        em.createQuery("delete from MedioPagoEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            MedioPagoEntity medioPago = factory.manufacturePojo(MedioPagoEntity.class);
            if(i == 0) {
                medioPago.setNumeroTarjeta(Long.valueOf("5184415481682802"));
            }
            
            em.persist(medioPago);
            data.add(medioPago);
        }
    }
    
    @Test
    public void createMedioPagoCreditoTest() throws BusinessLogicException {
        MedioPagoEntity medioPagoVISA = new MedioPagoEntity(Long.valueOf("4152671347008886"), 123, "04/27", 
                "direccion1", MedioPagoLogic.CREDITO, MedioPagoLogic.VISA);
        MedioPagoEntity medioPagoMASTERCARD = new MedioPagoEntity(Long.valueOf("5401345858879821"), 123, "12/99", 
                "direccion2", MedioPagoLogic.CREDITO, MedioPagoLogic.MASTERCARD);
        
        /**
         * Prueba de medio de pago crédito VISA.
         */
        MedioPagoEntity resultadoVISA = mpl.createMedioPago(medioPagoVISA);
        Assert.assertNotNull(resultadoVISA);
        
        MedioPagoEntity entityVISA = em.find(MedioPagoEntity.class, resultadoVISA.getId());
        Assert.assertEquals(medioPagoVISA.getId(), entityVISA.getId());
        Assert.assertEquals(medioPagoVISA.getNumeroTarjeta(), entityVISA.getNumeroTarjeta());
        
        /**
         * Prueba de medio de pago crédito MASTERCARD
         */
        MedioPagoEntity resultadoMASTERCARD = mpl.createMedioPago(medioPagoMASTERCARD);
        Assert.assertNotNull(resultadoMASTERCARD);
        
        MedioPagoEntity entityMASTERCARD = em.find(MedioPagoEntity.class, resultadoMASTERCARD.getId());
        Assert.assertEquals(medioPagoMASTERCARD.getId(), entityMASTERCARD.getId());
        Assert.assertEquals(medioPagoMASTERCARD.getNumeroTarjeta(), entityMASTERCARD.getNumeroTarjeta());
        
        /**
         * Prueba de datos incorrectos
         */
        MedioPagoEntity masterCard = new MedioPagoEntity(Long.valueOf("5412479594116731"), 1234567, "13/16", 
                "", MedioPagoLogic.CREDITO, MedioPagoLogic.VISA);
        MedioPagoEntity resultado;
        for(int i = 0; i < 4; i++) {
            String mensaje = "Número de tarjeta debería ser inválido.";
            if(i > 0) {
                masterCard.setNumeroTarjeta(Long.valueOf("5412479594116731"));
                mensaje = "Código de verificación debería ser inválido";
            }
            
            if(i > 1) {
                masterCard.setCodigoVerificacion(123);
                mensaje = "Fecha debería ser inválida";
            }
            
            if(i > 2) {
                masterCard.setFechaVencimiento("04/11");
                mensaje = "La dirección debería ser inválida";
            }
            
            try {
                resultado = mpl.createMedioPago(masterCard);
                Assert.fail(mensaje);
            } catch(BusinessLogicException e) {

            }
        }
    }
    
    @Test
    public void createMedioPagoDebitoTest() throws BusinessLogicException {
        MedioPagoEntity medioPagoDebito = new MedioPagoEntity(Long.valueOf("6016607271103708"), 
                null, "12/99", "direccion2", MedioPagoLogic.DEBITO, null);
        
        /**
         * Prueba de medio de pago débito.
         */
        MedioPagoEntity resultado = mpl.createMedioPago(medioPagoDebito);
        Assert.assertNotNull(resultado);
        
        MedioPagoEntity entity = em.find(MedioPagoEntity.class, resultado.getId());
        Assert.assertEquals(medioPagoDebito.getId(), entity.getId());
        Assert.assertEquals(medioPagoDebito.getNumeroTarjeta(), entity.getNumeroTarjeta());
    }
    
    @Test(expected = BusinessLogicException.class)
    public void createMedioPagoMismoNumeroTest() throws BusinessLogicException {
        MedioPagoEntity medioPago = factory.manufacturePojo(MedioPagoEntity.class);
        medioPago.setNumeroTarjeta(data.get(0).getNumeroTarjeta());
        
        mpl.createMedioPago(medioPago);
    }
    
    @Test
    public void updateMedioPago() throws BusinessLogicException {
        MedioPagoEntity medioPago = data.get(0);
        MedioPagoEntity entity = factory.manufacturePojo(MedioPagoEntity.class);
        entity.setId(medioPago.getId());
        entity.setNumeroTarjeta(Long.valueOf("5385369491353036"));
        entity.setCodigoVerificacion(123);
        entity.setFechaVencimiento("01/20");
        entity.setTipoTarjeta(MedioPagoLogic.CREDITO);
        entity.setTipoCredito(MedioPagoLogic.MASTERCARD);
        
        mpl.updateMedioPago(entity);
        MedioPagoEntity resultado = em.find(MedioPagoEntity.class, medioPago.getId());
        
        Assert.assertEquals(entity.getId(), resultado.getId());
        Assert.assertEquals(entity.getNumeroTarjeta(), resultado.getNumeroTarjeta());
    }
    
    @Test
    public void deleteMedioPago() throws BusinessLogicException {
        MedioPagoEntity medioPago = data.get(1);
        mpl.deleteMedioPago(medioPago.getId());
        MedioPagoEntity resultado = em.find(MedioPagoEntity.class, medioPago.getId());
        
        Assert.assertNull(resultado);
    }
    
    @Test
    public void getMedioPagoTest() {
        MedioPagoEntity medioPago = data.get(0);
        MedioPagoEntity resultado = mpl.getMedioPago(medioPago.getId());
        
        Assert.assertNotNull(resultado);
        Assert.assertEquals(medioPago.getId(), resultado.getId());
        Assert.assertEquals(medioPago.getNumeroTarjeta(), resultado.getNumeroTarjeta());
    }
}
