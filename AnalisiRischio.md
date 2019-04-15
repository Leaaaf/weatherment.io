## Analisi del rischio

### Valutazione dei beni
Bene | Valore | Esposizione
-|-|-|
Evento | Alto <br> La perdita di un evento non permette la ricostruzione esatta dello storico dei dati, e comporta una vera e propria perdita di informazioni | Alta <br>Danni d'immagine: mancato inserimento nei database di un evento più o meno significativo. 
Informazioni relative alla stazione meteo | Alto <br> Impossibilità di determinare la località dell'evento registrato; dati quindi inutilizzabili| Media/Alta <br> A seconda del motivo per il quale la stazione non è riuscita a comunicare la sua posizione; un errore interno, facilmente risolvibile o errori di comunicazione più gravi
Sistema informativo | Alto <br> Il sistema informativo essendo il componente principale per l'utente finale deve sempre essere funzionante, pena l'impossibilità di visualizzare dati e grafici| Alta <br> Sito totalmente non funzionante, danni d'immagine e alti costi di ripristino di sistema
Stazione meteo | Basso <br> Nel caso di malfunzionamento o di una perdita di una stazione meteo, non si riceveranno più eventi| Bassa <br> Data l'architettura del sistema, si possono rimuovere e aggiungere stazioni senza danneggiare il sistema stesso

### Analisi minacce e controlli
Minaccia | Probabilità | Controllo | Fattibilità
-|-|-|-|
Alterazione dei dati remoti | Bassa | Utilizzo di protocolli sicuri di comunicazione (HTTPS) | Costo basso/medio
DDoS (Distributed Denial of Service) | Media | Limitazione degli accessi | Costo basso in quanto difficile difendersi da questo tipo di attacchi
Invio di dati fake | Media | Autenticazione della stazione prima di poter comunicare | Costo alto, necessità di definire un sistema di accesso/registrazione per le varie stazioni. Perdita inoltre del concetto di sistema aperto a tutti 
Man in the middle | Bassa | Utilizzo di certificati di autenticità delle parti comunicanti | Costo basso/medio a seconda dell'utilizzo di un protocollo di comunicazione più o meno sicuro
Manomissione delle stazioni meteo | Media | Nessun controllo dato che ogni tipo di hardware capace di utilizzare dei sensori e trasmettere dati può essere una stazione | -

### Analisi della tecnologia dal punto di vista della sicurezza
Tecnologia | Vulnerabilità
-|-|
Architettura Client/Server | <li> Attacco DDoS<br><li> Man in the middle
Cifratura delle comunicazioni | Utilizzo di cifratura simmetrica e asimmetrica. Non strettamente necessaria in quanto i dati trasmessi non sono dati sensibili.
Stazione meteo | <li>Alterazione dei sensori<br> <li>Alterazione della posizione geografica della stazione<br> <li>Manomissione e blocco delle trasmissioni