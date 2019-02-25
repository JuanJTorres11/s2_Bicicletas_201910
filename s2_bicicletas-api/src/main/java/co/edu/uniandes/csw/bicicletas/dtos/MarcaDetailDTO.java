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
    private List<BicicletaDTO> bicicletas;
    
    /**
     * Constructor por defecto
     */
    public MarcaDetailDTO(){
    
    }
    
    public MarcaDetailDTO(MarcaEntity marcaEntity){
        super(marcaEntity);
        if (marcaEntity != null) {
            if (marcaEntity.getBicicletas() != null) {
                bicicletas = new ArrayList<>();
                for (BicicletaEntity entityBicicleta : marcaEntity.getBicicletas()) {
                   // bicicletas.add(new BicicletaDTO(entityBicicleta));
                }
            }
        }
    }
    
    @Override
    public MarcaEntity toEntity(){
        MarcaEntity marcaEntity = super.toEntity();
        if (bicicletas != null) {
            List<BicicletaEntity> bicicletasEntity = new ArrayList<>();
            for (BicicletaDTO dtoBicicleta : bicicletas) {
             //   bicicletasEntity.add(dtoBicicleta.toEntity());
            }
            marcaEntity.setBicicletas(bicicletasEntity);
        }
        return marcaEntity;
    }
    
}
