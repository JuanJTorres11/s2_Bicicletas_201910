/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bicicletas.dtos;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Andrea
 */
public class BicicletaDetailDTO implements Serializable{
    
    private List<ResenaDTO> resenas;
    
    
    public BicicletaDetailDTO(){
        
    }

    /**
     * @return the resenas
     */
    public List<ResenaDTO> getResenas() {
        return resenas;
    }

    /**
     * @param resenas the resenas to set
     */
    public void setResenas(List<ResenaDTO> resenas) {
        this.resenas = resenas;
    }
    
}
