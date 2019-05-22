DELETE FROM albumImages;
DELETE FROM ResenaEntity;
DELETE FROM BicicletaEntity;
DELETE FROM CategoriaEntity;
DELETE FROM MarcaEntity;
DELETE FROM MedioPagoEntity;
DELETE from VentaEntity;
DELETE FROM OrdenEntity;
DELETE FROM VendedorEntity;
DELETE FROM CompradorEntity;

INSERT INTO VendedorEntity (id, nombre, login, password) values (1,'Camilo Serrano', 'camilo@hotmail.com', 'abc123');
INSERT INTO VendedorEntity (id, nombre, login, password) values (2,'Laura Hernandez', 'Laura@gmail.com', 'abc123');
INSERT INTO VendedorEntity (id, nombre, login, password) values (3,'Benito Quintero', 'Bcam@outlook.com', 'abc123');
INSERT INTO VendedorEntity (id, nombre, login, password) values (4, 'Luis Miguel', 'lm@gmail.com', '1234');

insert into CategoriaEntity (id, nombre) values (100, 'Carreras');
insert into CategoriaEntity (id, nombre) values (110, 'Urbana');
insert into CategoriaEntity (id, nombre) values (120, 'Mountain');
insert into CategoriaEntity (id, nombre) values (140, 'Playera');
insert into CategoriaEntity (id, nombre) values (150, 'Kids');
insert into CategoriaEntity (id, nombre) values (160, 'BMX');

insert into CompradorEntity values (1, 'ricardo', 'ricardo@hotmail.com', '1234');

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

insert into MarcaEntity (id, nombre) values (100, 'Astoz');
insert into MarcaEntity (id, nombre) values (200, 'Vols');
insert into MarcaEntity (id, nombre) values (300, 'Fird');
insert into MarcaEntity (id, nombre) values (400, 'Benty');
insert into MarcaEntity (id, nombre) values (500, 'Pont');
insert into MarcaEntity (id, nombre) values (110, 'Cherol');
insert into MarcaEntity (id, nombre) values (310, 'Rover');
insert into MarcaEntity (id, nombre) values (410, 'XTreme');
insert into MarcaEntity (id, nombre) values (510, 'Chrysler');

INSERT INTO VendedorEntity (id, nombre, login, password, telefono) values (1,'Juan Torres', 'jj.torresr', 'abc123', 3102345678);
INSERT INTO VendedorEntity (id, nombre, login, password, telefono) values (2,'Andres Torres', 'aa@gmail.com', 'abc123', 3156345678);
INSERT INTO VendedorEntity (id, nombre, login, password, telefono) values (3,'Andrea', 'gp@outlook.com', 'abc123', 3212345678);
INSERT INTO VendedorEntity (id, nombre, login, password, telefono) values (4, 'Andres Donoso', 'af@gmail.com', '1234', 3215187692);
INSERT INTO MedioPagoEntity (numerotarjeta, tipocredito, tipotarjeta, vendedor_id) values (5401345858879821, 'VISA', 'Credito', 1);
INSERT INTO MedioPagoEntity (numerotarjeta, tipocredito, tipotarjeta, vendedor_id) values (5401345858879828, 'MASTERCARD', 'Credito', 3);
INSERT INTO MedioPagoEntity (numerotarjeta, tipocredito, tipotarjeta, vendedor_id, direccion, fechavencimiento) values (4743077472940495, 'VISA', 'Credito', 4, 'Cra 17b #175-91', '04/25');
INSERT INTO MedioPagoEntity (numerotarjeta, tipocredito, tipotarjeta, vendedor_id, direccion, fechavencimiento) values (5516943513327665, 'MASTERCARD', 'Credito', 4, 'Clle 120 #54-32', '08/43');
INSERT INTO MedioPagoEntity (numerotarjeta, tipocredito, tipotarjeta, vendedor_id, direccion, fechavencimiento) values (5382350133466355, null, 'Debito', 4, 'Cra 53 #74-81', '01/22');
INSERT INTO VentaEntity (precio, factura, fotos, vendedor_id) values (123000, 'factura.pdf', CAST (X'FFFF' AS BLOB), 2);

--BICICLETAS URBANA
INSERT INTO BicicletaEntity (id, calificacion, descripcion, referencia, precio, usada, stock, marca_id, categoria_id) 
                     values (300, 5, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam', 'Winora D-125', 850000, 1, 20, 300, 110);
INSERT INTO albumImages(bicicletaentity_id,album) values(300,'https://media2-synalabs-alltricks.turbobytes.net/medium/x59f1dd321e59e.jpg.pagespeed.ic.ZXFNrBe-DA.jpg');
INSERT INTO albumImages(bicicletaentity_id,album) values(300,'https://media.alltricks.com/medium/59f1dd9c23ff8.jpg');
INSERT INTO albumImages(bicicletaentity_id,album) values(300,'https://media.alltricks.com/medium/59f1dd97715bd.jpg');

INSERT INTO BicicletaEntity (id, calificacion, descripcion, referencia, precio, usada, stock, marca_id, categoria_id) 
                     values (310, 5, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam', 'Inspyre C-127', 730000, 0, 20, 100, 110);
INSERT INTO albumImages(bicicletaentity_id,album) values(310,'https://media1-synalabs-alltricks.turbobytes.net/medium/x5c1b7790982b9.jpg.pagespeed.ic.7NfLfG4AgJ.jpg');
INSERT INTO albumImages(bicicletaentity_id,album) values(310,'https://media.alltricks.com/medium/5c1b7794cb5f5.jpg');
INSERT INTO albumImages(bicicletaentity_id,album) values(310,'https://media.alltricks.com/medium/5c1b7798999a4.jpg');

INSERT INTO BicicletaEntity (id, calificacion, descripcion, referencia, precio, usada, stock, marca_id, categoria_id) 
                     values (320, 3, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam', 'Inspyre C-128', 740000, 0, 20, 300, 110);
INSERT INTO albumImages(bicicletaentity_id,album) values(320,'https://media2-synalabs-alltricks.turbobytes.net/medium/x59b9205f5a012.jpg.pagespeed.ic.uVbPEgY6-Z.jpg');
INSERT INTO albumImages(bicicletaentity_id,album) values(320,'https://media2-synalabs-alltricks.turbobytes.net/medium/x5c386f2cea07d.jpg.pagespeed.ic.qiu_6m2vsq.jpg');
INSERT INTO albumImages(bicicletaentity_id,album) values(320,'https://media1-synalabs-alltricks.turbobytes.net/medium/x5c386f3c29985.jpg.pagespeed.ic.guSSIaRlyE.jpg');

INSERT INTO BicicletaEntity (id, calificacion, descripcion, referencia, precio, usada, stock, marca_id, categoria_id) 
                     values (330, 5, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam', 'Inspyre C-129', 765000, 0, 20, 310, 110);
INSERT INTO albumImages(bicicletaentity_id,album) values(330,'https://favoritebike.com/wp-content/uploads/2017/02/embassy-hombre-blackjazz-01.jpg');

INSERT INTO BicicletaEntity (id, calificacion, descripcion, referencia, precio, usada, stock, marca_id, categoria_id) 
                     values (340, 2, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam', 'Winora B-130', 965000, 0, 20, 310, 110);
INSERT INTO albumImages(bicicletaentity_id,album) values(340,'https://mibicio.cl/wp-content/uploads/2015/02/Yellow-Bullhorn.jpg');

--BICICLETAS PLAYERAS
INSERT INTO BicicletaEntity (id, calificacion, descripcion, referencia, precio, usada, stock, marca_id, categoria_id) 
                     values (100, 4, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam', 'Electra A-123', 300000, 0, 20, 500, 140);
INSERT INTO albumImages(bicicletaentity_id,album) values(100,'http://www.ecoscootershop.com/WebRoot/StoreES2/Shops/ea8289/555B/657E/E6DF/02A4/10EC/0A02/1029/8FA1/37_Summer_noi_turkiz_feher.jpg');


INSERT INTO BicicletaEntity (id, calificacion, descripcion, referencia, precio, usada, stock, marca_id, categoria_id) 
                     values (110, 5, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam', 'Winora A-124', 400000, 0, 20, 110, 140);
INSERT INTO albumImages(bicicletaentity_id,album) values(110,'https://favoritebike.com/wp-content/uploads/2018/04/pave-junior-rosa.jpg');


INSERT INTO BicicletaEntity (id, calificacion, descripcion, referencia, precio, usada, stock, marca_id, categoria_id) 
                     values (120, 5, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam', 'Electra B-124', 400000, 0, 20, 400, 140);
INSERT INTO albumImages(bicicletaentity_id,album) values(120,'http://www.ecoscootershop.com/WebRoot/StoreES2/Shops/ea8289/4D5D/64D3/BEC0/E16C/1A33/D94C/9B1B/EF6C/24_RavennaAlivio_noi_babyblue_feher-krem.jpg');


INSERT INTO BicicletaEntity (id, calificacion, descripcion, referencia, precio, usada, stock, marca_id, categoria_id) 
                     values (130, 4, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam', 'Winora B-124', 600000, 0, 20, 100, 140);
INSERT INTO albumImages(bicicletaentity_id,album) values(130,'https://www.bicicletasvaldes.com/wp-content/uploads/2018/04/CRUISERVERDE.jpg');


INSERT INTO BicicletaEntity (id, calificacion, descripcion, referencia, precio, usada, stock, marca_id, categoria_id) 
                     values (140, 3, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam', 'Winora C-125', 600000, 0, 20, 100, 140);
INSERT INTO albumImages(bicicletaentity_id,album) values(140,'https://cdn.kemik.gt/2015/07/7_24_olympic_dama_playera_freno_coster.jpg');


INSERT INTO BicicletaEntity (id, calificacion, descripcion, referencia, precio, usada, stock, marca_id, categoria_id) 
                     values (150, 5, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam', 'Winora C-125', 600000, 0, 20, 300, 140);
INSERT INTO albumImages(bicicletaentity_id,album) values(150,'http://tiemponoticias.com.ar/noba/wp-content/uploads/2018/05/bicicleta-playera-mujer-full-rodado-26-dama-precio-unico-D_NQ_NP_358811-MLA20636045479_032016-F.jpg');

--BICICLETAS MONTAÑA

INSERT INTO BicicletaEntity (id, calificacion, descripcion, referencia, precio, usada, stock, marca_id, categoria_id) 
                     values (200, 3, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam', 'Inspyre A-123', 900000, 0, 20, 300, 120);
INSERT INTO albumImages(bicicletaentity_id,album) values(200,'https://media.alltricks.com/hd/5c2f265f9cc40.jpg');
INSERT INTO albumImages(bicicletaentity_id,album) values(200,'https://media2-synalabs-alltricks.turbobytes.net/medium/x5c2f26705c6aa.jpg.pagespeed.ic.XgYq9OMKpT.jpg');
INSERT INTO albumImages(bicicletaentity_id,album) values(200,'https://media2-synalabs-alltricks.turbobytes.net/medium/x5c2f267982e74.jpg.pagespeed.ic.Pi0Og6pZMu.jpg');
INSERT INTO albumImages(bicicletaentity_id,album) values(200,'https://media2-synalabs-alltricks.turbobytes.net/medium/x5c2f2668abd6e.jpg.pagespeed.ic.SBs4yGBR6a.jpg');

INSERT INTO BicicletaEntity (id, calificacion, descripcion, referencia, precio, usada, stock, marca_id, categoria_id) 
                     values (210, 3, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam', 'Mongoose B-123', 600000, 1, 20, 100, 120);
INSERT INTO albumImages(bicicletaentity_id,album) values(210,'https://images-na.ssl-images-amazon.com/images/I/81LFW3btqPL._SX466_.jpg');

INSERT INTO BicicletaEntity (id, calificacion, descripcion, referencia, precio, usada, stock, marca_id, categoria_id) 
                     values (220, 5, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam', 'Mongoose B-125', 800000, 0, 20, 100, 120);
INSERT INTO albumImages(bicicletaentity_id,album) values(220,'https://http2.mlstatic.com/bicicleta-montana-trek-marlin-4-2018-completamente-nueva-D_NQ_NP_640787-MLM26589399585_012018-F.jpg');

--BICICLETAS BMX
INSERT INTO BicicletaEntity (id, calificacion, descripcion, referencia, precio, usada, stock, marca_id, categoria_id) 
                     values (400, 4, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam', 'Sunn A-123', 330000, 0, 80, 510, 160);
INSERT INTO albumImages(bicicletaentity_id,album) values(400,'https://media2-synalabs-alltricks.turbobytes.net/medium/x5b69603e41318.jpg.pagespeed.ic.MS7iiLGgQM.jpg');
INSERT INTO albumImages(bicicletaentity_id,album) values(400,'https://media.alltricks.com/medium/5b696043dcde2.jpg');

INSERT INTO BicicletaEntity (id, calificacion, descripcion, referencia, precio, usada, stock, marca_id, categoria_id) 
                     values (410, 4, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam', 'Sunn B-123', 645000, 0, 50, 410, 160);
INSERT INTO albumImages(bicicletaentity_id,album) values(410,'https://media1-synalabs-alltricks.turbobytes.net/medium/x5c1b6541ee742.jpg.pagespeed.ic.CBT5T8nIa8.jpg');

INSERT INTO BicicletaEntity (id, calificacion, descripcion, referencia, precio, usada, stock, marca_id, categoria_id) 
                     values (420, 3, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam', 'Sunn C-124', 645000, 0, 50, 310, 160);
INSERT INTO albumImages(bicicletaentity_id,album) values(420,'https://media1-synalabs-alltricks.turbobytes.net/medium/x5a3786822e26a.jpg.pagespeed.ic.hiajU4QfNN.jpg');

INSERT INTO BicicletaEntity (id, calificacion, descripcion, referencia, precio, usada, stock, marca_id, categoria_id) 
                     values (430, 3, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam', 'Sunn D-125', 645000, 0, 50, 100, 160);
INSERT INTO albumImages(bicicletaentity_id,album) values(430,'https://media1-synalabs-alltricks.turbobytes.net/medium/x5acf6fb9cec67.jpg.pagespeed.ic.HY25SRD7Nz.jpg');

--BICICLETAS CARRERAS

INSERT INTO BicicletaEntity (id, calificacion, descripcion, referencia, precio, usada, stock, marca_id, categoria_id) 
                     values (500, 4, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam', 'Mongoose F-145', 330000, 0, 80, 510, 100);
INSERT INTO albumImages(bicicletaentity_id,album) values(500,'https://http2.mlstatic.com/bicicleta-carrera-ruta-nueva-golden-importador-puede-retira-D_NQ_NP_937307-MCO25761689171_072017-F.jpg');

INSERT INTO BicicletaEntity (id, calificacion, descripcion, referencia, precio, usada, stock, marca_id, categoria_id) 
                     values (510, 3, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam', 'Sunn G-146', 360000, 0, 80, 510, 100);
INSERT INTO albumImages(bicicletaentity_id,album) values(510,'https://http2.mlstatic.com/bicicleta-carrera-ruta-nueva-golden-importador-D_NQ_NP_703010-MCO25759876772_072017-F.jpg');

INSERT INTO BicicletaEntity (id, calificacion, descripcion, referencia, precio, usada, stock, marca_id, categoria_id) 
                     values (520, 4, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam', 'Mongoose H-123', 330000, 0, 80, 500, 100);
INSERT INTO albumImages(bicicletaentity_id,album) values(520,'https://http2.mlstatic.com/bicicleta-carrera-ruta-nueva-aspa-D_NQ_NP_686049-MCO25759862345_072017-F.jpg');

INSERT INTO BicicletaEntity (id, calificacion, descripcion, referencia, precio, usada, stock, marca_id, categoria_id) 
                     values (530, 5, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam', 'Sunn Z-123', 390000, 1, 80, 510, 100);
INSERT INTO albumImages(bicicletaentity_id,album) values(530,'https://tienda.benotto.com/images/Producto/49801/Bicicleta-Benotto-570-Ruta-Alum-R700C-14V-Shi-TY18-Fnos-Carrera-Blanca-54cm-RRU570701454BL.jpg');

--BICICLETAS NIÑOS

INSERT INTO BicicletaEntity (id, calificacion, descripcion, referencia, precio, usada, stock, marca_id, categoria_id) 
                     values (600, 4, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam', 'Play A-123', 330000, 0, 80, 510, 150);
INSERT INTO albumImages(bicicletaentity_id,album) values(600,'https://carrascoesciclismo.es/wp-content/uploads/2016/11/Bicicleta-para-ni%C3%B1o-de-8-a%C3%B1os-verde.jpg');

INSERT INTO BicicletaEntity (id, calificacion, descripcion, referencia, precio, usada, stock, marca_id, categoria_id) 
                     values (610, 4, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam', 'Play A-124', 390000, 0, 80, 510, 150);
INSERT INTO albumImages(bicicletaentity_id,album) values(610,'https://www.clootbike.com/images/products/bicicletas-nino-20-pulgadas-1.jpg');

INSERT INTO BicicletaEntity (id, calificacion, descripcion, referencia, precio, usada, stock, marca_id, categoria_id) 
                     values (620, 4, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam', 'Play A-125', 330000, 0, 90, 510, 150);
INSERT INTO albumImages(bicicletaentity_id,album) values(620,'https://static.evanscycles.com/production/bikes/kids-bikes/product-image/969-638/cube-kid-200-20-inch-2018-kids-bike-white-EV319889-9000-1.jpg');

INSERT INTO BicicletaEntity (id, calificacion, descripcion, referencia, precio, usada, stock, marca_id, categoria_id) 
                     values (630, 4, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam', 'Play A-126', 330000, 0, 30, 510, 150);
INSERT INTO albumImages(bicicletaentity_id,album) values(630,'https://static.evanscycles.com/production/bikes/kids-bikes/product-image/Original/cube-kid-240-24-inch-2018-kids-bike-red-EV319892-3000-1.jpg');

INSERT INTO BicicletaEntity (id, calificacion, descripcion, referencia, precio, usada, stock, marca_id, categoria_id) 
                     values (640, 4, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam', 'Kids A-123', 340000, 0, 78, 510, 150);
INSERT INTO albumImages(bicicletaentity_id,album) values(640,'https://isabela.iweb.co.uk/image/aHR0cHM6Ly93d3cucmFsZWlnaC5jby51ay9tZWRpYS9jYXRhbG9nL3Byb2R1Y3QvbS9pL21pbmlzaGVyd29vZC5qcGc%3D/?q=75&w=2646&h=2646&e=1209600&t=outbound');

INSERT INTO BicicletaEntity (id, calificacion, descripcion, referencia, precio, usada, stock, marca_id, categoria_id) 
                     values (650, 4, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam', 'Kids A-124', 3670000, 0, 80, 510, 150);
INSERT INTO albumImages(bicicletaentity_id,album) values(650,'https://isabela.iweb.co.uk/image/aHR0cHM6Ly93d3cucmFsZWlnaC5jby51ay9tZWRpYS9jYXRhbG9nL3Byb2R1Y3QvbS9vL21vbDE2Z3JfMl8xLmpwZw%3D%3D/?q=75&w=2646&h=2646&e=1209600&t=outbound');

INSERT INTO BicicletaEntity (id, calificacion, descripcion, referencia, precio, usada, stock, marca_id, categoria_id) 
                     values (660, 4, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam', 'Kids A-125', 380000, 0, 10, 510, 150);
INSERT INTO albumImages(bicicletaentity_id,album) values(660,'https://cdn.shopify.com/s/files/1/1250/7975/products/FROGBIKES_43_ORANGE_grande.jpg?v=1551997109');

INSERT INTO BicicletaEntity (id, calificacion, descripcion, referencia, precio, usada, stock, marca_id, categoria_id) 
                     values (670, 4, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam', 'Kids A-126', 330000, 0, 80, 510, 150);
INSERT INTO albumImages(bicicletaentity_id,album) values(670,'https://media.4rgos.it/i/Argos/6879675_R_Z001A?w=750&h=440&qlt=70');

insert into ItemCarritoEntity values (1, 500);

SELECT * FROM ResenaEntity;
SELECT * FROM BicicletaEntity;
SELECT * FROM CategoriaEntity;
SELECT * FROM MarcaEntity;
select * from ItemCarritoEntity;
select * from VendedorEntity;
select * from CompradorEntity;