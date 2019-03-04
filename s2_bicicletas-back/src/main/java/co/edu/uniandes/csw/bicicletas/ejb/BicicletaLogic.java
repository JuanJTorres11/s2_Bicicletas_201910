/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bicicletas.ejb;

import co.edu.uniandes.csw.bicicletas.entities.BicicletaEntity;
import co.edu.uniandes.csw.bicicletas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.bicicletas.persistence.BicicletaPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Andrea
 */
@Stateless
public class BicicletaLogic {

    private static final Logger LOGGER = Logger.getLogger(BicicletaLogic.class.getName());

    @Inject
    private BicicletaPersistence persistence;

    /**
     * Crea una bicicleta en la persistencia.
     *
     * @param bicicletaEntity La entidad que representa la bicicleta a
     * persistir.
     * @return La entiddad de la bicicleta luego de persistirla.
     * @throws BusinessLogicException Si la bicicleta a persistir ya existe.
     */
    public BicicletaEntity createBicicleta(BicicletaEntity bicicletaEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de creación de la bicicleta");

        //Verifica las reglas de negocio
        verificarReglasNegocioBicicleta(bicicletaEntity);

        // Invoca la persistencia para crear la bicileta
        persistence.create(bicicletaEntity);
        LOGGER.log(Level.INFO, "Termina proceso de creación de la bicicleta");

        return bicicletaEntity;
    }

    /**
     * Elimina una bicicleta
     *
     * @param id : id de la bicicleta que se quiere borrar
     */
    public void deleteBicicleta(Long id) {

        LOGGER.log(Level.INFO, "Inicia proceso de borrar la bicicleta con id = {0}", id);
        persistence.delete(id);
        LOGGER.log(Level.INFO, "Termina proceso de borrar la bicicleta con id = {0}", id);
    }

    /**
     * Retorna una bicicletaEntity
     *
     * @return Una lista con todas las BicletaEntity
     */
    public List<BicicletaEntity> getBicicletas() {

        LOGGER.log(Level.INFO, "Inicia proceso de buscar todas las bicletas");
        List<BicicletaEntity> bicicletas = persistence.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de buscar todas las bicletas");
        return bicicletas;
    }

    /**
     * Retorna una bicicletaEntity
     *
     * @param id: ide de la bicicleta que se quiere consultar
     * @return La entiddad de la bicicleta que se quiere consultar
     */
    public BicicletaEntity getBicicleta(Long id) {
        LOGGER.log(Level.INFO, "Inicia proceso de buscar una bicleta por id ", id);
        BicicletaEntity bicicleta = persistence.find(id);
        LOGGER.log(Level.INFO, "Termina proceso de buscar una bicleta por id", id);
        return bicicleta;
    }

    /**
     * Retorna una bicicletaEntity
     *
     * @param referenciaBicicleta : referencia de la bicicleta que se quiere
     * consultar
     * @return La entiddad de la bicicleta que se quiere consultar
     */
    public BicicletaEntity getBicicletaPorReferencia(String referenciaBicicleta) {
        LOGGER.log(Level.INFO, "Inicia proceso de buscar una bicleta por referencia ", referenciaBicicleta);
        BicicletaEntity bicicleta = persistence.findByReferencia(referenciaBicicleta);
        LOGGER.log(Level.INFO, "Termina proceso de buscar una bicleta por referencia", referenciaBicicleta);
        return bicicleta;
    }

    /**
     * Actualiza una bicicleta
     *
     * @param bicicletaEntity La Entidad con los cambios
     * @return La Entidad de la bicicleta modificada
     */
    public BicicletaEntity ubdateBicicleta(BicicletaEntity bicicletaEntity) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar una bicicleta con id", bicicletaEntity.getId());
        BicicletaEntity bikeE = persistence.update(bicicletaEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar una bicicleta con id", bicicletaEntity.getId());
        return bikeE;
    }

    public void verificarReglasNegocioBicicleta(BicicletaEntity bicicletaEntity) throws BusinessLogicException {
        // Verifica la regla de negocio: la misma referencia no puede ser null ni cadena vacia
        if (bicicletaEntity.getReferencia() == null) {
            throw new BusinessLogicException("La referencia no es valida\"" + bicicletaEntity.getReferencia() + "\"");
        }

        // Verifica la regla de negocio: no puede haber dos biciletas con la misma referencia
        if (persistence.findByReferencia(bicicletaEntity.getReferencia()) != null) {
            throw new BusinessLogicException("Ya existe una Bicicletaa con la referencia \"" + bicicletaEntity.getReferencia() + "\"");
        }
        
         if(bicicletaEntity.getAlbum() == null || bicicletaEntity.getAlbum().length == 0){
            throw new BusinessLogicException("La bicicleta debe tener al menos 1 foto \"" );
        }


        //Verifica la regla de negocio: el precio no puede ser negativo
        //if (bicicletaEntity.getPrecio() < 0.0) {
          //  throw new BusinessLogicException("El precio no puede ser un valor negativo \"" + bicicletaEntity.getPrecio() + "\"");
        //}

        //Verifica la regla de negocio: la marca no puede ser null
        //if (bicicletaEntity.getMarca() == null) {
          //  throw new BusinessLogicException("La bicicleta tiene que tener una marca \"");
        //}

        //Verifica la regla de negocio: la categoria no puede ser null
        //if (bicicletaEntity.getCategoria() == null) {
          //  throw new BusinessLogicException("La bicicleta tiene que tener una categoria \"");
        //}

        //Verifica la regla de negocio: el stock no puede ser menor a 0
        //if (bicicletaEntity.getStock() < 0) {
          //  throw new BusinessLogicException("El stock no puede ser negativo \"" + bicicletaEntity.getStock() + "\"");
        //}
        //Verifica la regla de negocio: debe tener al menos 1 foto
       
    }

}
