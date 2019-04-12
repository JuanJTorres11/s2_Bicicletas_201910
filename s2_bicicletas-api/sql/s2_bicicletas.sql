DELETE FROM BicicletaEntity;
DELETE FROM CategoriaEntity;
DELETE FROM CompradorEntity;
DELETE FROM MedioPagoEntity;
delete from VendedorEntity;

INSERT INTO CategoriaEntity(nombre) VALUES('Urbana');
INSERT INTO CategoriaEntity(nombre) VALUES('Estatica');
INSERT INTO CategoriaEntity(nombre) VALUES('BMX');
INSERT INTO CategoriaEntity(nombre) VALUES('Montaña');
INSERT INTO CategoriaEntity(nombre) VALUES('Deportiva');
INSERT INTO CategoriaEntity(nombre) VALUES('Todoterreno');

insert into BicicletaEntity (id, calificacion, descripcion, referencia, precio, usada, stock, album, marca_id, categoria_id)

values (100, 1, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua', 'XYZ-123', 800000, 0, 100, 'a', 100,100);