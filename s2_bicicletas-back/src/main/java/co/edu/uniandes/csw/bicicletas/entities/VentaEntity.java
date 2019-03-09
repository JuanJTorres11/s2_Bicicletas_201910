/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bicicletas.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author Juan Lozano
 */
@Entity
public class VentaEntity extends BaseEntity implements Serializable {

    /**
     * atrtibuto de la ruta de la factura.
     */
    private String factura;

    /**
     * atributo del precio de la bicicleta.
     */
    private Double precio;

    /**
     * atributo de si fue aprobada la compra.
     */
    private Boolean aprobado;

    /**
     * Identificador de la venta.
     */
    private Long identificador;

    /**
     * rutas con las imagenes de la bicicleta.
     */
    private String[] fotos;

    @PodamExclude
    @ManyToOne
    private VendedorEntity vendedor;

    /**
     * Constructor de la clase VentaEntity
     */
    public VentaEntity() {

    }

    /**
     * @return the factura
     */
    public String getFactura() {
        return factura;
    }

    /**
     * @param factura the factura to set
     */
    public void setFactura(String factura) {
        this.factura = factura;
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
     * @return the aprobado
     */
    public Boolean getAprobado() {
        return aprobado;
    }

    /**
     * @param aprobado the aprobado to set
     */
    public void setAprobado(Boolean aprobado) {
        this.aprobado = aprobado;
    }

    /**
     * @return the identificador
     */
    public Long getIdentificador() {
        return identificador;
    }

    /**
     * @param identificador the identificador to set
     */
    public void setIdentificador(Long identificador) {
        this.identificador = identificador;
    }

    /**
     *
     * @return the fotos
     */
    public String[] getFotos() {
        return fotos;
    }

    /**
     * @param fotos the fotos to set
     */
    public void setFotos(String[] fotos) {
        this.fotos = fotos;
    }

//    /**
//     * @return the vendedor
//     */
//    public VendedorEntity getVendedor() {
//        return vendedor;
//    }
//
//    /**
//     * @param vendedor the vendedor to set
//     */
//    public void setVendedor(VendedorEntity vendedor) {
//        this.vendedor = vendedor;
//    }

}
