/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bicicletas.dtos;

import co.edu.uniandes.csw.bicicletas.entities.BicicletaEntity;
import java.io.Serializable;

/**
 *
 * @author Andrea
 */
public class BicicletaDTO implements Serializable {

    private Long id;

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
    public BicicletaDTO() {
        //constructor vacio
    }

    /**
     * Constructor a partir de la entidad
     *
     * @param bikeEntity La entidad de la bicicleta
     */
    public BicicletaDTO(BicicletaEntity bikeEntity) {
        if (bikeEntity != null) {

            this.id = bikeEntity.getId();
            this.descripcion = bikeEntity.getDescripcion();
            this.referencia = bikeEntity.getReferencia();
            this.precio = bikeEntity.getPrecio();
            this.usada = bikeEntity.getUsada();
            this.stock = bikeEntity.getStock();
            this.album = bikeEntity.getAlbum();

            if (bikeEntity.getCategoria() != null) {
                this.categoria = new CategoriaDTO(bikeEntity.getCategoria());
            } else {
                this.categoria = null;
            }

            if (bikeEntity.getMarca() != null) {
                this.marca = new MarcaDTO(bikeEntity.getMarca());
            } else {
                this.marca = null;
            }
        }
    }

    /**
     * Transformar el DTO a una entidad
     *
     * @return La entidad que representa el libro.
     */
    public BicicletaEntity toEntity() {
        BicicletaEntity bicicletaEntity = new BicicletaEntity();
        bicicletaEntity.setAlbum(this.album);
        bicicletaEntity.setDescripcion(this.descripcion);
        bicicletaEntity.setId(this.id);
        bicicletaEntity.setPrecio(this.precio);
        bicicletaEntity.setReferencia(this.referencia);
        bicicletaEntity.setStock(this.stock);
        bicicletaEntity.setUsada(this.usada);
        if (this.marca != null) {
            bicicletaEntity.setMarca(this.marca.toEntity());
        }
        if (this.categoria != null) {
            bicicletaEntity.setCategoria(this.categoria.toEntity());
        }

        return bicicletaEntity;
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
