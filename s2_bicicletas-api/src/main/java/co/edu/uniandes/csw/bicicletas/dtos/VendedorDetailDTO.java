/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bicicletas.dtos;

import co.edu.uniandes.csw.bicicletas.entities.MedioPagoEntity;
import co.edu.uniandes.csw.bicicletas.entities.VendedorEntity;
import co.edu.uniandes.csw.bicicletas.entities.VentaEntity;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Juan José Torres
 */
public class VendedorDetailDTO extends VendedorDTO
{

    private List<VentaDTO> ventas;
    
    private List<MedioPagoDTO> mediosPago;

    public VendedorDetailDTO()
    {
        super();
    }

    public VendedorDetailDTO (VendedorEntity vendedor)
    {
        super();
        List<VentaEntity> listaVentas = vendedor.getVentas();
        List<MedioPagoEntity> listaPagos = vendedor.getMediosPago();
        if (listaVentas != null)
            for (VentaEntity vE : listaVentas)
                ventas.add(new VentaDTO(vE));
        if (listaPagos != null)
            for (MedioPagoEntity mpE : listaPagos)
                mediosPago.add(new MedioPagoDTO(mpE));
    }

    /**
     * @return the ventas
     */
    public List<VentaDTO> getVentas()
    {
        return ventas;
    }

    /**
     * @param ventas the ventas to set
     */
    public void setVentas(List<VentaDTO> ventas)
    {
        this.ventas = ventas;
    }
    
    /**
     * Crea un objeto de tipo VendedorEntity con los atributos de las listas.
     * @return
     */
    @Override
    public VendedorEntity toEntity()
    {
        VendedorEntity vendedor = super.toEntity();
        if (ventas != null)
        {
            ArrayList<VentaEntity> ventasEntity = new ArrayList<>();
            for(VentaDTO venta: ventas)
                ventasEntity.add(venta.toEntity());
            vendedor.setVentas(ventasEntity);
        }
        if (mediosPago != null)
        {
            ArrayList<MedioPagoEntity> pagos = new ArrayList<>();
            for(MedioPagoDTO medios: mediosPago)
                pagos.add(medios.toEntity());
            vendedor.setMediosPago(pagos);
        }
        return vendedor;
    }

    /**
     * @return the mediosPago
     */
    public List<MedioPagoDTO> getMediosPago()
    {
        return mediosPago;
    }

    /**
     * @param mediosPago the mediosPago to set
     */
    public void setMediosPago(List<MedioPagoDTO> mediosPago)
    {
        this.mediosPago = mediosPago;
    }
}
