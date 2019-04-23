DELETE FROM BicicletaEntity;
DELETE FROM CategoriaEntity;
DELETE FROM MedioPagoEntity;
DELETE from VentaEntity;
DELETE FROM OrdenEntity;
DELETE FROM MarcaEntity;
delete from VendedorEntity;
DELETE FROM CompradorEntity;

SELECT * FROM VendedorEntity;
SELECT * FROM MedioPagoEntity;
SELECT * FROM VentaEntity;
SELECT * FROM MarcaEntity;
SELECT * FROM OrdenEntity;

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

INSERT INTO VendedorEntity (id, nombre, login, password, telefono) values (1,'Juan Torres', 'jj.torresr', 'abc123', 3102345678);
INSERT INTO VendedorEntity (id, nombre, login, password, telefono) values (2,'Andres Torres', 'aa@gmail.com', 'abc123', 3156345678);
INSERT INTO VendedorEntity (id, nombre, login, password, telefono) values (3,'Andrea', 'gp@outlook.com', 'abc123', 3212345678);
INSERT INTO MedioPagoEntity (numerotarjeta, tipocredito, tipotarjeta, vendedor_id) values (5401345858879821, 'VISA', 'Credito', 1);
INSERT INTO MedioPagoEntity (numerotarjeta, tipocredito, tipotarjeta, vendedor_id) values (5401345858879828, 'MASTERCARD', 'Credito', 3);
INSERT INTO VentaEntity (precio, factura, fotos, vendedor_id) values (123000, 'factura.pdf', CAST (X'FFFF' AS BLOB), 2);