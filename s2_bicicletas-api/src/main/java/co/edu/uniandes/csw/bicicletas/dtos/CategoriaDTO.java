/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bicicletas.dtos;

import co.edu.uniandes.csw.bicicletas.entities.CategoriaEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Andres Donoso
 */
public class CategoriaDTO implements Serializable {
    
    /**
     * Nombre de la categoría
     */
    private String nombre;
    
    /**
     * Constructor vacío
     */
    public CategoriaDTO() {
        
    }
    
    /**
     * Construye una categoría a partir de la entidad.
     * @param categoria entidad de la categoria.
     */
    public CategoriaDTO(CategoriaEntity categoria) {
        if(categoria != null) {
            this.nombre = categoria.getNombre();
        }
    }
    
    public CategoriaEntity toEntity() {
        CategoriaEntity categoria = new CategoriaEntity();
        categoria.setNombre(this.nombre);
        
        return categoria;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
