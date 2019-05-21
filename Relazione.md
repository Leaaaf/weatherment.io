![](resources/logo.svg)

## Membri del team (Gruppo 38):
-   Alessandro Foglia (0000801918)
-   Mattia Forcellese (0000788898)
-   Federico Pomponii (0000803024)
  
<P style='page-break-before: always'>

- [Abstract](#abstract)
  - [Descrizione del progetto](#descrizione-del-progetto)
- [Analisi dei requisiti](#analisi-dei-requisiti)
  - [Requisiti del sistema](#requisiti-del-sistema)
  - [Analisi del dominio](#analisi-del-dominio)
    - [Vocabolario](#vocabolario)
  - [Analisi dei requisiti](#analisi-dei-requisiti-1)
    - [Casi D'uso](#casi-duso)
  - [Analisi del rischio](#analisi-del-rischio)
    - [Valutazione dei beni](#valutazione-dei-beni)
    - [Analisi minacce e controlli](#analisi-minacce-e-controlli)
    - [Analisi della tecnologia dal punto di vista della sicurezza](#analisi-della-tecnologia-dal-punto-di-vista-della-sicurezza)
    - [Security use case e Misuse case](#security-use-case-e-misuse-case)
    - [Requisiti di Protezione](#requisiti-di-protezione)
  - [Interfacce grafiche](#interfacce-grafiche)
- [Analisi del problema](#analisi-del-problema)
  - [Analisi del documento dei requisiti](#analisi-del-documento-dei-requisiti)
    - [Analisi delle funzionalità](#analisi-delle-funzionalit%C3%A0)
    - [Analisi dei vincoli](#analisi-dei-vincoli)
    - [Analisi delle interazioni](#analisi-delle-interazioni)
  - [Analisi dei ruoli e delle responsabilità](#analisi-dei-ruoli-e-delle-responsabilit%C3%A0)
  - [Creazione modello del dominio](#creazione-modello-del-dominio)
  - [Architettura logica](#architettura-logica)
    - [Struttura](#struttura)
      - [Diagramma dei package](#diagramma-dei-package)
      - [Diagramma delle classi](#diagramma-delle-classi)
    - [Interazione](#interazione)
      - [Diagramma di sequenza: Lettura dati dai diversi sensori](#diagramma-di-sequenza-lettura-dati-dai-diversi-sensori)
      - [Diagramma di sequenza: Trasmissione dei dati](#diagramma-di-sequenza-trasmissione-dei-dati)
      - [Diagramma di sequenza: Gestione dell'evento](#diagramma-di-sequenza-gestione-dellevento)
      - [Diagramma di sequenza: Proiezione](#diagramma-di-sequenza-proiezione)
    - [Comportamento](#comportamento)
      - [Diagramma di stato Trasmissione](#diagramma-di-stato-trasmissione)
    - [Piano del Lavoro](#piano-del-lavoro)
    - [Piano di Collaudo](#piano-di-collaudo)

<P style='page-break-before: always'>

# Abstract
## Descrizione del progetto

WeatherMent.IO nasce con l'idea di creare un database con informazioni raccolte da diverse stazioni metereologiche.


Il progetto è strutturato in modo tale da permettere la consultazione e visualizzazione dei dati grazie ad una interfaccia web che espone all'utente finale grafici dettagliati di dati relativi ad un preciso luogo ed in base a determinati intervalli temporali.


L'architettura è quella di un sistema ad eventi, distruibuito su diversi server e database per avere una maggiore efficienza, scalabilità e affidabilità.
Nello specifico i dati finali esposti all'utente sono organizzati rispetto all'evento che rappresentano su database documentali, mentre quelli raccolti direttamente dalle stazioni vengono processati ed immagazzinati da un server dedicato, che si appoggia invece su di un database relazionale. In questo modo viene garantita una maggiore efficienza su grandi quantità di informazioni, in quanto principali operazioni di lettura e scrittura avvengono in modo del tutto scorrelato.


Il progetto mira ad essere facilmente scalabile sia orizzontalmente che verticalmente ed espandibile sia dal punto di vista di nuove stazioni che di nuovi tipi di dato. Le nuove stazioni meteo prima di poter trasmettere i dati dovranno essere autenticate per garantire l'affidabilità del sistema. 


<P style='page-break-before: always'>

# Analisi dei requisiti

## Requisiti del sistema
ID  | Requisito | Tipo
-|-|-
F.1 | La stazione invia i dati al server solo quando avvengono cambiamenti nei valori letti dai sensori; così facendo non si generano eventi ridondanti  | Funzionale
F.2 | Nel momento in cui si genera un evento da inviare al server, la stazione provvederà a determinare la sua posizione e aggiungerà nazione e ZIP Code prima di inoltrarlo | Funzionale
F.3 | I dati raccolti dalle stazioni vengono validati dal server prima dell'inserimento sul database, assicurandone la correttezza all'utente finale | Funzionale
F.4 | Attraverso il client è possibile visualizzare i dati in tempo reale, in un intervallo di tempo definito dall'utente o visualizzare la vista città di una stazione | Funzionale
F.5 | L'interfaccia web deve permettere la consultazione attraverso filtri e criteri di ricerca | Funzionale
F.6 | L'interfaccia web deve permettere la consultazione di grafici riassuntivi | Funzionale
F.7 | Ogni evento generato dalla stazione meteo appartiene ad uno specifico topic | Funzionale
F.8 | Per ogni topic ci sono determinati tipi di evento | Funzionale
F.9 | Per ogni tipo di evento è definito un preciso schema che il payload deve rispettare | Funzionale
F.10 | Ogni volta che viene aggiunto un evento sul database si notifica ai server in ascolto | Funzionale 
D.1 | La temperatura inviata dai sensori deve essere misurata in *gradi Celsius (°C)* | Dominio
D.2 | La pressione inviata dai sensori deve essere misurata in *ettopascal (hPa)* | Dominio
D.3 | La velocità del vento inviata dai sensori deve essere misurata in *chilometri orari (km/h)*. Inoltre viene indicata anche la direzione | Dominio
D.4 | L'umidità inviata dai sensori deve essere misurata in *percentuale (%)* | Dominio
D.5 | La quantità di *CO*$_2$ inviata dai sensori deve essere misurata in *parti per milione (ppm)* | Dominio 
D.6 | Dalla stazione viene rilevato l'attuale stato metereologico | Dominio

<P style='page-break-before: always'>

## Analisi del dominio

### Vocabolario
Voce | Definizione | Sinonimi
-|-|-
Architettura | Definisce l'organizzazione e la comunicazione dei diversi componenti all'interno dell'ecosistema |
Autenticazione | Sistema di riconoscimento necessario per permettere ad una stazione di poter inviare i dati al sistema | Registrazione
Append-Only | Tipologia di database che permette soltanto l'inserimento dei dati e non la modifica o l'eliminazione
Barriera | Sistema di validazione dei dati inviati da una stazione secondo uno schema ben definito. Necessario per evitare di scrivere sul database dati non validi | Validazione
Buffer | Memoria dedicata nella stazione meteo per il salvataggio dei dati nel caso in cui il server non fosse pronto a riceverli o se ci dovesse essere qualche problema nel sistema |  
Database relazionale |  Modello logico di rappresentazione o strutturazione dei dati di un database implementato su sistemi di gestione di basi di dati |
Evento | La stazione emette un evento per notificare all'intero sistema dei cambiamenti, necessari per la ricostruzione della vista città | Cambiamento, Event
Frequenza di campionamento | Numero di volte al secondo in cui un segnale analogico viene misurato e memorizzato in forma digitale |
Notifica PostgreSQL | Viene segnalata l'immissione di una nuova riga all'interno del database relazionale postgres | Notify
Payload | Pacchetto contenente tutti i dati raccolti dalla stazione | Carico 
Proiezione | Interrogazione al database che fornisce all'utente soltanto i dati richiesti
Scalabilità | In informatica, la caratteristica di un sistema software o hardware facilmente modificabile nel caso di variazioni notevoli della mole o della tipologia dei dati trattati | Espandibilità
Scheda | Unisce tutti i componenti elettrici ed i sensori e ne permette il funzionamento | 
Schema | Definisce per ogni tipo di evento delle regole e dei formati necessari per la validazione | 
Sensore | Dispositivo elettronico in grado di rilevare una grandezza fisica e di trasmettere le variazioni a un sistema di misurazione o di controllo | Strumento di misura
Stazione | L'insieme dei sensori collegati alla scheda principale, situata in un determinato luogo, capace di comunicare con il sistema | 
Vista città | L'insieme di tutti i dati raccolti dalle diverse stazioni organizzati in base agli eventi a cui fanno riferimento | Stroico
Type | Definisce nello specifico il tipo di evento che riferisce il topic | Tipo evento
Topic | Definisce l'argomento a cui l'evento fa riferimento | Argomento
Version | Indica la versione della stazione

<P style='page-break-before: always'>

## Analisi dei requisiti

### Casi D'uso

![](resources/ScenaryCase.svg)

La stazione è considerata un attore in quanto è un sistema esterno con un ruolo attivo. Interviene nell’applicativo generando continuamente dati in ingresso che verranno utilizzati da Gestione Eventi solo nel caso in cui vengano rilevati dei cambiamenti.

L’utente ha la possibilità di consultare la vista città di tutti i dati meteo e le Statistiche. Entrambe le schermate dispongono di appositi filtri per la consultaizone.

<table>
<thead><h3>Scenari d'uso</h3></thead>
<tr><td><b>Titolo</b></td> <td>Gestione Evento</td></tr>
<tr><td><b>Descrizione</b></td><td>Lettura dati dalla stazione meteo, validazione dei dati, scrittura su sistema</td></tr>
<tr><td><b>Attori</b></td><td>Event, Stazione Meteo</td></tr>
<tr><td><b>Relazioni</b></td> <td></td></tr>
<tr><td><b>Precondizioni</b></td> <td>Si è verificato un evento registrato dalla stazione meteo (i.e. un cambio di temperatura, di pressione atmosferica etc)</td></tr>
<tr><td><b>Postcondizioni</b></td><td>Il sistema ha rilevato l'evento, controllato la sua validità e scritto in maniera persistente sul sistema</td></tr>
<tr><td><b>Scenario principale</b></td><td><li style="list-style-type: decimal;"> La stazione meteo invia i dati dell'evento a Gestione Evento <li style="list-style-type: decimal;"> Gestione Evento controlla che l'evento ricevuto sia valido secondo uno schema preciso, definito internamente al sistema<li style="list-style-type: decimal;">Gestione Evento registra in maniera persistente l'evento sul sistema<li style="list-style-type: decimal;">Il sistema prosegue la sua normale esecuzione, in attesa di ricevere altri eventi</td></tr>
<tr><td><b>Scenari alternativi</b> </td><td>La connessione con la stazione meteo viene persa o è molto lenta: <li style="list-style-type: decimal;">La stazione meteo nel caso in cui il server smetta di rispondere riempie un buffer<li style="list-style-type: decimal;">Appena il server torna a rispondere la stazione svuota il buffer inviando gli eventi memorizzati<li style="list-style-type: decimal;">La stazione meteo elimina localmente in via definitiva l'evento solo ed esclusivamente se il server ne conferma la ricezione, per evitare una perdita di eventi</td></tr>
<tr><td><b>Requisiti non funzionali</b></td><td>Integrità dei dati letti dal sensore <br> Velocità nella validazione dell'evento <br> Efficienza nella scrittura persisente sul sistema <br> Efficienza della stazione meteo nell'invio dei dati e nell'utilizzo di memoria cache</td></tr>
</table>
<br>
<table>
<tr><td><b>Titolo</b></td> <td>Vista</td></tr>
<tr><td><b>Descrizione</b></td><td>Il sistema permette all'utente di visualizzare l'elenco degli eventi registrati</td></tr>
<tr><td><b>Attori</b></td><td>Utente</td></tr>
<tr><td><b>Relazioni</b></td> <td>Filtro Grafici</td></tr>
<tr><td><b>Precondizioni</b></td> <td></td></tr>
<tr><td><b>Postcondizioni</b></td><td>Il sistema ha mostrato all'utente gli eventi registrati</td></tr>
<tr><td><b>Scenario principale</b></td><td><li style="list-style-type: decimal;"> L'utente ricerca la città di cui vuole visualizzare i dati<li style="list-style-type: decimal;"> Viene mostrata una schermata contenente tutti gli eventi relativi a quella città (registrati in base al cap della stazione meteo)<li style="list-style-type: decimal;">L'utente può decidere di filtrare attraverso Filtro Grafici per decidere la visualizzazione secondo criteri di tempo e di dato</td></tr>
<tr><td><b>Scenari alternativi</b> </td><td>La città ricercata non ha eventi: <li style="list-style-type: decimal;">Il sistema notifica all'utente e ridireziona alla schermata di ricerca</td></tr>
<tr><td><b>Requisiti non funzionali</b></td><td>Integrità dei dati <br>Semplicità nell'utilizzo e immediatezza nella lettura<br>Velocità in lettura</td></tr>
</table>
<br>
<table>
<tr><td><b>Titolo</b></td> <td>Filtro Grafici</td></tr>
<tr><td><b>Descrizione</b></td><td>Il sistema permette all'utente di filtrare gli eventi da visualizzare</td></tr>
<tr><td><b>Attori</b></td><td>Utente</td></tr>
<tr><td><b>Relazioni</b></td> <td></td></tr>
<tr><td><b>Precondizioni</b></td> <td></td></tr>
<tr><td><b>Postcondizioni</b></td><td>Il sistema ha mostrato all'utente gli eventi registrati filtrati a seconda dei criteri specificati</td></tr>
<tr><td><b>Scenario principale</b></td><td><li style="list-style-type: decimal;">L'utente imposta i criteri secondo cui filtrare gli eventi: temporali, oppure legati al dato da visualizzare: pressione, inquinamento aria, temperatura, vento e l'umidità<li style="list-style-type: decimal;">Il sistema effettua la ricerca e mostra all'utente gli eventi risultanti</td></tr>
<tr><td><b>Scenari alternativi</b> </td><td>La ricerca effettuata non ha eventi: <li style="list-style-type: decimal;">Il sistema notifica all'utente</td></tr>
<tr><td><b>Requisiti non funzionali</b></td><td>Rapidità ricerca</td></tr>
</table>
<br>
<table>
<tr><td><b>Titolo</b></td> <td>Statistiche</td></tr>
<tr><td><b>Descrizione</b></td><td>Il sistema permette all'utente di visualizzare dei rapporti statistici sugli eventi relativi alle varie località </td></tr>
<tr><td><b>Attori</b></td><td>Utente</td></tr>
<tr><td><b>Relazioni</b></td> <td>Filtro Statistiche</td></tr>
<tr><td><b>Precondizioni</b></td> <td></td></tr>
<tr><td><b>Postcondizioni</b></td><td>Il sistema ha mostrato all'utente statistiche dettagliate sugli eventi registrati nel sistema</td></tr>
<tr><td><b>Scenario principale</b></td><td><li style="list-style-type: decimal;">L'utente seleziona la schermata relativa alle statistiche<li style="list-style-type: decimal;">Il sistema mostra all'utente un report annuo generale di tutte le località<li style="list-style-type: decimal;">L'utente può decidere di filtrare le statistiche temporalmente e/o geograficamente</td></tr>
<tr><td><b>Scenari alternativi</b> </td><td>Non vi sono statistiche da mostrare:<li style="list-style-type: decimal;">Il sistema notifica all'utente di non poter proseguire l'azione</td></tr>
<tr><td><b>Requisiti non funzionali</b></td><td>Integrità dei dati<br>Velocità in lettura <br>Immediatezza e semplicità di utilizzo e di consultazione</td></tr>
</table>
<br>
<table>
<tr><td><b>Titolo</b></td> <td>Filtro Statistiche</td></tr>
<tr><td><b>Descrizione</b></td><td>Il sistema permette all'utente di filtrare i rapporti statistici sugli eventi relativi alle varie località a seconda di diversi criteri </td></tr>
<tr><td><b>Attori</b></td><td>Utente</td></tr>
<tr><td><b>Relazioni</b></td> <td>Statistiche</td></tr>
<tr><td><b>Precondizioni</b></td> <td></td></tr>
<tr><td><b>Postcondizioni</b></td><td>Il sistema ha mostrato all'utente statistiche dettagliate sugli eventi registrati nel sistema che soddisfano i criteri di ricerca</td></tr>
<tr><td><b>Scenario principale</b></td><td><li style="list-style-type: decimal;">L'utente imposta i criteri secondo cui filtrare le statistiche: temporalmente o geograficamente può impostare un'area di grandezza variabile che comprenda diverse località<li style="list-style-type: decimal;">Il sistema mostra all'utente un report che soddisfi i criteri impostati</td></tr>
<tr><td><b>Scenari alternativi</b> </td><td>Non vi sono statistiche che soddisfino i criteri da mostrare:<li style="list-style-type: decimal;">Il sistema notifica all'utente di non poter proseguire l'azione</td></tr>
<tr><td><b>Requisiti non funzionali</b></td><td>Rapidità ricerca</td></tr>
</table>

<P style='page-break-before: always'>

## Analisi del rischio

### Valutazione dei beni
Bene | Valore | Esposizione
-|-|-|
Event | Alto <br> La perdita di un evento non permette la ricostruzione esatta dei dati, e comporta una vera e propria perdita di informazioni | Alta <br>Danni d'immagine: mancato inserimento nei database di un evento più o meno significativo. 
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

### Security use case e Misuse case

![](resources/SecuritycaseMisusecase.svg)

<table>
<tr><td colspan=4><b>Caso d'uso:</b> integrità</td> </tr>
<tr><td colspan=4><b>Percorso del caso d'uso:</b> integrità nella trasmissione dei dati un sensore</td> </tr>
<tr><td colspan=4><b>Misuse case:</b> Man in the middle </td></tr>
<tr><td colspan=4><b>Rischi alla sicurezza:</b> Un malintenzionato può modificare i dati inviati da una stazione remota prima che arrivino al server </td></tr>
<tr><td colspan=4><b>Precondizioni:</b> L'hacker attaccante può intercettare i dati e modificarli, per poi ritrasmetterli al server</td> </tr>
<tr>
    <td rowspan=2><b>Interazioni dell'utente</b></td>
    <td rowspan=2><b>Interazioni dell'attaccante</b></td>
    <td colspan=2><b><center>Requisiti del sistema</center></b></td>
    <tr><td><b>Interazioni del sistema</b></td> <td><b>Azioni del sistema</b></td>
</tr>
<tr><td></td><td></td><td>La stazione meteo dovrebbe inviare i dati corretti al server</td><td>Il sistema deve impedire che i dati vengano manomessi da qualcuno senza che l'utente se ne accorga</td></tr>
<tr><td></td><td>L'attaccante intercetta e modifica i dati per poi ritrasmetterli al server</td><td></td><td></td></tr>
<tr><td>L'utente riceve dei dati corrotti</td><td></td><td></td><td>Il sistema rileva la corruzzione da parte dell'attaccante</td></tr>
<tr><td></td><td></td><td>Il sistema dovrebbe notificare all'utente la <i>non</i> correttezza dei dati e provvedere ad invalidare o sospendere la ricezione dati dalla stazione meteo</td><td></td></tr>
<tr><td colspan=4><b>Postcondizioni:</b> Il sistema deve aver notificato all'utente l'accaduto e deve aver bloccato la stazione</td></tr>
</table>

<table>
<tr><td colspan=4><b>Caso d'uso:</b> integrità</td> </tr>
<tr><td colspan=4><b>Percorso del caso d'uso:</b>Corretto funzionamento del sistema</td> </tr>
<tr><td colspan=4><b>Misuse case:</b>Attacco DDoS </td></tr>
<tr><td colspan=4><b>Rischi alla sicurezza:</b>Un malintenzionato potrebbe tentare di sovraccaricare le risorse del sistema con conseguente malfunzionamento dello stesso</td></tr>
<tr><td colspan=4><b>Precondizioni:</b>Il sistema non può gestire un numero molto elevato di richieste in contemporanea</td> </tr>
<tr>
    <td rowspan=2><b>Interazioni dell'utente</b></td>
    <td rowspan=2><b>Interazioni dell'attaccante</b></td>
    <td colspan=2><b><center>Requisiti del sistema</center></b></td>
    <tr><td><b>Interazioni del sistema</b></td> <td><b>Azioni del sistema</b></td>
</tr>
<tr><td></td><td>L'attaccante effettua un numero molto alto di richieste al sistema in modo da sovraccaricarlo</td><td></td><td></td></tr>
<tr><td></td><td></td><td></td><td>Il sistema deve bloccare l'attaccante nel caso in cui rilevi un numero molto elevato di tentativi di trasmissione o di richieste</td></tr>
<tr><td></td><td></td><td>Il sistema dovrebbe registrare l'attacco avvenuto per poter poi essere analizzato dall'amministratore</td><td></td></tr>
<tr><td colspan=4><b>Postcondizioni:</b> Il sistema deve aver notificato nei log l'avvenuto</td></tr>
</table>

<table>
<tr><td colspan=4><b>Caso d'uso:</b> Integrità</td> </tr>
<tr><td colspan=4><b>Percorso del caso d'uso:</b> integrità dei dati salvati dal sistema</td> </tr>
<tr><td colspan=4><b>Misuse case:</b> Operazione vietata</td></tr>
<tr><td colspan=4><b>Rischi alla sicurezza:</b> Un malintenzionato può modificare i dati già salvati sul database, modificando lo storico degli eventi</td></tr>
<tr><td colspan=4><b>Precondizioni:</b> Il sistema ha in memoria dei dati che non possono e non devono essere modificati in quanto invaliderebbero lo storico degli eventi</td> </tr>
<tr>
    <td rowspan=2><b>Interazioni dell'utente</b></td>
    <td rowspan=2><b>Interazioni dell'attaccante</b></td>
    <td colspan=2><b><center>Requisiti del sistema</center></b></td>
    <tr><td><b>Interazioni del sistema</b></td> <td><b>Azioni del sistema</b></td>
</tr>
<tr><td></td><td>L'attaccante tenta di modificare i dati salvati dal sistema</td><td></td><td></td></tr>
<tr><td></td><td></td><td></td><td>Il sistema deve impedire che i dati vengano manomessi una volta memorizzati</td></tr>
<tr><td></td><td></td><td>Il sistema dovrebbe registrare il tentativo di attacco per poter poi essere analizzato dall'amministratore</td><td></td></tr>
<tr><td colspan=4><b>Postcondizioni:</b> Il sistema deve verificare la correttezza dei dati memorizzati</td></tr>
</table>

### Requisiti di Protezione
Dopo l'analisi dei rischi, vi è quindi il bisogno di nuovi requisiti riguardanti la protezione del sistema e dei dati:
1. Un sistema di log che permetta all'amministratore di visualizzare tutte le azioni avvenute sul sistema, registrate in modo permanente; nel caso di attacchi esterni come ManInTheMiddle, DDoS o di tentativi di corruzione dei dati, è possibile risalire alla causa e analizzare nel dettaglio quanto avvenuto. I log verranno visualizzati e gestiti con un editor di testo esterno, non rilevante e non implementato per il progetto.
2. I dati sismici trasmessi in remoto devono essere protetti da attacchi di tipo man in the middle, eventualmente adottando una cifratura dei dati in transito.

<P style='page-break-before: always'>

## Interfacce grafiche

<P style='page-break-before: always'>

# Analisi del problema
## Analisi del documento dei requisiti
### Analisi delle funzionalità

**Tabella Funzionalità** 

Funzionalità | Tipo | Grado Complessità
|-|-|-
Gestione Eventi | Gestione e memorizzazione dei dati, interazione con l'esterno | Complessa
Scrittura Log | Memorizzazione dati | Semplice
Statistiche | Visualizzazione report generali filtrabili | Semplice
Storico | Visualizzazione eventi filtrabili | Semplice

**Gestione Evento: Tabella Informazioni/ Flusso**

Informazione | Tipo | Livello Protezione/ Privacy | Input/ Oputput | Vincoli
|-|-|-|-|-
Dato sensore Hardware | Semplice | Media | Input | |
Event | Composto | Alta | Output | |
Data | Semplice | Media | Input | |
Ora | Semplice | Media | Input | |
Stazione D'origine | Composto | Media | Input | |

**Scrittura Log: Tabella Informazioni/ Flusso** 

Informazione | Tipo | Livello Protezione/ Privacy | Input/ Oputput | Vincoli
|-|-|-|-|-
IP | Semplice | Alta | Input | |
Azione | Semplice | Alta | Input | |
Ora | Semplice | Media | Input | |
Data | Semplice | Media | Input | |

**Statistiche: Tabella Informazioni/ Flusso** 

Informazione | Tipo | Livello Protezione/ Privacy | Input/ Oputput | Vincoli
|-|-|-|-|-
**Event** composto da: | Composto | Alta | Input | |
Topic | Semplice | Media | Input | Stringa |
Type | Semplice | Media | Input | Stringa |Version | Semplice | Media | Input | Intero maggiore di zero
Payload | Composto | Media | Input | |

**VistaCitta: Tabella Informazioni/ Flusso** 

Informazione | Tipo | Livello Protezione/ Privacy | Input/ Oputput | Vincoli
|-|-|-|-|-
**Event** composto da: | Composto | Alta | Input | |
Topic | Semplice | Media | Input | Stringa |
Type | Semplice | Media | Input | Stringa |Version | Semplice | Media | Input | Intero maggiore di zero
Payload | Composto | Media | Input | |

### Analisi dei vincoli

**Tabella dei vincoli**

Requisito | Categorie | Impatto | Funzionalità
|-|-|-|-
Integrità | Integrità | Rallentamento nella fase di scrittura e di validazione del dato; correttezza del dato assicurata dalla barriera | Gestione Evento, Statistiche, VistaCitta
Efficienza scrittura | Performance | Maggiore efficienza nella fase di scrittura persistente del dato ed un tempo di risposta ridotto | Gestione Evento
Velocità validazione | Performance | Maggiore efficienza nella validazione del dato ed un tempo di risposta ridotto | Gestione Evento
Efficienza stazione meteo | Sistema esterno | Impatto sul sistema nullo; possibilità di elaborare velocemente i dati letti dai sensori |
Rapidità di ricerca | Tempo di risposta | Maggiore efficienza nella fase di fetch dei dati all'interno del database | VistaCitta, Statistiche
Velocità di lettura | Tempo di risposta | Maggiore efficienza nella fase di lettura del dato ed un tempo di risposta ridotto | VistaCitta, Statistiche
Semplicità ed immediatezza nell'utilizzo |  Usabilità | Migliore usabilità da parte dell'utente finale; interfacce grafiche intuitive | VistaCitta, Statistiche

### Analisi delle interazioni

**Tabella maschere**

Maschera | Informazioni | Funzionalità
|-|-|-
Homepage | Pagina iniziale con logo, barra di richerca della località d'interesse e relativo bottone per le statistiche generali | VistaCitta, Statistiche
VistaCitta | Dati in tempo reale filtrabili e grafici degli eventi relativi alla località cercata | VistaCitta
Schermata statistiche | Dati in tempo reale e report filtrabili relativi alla località cercata | Statistiche
 
**Tabella sistemi esterni**

Sistema | Descrizione | Protocollo di interazione | Livello di sicurezza
|-|-|-|-
Stazione meteo | Insieme di sensori e componenti hardware che formano una stazione meteo | La stazione invia al server gli eventi seguendo lo schema di default. Il sistema li convalida e li scrive in modo persistente sul database | Medio; la stazione può subire attacchi esterni sia dal punto di vista software che hardware

## Analisi dei ruoli e delle responsabilità

Ruolo | Responsabilità | Maschere | Riservatezza | Numerosità
-|-|-|-|-|
Utente | Consulta i dati in tempo reale delle stazioni meteo utilizzando i filtri offerti dall'applicazione e il relativo storico | Homepage, VistaCitta, Schermata statistiche | Bassa | Il numero di utenti non è limitato. Dipende dalla scalabilità del sistema

**Utente : Tabella ruolo-informazioni**

Informazione | Tipo di accesso
-|-|
VistaCitta | Lettura
Statistiche | Lettura

## Creazione modello del dominio

Direction:<br>N; NNE; NE; ENE; E; ESE; SE; SSE; S; SSO; SO; OSO; O; ONO; NO; NNO

State:<br>SUNNY; CLOUDY; RAINY; SNOWY

![](resources/DominioStazione.svg)

![](resources/ModelloDelDominio.svg)

## Architettura logica

### Struttura

#### Diagramma dei package

![](resources/StrutturaPackage.svg)

#### Diagramma delle classi

![](resources/DiagrammaClassiUtente.svg)

**ViewHomePage** interroga la VistaCittàController per quanto riguarda la visualizzazione di una città specifica; altrimenti StatisticheController per avere dei report nazionali o della specifica città cercata. <br>
**ViewCitta** oltre alla visualizzazione specifica della località cercata mostra all'utente i relativi dati in tempo reale. <br>
**VistaCittaController** filtra per un determinato tipo di evento e per un determinato lasso di tempo. <br>
**StatisticheController** filtra per città cercata e un determinato intervallo di tempo.

![](resources/DiagrammaClassiSistema.svg)

**LogController** scrive dei file di log a seconda delle azioni avvenute sul sistema. <br>
**TrasmissioneController** si occupa della trasmissione dei dati dalla stazione meteo al server. <br>
**EventoController** scrive in maniera persistente gli eventi dopo aver effettuato la validazione secondo lo schema predefinito. <br>
**ProiezioniController** aggrega gli eventi letti da EventoController creando delle proiezioni a seconda degli eventi ricevuti. Le proiezioni così create verranno poi utilizzate da VistaCittaController e da StatisticheController.

### Interazione

#### Diagramma di sequenza: Lettura dati dai diversi sensori
![](resources/InteractionLetturaSensori.svg)
In maniera ciclica e parallela i sensori acquisiscono i dati che vengono poi inoltrati alla stazione meteo.

#### Diagramma di sequenza: Trasmissione dei dati
![](resources/InteractionTrasmissione.svg)
I dati ricevuti dai sensori vengono elaborati da **StazioneController** il quale, se verifica un cambiamento, crea un evento da trasmettere all'apposito controller. In caso di errori nella trasmissione è previsto un sistema di backup e reinvio di dati per garantire l'integrità di tutti gli eventi.

#### Diagramma di sequenza: Gestione dell'evento
![](resources/InteractionGestioneEvento.svg)
**EventoController** si occupa della validazione dell'evento ricevuto, superato tale controllo provvede alla scrittura persistente dell'evento.

#### Diagramma di sequenza: Proiezione
![](resources/InteractionProiezione.svg)
Il **ProiezioniController** viene notificato [ `notify()` ] della scrittura di un nuovo evento, che subisce un ulteriore elaborazione per creare le diverse proiezioni del dato.

### Comportamento

#### Diagramma di stato Trasmissione

Il seguente diagramma mostra l'algoritmo di reinvio e backup dei dati in caso di erorri di trasmissione.

![](resources/StatoBuffer.svg)

### Piano del Lavoro

Il lavoro di progettazione e di sviluppo è stato suddiviso tra i vari membri del team come segue:

Package | Progetto | Sviluppo
-|-|-|
InterfaceUtente | Foglia, Forcellese, Pomponii | Forcellese
VistaCitta | Foglia, Forcellese  | Pomponii
Statistiche | Foglia, Forcellese | Forcellese, Pomponii
Proiezioni | Foglia, Forcellese, Pomponii | Foglia, Forcellese, Pomponii
Log | Pomponii | Pomponii
GestioneEvento | Foglia, Forcellese, Pomponii | Foglia, Forcellese, Pomponii
Trasmissione | Foglia, Forcellese, Pomponii | Pomponii
Dominio (*Records, Event, WeatherNow) | Foglia, Forcellese, Pomponii | Foglia, Forcellese, Pomponii

Dopo aver valutato attentamente la mole di lavoro richiesta, i tempi previsti sono i seguenti:
-   Progettazione: 3 settimane circa.
-   Sviluppo dei vari package: entro 1/2 settimane dalla fine della progettazione.
-   Test unitari e testing totale del sistema: entro una settimana dallo sviluppo di tutti i package.

### Piano di Collaudo 

Per garantire il corretto funzionamento del sistema sono necessari una diversi test unitari che permettono di verificare il corretto funzionamento delle diverse parti che lo compongono.

```java
@TestEvent
ObjectMapper mapper = new ObjectMapper();
JsonNode payload = null;
try {
    String json = "{\"boardId\": \"cafebafe-cafebabe-cafebabe\", \"boardOffset\": 3 , \"bar\": \"5\", \"zipcode\": \"\40125", \"emittedAt\": 857671257612 }";
    payload = mapper.readTree(json);
} catch (IOException e) {
        Assertions.fail();
}
try {
    Event sut = Event.fromJson(topic, type, version, payload);
} catch (MyException e) {
    Assertions.fail();
}

Assertions.assertEquals(sut.getBoardId(), "cafebafe-cafebabe-cafebabe");
Assertions.assertEquals(sut.getBoardOffset(), 3);
Assertions.assertEquals(sut.getPressure(), "5");
Assertions.assertEquals(sut.getZipCode(), "40125");
Assertions.assertEquals(sut.getEmittedAt(), "857671257612");
```

```js
@Test
describe('/GET mock boardstate', () => {
  it('it should get the BoardState', (done) => {
    server
      .get('/mock/boardState')
      .end((err, res) => {
        res.should.have.status(200);
        res.body.zipCode.should.equal(64100);
        res.body.wind.speed.should.equal(10);
        res.body.pollution.CO2.should.equal(10);
        res.body.pressure.pressure.should.equal(10);
        res.body.weatherState.state.should.equal("STATE_ENUM");
        done();
      });
  });
});

describe('/GET mock temperature', () => {
  it('it should get the temperatures of 64100 zipCode', (done) => {
    server
      .get('/mock/temperatures')
      .end((err, res) => {
        res.should.have.status(200);
        res.body.temperatures.should.be.a('array');
        res.body.zipCode.should.equal(64100);
        res.body.temperatures[0].value.should.equal(18);
        done();
      });
  });
});

describe('/GET generic error', () => {
  it('it should return response with status 500', (done) => {
    server
      .get('/mock/error')
      .end((err, res) => {
        res.should.have.status(500);
        done();
      });
  });
});

describe('/GET unauthorized error', () => {
  it('it should return response with status 500', (done) => {
    server
      .get('/mock/unauthorized')
      .end((err, res) => {
        res.should.have.status(401);
        done();
      });
  });
});
```