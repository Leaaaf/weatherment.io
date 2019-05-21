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