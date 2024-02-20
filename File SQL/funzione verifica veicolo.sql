CREATE OR REPLACE Function Verifica_Veicolo(codordine int, dataIN date,targaVEC VARCHAR)
RETURNS BOOLEAN AS $$
DECLARE
   pesotot INT;
   codice_magazzino record;
   riga record;
   risultato boolean default false;
BEGIN
   -- Calcolo del pesotot
   SELECT SUM(s.quantità * m.peso) INTO pesotot
	   FROM specifiche s
	   JOIN merce m ON m.id_merce = s.id_merce
	   WHERE s.id_ordine = codordine;

   -- Selezione del codice_magazzino
   SELECT mag.id_magazzino INTO codice_magazzino
   FROM (((ordine o 
      JOIN specifiche s ON o.id_ordine = s.id_ordine)
      JOIN merce m ON m.id_merce = s.id_merce)
      JOIN inventario i ON i.id_merce = m.id_merce)
      JOIN magazzino as mag ON mag.id_magazzino = i.id_magazzino
   WHERE o.id_ordine = codordine AND i.quantitativo > s.quantità;


   SELECT v.targa, m.città into riga
    FROM veicoli v
    JOIN reperibilità r ON v.targa = r.targa
    JOIN magazzino m ON m.id_magazzino = v.id_magazzino
    WHERE r.maxtrasportabile > pesotot AND r.data = dataIN AND r.disponibilità = true 
	AND v.targa = targaVEC;
 
   -- Verifico se sono state trovate righe
   IF  FOUND THEN
      Risultato = true;
   ELSE RAISE EXCEPTION 'Il veicolo non può trasportare quest ordine';
   END IF;
   
   RETURN risultato;
   
EXCEPTION
   WHEN OTHERS THEN -- Gestione delle eccezioni generiche
      RAISE EXCEPTION 'Errore durante l''esecuzione della ricerca: %', SQLERRM;
END;
$$ LANGUAGE plpgsql;