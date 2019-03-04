/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bicicletas.dtos;

import co.edu.uniandes.csw.bicicletas.entities.BicicletaEntity;
import co.edu.uniandes.csw.bicicletas.entities.ResenaEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Andrea
 */
public class BicicletaDetailDTO extends BicicletaDTO implements Serializable{
    
    private List<ResenaDTO> resenas;
    
    
    public BicicletaDetailDTO(){
       //constructor vacio
    }
    
    public BicicletaDetailDTO(BicicletaEntity bikeEntity){
        
        super(bikeEntity);
        if(bikeEntity.getResenas() != null){
            resenas = new ArrayList<ResenaDTO>();
            
            for(ResenaEntity rEntity : bikeEntity.getResenas()){
                resenas.add(new ResenaDTO(rEntity));
            }
        }
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
