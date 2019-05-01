/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bicicletas.dtos;

import co.edu.uniandes.csw.bicicletas.entities.CategoriaEntity;
import java.io.Serializable;

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
     * Id de la categoria
     */
    
    private Long id;
    
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
            this.id = categoria.getId();
            this.nombre = categoria.getNombre();
        }
    }
    
    public CategoriaEntity toEntity() {
        CategoriaEntity categoria = new CategoriaEntity();
        categoria.setNombre(this.nombre);
        categoria.setId(this.id);
        
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

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }
}
