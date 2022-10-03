insert into tbl_persona (nombre, genero, direccion, telefono, edad, status) values ('Jose Lema', 'MASCULINO', 'Otavalo sn y principal', '098254785', 25, TRUE);
insert into tbl_persona (nombre, genero, direccion, telefono, edad, status) values ('Marianela Montalvo', 'FEMENINO', 'Amazonas y NNUU', '097548965', 25, TRUE);
insert into tbl_persona (nombre, genero, direccion, telefono, edad, status) values ('Juan Ososrio', 'MASCULINO', '13 junio y Equinoccial', '098874587', 25, TRUE);

insert into tbl_cliente(password, id_persona, id) values ('1234', 1, 1);
insert into tbl_cliente(password, id_persona, id) values ('5678', 2, 2);
insert into tbl_cliente(password, id_persona, id) values ('1245', 3, 3);

insert into tbl_cuenta (numero_cuenta, saldo, tipo_cuenta, status, cliente_id) values ('478758', 2000.00, 'AHORRO', TRUE, 1);
insert into tbl_cuenta (numero_cuenta, saldo, tipo_cuenta, status, cliente_id) values ('225487', 100.00, 'CORRIENTE', TRUE, 2);
insert into tbl_cuenta (numero_cuenta, saldo, tipo_cuenta, status, cliente_id) values ('495878', 0.00, 'AHORRO', TRUE, 3);
insert into tbl_cuenta (numero_cuenta, saldo, tipo_cuenta, status, cliente_id) values ('496825', 540.00, 'AHORRO', TRUE, 2);

--insert into tbl_cuenta (numero_cuenta, saldo, tipo_cuenta, status, cliente_id) values ('585545', 1000.00, 'CORRIENTE', TRUE, 1);
