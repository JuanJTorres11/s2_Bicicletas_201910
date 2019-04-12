/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bicicletas.ejb;

import co.edu.uniandes.csw.bicicletas.entities.OrdenEntity;
import co.edu.uniandes.csw.bicicletas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.bicicletas.persistence.OrdenPersistence;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Mateo
 */
@Stateless
public class OrdenLogic {

    /**
     * Logger que permite imprimir eventos
     */
    private static final Logger LOGGER = Logger.getLogger(OrdenLogic.class.getName());

    /**
     * Persistencia de la orden
     */
    @Inject
    private OrdenPersistence ordenPersistence;

    /**
     * Crea una orden en la persistencia.
     *
     * @param ord La entidad que representa la orden a persistir.
     * @return La orden luego de persistirla.
     * @throws BusinessLogicException Si la orden a persistir ya existe.
     */
    public OrdenEntity createOrden(OrdenEntity ord) throws BusinessLogicException, ParseException {
        LOGGER.log(Level.INFO, "Inicia proceso de creación de la orden");
        verificarReglasNegocio(ord.getFecha(), ord.getCantidad(), ord.getCostoTotal());
        ordenPersistence.create(ord);
        LOGGER.log(Level.INFO, "Termina proceso de creación de la orden");
        return ord;
    }

    /**
     *
     * Obtener todas las ordenes existentes en la base de datos.
     *
     * @return una lista de ordenes.
     */
    public List<OrdenEntity> getOrdenes() {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todas las ordenes");
        List<OrdenEntity> ordenes = ordenPersistence.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todas las ordenes");
        return ordenes;
    }

    /**
     *
     * Obtener una orden por medio de su id.
     *
     * @param ordenId: id de la orden para ser buscada.
     * @return la orden solicitada por medio de su id.
     */
    public OrdenEntity getOrden(Long ordenId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar la orden con id = {0}", ordenId);
        OrdenEntity ordenEntity = ordenPersistence.find(ordenId);
        if (ordenEntity == null) {
            LOGGER.log(Level.SEVERE, "La orden con el id = {0} no existe", ordenId);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar la orden con id = {0}", ordenId);
        return ordenEntity;
    }
    
    /**
     * Verifica las reglas de negocio de creación de una orden
     * @param fecha Fecha de la orden
     * @param cantidad Cantidad de bicicletas compradas en la orden
     * @param costo Costo de la orden
     * @throws BusinessLogicException Si hubo un error en las reglas de negocio
     * @throws ParseException Si hubo un error en el formato de los parámetros
     */
    private void verificarReglasNegocio(String fecha, Integer cantidad, Double costo) throws BusinessLogicException, ParseException{
        
        verificarFecha(fecha, "dd/MM/YYYY");
        if(cantidad<=0 || cantidad == null){
            throw new BusinessLogicException("La cantidad debe estar establecida como un valor mayor a 0");
        }
        else if(costo<=0 || costo == null){
            throw new BusinessLogicException("El costo debe estar establecido como un valor mayor a 0");
        }
        
    }
    
    /**
     * Verifica si la fecha está en el formato introducido por parámetro.
     * @param fecha Fecha que se quiere verificar. fecha != null
     * @param formato Formato en el que se quiere verificar. formato != null && != "".
     * @throws ParseException   1. Si el formato no es correcto.
     */
    private void verificarFecha(String fecha, String formato) throws ParseException {
        SimpleDateFormat f = new SimpleDateFormat(formato);
        f.setLenient(false);
        
        f.parse(fecha);
    }
}
