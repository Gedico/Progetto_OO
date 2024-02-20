-- Inserimento dei dati nella tabella Operatore
INSERT INTO Operatore (ID_Operatore, Username, Nome, Cognome, Password) VALUES
(4001, 'Frner', 'Francesco', 'Neri', 'passW123'),
(3032, 'Labigi', 'Laura', 'Biagi', 'passW456'),
(2861, 'Marberi', 'Mario', 'Barbieri', 'pwd01ERo'),
(6354, 'Alfeno', 'Alfredo', 'Reno', 'pwd02RF1'),
(1405, 'Gierdi', 'Giulia', 'Verdi', 'pwd03331'),
(5598, 'gedico', 'Gennaro', 'Di Costanzo', '12345678');


-- Inserimento dei dati nella tabella Corriere
INSERT INTO Corriere (Matricola, Nome, Cognome) VALUES
(1237, 'Giovanni', 'Verdi'),
(4509, 'Anna', 'Gialli'),
(2861, 'Marco', 'Grande'),
(6354, 'Lucia', 'Stolfo'),
(1405, 'Andrea', 'Vincente'),
(5598, 'Sofia', 'Tori');


-- Inserimento dei dati nella tabella Veicoli
INSERT INTO Veicoli (Targa, Max_Trasportabile, Matricola, Descrizione,id_magazzino) VALUES
('RM12345', 750, 1237, 'Furgone piccolo',3001),
('MI67890', 3000, 4509, 'Camion grande',3002),
('AA123BB', 800, 6354, 'Furgone piccolo',3001),
('CC456DD', 2000, 5598, 'Camion medio',3003),
('EE789FF', 750, 2861, 'Furgone piccolo',3002),
('GG012HH', 3500, 1405, 'Camion grande per trasporto pesante',3003);


-- Inserimento dei dati nella tabella Merce
INSERT INTO Merce (ID_Merce, Peso, Descrizione) VALUES
(2001, 2, 'Mouse'),
(2002, 5, 'Stampante'),
(1341, 2, 'Palla da calcio'),
(8809, 1, 'Pallina da tennis'),
(3140, 2, 'Racchetta da tennis'),
(4455, 3, 'Zaino trekking'),
(5550, 1, 'Cappellino'),
(6767, 1, 'Borraccia');


-- Inserimento dei dati nella tabella Magazzino
INSERT INTO Magazzino (ID_Magazzino, Città, Descrizione) VALUES
(3001, 'Napoli', 'Magazzino centrale'),
(3002, 'Torino', 'Magazzino periferico'),
(3003, 'Sicilia', 'Magazzino periferico');


-- Inserimento dei dati nella tabella Inventario
INSERT INTO Inventario (Quantitativo, ID_Merce, ID_Magazzino) VALUES
(100, 2001, 3001),
(50, 3140, 3002),
(20, 8809, 3001),
(300, 4455, 3002),
(10, 6767, 3002),
(200, 5550, 3003),
(50, 2002, 3003);


-- Inserimento dei dati nella tabella Indirizzo
INSERT INTO Indirizzo (Codice_Indirizzo, Città, CAP, Via, N_Civico) VALUES
(6060, 'Roma', '00100', 'Via Roma', '10'),
(2002, 'Milano', '20100', 'Via Milano', '20'),
(1234, 'Napoli', '81033', 'Via Napoli', '10'),
(7372, 'Cassino', '41050', 'Via Casa', '23'),
(4512, 'Trapani', '70123', 'Via Boldi', '7'),
(8010, 'Latina', '11003', 'Via Mare', '8');


-- Inserimento dei dati nella tabella Spedizione
INSERT INTO Spedizione (ID_Spedizione, Targa, ID_Operatore, Peso_Totale, Data_Spedizione) VALUES
(1001, 'RM12345', 4001, 100, current_date),
(1012, 'MI67890', 1405, 200, current_date),
(2164, 'CC456DD', 6354, 200, current_date),
(8818, 'AA123BB', 3032, 700, current_date),
(3740, 'EE789FF', 5598, 700 , current_date),
(5101, 'GG012HH', 2861, 3000, current_date);


-- Inserimento dei dati nella tabella Cliente
INSERT INTO Cliente (CF, nome, cognome, nTelefono) VALUES
('RSSMRA85M01H501U', 'Mario', 'Rossi', '0123456789'),
('BNCLCD82S18F205Z', 'Luca', 'Bianchi', '0987654321'),
('VREOIO80H15WBJGT', 'Giovanni', 'Verdi', '0987654322'),
('ESPMRA00A01Y0JGT', 'Mario', 'Esposito', '0987654323'),
('VQNFSQ40D27BQJ0I', 'Luigi', 'Vero', '0987654325'),
('SOLGIU80E15H501', 'Giuseppe','Solo', '0987634326');


INSERT INTO  Reperibilità (Targa, Disponibilità, Data,Maxtrasportabile) VALUES
('RM12345',TRUE,'2024-02-28', 750),
('AA123BB',TRUE,'2024-02-27',3000),
('CC456DD',FALSE,'2024-03-21',800),
('GG012HH',TRUE,'2024-03-27', 2000),
('EE789FF',FALSE,'2024-04-20',750),
('MI67890',TRUE,'2024-04-3', 3500);

--Inserimento della tabella ordine
INSERT INTO Ordine (ID_Ordine , CF,  Codice_Indirizzo, DataOrdinato, Elaborato, Tipologia) VALUES
(173846, 'BNCLCD82S18F205Z', 6060, '2024-01-11', TRUE, 'Standard'),
(751467, 'ESPMRA00A01Y0JGT', 2002, '2024-01-01', FALSE, 'Premium'),
(671119, 'VQNFSQ40D27BQJ0I', 7372, '2024-02-4', TRUE, 'Ultimate'),
(342874, 'VREOIO80H15WBJGT', 1234 , '2024-02-8', FALSE, 'Standard'),
(92854, 'SOLGIU80E15H501', 4512 ,'2024-02-2', FALSE, 'Standard'),
(17409, 'RSSMRA85M01H501U', 8010, '2024-02-9', TRUE, 'Premium'),
(69691, 'VQNFSQ40D27BQJ0I', 7372 , '2024-02-5', TRUE, 'Ultimate');


-- Inserimento dei dati nella tabella Specifiche
INSERT INTO Specifiche (Quantità, ID_Merce, ID_Ordine) VALUES
(10, 8809, 671119 ),
(6, 4455,92854),
(3, 3140,17409 ),
(5, 6767, 69691),
(15, 5550, 342874),
(20, 1341, 671119 ),
(13, 2001, 751467);

