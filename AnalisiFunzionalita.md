## Analisi del problema
### Analisi delle funzionalità

**Tabella Funzionalità** 

**Funzionalità** | **Tipo** | **Grado Complessità**
|-|-|-
Gestione Eventi | Gestione e memorizzazione dei dati, interazione con l'esterno | Complessa
Gestione Stazioni | Gestione e memorizzazione dati, interazione con l'esterno | Complessa
Storico | Gestione e memorizzazione dati | Semplice
Scrittura Log | Memorizzazione dati | Semplice

**Gestione Eventi: Tabella Informazioni/ Flusso**

Informazione | Tipo | Livello Protezione/ Privacy | Input/ Oputput | Vincoli
|-|-|-|-|-
Dato sensore Hardware | Semplice | Media | Input | |
Evento | Composto | Alta | Output | |
Data | Semplice | Media | Input | |
Ora | Semplice | Media | Input | |
Stazione D'origine | Composto | Media | Input | |

**Gestione Stazioni: Tabella Informazioni/ Flusso** 

Informazione | Tipo | Livello Protezione/ Privacy | Input/ Oputput | Vincoli
|-|-|-|-|-
UUID | Semplice | Bassa | Input | Deve essere valido |
CAP | Semplcie | Bassa | Input | Non più di 5 caratteri
IP | Semplice | Bassa | Input | Deve essere un IP valido
Porta | Semplice | Bassa | Input | Intero compreso tra 1024 e 65535

**Storico: Tabella Informazioni/ Flusso** 

Informazione | Tipo | Livello Protezione/ Privacy | Input/ Oputput | Vincoli
|-|-|-|-|-
**Evento** composto da: | Composto | Alta | Input | |
Topic | Semplice | Media | Input | Stringa |
Type | Semplice | Media | Input | Stringa |Version | Semplice | Media | Input | Intero maggiore di zero
Payload | Composto | Media | Input | |

**Scrittura Log: Tabella Informazioni/ Flusso** 

Informazione | Tipo | Livello Protezione/ Privacy | Input/ Oputput | Vincoli
|-|-|-|-|-
IP | Semplice | Alta | Input | |
Azione | Semplice | Alta | Input | |
Ora | Semplice | Media | Input | |
Data | Semplice | Media | Input | |

**Sistemi esterni**

Sistema | Descrizione | Protocollo di interazione | Livello di sicurezza
|-|-|-|-
Stazione meteo | Insieme di sensori e componenti hardware che formano una stazione meteo | La stazione invia al server gli eventi in un formato prestabolito. Il server, dopo la validazione dei dati ricevuti, salva gli eventi sul database. | Alto

**Analisi dei ruoli e delle responsabilità**

Ruolo | Responsabilità | Maschere | Riservatezza | Numerosità
-|-|-|-|-|
Utente | Consulta i dati delle stazioni meteo utilizzando i filtri offerti dall'applicazione. | | | Il numero di utenti non è limitato. Dipende dalla scalabilità del sistema

**Utente : tabella ruolo/informazioni**

Informazione | Tipo di accesso
-|-|
Storico | Lettura
