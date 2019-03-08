/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bicicletas.dtos;

import co.edu.uniandes.csw.bicicletas.entities.ResenaEntity;
import java.io.Serializable;

/**
 *
 * @author Andrea
 */
public class ResenaDTO implements Serializable {

    private Long id;

    private String descripcion;

    private Integer calificacion;

    private String titulo;

    /*
    * Relación a una bicicleta
    * dado que esta tiene cardinalidad 1.
     */
    private BicicletaDTO bicicleta;

    /**
     * Constructor por defecto
     */
    public ResenaDTO() {
        //constructor vacio
    }

    public ResenaDTO(ResenaEntity resenaEntitiy) {

        if (resenaEntitiy != null) {
            this.calificacion = resenaEntitiy.getCalificacion();
            this.descripcion = resenaEntitiy.getDescripcion();
            this.id = resenaEntitiy.getId();
            this.titulo = resenaEntitiy.getTitulo();

            if (resenaEntitiy.getBicicleta() != null) {
                this.bicicleta = new BicicletaDTO(resenaEntitiy.getBicicleta());
            } else {
                this.bicicleta = null;
            }
        }

    }

    /**
     * Método para transformar el DTO a una entidad.
     *
     * @return La entidad .
     */
    public ResenaEntity toEntity() {
        ResenaEntity entity = new ResenaEntity();
        
        entity.setCalificacion(this.calificacion);
        entity.setDescripcion(this.descripcion);
        entity.setId(this.id);
        entity.setTitulo(this.titulo);
        if (this.getBicicleta() != null) {
            entity.setBicicleta(this.getBicicleta().toEntity());
        }
        return entity;
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
    public BicicletaDTO getBicicleta() {
        return bicicleta;
    }

    /**
     * @param bicicleta the bicicleta to set
     */
    public void setBicicleta(BicicletaDTO bicicleta) {
        this.bicicleta = bicicleta;
    }

   
}
