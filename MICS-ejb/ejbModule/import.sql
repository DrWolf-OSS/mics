-- SQL statements which are executed at application startup if hibernate.hbm2ddl.auto is 'create' or 'create-drop'
-- Inserisce gli anni su cui si calcano i dati di bilancio
INSERT INTO `mics`.`anno_bilancio` (`anno`) VALUES (2007);
INSERT INTO `mics`.`anno_bilancio` (`anno`) VALUES (2008);
INSERT INTO `mics`.`anno_bilancio` (`anno`) VALUES (2009);
INSERT INTO `mics`.`anno_bilancio` (`anno`) VALUES (2010);
-- Inserisce i trimestri su cui si effettua la simulaizone
INSERT INTO `mics`.`trimestre_simulazione` (`trimestre`, `nome_trimestre`) VALUES (1, 'Primo');
INSERT INTO `mics`.`trimestre_simulazione` (`trimestre`, `nome_trimestre`) VALUES (2, 'Secondo');
INSERT INTO `mics`.`trimestre_simulazione` (`trimestre`, `nome_trimestre`) VALUES (3, 'Terzo');
INSERT INTO `mics`.`trimestre_simulazione` (`trimestre`, `nome_trimestre`) VALUES (4, 'Quarto');
INSERT INTO `mics`.`trimestre_simulazione` (`trimestre`, `nome_trimestre`) VALUES (5, 'Quinto');
INSERT INTO `mics`.`trimestre_simulazione` (`trimestre`, `nome_trimestre`) VALUES (6, 'Sesto');
INSERT INTO `mics`.`trimestre_simulazione` (`trimestre`, `nome_trimestre`) VALUES (7, 'Settimo');
INSERT INTO `mics`.`trimestre_simulazione` (`trimestre`, `nome_trimestre`) VALUES (8, 'Ottavo');
INSERT INTO `mics`.`trimestre_simulazione` (`trimestre`, `nome_trimestre`) VALUES (9, 'Nono');
INSERT INTO `mics`.`trimestre_simulazione` (`trimestre`, `nome_trimestre`) VALUES (10, 'Decimo');
INSERT INTO `mics`.`trimestre_simulazione` (`trimestre`, `nome_trimestre`) VALUES (11, 'Undicesimo');
INSERT INTO `mics`.`trimestre_simulazione` (`trimestre`, `nome_trimestre`) VALUES (12, 'Dodicesimo');
