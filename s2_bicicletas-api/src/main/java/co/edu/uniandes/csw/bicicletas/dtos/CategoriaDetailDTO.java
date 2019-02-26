/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bicicletas.dtos;

import co.edu.uniandes.csw.bicicletas.entities.BicicletaEntity;
import co.edu.uniandes.csw.bicicletas.entities.CategoriaEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Andres Donoso
 */
public class CategoriaDetailDTO extends CategoriaDTO implements Serializable {
    
    /**
     * Contiene la lista de bicicletas que pertenecen a esta categoría
     */
    private List<BicicletaDTO> bicicletas;
    
    public CategoriaDetailDTO() {
        
    }
    
    public CategoriaDetailDTO(CategoriaEntity categoria) {
        super(categoria);
        if(categoria.getBicicletas() != null) {
            this.bicicletas = new ArrayList<>();
            for(BicicletaEntity bicicleta: categoria.getBicicletas()) {
                //this.bicicletas.add(new BicicletaDTO(bicicleta));
            }
        }
    }
    
    public CategoriaEntity toEntity() {
        CategoriaEntity categoria = super.toEntity();
        if(bicicletas != null) {
            List<BicicletaEntity> bicicletaEntities = new ArrayList<>();
            for(BicicletaDTO bicicleta: bicicletas) {
                //bicicletaEntities.add(bicicleta.toEntity());
            }
            categoria.setBicicletas(bicicletaEntities);
        }
        
        return categoria;
    }

    /**
     * @return the bicicletas
     */
    public List<BicicletaDTO> getBicicletas() {
        return bicicletas;
    }

    /**
     * @param bicicletas the bicicletas to set
     */
    public void setBicicletas(List<BicicletaDTO> bicicletas) {
        this.bicicletas = bicicletas;
    }
}
