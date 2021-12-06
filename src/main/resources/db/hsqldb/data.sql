-- One admin user, named admin1 with passwor 4dm1n and authority admin
INSERT INTO users(username,password,enabled) VALUES ('admin1','4dm1n',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (1,'admin1','admin');
-- One owner user, named owner1 with passwor 0wn3r
INSERT INTO users(username,password,enabled) VALUES ('owner1','0wn3r',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (2,'owner1','owner');
-- One vet user, named vet1 with passwor v3t
INSERT INTO users(username,password,enabled) VALUES ('vet1','v3t',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (3,'vet1','veterinarian');
-- One client user, named cliente1 with password cliente1
INSERT INTO users(username,password,enabled) VALUES('cliente1','cliente1',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (4, 'cliente1', 'cliente');
-- One client user, named cliente2 with password cliente2
INSERT INTO users(username,password,enabled) VALUES('cliente2','cliente2',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (5, 'cliente2', 'cliente');
-- One empresa user, named empresa1 with password empresa1
INSERT INTO users(username,password,enabled) VALUES('empresa1','empresa1',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (6, 'empresa1', 'empresa');
-- One empresa user, named empresa2 with password empresa2
INSERT INTO users(username,password,enabled) VALUES('empresa2','empresa2',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (7, 'empresa2', 'empresa');
-- One empresa user, named conductor2 with password conductor2
INSERT INTO users(username,password,enabled) VALUES('conductor2','conductor2',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (8, 'conductor2', 'conductor');
-- One conductor user, named conductor1 with password conductor1
INSERT INTO users(username,password,enabled) VALUES('conductor1','conductor1',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (9, 'conductor1', 'conductor');

INSERT INTO vets VALUES (1, 'James', 'Carter');
INSERT INTO vets VALUES (2, 'Helen', 'Leary');
INSERT INTO vets VALUES (3, 'Linda', 'Douglas');
INSERT INTO vets VALUES (4, 'Rafael', 'Ortega');
INSERT INTO vets VALUES (5, 'Henry', 'Stevens');
INSERT INTO vets VALUES (6, 'Sharon', 'Jenkins');

INSERT INTO specialties VALUES (1, 'radiology');
INSERT INTO specialties VALUES (2, 'surgery');
INSERT INTO specialties VALUES (3, 'dentistry');

INSERT INTO vet_specialties VALUES (2, 1);
INSERT INTO vet_specialties VALUES (3, 2);
INSERT INTO vet_specialties VALUES (3, 3);
INSERT INTO vet_specialties VALUES (4, 2);
INSERT INTO vet_specialties VALUES (5, 1);

INSERT INTO tipos_vehiculo VALUES (1, 'SUV');
INSERT INTO tipos_vehiculo VALUES (2, 'Turismo');
INSERT INTO tipos_vehiculo VALUES (3, 'Todoterreno');
INSERT INTO tipos_vehiculo VALUES (4, 'Furgoneta');
INSERT INTO tipos_vehiculo VALUES (5, 'Lancha');
INSERT INTO tipos_vehiculo VALUES (6, 'Velero');
INSERT INTO tipos_vehiculo VALUES (7, 'Yate');
INSERT INTO tipos_vehiculo VALUES (8, 'Moto de agua');

INSERT INTO types VALUES (1, 'cat');
INSERT INTO types VALUES (2, 'dog');
INSERT INTO types VALUES (3, 'lizard');
INSERT INTO types VALUES (4, 'snake');
INSERT INTO types VALUES (5, 'bird');
INSERT INTO types VALUES (6, 'hamster');

INSERT INTO cliente VALUES (1, 'example@example.com','Javier','665738445','Avenida Reina Mercedes s/n','17473663B','cliente1');
INSERT INTO cliente VALUES (2, 'simeon@simeon.com','Simeon','123456789','Avenida Reina Mercedes s/n','12641431V','cliente2');

INSERT INTO empresa VALUES (1, 'example@example.com','Universidad de Sevilla','665738445','España','empresa1');
INSERT INTO empresa VALUES (2, 'ejemplo@ejemplo.com','Aytos','123456789','España','empresa2');

INSERT INTO conductor VALUES(1, 'simeon@simeon.com','Simeon','123456789','Sevilla','12641431V','7',TRUE,FALSE,150.0,12.0,'conductor1');
INSERT INTO conductor VALUES(2, 'example@example.com','Javier','665738445','Sevilla','17473663B','9',TRUE,TRUE,180.0,20.0,'conductor2');

INSERT INTO oficinas(id,ciudad,codigo_postal,direccion,empresa_id) VALUES(1,'Sevilla', 41400,'Avenida Reina Mercedes s/n', 1);
INSERT INTO oficinas(id,ciudad,codigo_postal,direccion,empresa_id) VALUES(2,'Madrid', 41400,'Plaza del Sol', 1);
INSERT INTO oficinas(id,ciudad,codigo_postal,direccion,empresa_id) VALUES(3,'Sevilla', 41400,'Plaza de España', 1);
INSERT INTO oficinas(id,ciudad,codigo_postal,direccion,empresa_id) VALUES(4,'Malaga', 41400,'Puerto deportivo', 2);

INSERT INTO vehiculos(id,marca,modelo,precio_base,precio_por_dia,stock,tipo_vehiculo_id) VALUES (1,'Seat','Leon',150.0,15.0,50,2);
INSERT INTO vehiculos(id,marca,modelo,precio_base,precio_por_dia,stock,tipo_vehiculo_id) VALUES (2,'Seat','Ibiza',120.0,12.0,80,2);
INSERT INTO vehiculos(id,marca,modelo,precio_base,precio_por_dia,stock,tipo_vehiculo_id) VALUES (3,'Land Rover','Range Rover',150.0,15.0,20,3);
INSERT INTO vehiculos(id,marca,modelo,precio_base,precio_por_dia,stock,tipo_vehiculo_id) VALUES (4,'Quicksilver','Velero deportivo',350.0,25.0,10,6);
INSERT INTO vehiculos(id,marca,modelo,precio_base,precio_por_dia,stock,tipo_vehiculo_id) VALUES (5,'Renault','Clio',150.0,15.0,80,2);
INSERT INTO vehiculos(id,marca,modelo,precio_base,precio_por_dia,stock,tipo_vehiculo_id) VALUES (6,'Renault','Megane',250.0,15.0,40,2);
INSERT INTO vehiculos(id,marca,modelo,precio_base,precio_por_dia,stock,tipo_vehiculo_id) VALUES (7,'Ford','Focus',150.0,15.0,50,2);
INSERT INTO vehiculos(id,marca,modelo,precio_base,precio_por_dia,stock,tipo_vehiculo_id) VALUES (8,'Dacia','Sandero',220.0,20.0,10,2);
INSERT INTO vehiculos(id,marca,modelo,precio_base,precio_por_dia,stock,tipo_vehiculo_id) VALUES (9,'Nissan','Qashqai',250.0,22.0,35,2);

INSERT INTO vehiculos_oficinas(oficina_id,vehiculo_id) VALUES (1,1);
INSERT INTO vehiculos_oficinas(oficina_id,vehiculo_id) VALUES (1,2);
INSERT INTO vehiculos_oficinas(oficina_id,vehiculo_id) VALUES (1,3);
INSERT INTO vehiculos_oficinas(oficina_id,vehiculo_id) VALUES (4,4);
INSERT INTO vehiculos_oficinas(oficina_id,vehiculo_id) VALUES (2,5);
INSERT INTO vehiculos_oficinas(oficina_id,vehiculo_id) VALUES (2,1);
INSERT INTO vehiculos_oficinas(oficina_id,vehiculo_id) VALUES (3,8);
INSERT INTO vehiculos_oficinas(oficina_id,vehiculo_id) VALUES (1,9);
INSERT INTO vehiculos_oficinas(oficina_id,vehiculo_id) VALUES (1,7);
INSERT INTO vehiculos_oficinas(oficina_id,vehiculo_id) VALUES (3,6);
INSERT INTO vehiculos_oficinas(oficina_id,vehiculo_id) VALUES (3,9);




INSERT INTO owners VALUES (1, 'George', 'Franklin', '110 W. Liberty St.', 'Madison', '6085551023', 'owner1');
INSERT INTO owners VALUES (2, 'Betty', 'Davis', '638 Cardinal Ave.', 'Sun Prairie', '6085551749', 'owner1');
INSERT INTO owners VALUES (3, 'Eduardo', 'Rodriquez', '2693 Commerce St.', 'McFarland', '6085558763', 'owner1');
INSERT INTO owners VALUES (4, 'Harold', 'Davis', '563 Friendly St.', 'Windsor', '6085553198', 'owner1');
INSERT INTO owners VALUES (5, 'Peter', 'McTavish', '2387 S. Fair Way', 'Madison', '6085552765', 'owner1');
INSERT INTO owners VALUES (6, 'Jean', 'Coleman', '105 N. Lake St.', 'Monona', '6085552654', 'owner1');
INSERT INTO owners VALUES (7, 'Jeff', 'Black', '1450 Oak Blvd.', 'Monona', '6085555387', 'owner1');
INSERT INTO owners VALUES (8, 'Maria', 'Escobito', '345 Maple St.', 'Madison', '6085557683', 'owner1');
INSERT INTO owners VALUES (9, 'David', 'Schroeder', '2749 Blackhawk Trail', 'Madison', '6085559435', 'owner1');
INSERT INTO owners VALUES (10, 'Carlos', 'Estaban', '2335 Independence La.', 'Waunakee', '6085555487', 'owner1');

INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (1, 'Leo', '2010-09-07', 1, 1);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (2, 'Basil', '2012-08-06', 6, 2);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (3, 'Rosy', '2011-04-17', 2, 3);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (4, 'Jewel', '2010-03-07', 2, 3);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (5, 'Iggy', '2010-11-30', 3, 4);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (6, 'George', '2010-01-20', 4, 5);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (7, 'Samantha', '2012-09-04', 1, 6);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (8, 'Max', '2012-09-04', 1, 6);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (9, 'Lucky', '2011-08-06', 5, 7);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (10, 'Mulligan', '2007-02-24', 2, 8);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (11, 'Freddy', '2010-03-09', 5, 9);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (12, 'Lucky', '2010-06-24', 2, 10);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (13, 'Sly', '2012-06-08', 1, 10);

INSERT INTO visits(id,pet_id,visit_date,description) VALUES (1, 7, '2013-01-01', 'rabies shot');
INSERT INTO visits(id,pet_id,visit_date,description) VALUES (2, 8, '2013-01-02', 'rabies shot');
INSERT INTO visits(id,pet_id,visit_date,description) VALUES (3, 8, '2013-01-03', 'neutered');
INSERT INTO visits(id,pet_id,visit_date,description) VALUES (4, 7, '2013-01-04', 'spayed');

