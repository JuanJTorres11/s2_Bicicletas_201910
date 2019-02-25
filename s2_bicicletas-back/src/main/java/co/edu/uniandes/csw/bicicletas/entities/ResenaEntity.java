/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bicicletas.entities;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author Andrea
 */

@Entity
public class ResenaEntity extends BaseEntity implements Serializable  {
    
    private String titulo;
    
    private String descripcion;
    
    private Integer calificacion;
    
    @PodamExclude
    @ManyToOne(cascade = CascadeType.PERSIST)
    private BicicletaEntity bicicleta;

    public ResenaEntity(){
      
    }
    /**
     * @return the titulo
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * @param titulo the titulo to set
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the calificacion
     */
    public Integer getCalificacion() {
        return calificacion;
    }

    /**
     * @param calificacion the calificacion to set
     */
    public void setCalificacion(Integer calificacion) {
        this.calificacion = calificacion;
    }

    /**
     * @return the bicicleta
     */
    public BicicletaEntity getBicicleta() {
        return bicicleta;
    }

    /**
     * @param bicicleta the bicicleta to set
     */
    public void setBicicleta(BicicletaEntity bicicleta) {
        this.bicicleta = bicicleta;
    }
    
    
    
    
}
