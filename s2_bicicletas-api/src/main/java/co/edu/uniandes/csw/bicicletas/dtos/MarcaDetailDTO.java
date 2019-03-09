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
        if(marcaEntity.getBicicletasMarca()!=null){
            bicicletasMarca=new ArrayList<BicicletaDTO>();
            for(BicicletaEntity bike: marcaEntity.getBicicletasMarca()){
                bicicletasMarca.add(new BicicletaDTO(bike));
            }
        }
    }
    
    @Override
    public MarcaEntity toEntity(){
        MarcaEntity marcaEntity = super.toEntity();
        if(getBicicletasMarca()!=null){
            List<BicicletaEntity> bicicletasEntity = new ArrayList<BicicletaEntity>();
            for (BicicletaDTO dtoBicicleta : getBicicletasMarca()) {
                bicicletasEntity.add(dtoBicicleta.toEntity());
            }
            marcaEntity.setBicicletasMarca(bicicletasEntity);
        }
        return marcaEntity;
    }

    /**
     * @return the bicicletasMarca
     */
    public List<BicicletaDTO> getBicicletasMarca() {
        return bicicletasMarca;
    }

    /**
     * @param bicicletasMarca the bicicletasMarca to set
     */
    public void setBicicletasMarca(List<BicicletaDTO> bicicletasMarca) {
        this.bicicletasMarca = bicicletasMarca;
    }
    
}
