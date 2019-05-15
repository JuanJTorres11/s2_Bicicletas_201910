/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bicicletas.entities;


import java.util.List;
import java.util.logging.Logger;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author Juan Lozano
 */

//PodamExclude, para que no quede en un stack over flow en la creacion de objetos. 
@Entity
public class CompradorEntity extends UsuarioEntity {
    
    
    private static final Logger LOGGER = Logger.getLogger(VendedorEntity.class.getName());
      
      
    @PodamExclude
    @OneToMany(mappedBy = "comprador", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<MedioPagoEntity> mediosPago;
    
    @PodamExclude
    @OneToMany(mappedBy = "comprador", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<OrdenEntity> ordenes;
    
   @PodamExclude
//   @OneToMany(mappedBy = "comprador", cascade = CascadeType.PERSIST, orphanRemoval = true)
   @ManyToMany
   private List<BicicletaEntity> listaDeDeseos;

//   @PodamExclude
//   @OneToMany(mappedBy = "comprador", cascade = CascadeType.PERSIST, orphanRemoval = true)
//   private List<BicicletaEntity> carrito;
//    
//    @PodamExclude
//    @OneToMany(mappedBy = "comprador"
//    private BicicletaDTO bicicletaDTO;
    
    public CompradorEntity()
    {

    }

    /**
     * @return the mediosPago
     */
    public List<MedioPagoEntity> getMediosPago() {
        return mediosPago;
    }

    /**
     * @param mediosPago the mediosPago to set
     */
    public void setMediosPago(List<MedioPagoEntity> mediosPago) {
        this.mediosPago = mediosPago;
    }

    /**
     * @return the ordenes
     */
    public List<OrdenEntity> getOrdenes() {
        return ordenes;
    }

    /**
     * @param ordenes the ordenes to set
     */
    public void setOrdenes(List<OrdenEntity> ordenes) {
        this.ordenes = ordenes;
    }

    /** 
     * La lista de deseos de un comprador. 
     */
    
    public void setListaDeseos(List<BicicletaEntity> bicicletas)
    {
        this.listaDeDeseos = bicicletas;
    }
    
    /**
     * Retorna la lista de deseos de un comprador. 
     * @return La lista de deseos
      */

      public List<BicicletaEntity> getListaDeseos()
      {
          return listaDeDeseos;
      }

//    /** 
//     * Carrito de un comprador. 
//     * @param bicicletasCarrito
//     */
//    
//    public void setCarrito(List<BicicletaEntity> bicicletasCarrito)
//    {
//        this.carrito = bicicletasCarrito;
//    }
    
//    /**
//     * Retorna el carrito de un comprador. 
//     * @return la lista del carrito
//      */
//
//      public List<BicicletaEntity> getCarrito()
//      {
//          return carrito;
//      }

}
