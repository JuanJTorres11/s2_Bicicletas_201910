/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bicicletas.dtos;

import co.edu.uniandes.csw.bicicletas.entities.BicicletaEntity;
import co.edu.uniandes.csw.bicicletas.entities.CompradorEntity;
import co.edu.uniandes.csw.bicicletas.entities.OrdenEntity;
import java.util.List;

/**
 *
 * @author Juan Lozano
 */
public class CompradorDetailDTO extends CompradorDTO {

    private List<OrdenDTO> ordenDTO;

    private List<BicicletaDTO> listaDeDeseos;

    private List<BicicletaDTO> compradas;
    
    private List<BicicletaDTO> carrito; 

    public CompradorDetailDTO() {
        super();
    }

//    public CompradorDetailDTO(CompradorEntity pComprador) {
//        List<OrdenEntity> ordenes = pComprador.getOrdenes();
//        List<BicicletaEntity> deseos = pComprador.getListaDeseos;
//        if (ordenes != null) {
//            for (OrdenEntity oE : ordenes) {
//                ordenDTO.add(new OrdenDTO(oE));
//            }
//        }
//        if (deseos != null) {
//            for (BicicletaEntity mpE : deseos) {
//                listaDeDeseos.add(new BicicletaDTO(mpE));
//            }
//        }
//    }

    /**
     * @return the ordenDTO
     */
    public List<OrdenDTO> getOrdenDTO() {
        return ordenDTO;
    }

    /**
     * @param ordenDTO the ordenDTO to set
     */
    public void setOrdenDTO(List<OrdenDTO> ordenDTO) {
        this.ordenDTO = ordenDTO;
    }

    /**
     * @return the listaDeDeseos
     */
    public List<BicicletaDTO> getListaDeDeseos() {
        return listaDeDeseos;
    }

    /**
     * @param listaDeDeseos the listaDeDeseos to set
     */
    public void setListaDeDeseos(List<BicicletaDTO> listaDeDeseos) {
        this.listaDeDeseos = listaDeDeseos;
    }

    /**
     * @return the compradas
     */
    public List<BicicletaDTO> getCompradas() {
        return compradas;
    }

    /**
     * @param compradas the compradas to set
     */
    public void setCompradas(List<BicicletaDTO> compradas) {
        this.compradas = compradas;
    }

    /**
     * @return the carrito
     */
    public List<BicicletaDTO> getCarrito() {
        return carrito;
    }

    /**
     * @param carrito the carrito to set
     */
    public void setCarrito(List<BicicletaDTO> carrito) {
        this.carrito = carrito;
    }
}
