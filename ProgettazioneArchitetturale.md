## Progettazione architetturale

### Requisiti non funzionali

Dall'analisi dei requisiti non funzionali sono emersi i seguenti requisiti:
-   Efficienza scrittura
-   Efficienza stazione meteo	
-   Integrità
-   Rapidità di ricerca	
-   Semplicità ed immediatezza nell'utilizzo
-   Velocità di lettura
-   Velocità validazione

L'efficienza del sistema è estremamente importante nonchè il suo punto di forza; avendo una grande mole di dati da ricevere e processare, è necessario garantire delle performance elevate per poter permetterne la fluidità. <br>Essendo il sistema in grado di determinare quando un evento si è verificato, la quantità di dati trasmessa sulla rete può essere ridotta al minimo indispensabile. La comunicazione può anche avvenire in maniera più rapida a discapito della sicurezza e dell'integrità del dato, in quanto esso stesso non è sensibile e non necessita una forte protezione.<br>Per quanto riguarda la rapidità di ricerca, anch'esso è un requisito abbastanza importante per permettere una fruizione rapida ed efficace da parte dell'utente. Il sistema inoltre deve riuscire a validare gli eventi letti dai sensori senza fare da *collo di bottiglia* alle prestazioni generali. <br>Per garantire una buona performance a livello di persistenza, e per permettere l'utilizzo di alcune funzioni che riducono i tempi di sviluppo software (in quanto già predisposto), viene utilizzato un database *PostgreSQL* che permette l'invio asincrono di funzioni di notifica tra vari componenti del sistema. <br>La stazione meteo non è legata ad uno specifico hardware, così come i sensori non sono necessariamente ristretti a quelli descritti nell'analisi dei requisiti; data la flessibilità del sistema è possibile in versioni future aggiungere facilmente nuovi tipi di sensori e di dati letti.

### Scelta dell'architettura

Il sistema prevede un'archittetura client/server a tre livelli, distribuita su più server e su più layer di persistenza. Il pattern architetturale scelto è l'**MVC** (Model View Controller).