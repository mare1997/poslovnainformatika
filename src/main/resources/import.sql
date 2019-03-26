INSERT INTO `poslovnainformatika`.`faktura` (`id_fakture`, `broj_fakture`, `datum_fakture`, `datum_valute`, `iznos_za_placanje`, `obrisano`, `osnovica`, `status_fakture`, `ukupan_pdv`, `kupac_id`, `narudzbenica_rel`, `otpremnica_rel`, `preduzece`, `user_id`) VALUES ('1', '1', '2019-02-07', '2019-02-01', '500', '0', '50', '01', '15', '1', '1', '1', '1', '1');
INSERT INTO `poslovnainformatika`.`faktura` (`id_fakture`, `broj_fakture`, `datum_fakture`, `datum_valute`, `iznos_za_placanje`, `obrisano`, `osnovica`, `status_fakture`, `ukupan_pdv`, `kupac_id`, `narudzbenica_rel`, `otpremnica_rel`, `preduzece`, `user_id`) VALUES ('2', '2', '2019-02-05', '2019-02-01', '6000', '0', '600', '01', '18', '2', '2', '2', '1', '2');
INSERT INTO `poslovnainformatika`.`faktura` (`id_fakture`, `broj_fakture`, `datum_fakture`, `datum_valute`, `iznos_za_placanje`, `obrisano`, `osnovica`, `status_fakture`, `ukupan_pdv`, `kupac_id`, `narudzbenica_rel`, `otpremnica_rel`, `preduzece`, `user_id`) VALUES ('3', '3', '2019-03-05', '2019-03-01', '52324', '0', '6743', '01', '20', '2', '3', '3', '1', '2');

INSERT INTO `poslovnainformatika`.`cenovnik` (`cenovnik_id`,`name`, `datum_vazenja`,`datum_kreiranja`, `obrisano`, `preduzece_id`,`aktivan`) VALUES ('1','Cenovnik 1', '2019-07-01','2019-04-01', '0', '1','1');
INSERT INTO `poslovnainformatika`.`cenovnik` (`cenovnik_id`,`name`, `datum_vazenja`,`datum_kreiranja`, `obrisano`, `preduzece_id`,`aktivan`) VALUES ('2','Cenovnik 2', '2019-04-01','2019-03-01', '0', '1','0');
INSERT INTO `poslovnainformatika`.`cenovnik` (`cenovnik_id`,`name`, `datum_vazenja`,`datum_kreiranja`, `obrisano`, `preduzece_id`,`aktivan`) VALUES ('3','Cenovnik 3', '2019-06-01','2019-04-01', '0', '1','0');
INSERT INTO `poslovnainformatika`.`cenovnik` (`cenovnik_id`,`name`, `datum_vazenja`,`datum_kreiranja`, `obrisano`, `preduzece_id`,`aktivan`) VALUES ('4','Cenovnik 4', '2019-04-01','2019-03-01', '0', '1','0');
INSERT INTO `poslovnainformatika`.`cenovnik` (`cenovnik_id`,`name`, `datum_vazenja`,`datum_kreiranja`, `obrisano`, `preduzece_id`,`aktivan`) VALUES ('5','Cenovnik 5', '2019-06-01','2019-04-01', '0', '1','0');

INSERT INTO `poslovnainformatika`.`preduzece` (`preduzece_id`, `adresa`, `email`, `name`, `obrisano`, `pib`, `telefon`, `mesto_id`) VALUES ('1', 'adresa1', 'email1', 'Poslovna informatika', '0', '0123', '12312', '1');


INSERT INTO `poslovnainformatika`.`grupa_robe` (`grupa_robe_id`, `name`, `obrisano`, `pdv_id`, `preduzece_id`,`datum_kreiranja`) VALUES ('1', 'Alat', '0', '1', '1', '2019-03-01');
INSERT INTO `poslovnainformatika`.`grupa_robe` (`grupa_robe_id`, `name`, `obrisano`, `pdv_id`, `preduzece_id`,`datum_kreiranja`) VALUES ('2', 'Hrana', '0', '2', '1', '2019-04-01');
INSERT INTO `poslovnainformatika`.`grupa_robe` (`grupa_robe_id`, `name`, `obrisano`, `pdv_id`, `preduzece_id`,`datum_kreiranja`) VALUES ('3', 'Garderoba', '0', '2', '1', '2019-05-01');
INSERT INTO `poslovnainformatika`.`grupa_robe` (`grupa_robe_id`, `name`, `obrisano`, `pdv_id`, `preduzece_id`,`datum_kreiranja`) VALUES ('4', 'Tehnika', '0', '2', '1', '2019-04-01');
INSERT INTO `poslovnainformatika`.`grupa_robe` (`grupa_robe_id`, `name`, `obrisano`, `pdv_id`, `preduzece_id`,`datum_kreiranja`) VALUES ('5', 'Namestaj', '0', '1', '1', '2019-05-01');


INSERT INTO `poslovnainformatika`.`kupac` (`kupac_id`, `adresa`, `name`, `obrisano`, `pib_jmbg`, `mesto_id`, `preduzece_id`,`datum_kreiranja`) VALUES ('1', 'adresa1', 'Maki', '0', '1234567890123', '1', '1', '2019-03-01');
INSERT INTO `poslovnainformatika`.`kupac` (`kupac_id`, `adresa`, `name`, `obrisano`, `pib_jmbg`, `mesto_id`, `preduzece_id`,`datum_kreiranja`) VALUES ('2', 'adresa1', 'Sandra', '0', '1234563214598', '1', '1', '2019-03-01');
INSERT INTO `poslovnainformatika`.`kupac` (`kupac_id`, `adresa`, `name`, `obrisano`, `pib_jmbg`, `mesto_id`, `preduzece_id`,`datum_kreiranja`) VALUES ('3', 'adresa1', 'Marko', '0', '1234567890123', '2', '1', '2019-03-01');
INSERT INTO `poslovnainformatika`.`kupac` (`kupac_id`, `adresa`, `name`, `obrisano`, `pib_jmbg`, `mesto_id`, `preduzece_id`,`datum_kreiranja`) VALUES ('4', 'adresa2', 'Stefan', '0', '0987654321321', '2', '1', '2019-04-01');


INSERT INTO `poslovnainformatika`.`mesto` (`mesto_id`, `grad`, `obrisano`, `postanski_broj`) VALUES ('1', 'Novi Sad', '0', '21101');
INSERT INTO `poslovnainformatika`.`mesto` (`mesto_id`, `grad`, `obrisano`, `postanski_broj`) VALUES ('2', 'Beograd', '0', '11000');

INSERT INTO `poslovnainformatika`.`otpremnica` (`id_otpremnice`, `broj_otpremnice`, `datum_isporuke`, `datum_otpremnice`, `obrisano`, `primljena_roba`, `faktura_rel`, `kupac_id`, `preduzece_id`, `prevoznik_id`, `user_id`) VALUES ('1', '1', '2019-02-15', '2019-02-08', '0', '0', '1', '1', '1', '1', '1');
INSERT INTO `poslovnainformatika`.`otpremnica` (`id_otpremnice`, `broj_otpremnice`, `datum_isporuke`, `datum_otpremnice`, `obrisano`, `primljena_roba`, `faktura_rel`, `kupac_id`, `preduzece_id`, `prevoznik_id`, `user_id`) VALUES ('2', '2', '2019-02-22', '2019-02-02', '0', '0', '2', '2', '1', '2', '2');
INSERT INTO `poslovnainformatika`.`otpremnica` (`id_otpremnice`, `broj_otpremnice`, `datum_isporuke`, `datum_otpremnice`, `obrisano`, `primljena_roba`, `faktura_rel`, `kupac_id`, `preduzece_id`, `prevoznik_id`, `user_id`) VALUES ('3', '3', '2019-02-25', '2019-02-20', '0', '0', '3', '2', '1', '2', '2');

INSERT INTO `poslovnainformatika`.`narudzbenica` (`id_narudzbenice`, `broj_narudzbenice`, `datum_isporuke`, `datum_izrade`, `obrisano`, `aktivna`, `faktura_rel`, `kupac_id`, `preduzece`, `user_id`) VALUES ('1', '1', '2019-02-14', '2019-02-12', '0', '1', '1', '1', '1', '1');
INSERT INTO `poslovnainformatika`.`narudzbenica` (`id_narudzbenice`, `broj_narudzbenice`, `datum_isporuke`, `datum_izrade`, `obrisano`, `aktivna`, `faktura_rel`, `kupac_id`, `preduzece`, `user_id`) VALUES ('2', '2', '2019-02-22', '2019-02-16', '0', '1', '2', '2', '1', '2');
INSERT INTO `poslovnainformatika`.`narudzbenica` (`id_narudzbenice`, `broj_narudzbenice`, `datum_isporuke`, `datum_izrade`, `obrisano`, `aktivna`, `faktura_rel`, `kupac_id`, `preduzece`, `user_id`) VALUES ('3', '3', '2019-03-25', '2019-03-03', '0', '1', '3', '1', '1', '2');

INSERT INTO `poslovnainformatika`.`pdv` (`pdv_id`, `name`, `obrisano`) VALUES ('1', 'pdv 1', '0');
INSERT INTO `poslovnainformatika`.`pdv` (`pdv_id`, `name`, `obrisano`) VALUES ('2', 'pdv 2', '0');
INSERT INTO `poslovnainformatika`.`pdv` (`pdv_id`, `name`, `obrisano`) VALUES ('3', 'pdv 3', '0');


INSERT INTO `poslovnainformatika`.`poslovna_godina` (`poslovna_godina_id`, `godina`, `zavrsena`, `datum_pocetak` ,  `datum_kraj`,`preduzece_id`) VALUES ('1', '2017', '1','2017-02-01','2018-02-01','1');
INSERT INTO `poslovnainformatika`.`poslovna_godina` (`poslovna_godina_id`, `godina`, `zavrsena`, `datum_pocetak` ,  `datum_kraj`,`preduzece_id`) VALUES ('2', '2018', '1','2018-02-01','2019-02-01','1');
INSERT INTO `poslovnainformatika`.`poslovna_godina` (`poslovna_godina_id`, `godina`, `zavrsena`, `datum_pocetak`,`preduzece_id` ) VALUES ('3', '2019', '0','2019-02-01','1');

INSERT INTO `poslovnainformatika`.`prevoznik` (`prevoznik_id`, `name`, `obrisano`) VALUES ('1', 'Bex', '0');
INSERT INTO `poslovnainformatika`.`prevoznik` (`prevoznik_id`, `name`, `obrisano`) VALUES ('2', 'Aks', '0');
INSERT INTO `poslovnainformatika`.`prevoznik` (`prevoznik_id`, `name`, `obrisano`) VALUES ('3', 'Post express', '0');

INSERT INTO `poslovnainformatika`.`roba` (`roba_id`, `jedninica_mere`, `name`, `obrisano`, `grupa_robe_id`) VALUES ('1', 'l', 'Mleko', '0', '2');
INSERT INTO `poslovnainformatika`.`roba` (`roba_id`, `jedninica_mere`, `name`, `obrisano`, `grupa_robe_id`) VALUES ('2', 'l', 'Pivo', '0', '2');
INSERT INTO `poslovnainformatika`.`roba` (`roba_id`, `jedninica_mere`, `name`, `obrisano`, `grupa_robe_id`) VALUES ('3', 'kg', 'Brasno', '0', '2');
INSERT INTO `poslovnainformatika`.`roba` (`roba_id`, `jedninica_mere`, `name`, `obrisano`, `grupa_robe_id`) VALUES ('4', 'komad', 'Srafciger', '0', '1');
INSERT INTO `poslovnainformatika`.`roba` (`roba_id`, `jedninica_mere`, `name`, `obrisano`, `grupa_robe_id`) VALUES ('5', 'komad', 'Klesta', '0', '1');
INSERT INTO `poslovnainformatika`.`roba` (`roba_id`, `jedninica_mere`, `name`, `obrisano`, `grupa_robe_id`) VALUES ('6', 'komad', 'Cekic', '0', '1');
INSERT INTO `poslovnainformatika`.`roba` (`roba_id`, `jedninica_mere`, `name`, `obrisano`, `grupa_robe_id`) VALUES ('7', 'komad', 'Kauc', '0', '5');
INSERT INTO `poslovnainformatika`.`roba` (`roba_id`, `jedninica_mere`, `name`, `obrisano`, `grupa_robe_id`) VALUES ('8', 'komad', 'Fotelja', '0', '5');
INSERT INTO `poslovnainformatika`.`roba` (`roba_id`, `jedninica_mere`, `name`, `obrisano`, `grupa_robe_id`) VALUES ('9', 'komad', 'Sto', '0', '5');
INSERT INTO `poslovnainformatika`.`roba` (`roba_id`, `jedninica_mere`, `name`, `obrisano`, `grupa_robe_id`) VALUES ('10', 'komad', 'Majica', '0', '3');
INSERT INTO `poslovnainformatika`.`roba` (`roba_id`, `jedninica_mere`, `name`, `obrisano`, `grupa_robe_id`) VALUES ('11', 'komad', 'Pantalone', '0', '3');
INSERT INTO `poslovnainformatika`.`roba` (`roba_id`, `jedninica_mere`, `name`, `obrisano`, `grupa_robe_id`) VALUES ('12', 'par', 'Patike', '0', '3');
INSERT INTO `poslovnainformatika`.`roba` (`roba_id`, `jedninica_mere`, `name`, `obrisano`, `grupa_robe_id`) VALUES ('13', 'komad', 'Telefon', '0', '4');
INSERT INTO `poslovnainformatika`.`roba` (`roba_id`, `jedninica_mere`, `name`, `obrisano`, `grupa_robe_id`) VALUES ('14', 'komad', 'Televizor', '0', '4');
INSERT INTO `poslovnainformatika`.`roba` (`roba_id`, `jedninica_mere`, `name`, `obrisano`, `grupa_robe_id`) VALUES ('15', 'komad', 'Sporet', '0', '4');





INSERT INTO `poslovnainformatika`.`stavka_cenovnika` (`stavka_cenovnika_id`, `cena`, `obrisano`, `cenovnik_id`, `roba_id`) VALUES ('1', '100', '0', '1', '1');
INSERT INTO `poslovnainformatika`.`stavka_cenovnika` (`stavka_cenovnika_id`, `cena`, `obrisano`, `cenovnik_id`, `roba_id`) VALUES ('2', '100', '0', '1', '2');
INSERT INTO `poslovnainformatika`.`stavka_cenovnika` (`stavka_cenovnika_id`, `cena`, `obrisano`, `cenovnik_id`, `roba_id`) VALUES ('3', '50', '0', '1', '3');
INSERT INTO `poslovnainformatika`.`stavka_cenovnika` (`stavka_cenovnika_id`, `cena`, `obrisano`, `cenovnik_id`, `roba_id`) VALUES ('4', '110', '0', '1', '4');
INSERT INTO `poslovnainformatika`.`stavka_cenovnika` (`stavka_cenovnika_id`, `cena`, `obrisano`, `cenovnik_id`, `roba_id`) VALUES ('5', '120', '0', '1', '4');
INSERT INTO `poslovnainformatika`.`stavka_cenovnika` (`stavka_cenovnika_id`, `cena`, `obrisano`, `cenovnik_id`, `roba_id`) VALUES ('6', '200', '0', '1', '5');
INSERT INTO `poslovnainformatika`.`stavka_cenovnika` (`stavka_cenovnika_id`, `cena`, `obrisano`, `cenovnik_id`, `roba_id`) VALUES ('7', '150', '0', '1', '6');
INSERT INTO `poslovnainformatika`.`stavka_cenovnika` (`stavka_cenovnika_id`, `cena`, `obrisano`, `cenovnik_id`, `roba_id`) VALUES ('8', '13000', '0', '1', '7');
INSERT INTO `poslovnainformatika`.`stavka_cenovnika` (`stavka_cenovnika_id`, `cena`, `obrisano`, `cenovnik_id`, `roba_id`) VALUES ('9', '15000', '0', '1', '7');
INSERT INTO `poslovnainformatika`.`stavka_cenovnika` (`stavka_cenovnika_id`, `cena`, `obrisano`, `cenovnik_id`, `roba_id`) VALUES ('10', '6500', '0', '1', '8');
INSERT INTO `poslovnainformatika`.`stavka_cenovnika` (`stavka_cenovnika_id`, `cena`, `obrisano`, `cenovnik_id`, `roba_id`) VALUES ('11', '8500', '0', '1', '9');
INSERT INTO `poslovnainformatika`.`stavka_cenovnika` (`stavka_cenovnika_id`, `cena`, `obrisano`, `cenovnik_id`, `roba_id`) VALUES ('12', '10000', '0', '1', '9');
INSERT INTO `poslovnainformatika`.`stavka_cenovnika` (`stavka_cenovnika_id`, `cena`, `obrisano`, `cenovnik_id`, `roba_id`) VALUES ('13', '1000', '0', '1', '10');
INSERT INTO `poslovnainformatika`.`stavka_cenovnika` (`stavka_cenovnika_id`, `cena`, `obrisano`, `cenovnik_id`, `roba_id`) VALUES ('14', '3000', '0', '1', '11');
INSERT INTO `poslovnainformatika`.`stavka_cenovnika` (`stavka_cenovnika_id`, `cena`, `obrisano`, `cenovnik_id`, `roba_id`) VALUES ('15', '10000', '0', '1', '12');
INSERT INTO `poslovnainformatika`.`stavka_cenovnika` (`stavka_cenovnika_id`, `cena`, `obrisano`, `cenovnik_id`, `roba_id`) VALUES ('16', '30000', '0', '1', '13');
INSERT INTO `poslovnainformatika`.`stavka_cenovnika` (`stavka_cenovnika_id`, `cena`, `obrisano`, `cenovnik_id`, `roba_id`) VALUES ('17', '25000', '0', '1', '14');
INSERT INTO `poslovnainformatika`.`stavka_cenovnika` (`stavka_cenovnika_id`, `cena`, `obrisano`, `cenovnik_id`, `roba_id`) VALUES ('18', '20000', '0', '1', '15');

INSERT INTO `poslovnainformatika`.`stavka_otpremnice` (`id_stavke_otpremnice`, `cena`, `isporucena_kolicina`, `jedinica_mere`, `napomena`, `naziv`, `obrisano`,`roba_usluga_id`, `redni_broj`, `id_otpremnice`) VALUES ('1', '500', '2', 'kg', 'napomena1', 'stavka1', '0', '1', '1', '1');
INSERT INTO `poslovnainformatika`.`stavka_otpremnice` (`id_stavke_otpremnice`, `cena`, `isporucena_kolicina`, `jedinica_mere`, `napomena`, `naziv`, `obrisano`,`roba_usluga_id`, `redni_broj`, `id_otpremnice`) VALUES ('2', '600', '1', 'l', 'napomena2', 'stavka2', '0','1', '2', '1');
INSERT INTO `poslovnainformatika`.`stavka_otpremnice` (`id_stavke_otpremnice`, `cena`, `isporucena_kolicina`, `jedinica_mere`, `napomena`, `naziv`, `obrisano`,`roba_usluga_id`, `redni_broj`, `id_otpremnice`) VALUES ('3', '6005', '3', 'kg', 'napomena3', 'stavka3', '0','2', '1', '2');
INSERT INTO `poslovnainformatika`.`stavka_otpremnice` (`id_stavke_otpremnice`, `cena`, `isporucena_kolicina`, `jedinica_mere`, `napomena`, `naziv`, `obrisano`,`roba_usluga_id`, `redni_broj`, `id_otpremnice`) VALUES ('4', '123', '10', 'l', 'napomena4', 'stavka4', '0','2', '2', '2');
INSERT INTO `poslovnainformatika`.`stavka_otpremnice` (`id_stavke_otpremnice`, `cena`, `isporucena_kolicina`, `jedinica_mere`, `napomena`, `naziv`, `obrisano`,`roba_usluga_id`, `redni_broj`, `id_otpremnice`) VALUES ('5', '55555', '1', 'm', 'napomena5', 'stavka5', '1','2', '3', '1');

INSERT INTO `poslovnainformatika`.`stavka_narudzbenice` (`id_stavke_narudzbenice`, `jedinica_mere`, `kolicina`, `naziv`, `obrisano`,`roba_usluga_id`, `id_narudzbenice`) VALUES ('1', 'kg', '5', 'stavka1', '0','1', '1');
INSERT INTO `poslovnainformatika`.`stavka_narudzbenice` (`id_stavke_narudzbenice`, `jedinica_mere`, `kolicina`, `naziv`, `obrisano`,`roba_usluga_id`, `id_narudzbenice`) VALUES ('2', 'l', '3', 'stavka2', '0','1', '2');
INSERT INTO `poslovnainformatika`.`stavka_narudzbenice` (`id_stavke_narudzbenice`, `jedinica_mere`, `kolicina`, `naziv`, `obrisano`,`roba_usluga_id`, `id_narudzbenice`) VALUES ('3', 'l', '6', 'stavka3', '0','2', '1');
INSERT INTO `poslovnainformatika`.`stavka_narudzbenice` (`id_stavke_narudzbenice`, `jedinica_mere`, `kolicina`, `naziv`, `obrisano`,`roba_usluga_id`, `id_narudzbenice`) VALUES ('4', 'm', '10', 'stavka4', '0','2', '2');
INSERT INTO `poslovnainformatika`.`stavka_narudzbenice` (`id_stavke_narudzbenice`, `jedinica_mere`, `kolicina`, `naziv`, `obrisano`,`roba_usluga_id`, `id_narudzbenice`) VALUES ('5', 't', '1', 'stavka5', '1','1', '1');

INSERT INTO `poslovnainformatika`.`stavka_fakture` (`id_stavke_fakture`, `iznos_pdv`, `iznos_stavke`, `jedinica_mere`, `jedinicna_cena`, `kolicina`, `obrisano`, `osnovica_za_pdv`, `procenat_pdv`, `rabat`, `id_fakture`, `roba_usluga_id`) VALUES ('1', '50', '500', 'kg', '45', '11', '0', '12', '5', '50', '1', '1');
INSERT INTO `poslovnainformatika`.`stavka_fakture` (`id_stavke_fakture`, `iznos_pdv`, `iznos_stavke`, `jedinica_mere`, `jedinicna_cena`, `kolicina`, `obrisano`, `osnovica_za_pdv`, `procenat_pdv`, `rabat`, `id_fakture`, `roba_usluga_id`) VALUES ('2', '60', '600', 'l', '57', '5', '0', '8', '18', '89', '1', '2');
INSERT INTO `poslovnainformatika`.`stavka_fakture` (`id_stavke_fakture`, `iznos_pdv`, `iznos_stavke`, `jedinica_mere`, `jedinicna_cena`, `kolicina`, `obrisano`, `osnovica_za_pdv`, `procenat_pdv`, `rabat`, `id_fakture`, `roba_usluga_id`) VALUES ('3', '70', '700', 'l', '70', '8', '0', '16', '8', '566', '2', '2');
INSERT INTO `poslovnainformatika`.`stavka_fakture` (`id_stavke_fakture`, `iznos_pdv`, `iznos_stavke`, `jedinica_mere`, `jedinicna_cena`, `kolicina`, `obrisano`, `osnovica_za_pdv`, `procenat_pdv`, `rabat`, `id_fakture`, `roba_usluga_id`) VALUES ('4', '80', '888', 't', '79', '7', '1', '12', '7', '789', '2', '3');

INSERT INTO `poslovnainformatika`.`stopa_pdv` (`stopa_pdv_id`, `datum_vazenja`, `obrisano`, `procenat`, `pdv_id`) VALUES ('1', '2019-02-28', '0', '15', '1');
INSERT INTO `poslovnainformatika`.`stopa_pdv` (`stopa_pdv_id`, `datum_vazenja`, `obrisano`, `procenat`, `pdv_id`) VALUES ('2', '2019-03-30', '0', '20', '2');
INSERT INTO `poslovnainformatika`.`stopa_pdv` (`stopa_pdv_id`, `datum_vazenja`, `obrisano`, `procenat`, `pdv_id`) VALUES ('3', '2019-02-01', '0', '18', '3');

INSERT INTO `poslovnainformatika`.`users` (`user_id`, `firstname`, `lastname`, `obrisano`, `user_pasword`,  `username`, `preduzece_id`) VALUES ('1', 'ime1', 'prezime1', '0', '$2a$04$bAsNVI05EjajIzH4AHFdu.RimWMl2K5hmnzeBrcqRX7Cm8BtMgIFK',  'admin', '1');
INSERT INTO `poslovnainformatika`.`users` (`user_id`, `firstname`, `lastname`, `obrisano`, `user_pasword`,  `username`, `preduzece_id`) VALUES ('2', 'ime2', 'prezime2', '0', '$2a$04$bAsNVI05EjajIzH4AHFdu.RimWMl2K5hmnzeBrcqRX7Cm8BtMgIFK', 'user', '1');
INSERT INTO `poslovnainformatika`.`users` (`user_id`, `firstname`, `lastname`, `obrisano`, `user_pasword`,  `username`, `preduzece_id`) VALUES ('3', 'ime3', 'prezime3', '0', '$2a$04$bAsNVI05EjajIzH4AHFdu.RimWMl2K5hmnzeBrcqRX7Cm8BtMgIFK',  'user3', '1');

INSERT INTO authority(name)VALUES('ADMIN');
INSERT INTO authority(name)VALUES('REGULAR');

INSERT INTO user_authority(user_id,authority_id)VALUES(1,1);
INSERT INTO user_authority(user_id,authority_id)VALUES(2,2);
INSERT INTO user_authority(user_id,authority_id)VALUES(3,2);

