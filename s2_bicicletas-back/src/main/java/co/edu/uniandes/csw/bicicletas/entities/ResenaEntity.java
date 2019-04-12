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
    
    /**
     * Login del usuario calificador
     */
    private String usuario;
    
    /**
     * El titulo de la reseña
     */
    private String titulo;
    
    /**
     * La descripcion de la reseña
     */
    private String descripcion;
    
    /**
     * La calificacion de la reseña
     */
    private Integer calificacion;
    
    /**
     * La bicicleta asociada a la reseña
     */
    @PodamExclude
    @ManyToOne(cascade = CascadeType.PERSIST)
    private BicicletaEntity bicicleta;

    
    /**
     * Constructor vacio
     */
    public ResenaEntity(){
      
    }
    
    /**
     * Devuelve el titulo de la reseña 
     * @return the titulo
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Modifica el titulo de la reseña 
     * @param titulo the titulo to set
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * Devuelve la descripcion titulo de la reseña
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Modifica la descripcion titulo de la reseña
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Devuelve la calificacion de la reseña
     * @return the calificacion
     */
    public Integer getCalificacion() {
        return calificacion;
    }

    /**
     * Modifica la calificacion de la reseña
     * @param calificacion the calificacion to set
     */
    public void setCalificacion(Integer calificacion) {
        this.calificacion = calificacion;
    }

    /**
     * Devuelve la bicicleta asociada a la reseña
     * @return the bicicleta
     */
    public BicicletaEntity getBicicleta() {
        return bicicleta;
    }

    /**
     * Modifica la bicicleta asociada a la reseña
     * @param bicicleta the bicicleta to set
     */
    public void setBicicleta(BicicletaEntity bicicleta) {
        this.bicicleta = bicicleta;
    }

    /**
     * @return the usuario
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    
    
    
    
}
