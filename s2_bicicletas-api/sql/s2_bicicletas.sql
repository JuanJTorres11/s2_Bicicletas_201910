DELETE FROM BicicletaEntity;
DELETE FROM CategoriaEntity;
DELETE FROM CompradorEntity;
DELETE FROM MedioPagoEntity;
delete from VendedorEntity;

INSERT INTO CategoriaEntity (id, nombre) VALUES (100, 'BMX');
INSERT INTO CategoriaEntity (id, nombre) VALUES (200, 'Estatica');