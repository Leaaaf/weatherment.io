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

**Gestione Eventi: Tabella Informazioni/ Flusso**

Informazione | Tipo | Livello Protezione/ Privacy | Input/ Oputput | Vincoli
|-|-|-|-|-
Dato sensore Hardware | Semplice | Media | Input | |
Evento | Composto | Alta | Output | |
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
**Evento** composto da: | Composto | Alta | Input | |
Topic | Semplice | Media | Input | Stringa |
Type | Semplice | Media | Input | Stringa |Version | Semplice | Media | Input | Intero maggiore di zero
Payload | Composto | Media | Input | |

**Storico: Tabella Informazioni/ Flusso** 

Informazione | Tipo | Livello Protezione/ Privacy | Input/ Oputput | Vincoli
|-|-|-|-|-
**Evento** composto da: | Composto | Alta | Input | |
Topic | Semplice | Media | Input | Stringa |
Type | Semplice | Media | Input | Stringa |Version | Semplice | Media | Input | Intero maggiore di zero
Payload | Composto | Media | Input | |

### Analisi dei vincoli

**Tabella dei vincoli**

Requisito | Categorie | Impatto | Funzionalità
|-|-|-|-
Integrità | Integrità | Rallentamento nella fase di scrittura e di validazione del dato; correttezza del dato assicurata dalla barriera | Gestione Evento, Statistiche, Storico
Velocità validazione | Performance | Maggiore efficienza nella validazione del dato ed un tempo di risposta ridotto | Gestione Evento
Efficienza scrittura | Performance | Maggiore efficienza nella fase di scrittura persistente del dato ed un tempo di risposta ridotto | Gestione Evento
Efficienza stazione meteo | Sistema esterno | Impatto sul sistema nullo; possibilità di elaborare velocemente i dati letti dai sensori |
Semplicità ed immediatezza nell'utilizzo |  Usabilità | Migliore usabilità da parte dell'utente finale; interfacce grafiche intuitive | Storico, Statistiche
Velocità di lettura | Tempo di risposta | Maggiore efficienza nella fase di lettura del dato ed un tempo di risposta ridotto | Storico, Statistiche
Rapidità di ricerca | Tempo di risposta | Maggiore efficienza nella fase di fetch dei dati all'interno del database | Storico, Statistiche


**Tabella sistemi esterni**

Sistema | Descrizione | Protocollo di interazione | Livello di sicurezza
|-|-|-|-
Stazione meteo | Insieme di sensori e componenti hardware che formano una stazione meteo | La stazione invia al server gli eventi in un formato prestabolito. Il server, dopo la validazione dei dati ricevuti, salva gli eventi sul database | Alto

**Analisi dei ruoli e delle responsabilità**

Ruolo | Responsabilità | Maschere | Riservatezza | Numerosità
-|-|-|-|-|
Utente | Consulta i dati delle stazioni meteo utilizzando i filtri offerti dall'applicazione | | | Il numero di utenti non è limitato Dipende dalla scalabilità del sistema

**Utente : tabella ruolo/informazioni**

Informazione | Tipo di accesso
-|-|
Storico | Lettura
