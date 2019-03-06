/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bicicletas.dtos;

import co.edu.uniandes.csw.bicicletas.entities.BicicletaEntity;
import co.edu.uniandes.csw.bicicletas.entities.MarcaEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mateo
 */
public class MarcaDetailDTO extends MarcaDTO implements Serializable{
    
    /**
     * Lista de tipo BicicletaDTO asociada a una marca
     */
    private List<BicicletaDTO> bicicletasMarca;
    
    /**
     * Constructor por defecto
     */
    public MarcaDetailDTO(){
    
    }
    
    public MarcaDetailDTO(MarcaEntity marcaEntity){
        super(marcaEntity);
        
    }
    
    @Override
    public MarcaEntity toEntity(){
        MarcaEntity marcaEntity = super.toEntity();
        
        return marcaEntity;
    }
    
}
