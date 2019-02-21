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
import org.eclipse.persistence.jpa.config.Cascade;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author Andrea
 */
@Entity
public class BicicletaEntity extends BaseEntity implements Serializable {

    private String descripcion;

    private String referencia;

    private double precio;

    private boolean usada;

    private int stock;

    private String[] album;

    @ManyToOne 
    private MarcaEntity marca;
    
    @ManyToOne
    private CategoriaEntity categoria;

    @PodamExclude
    @OneToMany(mappedBy = "bicicleta", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<ResenaEntity> resenas = new ArrayList<ResenaEntity>();

    /**
     * Constructor vacio
     */
    public BicicletaEntity() {

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
    public double getPrecio() {
        return precio;
    }

    /**
     * @param precio the precio to set
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    /**
     * @return the usada
     */
    public boolean isUsada() {
        return usada;
    }

    /**
     * @param usada the usada to set
     */
    public void setUsada(boolean usada) {
        this.usada = usada;
    }

    /**
     * @return the stock
     */
    public int getStock() {
        return stock;
    }

    /**
     * @param stock the stock to set
     */
    public void setStock(int stock) {
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
   
}
