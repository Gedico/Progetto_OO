--Creazione della tabella 
CREATE TABLE Cliente(
	CF varchar(50) PRIMARY KEY,
	nome Varchar(50) NOT NULL,
	cognome Varchar(50) NOT NULL,
	nTelefono Varchar(50) NOT NULL); 


--Creazione vincoli sulla Tabella Cliente
ALTER TABLE Cliente

--Vincolo di unicità numero di telefono
ADD CONSTRAINT numeroUnico UNIQUE (nTelefono),
--Vincolo di uso di soli numeri in numero di telefono
ADD CONSTRAINT Controllo_Numero_Telefono CHECK (Controllo_Numerico(nTelefono)),
--Vincolo per il controllo del nome
ADD CONSTRAINT Controllo_nome_cliente CHECK (Controllo_Nome(nome)),
--Vincolo per il controllo del cognome
ADD CONSTRAINT Controllo_Cognome_cliente CHECK (Controllo_Cognome(cognome)),
--Vincolo per CF
ADD CONSTRAINT Controllo_CF CHECK (Controllo_CoDfis(CF));

