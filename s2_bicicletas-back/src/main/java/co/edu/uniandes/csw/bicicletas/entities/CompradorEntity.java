/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bicicletas.entities;


import java.util.List;
import java.util.logging.Logger;
import javax.persistence.Entity;
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
    @OneToMany(mappedBy = "comprador")
    private List<MedioPagoEntity> mediosPago;
    
    @PodamExclude
    @OneToMany(mappedBy = "comprador")
    private List<OrdenEntity> ordenes;
    
//    @PodamExclude
//    @OneToMany(mappedBy = "")
//    private List<BicicletaDTO> listaDeDeseos;
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
       
}
