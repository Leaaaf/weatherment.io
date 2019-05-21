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