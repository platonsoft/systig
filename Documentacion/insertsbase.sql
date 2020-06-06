-- PAISES DISPONIBLES,
insert into stg_paises (id_pais, cod_moneda, cod_pais, is_disponible, nombre)
values (1,'VES','VEN', TRUE, 'VENEZUELA');

insert into stg_paises (id_pais, cod_moneda, cod_pais, is_disponible, nombre)
values (2,'COP','COL', TRUE, 'COLOMBIA');

insert into stg_paises (id_pais, cod_moneda, cod_pais, is_disponible, nombre)
values (3,'PEN','PEN', TRUE, 'PERU');

-- DOCUMENTOS DE IDENTIDAD DE COLOMBIA
insert into stg_nom_tipo_doc_identif (abrev_doc, is_activo, is_empresa, nombre_doc, id_pais)
values ('TI',TRUE,FALSE,'TARJETA IDENTIDAD',2);

insert into stg_nom_tipo_doc_identif (abrev_doc, is_activo, is_empresa, nombre_doc, id_pais)
values ('CC',TRUE,FALSE,'CEDULA CIUDADANIA',2);

insert into stg_nom_tipo_doc_identif (abrev_doc, is_activo, is_empresa, nombre_doc, id_pais)
values ('CE',TRUE,FALSE,'CEDULA EXTRANJERIA',2);

insert into stg_nom_tipo_doc_identif (abrev_doc, is_activo, is_empresa, nombre_doc, id_pais)
values ('PA',TRUE,FALSE,'PASAPORTE',2);

insert into stg_nom_tipo_doc_identif (abrev_doc, is_activo, is_empresa, nombre_doc, id_pais)
values ('PE',TRUE,FALSE,'PERMISO ESPECIAL DE PERMANENCIA',2);

-- DOCUMENTOS DE IDENTIDAD DE VENEZUELA
insert into stg_nom_tipo_doc_identif (abrev_doc, is_activo, is_empresa, nombre_doc, id_pais)
values ('CI',TRUE,FALSE,'CEDULA IDENTIDAD',1);

insert into stg_nom_tipo_doc_identif (abrev_doc, is_activo, is_empresa, nombre_doc, id_pais)
values ('PA',TRUE,FALSE,'PASAPORTE',1);

insert into stg_nom_tipo_doc_identif (abrev_doc, is_activo, is_empresa, nombre_doc, id_pais)
values ('CE',TRUE,FALSE,'CEDULA EXTRANJERIA',1);

-- DOCUMENTOS DE IDENTIDAD DE PERU
insert into stg_nom_tipo_doc_identif (abrev_doc, is_activo, is_empresa, nombre_doc, id_pais)
values ('DNI',TRUE,FALSE,'DNI',3);

insert into stg_nom_tipo_doc_identif (abrev_doc, is_activo, is_empresa, nombre_doc, id_pais)
values ('PAS',TRUE,FALSE,'PASAPORTE',3);

insert into stg_nom_tipo_doc_identif (abrev_doc, is_activo, is_empresa, nombre_doc, id_pais)
values ('PTP',TRUE,FALSE,'PERMISO TEMPORAL DE PERMANENCIA',3);

insert into stg_nom_tipo_doc_identif (abrev_doc, is_activo, is_empresa, nombre_doc, id_pais)
values ('CE',TRUE,FALSE,'CARNET DE EXTRANJERIA',3);

-- ENTIDADES FINANCIERAS VENEZUELA
insert into stg_nom_entidades_financieras (codigo, is_activo, logotipo, nombre_banco, id_pais)
values ('0156',true,null,'100%BANCO',1);

insert into stg_nom_entidades_financieras (codigo, is_activo, logotipo, nombre_banco, id_pais)
values ('0196',true,null,'ABN AMRO BANK',1);

insert into stg_nom_entidades_financieras (codigo, is_activo, logotipo, nombre_banco, id_pais)
values ('0172',true,null,'BANCAMIGA BANCO MICROFINANCIERO, C.A.',1);

insert into stg_nom_entidades_financieras (codigo, is_activo, logotipo, nombre_banco, id_pais)
values ('0171',true,null,'BANCO ACTIVO BANCO COMERCIAL, C.A.',1);

insert into stg_nom_entidades_financieras (codigo, is_activo, logotipo, nombre_banco, id_pais)
values ('0166',true,null,'BANCO AGRICOLA',1);

insert into stg_nom_entidades_financieras (codigo, is_activo, logotipo, nombre_banco, id_pais)
values ('0175',true,null,'BANCO BICENTENARIO',1);

insert into stg_nom_entidades_financieras (codigo, is_activo, logotipo, nombre_banco, id_pais)
values ('0128',true,null,'BANCO CARONI, C.A. BANCO UNIVERSAL',1);

insert into stg_nom_entidades_financieras (codigo, is_activo, logotipo, nombre_banco, id_pais)
values ('0164',true,null,'BANCO DE DESARROLLO DEL MICROEMPRESARIO',1);

insert into stg_nom_entidades_financieras (codigo, is_activo, logotipo, nombre_banco, id_pais)
values ('0102',true,null,'BANCO DE VENEZUELA S.A.I.C.A.',1);

insert into stg_nom_entidades_financieras (codigo, is_activo, logotipo, nombre_banco, id_pais)
values ('0114',true,null,'BANCO DEL CARIBE C.A.',1);

insert into stg_nom_entidades_financieras (codigo, is_activo, logotipo, nombre_banco, id_pais)
values ('0149',true,null,'BANCO DEL PUEBLO SOBERANO C.A.',1);

insert into stg_nom_entidades_financieras (codigo, is_activo, logotipo, nombre_banco, id_pais)
values ('0163',true,null,'BANCO DEL TESORO',1);

insert into stg_nom_entidades_financieras (codigo, is_activo, logotipo, nombre_banco, id_pais)
values ('0176',true,null,'BANCO ESPIRITO SANTO, S.A.',1);

insert into stg_nom_entidades_financieras (codigo, is_activo, logotipo, nombre_banco, id_pais)
values ('0115',true,null,'BANCO EXTERIOR C.A.',1);

insert into stg_nom_entidades_financieras (codigo, is_activo, logotipo, nombre_banco, id_pais)
values ('0003',true,null,'BANCO INDUSTRIAL DE VENEZUELA.',1);

insert into stg_nom_entidades_financieras (codigo, is_activo, logotipo, nombre_banco, id_pais)
values ('0173',true,null,'BANCO INTERNACIONAL DE DESARROLLO, C.A.',1);

insert into stg_nom_entidades_financieras (codigo, is_activo, logotipo, nombre_banco, id_pais)
values ('0105',true,null,'BANCO MERCANTIL C.A.',1);

insert into stg_nom_entidades_financieras (codigo, is_activo, logotipo, nombre_banco, id_pais)
values ('0191',true,null,'BANCO NACIONAL DE CREDITO',1);

insert into stg_nom_entidades_financieras (codigo, is_activo, logotipo, nombre_banco, id_pais)
values ('0116',true,null,'BANCO OCCIDENTAL DE DESCUENTO.',1);

insert into stg_nom_entidades_financieras (codigo, is_activo, logotipo, nombre_banco, id_pais)
values ('0138',true,null,'BANCO PLAZA',1);

insert into stg_nom_entidades_financieras (codigo, is_activo, logotipo, nombre_banco, id_pais)
values ('0108',true,null,'BANCO PROVINCIAL BBVA',1);

insert into stg_nom_entidades_financieras (codigo, is_activo, logotipo, nombre_banco, id_pais)
values ('0104',true,null,'BANCO VENEZOLANO DE CREDITO S.A.',1);

insert into stg_nom_entidades_financieras (codigo, is_activo, logotipo, nombre_banco, id_pais)
values ('0168',true,null,'BANCRECER S.A. BANCO DE DESARROLLO',1);

insert into stg_nom_entidades_financieras (codigo, is_activo, logotipo, nombre_banco, id_pais)
values ('0134',true,null,'BANESCO BANCO UNIVERSAL',1);

insert into stg_nom_entidades_financieras (codigo, is_activo, logotipo, nombre_banco, id_pais)
values ('0177',true,null,'BANFANB',1);

insert into stg_nom_entidades_financieras (codigo, is_activo, logotipo, nombre_banco, id_pais)
values ('0146',true,null,'BANGENTE',1);

insert into stg_nom_entidades_financieras (codigo, is_activo, logotipo, nombre_banco, id_pais)
values ('0174',true,null,'BANPLUS BANCO COMERCIAL C.A',1);

insert into stg_nom_entidades_financieras (codigo, is_activo, logotipo, nombre_banco, id_pais)
values ('0190',true,null,'CITIBANK.',1);

insert into stg_nom_entidades_financieras (codigo, is_activo, logotipo, nombre_banco, id_pais)
values ('0121',true,null,'CORP BANCA.',1);

insert into stg_nom_entidades_financieras (codigo, is_activo, logotipo, nombre_banco, id_pais)
values ('0157',true,null,'DELSUR BANCO UNIVERSAL',1);

insert into stg_nom_entidades_financieras (codigo, is_activo, logotipo, nombre_banco, id_pais)
values ('0151',true,null,'FONDO COMUN',1);

insert into stg_nom_entidades_financieras (codigo, is_activo, logotipo, nombre_banco, id_pais)
values ('0601',true,null,'INSTITUTO MUNICIPAL DE CR&#201;DITO POPULAR',1);

insert into stg_nom_entidades_financieras (codigo, is_activo, logotipo, nombre_banco, id_pais)
values ('0169',true,null,'MIBANCO BANCO DE DESARROLLO, C.A.',1);

-- ENTIDADES FINANCIERAS COLOMBIA
insert into stg_nom_entidades_financieras (codigo, is_activo, logotipo, nombre_banco, id_pais)
values ('0001',true,null,'Bancafé',2);

insert into stg_nom_entidades_financieras (codigo, is_activo, logotipo, nombre_banco, id_pais)
values ('0002',true,null,'Banco AV Villas',2);

insert into stg_nom_entidades_financieras (codigo, is_activo, logotipo, nombre_banco, id_pais)
values ('0003',true,null,'Banco Bilbao Vizcaya Argentaria Colombia S.A. (BBVA )',2);

insert into stg_nom_entidades_financieras (codigo, is_activo, logotipo, nombre_banco, id_pais)
values ('0004',true,null,'Banco Caja Social BCSC',2);

insert into stg_nom_entidades_financieras (codigo, is_activo, logotipo, nombre_banco, id_pais)
values ('0005',true,null,'Banco de Bogotá',2);

insert into stg_nom_entidades_financieras (codigo, is_activo, logotipo, nombre_banco, id_pais)
values ('0006',true,null,'Banco de Credito',2);

insert into stg_nom_entidades_financieras (codigo, is_activo, logotipo, nombre_banco, id_pais)
values ('0007',true,null,'Banco de la República de Colombia',2);

insert into stg_nom_entidades_financieras (codigo, is_activo, logotipo, nombre_banco, id_pais)
values ('0008',true,null,'Banco de Occidente',2);

insert into stg_nom_entidades_financieras (codigo, is_activo, logotipo, nombre_banco, id_pais)
values ('0009',true,null,'Banco GNB Sudameris',2);

insert into stg_nom_entidades_financieras (codigo, is_activo, logotipo, nombre_banco, id_pais)
values ('0010',true,null,'Banco Granahorrar',2);

insert into stg_nom_entidades_financieras (codigo, is_activo, logotipo, nombre_banco, id_pais)
values ('0011',true,null,'Banco Popular',2);

insert into stg_nom_entidades_financieras (codigo, is_activo, logotipo, nombre_banco, id_pais)
values ('0012',true,null,'Banco Popular Colombia',2);

insert into stg_nom_entidades_financieras (codigo, is_activo, logotipo, nombre_banco, id_pais)
values ('0013',true,null,'Banco Santander Colombia',2);

insert into stg_nom_entidades_financieras (codigo, is_activo, logotipo, nombre_banco, id_pais)
values ('0014',true,null,'Bancoldex',2);

insert into stg_nom_entidades_financieras (codigo, is_activo, logotipo, nombre_banco, id_pais)
values ('0015',true,null,'Bancolombia',2);

insert into stg_nom_entidades_financieras (codigo, is_activo, logotipo, nombre_banco, id_pais)
values ('0016',true,null,'BBVA Banco Ganadero',2);

insert into stg_nom_entidades_financieras (codigo, is_activo, logotipo, nombre_banco, id_pais)
values ('0017',true,null,'Citi Bank',2);

insert into stg_nom_entidades_financieras (codigo, is_activo, logotipo, nombre_banco, id_pais)
values ('0018',true,null,'Colmena BCSC',2);

insert into stg_nom_entidades_financieras (codigo, is_activo, logotipo, nombre_banco, id_pais)
values ('0019',true,null,'Colpatria',2);

insert into stg_nom_entidades_financieras (codigo, is_activo, logotipo, nombre_banco, id_pais)
values ('0020',true,null,'Conavi',2);

insert into stg_nom_entidades_financieras (codigo, is_activo, logotipo, nombre_banco, id_pais)
values ('0021',true,null,'Credit Suisse Representaciõn para Colombia',2);

insert into stg_nom_entidades_financieras (codigo, is_activo, logotipo, nombre_banco, id_pais)
values ('0022',true,null,'Davivienda',2);

insert into stg_nom_entidades_financieras (codigo, is_activo, logotipo, nombre_banco, id_pais)
values ('0023',true,null,'Deutsche Bank',2);

insert into stg_nom_entidades_financieras (codigo, is_activo, logotipo, nombre_banco, id_pais)
values ('0024',true,null,'Helm Financial Services',2);

insert into stg_nom_entidades_financieras (codigo, is_activo, logotipo, nombre_banco, id_pais)
values ('0025',true,null,'Megabanco',2);

-- ENTIDADES FINANCIERAS PERU
insert into stg_nom_entidades_financieras (codigo, is_activo, logotipo, nombre_banco, id_pais)
values ('0001',TRUE,NULL,'AFP Integra',3);

insert into stg_nom_entidades_financieras (codigo, is_activo, logotipo, nombre_banco, id_pais)
values ('0002',TRUE,NULL,'Banco Central de Reserva',3);

insert into stg_nom_entidades_financieras (codigo, is_activo, logotipo, nombre_banco, id_pais)
values ('0003',TRUE,NULL,'Banco de Comercio',3);

insert into stg_nom_entidades_financieras (codigo, is_activo, logotipo, nombre_banco, id_pais)
values ('0004',TRUE,NULL,'Banco de Crédito del Perú',3);

insert into stg_nom_entidades_financieras (codigo, is_activo, logotipo, nombre_banco, id_pais)
values ('0005',TRUE,NULL,'Banco de la Nacion',3);

insert into stg_nom_entidades_financieras (codigo, is_activo, logotipo, nombre_banco, id_pais)
values ('0006',TRUE,NULL,'Banco del Trabajo',3);

insert into stg_nom_entidades_financieras (codigo, is_activo, logotipo, nombre_banco, id_pais)
values ('0007',TRUE,NULL,'Banco Financiero',3);

insert into stg_nom_entidades_financieras (codigo, is_activo, logotipo, nombre_banco, id_pais)
values ('0008',TRUE,NULL,'Banco Latino',3);

insert into stg_nom_entidades_financieras (codigo, is_activo, logotipo, nombre_banco, id_pais)
values ('0009',TRUE,NULL,'Banco Republica',3);

insert into stg_nom_entidades_financieras (codigo, is_activo, logotipo, nombre_banco, id_pais)
values ('0010',TRUE,NULL,'Banco Sudamericano',3);

insert into stg_nom_entidades_financieras (codigo, is_activo, logotipo, nombre_banco, id_pais)
values ('0011',TRUE,NULL,'Banco Wiese Sudameris',3);

insert into stg_nom_entidades_financieras (codigo, is_activo, logotipo, nombre_banco, id_pais)
values ('0012',TRUE,NULL,'Corporación Financiera de Desarrollo',3);

insert into stg_nom_entidades_financieras (codigo, is_activo, logotipo, nombre_banco, id_pais)
values ('0013',TRUE,NULL,'CSG Asesoria S.A.',3);

insert into stg_nom_entidades_financieras (codigo, is_activo, logotipo, nombre_banco, id_pais)
values ('0014',TRUE,NULL,'Interbank',3);

insert into stg_nom_entidades_financieras (codigo, is_activo, logotipo, nombre_banco, id_pais)
values ('0015',TRUE,NULL,'Mibanco',3);

insert into stg_nom_entidades_financieras (codigo, is_activo, logotipo, nombre_banco, id_pais)
values ('0016',TRUE,NULL,'NorAndina Merchant Bankers',3);

insert into stg_nom_entidades_financieras (codigo, is_activo, logotipo, nombre_banco, id_pais)
values ('0017',TRUE,NULL,'Standard Chartered',3);

insert into stg_nom_entidades_financieras (codigo, is_activo, logotipo, nombre_banco, id_pais)
values ('0018',TRUE,NULL,'UBS AG in Lima',3);


