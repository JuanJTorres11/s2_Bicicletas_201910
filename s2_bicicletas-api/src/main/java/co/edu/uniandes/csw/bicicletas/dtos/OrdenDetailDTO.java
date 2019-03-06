/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bicicletas.dtos;

import co.edu.uniandes.csw.bicicletas.entities.OrdenEntity;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Mateo
 */
public class OrdenDetailDTO extends OrdenDTO implements Serializable {

    /**
     * Lista de tipo BicicletaDTO compradas en una orden
     */
    //private List<BicicletaDTO> bicicletas;
    /**
     * Constructor por defecto
     */
    public OrdenDetailDTO() {

    }

    public OrdenDetailDTO(OrdenEntity ordenEntity) {
        super(ordenEntity);
        if (ordenEntity != null) {
            // if (ordenEntity.getBooks() != null) {
            // books = new ArrayList<>();
            // for (BookEntity entityBook : ordenEntity.getBooks()) {
            //     books.add(new BookDTO(entityBook));
            //   }
            // }
        }
    }

    @Override
    public OrdenEntity toEntity() {
        OrdenEntity ordenEntity = super.toEntity();
       // if (books != null) {
         //   List<BookEntity> booksEntity = new ArrayList<>();
         //   for (BookDTO dtoBook : books) {
        //        booksEntity.add(dtoBook.toEntity());
       //     }
       //     ordenEntity.setBooks(booksEntity);
       // }
        return ordenEntity;
    }
}
