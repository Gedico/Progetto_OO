CREATE OR REPLACE PROCEDURE Selezione_Ordini_Da_Fare()
language plpgsql    
as $$
DECLARE

idordine Ordine.id_ordine%TYPE;
codfisc Ordine.CF%TYPE;
codsped Ordine.id_spedizione%TYPE;
codind Ordine.codice_indirizzo%TYPE;
dataor Ordine.dataordinato%TYPE;
tipolo Ordine.tipologia%TYPE;

	Ricerca CURSOR FOR
	SELECT *
	FROM ordine 
	WHERE Ordine.elaborato = FALSE ;
	
BEGIN
FETCH Ricerca INTO idordine, codfisc, codsped, codind, dataor, tipolo;

IF NOT FOUND THEN
      RAISE NOTICE 'Nessun risultato trovato.';
   ELSE

FOR rigacorrente IN Ricerca
LOOP

RAISE NOTICE 'ID Ordine: %, CF: %, ID Spedizione: %, Codice Indirizzo: %, Data Ordinato: %, Tipologia: %',
               idordine, codfisc, codsped, codind, dataor, tipolo;


END LOOP;
END IF;
END;$$;
