/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bicicletas.dtos;

/**
 *
 * @author 
 */
public class VentaDetailDTO extends VentaDTO
{
    /**
     * Objeto bicicleta a vender.
     */
    private BicicletaDTO bicicletaDTO;
    
    public VentaDetailDTO () {
        super();
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
}