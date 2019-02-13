/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bicicletas.dtos;

import java.util.List;

/**
 *
 * @author Andres Donoso
 */
public class CategoriaDetailDTO extends CategoriaDTO {
    
    /**
     * Contiene la lista de bicicletas que pertenecen a esta categoría
     */
    private List<BicicletaDTO> bicicletas;
    
    public CategoriaDetailDTO() {
        
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
