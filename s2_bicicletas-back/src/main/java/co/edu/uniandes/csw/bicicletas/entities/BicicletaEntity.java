/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bicicletas.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;

/**
 * Clase que representa una biciclceta en la persistencia y permite su serialización
 * @author Andrea
 */
@Entity
public class BicicletaEntity extends BaseEntity implements Serializable {

    
    /**
     * La descripcion de la bicicleta
     */
    private String descripcion;

    /**
     * La referencia de la bicicleta
     */
    private String referencia;

    
    /**
     * El precio de la bicicleta
     */
    private Double precio;

    /**
     * Indica si la bicicleta es usada o no
     */
    private Boolean usada;

    /**
     * Indica la cantidad de bicicletas disponibles en la tienda
     */
    private Integer stock;

    /**
     * Las rutas de las imagenes de la bicicleta
     */
    private String[] album;

    /**
     * La marca de la bicicleta
     */
    @PodamExclude
    @ManyToOne(cascade = CascadeType.PERSIST)
    private MarcaEntity marca;
    
    /**
     * LA categoria de la bicicleta
     */
    @PodamExclude
    @ManyToOne(cascade = CascadeType.PERSIST)
    private CategoriaEntity categoria;

    /**
     * Las reseñas asociadas a la bicicleta
     */
    @PodamExclude
    @OneToMany(mappedBy = "bicicleta", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<ResenaEntity> resenas = new ArrayList<ResenaEntity>();
    
    /**
     * La orden asociada a la bicicleta
     */
    @PodamExclude
    @ManyToOne
    private OrdenEntity orden;

    /**
     * Constructor vacio
     */
    public BicicletaEntity() {

    }

    /**
     * Devuelve la descripcion de la bicicleta
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Modifica la descripcion de la bicicleta
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Devuelve la referencia de la bicicleta
     * @return the referencia
     */
    public String getReferencia() {
        return referencia;
    }

    /**
     * Modifica la referencia de la bicicleta
     * @param referencia the referencia to set
     */
    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    /**
     * Devuelve el precio de la bicicleta
     * @return the precio
     */
    public Double getPrecio() {
        return precio;
    }

    /**
     * Modifica el precio de la bicicleta
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
    public MarcaEntity getMarca() {
        return marca;
    }

    /**
     * @param marca the marca to set
     */
    public void setMarca(MarcaEntity marca) {
        this.marca = marca;
    }

    /**
     * @return the categoria
     */
    public CategoriaEntity getCategoria() {
        return categoria;
    }

    /**
     * @param categoria the categoria to set
     */
    public void setCategoria(CategoriaEntity categoria) {
        this.categoria = categoria;
    }

    /**
     * @return the resenas
     */
    public List<ResenaEntity> getResenas() {
        return resenas;
    }

    /**
     * @param resenas the resenas to set
     */
    public void setResenas(List<ResenaEntity> resenas) {
        this.resenas = resenas;
    }
    
    /**
     * @return the orden
     */
    public OrdenEntity getOrden() {
        return orden;
    }

    /**
     * @param orden the orden to set
     */
    public void setOrden(OrdenEntity orden) {
        this.orden = orden;
    }

   
}
