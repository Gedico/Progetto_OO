-- Creazione della tabella Specifiche
CREATE TABLE Specifiche 
(
	Quantità INT NOT NULL,
	ID_Merce INT NOT NULL,
	ID_Ordine INT NOT NULL,
  
  -- Creazione foreign key su ID_Merce 
  CONSTRAINT FK_ID_Merce FOREIGN KEY (ID_Merce)
    REFERENCES Merce(ID_Merce) ON DELETE CASCADE,
  
  -- Creazione foreign key su ID_Ordine
  CONSTRAINT FK_ID_Ordine FOREIGN KEY (ID_Ordine)
    REFERENCES Ordine(ID_Ordine) ON DELETE CASCADE,
  
  -- Gestione della Quantità
  CONSTRAINT Controllo_Quantità CHECK (Quantità > 0),
  
  -- Gestione che quantità meno di disponibilità
  CONSTRAINT Quantità_In_Inventario CHECK (Verifica_Quantita_Inventario(ID_Merce, Quantità))
);
