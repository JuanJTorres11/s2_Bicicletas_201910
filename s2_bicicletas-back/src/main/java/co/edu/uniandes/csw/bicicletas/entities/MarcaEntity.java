/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bicicletas.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author Mateo
 */

@Entity
public class MarcaEntity extends BaseEntity implements Serializable{
    
    /**
     * Nombre de la marca
     */
    private String nombre;
    
    @PodamExclude
    @OneToMany(mappedBy="marca", cascade = CascadeType.PERSIST)
    private List<BicicletaEntity> bicicletasMarca;

    public MarcaEntity(){
        
    }
    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the bicicletasMarca
     */
    public List<BicicletaEntity> getBicicletasMarca() {
        return bicicletasMarca;
    }

    /**
     * @param bicicletasMarca the bicicletasMarca to set
     */
    public void setBicicletasMarca(List<BicicletaEntity> bicicletasMarca) {
        this.bicicletasMarca = bicicletasMarca;
    }

    
}
