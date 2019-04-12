DELETE FROM BicicletaEntity;
DELETE FROM CategoriaEntity;
DELETE FROM MedioPagoEntity;
DELETE from VentaEntity;
delete from VendedorEntity;
DELETE FROM CompradorEntity;
DELETE FROM OrdenEntity;
DELETE FROM MarcaEntity;



insert into OrdenEntity (fecha, cantidad, costoTotal) values ('07/11/2018', 2, 104.04);
insert into OrdenEntity (fecha, cantidad, costoTotal) values ('21/06/2018', 5, 126.01);
insert into OrdenEntity (fecha, cantidad, costoTotal) values ('05/01/2019', 4, 169.68);
insert into OrdenEntity (fecha, cantidad, costoTotal) values ('10/12/2018', 8, 113.07);
insert into OrdenEntity (fecha, cantidad, costoTotal) values ('19/07/2018', 10, 166.06);
insert into OrdenEntity (fecha, cantidad, costoTotal) values ('20/05/2018', 6, 76.48);
insert into OrdenEntity (fecha, cantidad, costoTotal) values ('14/08/2018', 5, 162.66);
insert into OrdenEntity (fecha, cantidad, costoTotal) values ('19/06/2018', 7, 111.45);
insert into OrdenEntity (fecha, cantidad, costoTotal) values ('07/11/2018', 3, 159.56);
insert into OrdenEntity (fecha, cantidad, costoTotal) values ('14/08/2018', 9, 122.43);

insert into MarcaEntity (nombre) values ('Astoz');
insert into MarcaEntity (nombre) values ('Vols');
insert into MarcaEntity (nombre) values ('Fird');
insert into MarcaEntity (nombre) values ('Benty');
insert into MarcaEntity (nombre) values ('Pont');
insert into MarcaEntity (nombre) values ('Cherol');
insert into MarcaEntity (nombre) values ('BMX');
insert into MarcaEntity (nombre) values ('Rover');
insert into MarcaEntity (nombre) values ('XTreme');
insert into MarcaEntity (nombre) values ('Chrysler');

SELECT * FROM MarcaEntity;
SELECT * FROM OrdenEntity;
