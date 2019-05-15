/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bicicletas.dtos;

import co.edu.uniandes.csw.bicicletas.entities.BicicletaEntity;
import co.edu.uniandes.csw.bicicletas.entities.CompradorEntity;
import co.edu.uniandes.csw.bicicletas.entities.MedioPagoEntity;
import co.edu.uniandes.csw.bicicletas.entities.OrdenEntity;
import java.util.List;

/**
 *
 * @author Juan Lozano
 */
public class CompradorDetailDTO extends CompradorDTO {

    private List<OrdenDTO> ordenDTO;

   private List<BicicletaDTO> listaDeDeseosDTO;

//    private List<BicicletaDTO> compradas;
   
//   private List<BicicletaDTO> carritoDTO; 
    
    private List<MedioPagoDTO> medioPagoDTO;

    public CompradorDetailDTO() {
        super();
    }

    public CompradorDetailDTO(CompradorEntity pComprador) {
        super();
        List<OrdenEntity> ordenes = pComprador.getOrdenes();

        List<MedioPagoEntity> pagos = pComprador.getMediosPago();

        List<BicicletaEntity> deseos = pComprador.getListaDeseos();

//        List<BicicletaEntity> carr = pComprador.getCarrito();

    //    List<BicicletaEntity> deseos = pComprador.;
        if (ordenes != null) {
            for (OrdenEntity oE : ordenes) {
                ordenDTO.add(new OrdenDTO(oE));
            }
        }
         if (pagos != null) {
            for (MedioPagoEntity mPE : pagos) {
                medioPagoDTO.add(new MedioPagoDTO(mPE));
            }
        } 
       if (deseos != null) {
           for (BicicletaEntity bE : deseos) {
               listaDeDeseosDTO.add(new BicicletaDTO(bE));
           }
        }

//        if (carr != null) {
//            for (BicicletaEntity bEn : carr) {
//                carritoDTO.add(new BicicletaDTO(bEn));
//            }
//         }
    }

    /**
     * @return the ordenDTO
     */
    public List<OrdenDTO> getOrdenDTO() {
        return ordenDTO;
    }

    /**
     * @param  the ordenDTO to set
     */
    public void setOrdenDTO(List<OrdenDTO> ordenDTO) {
        this.ordenDTO = ordenDTO;
    }

    /**
     * @param ordenDTO the ordenDTO to set
     */
    public void setMediosPago(List<MedioPagoDTO> mediosPago) {
        this.medioPagoDTO = mediosPago;
    }

    /**
     * @return the ordenDTO
     */
    public List<MedioPagoDTO> getMediosPago() {
        return medioPagoDTO;
    }

   /**
    * @return the listaDeDeseos
    */
   public List<BicicletaDTO> getListaDeDeseos() {
       return listaDeDeseosDTO;
   }

   /**
    * @param listaDeDeseos the listaDeDeseos to set
    */
   public void setListaDeDeseos(List<BicicletaDTO> listaDeDeseos) {
       this.listaDeDeseosDTO = listaDeDeseos;
   }

//    /**
//    * @return the listaDeDeseos
//    */
//    public List<BicicletaDTO> getCarrito() {
//        return carritoDTO;
//    }
// 
//    /**
//     * @param listaDeDeseos the listaDeDeseos to set
//     */
//    public void setCarrito(List<BicicletaDTO> listaDeDeseos) {
//        this.carritoDTO = listaDeDeseos;
//    }
    
    
}
