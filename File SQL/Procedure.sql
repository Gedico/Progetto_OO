CREATE OR REPLACE PROCEDURE ricerca_in_ordini(colonna_ricerca varchar,valore_ricerca varchar)
LANGUAGE plpgsql    
AS $$
DECLARE
   riga_corrente record;
   comando varchar;
BEGIN
-- Verifico che la colonna di ricerca esista
   IF colonna_ricerca NOT IN ('id_ordine', 'cf', 'codice_indirizzo', 'dataordinato', 'tipologia','elaborato') THEN
      RAISE EXCEPTION 'Colonna di ricerca non valida: %', colonna_ricerca;
   END IF;
   
  comando := 'SELECT * FROM ordine WHERE ' || colonna_ricerca || ' = ' || quote_literal(valore_ricerca);

   FOR riga_corrente IN EXECUTE comando-- Apro un cursore FOR e eseguo la query dinamica
   LOOP
      -- Stampo i dettagli della riga corrente
      RAISE NOTICE 'ID Ordine: %, CF: %, Codice Indirizzo: %, Data Ordinato: %, Tipologia: %',
                    riga_corrente.id_ordine, riga_corrente.cf,
                    riga_corrente.codice_indirizzo, riga_corrente.dataordinato, riga_corrente.tipologia::TEXT;
   END LOOP;

   IF NOT FOUND THEN-- Verifico se sono state trovate righe
      RAISE NOTICE 'Nessun risultato trovato.';
   END IF;
   
EXCEPTION
WHEN OTHERS THEN -- Gestione delle eccezioni generiche
RAISE EXCEPTION 'Errore durante l''esecuzione della ricerca: %', SQLERRM;
END;
$$;