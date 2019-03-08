/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bicicletas.ejb;

import co.edu.uniandes.csw.bicicletas.entities.BicicletaEntity;
import co.edu.uniandes.csw.bicicletas.entities.MarcaEntity;
import co.edu.uniandes.csw.bicicletas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.bicicletas.persistence.MarcaPersistence;
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
public class MarcaLogic {

    private static final Logger LOGGER = Logger.getLogger(MarcaLogic.class.getName());

    @Inject
    private MarcaPersistence marcaPersistence;

    /**
     * Crea una marca en la persistencia.
     *
     * @param MarcaEntity La entidad que representa la marca a persistir.
     * @return La marca de la marca luego de persistirla.
     * @throws BusinessLogicException Si ya existe una marca con ese nombre.
     */
    public MarcaEntity createMarca(MarcaEntity mar) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de creación de la marca");

        if (mar.getNombre() == null || mar.getNombre().equals("")) {
            throw new BusinessLogicException("El nombre de la marca a crear no cumple con los requerimientos");
        }
        else if (marcaPersistence.findByName(mar.getNombre()) != null) {
            throw new BusinessLogicException("Ya existe una Marca con el nombre \"" + mar.getNombre() + "\"");
        }
        marcaPersistence.create(mar);
        LOGGER.log(Level.INFO, "Termina proceso de creación de la marca");
        return mar;
    }

    /**
     *
     * Obtener todas las marcas existentes en la base de datos.
     *
     * @return una lista de marcas.
     */
    public List<MarcaEntity> getMarcas() {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todas las marcas");
        List<MarcaEntity> marcas = marcaPersistence.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todas las marcas");
        return marcas;
    }

    /**
     *
     * Obtener una marca por medio de su id.
     *
     * @param marcaId: id de la marca para ser buscada.
     * @return la marca solicitada por medio de su id.
     */
    public MarcaEntity getMarca(Long marcaId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar la marca con id = {0}", marcaId);
        MarcaEntity marcaEntity = marcaPersistence.find(marcaId);
        if (marcaEntity == null) {
            LOGGER.log(Level.SEVERE, "La marca con el id = {0} no existe", marcaId);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar la marca con id = {0}", marcaId);
        return marcaEntity;
    }

    /**
     *
     * Actualizar una marca.
     *
     * @param marcaId: id de la marca para buscarla en la base de datos.
     * @param marcaEntity: marca con los cambios para ser actualizada, por
     * ejemplo el nombre.
     * @return la marca con los cambios actualizados en la base de datos.
     */
    public MarcaEntity updateMarca(Long marcaId, MarcaEntity marcaEntity) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar la marca con id = {0}", marcaId);
        MarcaEntity newEntity = marcaPersistence.update(marcaEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar la marca con id = {0}", marcaEntity.getId());
        return newEntity;
    }

    /**
     * Borrar un marca
     *
     * @param marcasId: id de la marca a borrar
     * @throws BusinessLogicException Si la marca a eliminar tiene bicicletas.
     */
    public void deleteMarca(Long marcasId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar la marca con id = {0}", marcasId);
        List<BicicletaEntity> bicicletas = getMarca(marcasId).getBicicletasMarca();
        if (bicicletas!=null) {
            if( !bicicletas.isEmpty()){
            throw new BusinessLogicException("No se puede borrar la marca con id = " + marcasId + " porque tiene bicicletas asociados");
            }
        }
        marcaPersistence.delete(marcasId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar la marca con id = {0}", marcasId);
    }
}
