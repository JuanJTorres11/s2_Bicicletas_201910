/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bicicletas.dtos;

import java.io.Serializable;

/**
 *
 * @author Andrea
 */
public class BicicletaDTO implements Serializable {
    
    private String descripcion;
    
    private String referencia;
    
    private Double precio;
    
    private Boolean usada;
    
    private Integer stock;
    
    private String[] album;
    
    private MarcaDTO marca;
    
    private CategoriaDTO categoria;
    
    
     /**
     * Constructor por defecto
     */
    public BicicletaDTO(){
        
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
     * @return the referencia
     */
    public String getReferencia() {
        return referencia;
    }

    /**
     * @param referencia the referencia to set
     */
    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    /**
     * @return the precio
     */
    public Double getPrecio() {
        return precio;
    }

    /**
     * @param precio the precio to set
     */
    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    /**
     * @return the usada
     */
    public Boolean getUsada() {
        return usada;
    }

    /**
     * @param usada the usada to set
     */
    public void setUsada(Boolean usada) {
        this.usada = usada;
    }

    /**
     * @return the stock
     */
    public Integer getStock() {
        return stock;
    }

    /**
     * @param stock the stock to set
     */
    public void setStock(Integer stock) {
        this.stock = stock;
    }

    /**
     * @return the album
     */
    public String[] getAlbum() {
        return album;
    }

    /**
     * @param album the album to set
     */
    public void setAlbum(String[] album) {
        this.album = album;
    }

    /**
     * @return the marca
     */
    public MarcaDTO getMarca() {
        return marca;
    }

    /**
     * @param marca the marca to set
     */
    public void setMarca(MarcaDTO marca) {
        this.marca = marca;
    }

    /**
     * @return the categoria
     */
    public CategoriaDTO getCategoria() {
        return categoria;
    }

    /**
     * @param categoria the categoria to set
     */
    public void setCategoria(CategoriaDTO categoria) {
        this.categoria = categoria;
    }

       
    
}
