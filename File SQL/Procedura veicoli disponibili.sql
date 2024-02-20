CREATE OR REPLACE PROCEDURE Disponibilità_per_ordine(codordine int, data date)
LANGUAGE plpgsql    
AS $$
DECLARE
   pesotot int;
   codice_magazzino int;
   riga record;
BEGIN
-- Controllo se l'ordine è già stato gestito
   IF EXISTS (SELECT * FROM Ordine WHERE ID_ORDINE = codordine AND ELABORATO = TRUE) THEN
      RAISE EXCEPTION 'Ordine selezionato già gestito %', codordine;
	  
--Controllo se ordine esiste
	 ELSE IF NOT EXISTS (SELECT * FROM ordine WHERE ID-Ordine = codordine)THEN
	  RAISE EXCEPTION 'Ordine inesistente: %',codordine;
	  
--Controllo se la data è maggioreo uguale della data corrente 
	ELSE IF (data < current_date)THEN
	 RAISE EXCEPTION 'Non puoi inserire una data minore a quella attuale: % ', data;
   END IF;
   END IF;
   END IF;

   -- Calcolo del pesotot
   SELECT SUM(s.quantità * m.peso) INTO pesotot
	   FROM specifiche s
	   JOIN merce m ON m.id_merce = s.id_merce
	   WHERE s.id_ordine = codordine;
	   
   RAISE NOTICE 'pesotot: %', pesotot;

   -- Selezione del codice_magazzino
   SELECT mag.id_magazzino INTO codice_magazzino
   FROM (((ordine o 
      JOIN specifiche s ON o.id_ordine = s.id_ordine)
      JOIN merce m ON m.id_merce = s.id_merce)
      JOIN inventario i ON i.id_merce = m.id_merce)
      JOIN magazzino as mag ON mag.id_magazzino = i.id_magazzino
   WHERE o.id_ordine = codordine AND i.quantitativo > s.quantità;
   
   RAISE NOTICE 'cod magazzino: %', codice_magazzino;

   -- Iterazione attraverso i veicoli disponibili
   FOR riga IN EXECUTE 
   'SELECT v.targa, m.città
    FROM veicoli v
    JOIN reperibilità r ON v.targa = r.targa
    JOIN magazzino m ON m.id_magazzino = v.id_magazzino
    WHERE r.maxtrasportabile > $1 AND r.data = $2 AND r.disponibilità = true'
   USING pesotot, data
   LOOP
      -- Stampo i dettagli della riga corrente
      RAISE NOTICE 'Targa: %, Città: %', riga.targa, riga.città;
   END LOOP;

   -- Verifico se sono state trovate righe
   IF NOT FOUND THEN
      RAISE NOTICE 'Nessun risultato trovato.';
   END IF;
EXCEPTION
   WHEN OTHERS THEN -- Gestione delle eccezioni generiche
      RAISE EXCEPTION 'Errore durante l''esecuzione della ricerca: %', SQLERRM;
END;
$$;


 
		  