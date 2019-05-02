/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bicicletas.dtos;

import java.io.Serializable;
import co.edu.uniandes.csw.bicicletas.entities.VentaEntity;

/**
 *
 * @author Juan Lozano
 */
public class VentaDTO implements Serializable {

    /**
     * Atributo que representa la factura.
     */
    private String factura;

    /**
     * Atributo que representa el precio.
     */
    private double precio;

    /**
     * Atributo que representa True si fue aprobado.False de lo contrario.
     */
    private boolean aprobado;

    /**
     * Atributo que representa un arreglo con las fotos de la bicicleta
     * asociada.
     */
    private String[] fotos;
    
    /**
     * Atributo que representa el id de una venta. 
     */
    private Long id;

    /**
     * Objeto bicicleta a vender.
     */
    private BicicletaDTO bicicletaDTO;

    /**
     * Metodo constructor de la clase VentaDTO.
     */
    public VentaDTO() {

    }

    /**
     * Metodo que convierte un VentaEntity en un VentaDTO
     *
     * @param venta Revibe un objeto tipo VentaEntity
     */
    public VentaDTO(VentaEntity venta) {
        if (venta != null) {
            this.factura = venta.getFactura();
            this.precio = venta.getPrecio();
            this.aprobado = venta.getAprobado();
            this.fotos = venta.getFotos();
            this.id = venta.getId();

        }
    }

    /**
     * Convierte un entity
     *
     * @return Retorna un objeto tipo VentaEntity
     */
    public VentaEntity toEntity() {
        VentaEntity venta = new VentaEntity();
        venta.setPrecio(this.precio);
        venta.setFactura(this.factura);
        venta.setFotos(this.fotos);
        venta.setAprobado(this.aprobado);
        venta.setId(this.id);

        return venta;
    }

    /**
     * Obtiene la factura.
     *
     * @return the factura
     */
    public String getFactura() {
        return factura;
    }

    /**
     * Asigna la factura.
     *
     * @param factura the factura to set
     */
    public void setFactura(String factura) {
        this.factura = factura;
    }

    /**
     * Obtiene el precio de la bicicleta.
     *
     * @return the precio
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * Cambia el precio por el recibido por parametro.
     *
     * @param precio the precio to set
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    /**
     * Si es aprobada la venta o no.
     *
     * @return the aprobado
     */
    public boolean isAprobado() {
        return aprobado;
    }

    /**
     * Indica si se realiza la transaccion de la venta o no.
     *
     * @param aprobado the aprobado to set
     */
    public void setAprobado(boolean aprobado) {
        this.aprobado = aprobado;
    }

    /**
     * Obtiene todas las fotos asociadas a la bicicleta.
     *
     * @return the fotos
     */
    public String[] getFotos() {
        return fotos;
    }

    /**
     * Cambia las fotos que recibe por las que pudiese tener.
     *
     * @param fotos the fotos to set
     */
    public void setFotos(String[] fotos) {
        this.fotos = fotos;
    }

    /**
     * @return the bicicletaDTO
     */
    public BicicletaDTO getBicicletaDTO() {
        return bicicletaDTO;
    }

    /**
     * @param bicicletaDTO the bicicletaDTO to set
     */
    public void setBicicletaDTO(BicicletaDTO bicicletaDTO) {
        this.bicicletaDTO = bicicletaDTO;
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
