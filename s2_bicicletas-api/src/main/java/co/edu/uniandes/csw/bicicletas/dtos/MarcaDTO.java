/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bicicletas.dtos;

import co.edu.uniandes.csw.bicicletas.entities.MarcaEntity;
import java.io.Serializable;

/**
 *
 * @author Mateo
 */
public class MarcaDTO implements Serializable{
    
    /**
     * Nombre de la marca
     */
    private String nombre;
    
    /**
     * Id de la marca
     */
    private Long id;
        
    /**
     * Constructor por defecto
     */
    public MarcaDTO(){
        
    }
    
    public MarcaDTO(MarcaEntity marcaEntity){
        if (marcaEntity != null) {
            this.id = marcaEntity.getId();
            this.nombre = marcaEntity.getNombre();
        }
    }
    
    public MarcaEntity toEntity(){
        MarcaEntity marcaEntity = new MarcaEntity();
        marcaEntity.setId(this.id);
        marcaEntity.setNombre(this.nombre);
        return marcaEntity;
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
