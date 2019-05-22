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

    private List<MedioPagoDTO> medioPagoDTO;

    public CompradorDetailDTO() {
        super();
    }

    public CompradorDetailDTO(CompradorEntity pComprador) {
        super();
        List<OrdenEntity> ordenes = pComprador.getOrdenes();

        List<MedioPagoEntity> pagos = pComprador.getMediosPago();

        List<BicicletaEntity> deseos = pComprador.getListaDeseos();

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
    }

    /**
     * @return the ordenDTO
     */
    public List<OrdenDTO> getOrdenDTO() {
        return ordenDTO;
    }

    /**
     * @param the ordenDTO to set
     */
    public void setOrdenDTO(List<OrdenDTO> ordenDTO) {
        this.ordenDTO = ordenDTO;
    }

    /**
     * @param ordenDTO the ordenDTO to set
     */
    public void setMediosPagoDTO(List<MedioPagoDTO> mediosPago) {
        this.medioPagoDTO = mediosPago;
    }

    /**
     * @return the ordenDTO
     */
    public List<MedioPagoDTO> getMediosPagoDTO() {
        return medioPagoDTO;
    }

    /**
     * @return the listaDeDeseosDTO
     */
    public List<BicicletaDTO> getListaDeDeseosDTO() {
        return listaDeDeseosDTO;
    }

    /**
     * @param listaDeDeseosDTO the listaDeDeseosDTO to set
     */
    public void setListaDeDeseosDTO(List<BicicletaDTO> listaDeDeseosDTO) {
        this.listaDeDeseosDTO = listaDeDeseosDTO;
    }

}
