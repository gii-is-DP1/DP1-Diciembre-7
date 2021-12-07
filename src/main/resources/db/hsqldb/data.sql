-- One admin user, named admin1 with passwor 4dm1n and authority admin
INSERT INTO users(username,password,enabled) VALUES ('admin1','4dm1n',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (1,'admin1','admin');
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

INSERT INTO tipos_vehiculo VALUES (1, 'SUV');
INSERT INTO tipos_vehiculo VALUES (2, 'Turismo');
INSERT INTO tipos_vehiculo VALUES (3, 'Todoterreno');
INSERT INTO tipos_vehiculo VALUES (4, 'Furgoneta');
INSERT INTO tipos_vehiculo VALUES (5, 'Lancha');
INSERT INTO tipos_vehiculo VALUES (6, 'Velero');
INSERT INTO tipos_vehiculo VALUES (7, 'Yate');
INSERT INTO tipos_vehiculo VALUES (8, 'Moto de agua');

INSERT INTO cliente VALUES (1, 'example@example.com','Javier','112345678','Avenida Reina Mercedes s/n','98765432B','cliente1');
INSERT INTO cliente VALUES (2, 'simeon@simeon.com','Simeon','123456789','Avenida Reina Mercedes s/n','12641431V','cliente2');

INSERT INTO empresa VALUES (1, 'example@example.com','Universidad de Sevilla','124354768','España','empresa1');
INSERT INTO empresa VALUES (2, 'ejemplo@ejemplo.com','Aytos','123456789','España','empresa2');

INSERT INTO conductor VALUES(1, 'simeon@simeon.com','Simeon','123456789','Sevilla','12641431V','7',FALSE,TRUE,150.0,12.0,'conductor1');
INSERT INTO conductor VALUES(2, 'example@example.com','Javier','112345678','Sevilla','98765432B','9',TRUE,TRUE,180.0,20.0,'conductor2');

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

INSERT INTO reservas(id,fecha_inicio,fecha_fin,precio_final,ciudad,cliente_id,vehiculo_id,conductor_id) VALUES (1, '2020-10-12', '2020-10-15', 200.0,'Sevilla',1,1,2);

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

