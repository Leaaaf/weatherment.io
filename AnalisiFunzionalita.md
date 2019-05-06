## Analisi del problema
### Analisi delle funzionalità

**Tabella Funzionalità** 
**Funzionalità** | **Tipo** | **Grado Complessità**
-|-|-
Gestione Eventi | Gestione e memorizzazione dei dati, interazione con l'esterno | Complessa
Gestione Stazioni | Gestione e memorizzazione dati, interazione con l'esterno | Complessa
Storico | Gestione e memorizzazione dati | Semplice
Scrittura Log | Memorizzazione dati | Semplice

**Gestione Eventi: Tabella Informazioni/ Flusso**
Informazione | Tipo | Livello Protezione/ Privacy | Input/ Oputput | Vincoli
-|-|-|-|-|-
Dato sensore Hardware | Semplice | Media | Input | |
Evento | Composto | Alta | Output | |
Data | Semplice | Media | Input | |
Ora | Semplice | Media | Input | |
Stazione D'origine | Composto | Media | Input | |

**Gestione Stazioni: Tabella Informazioni/ Flusso** 
Informazione | Tipo | Livello Protezione/ Privacy | Input/ Oputput | Vincoli
-|-|-|-|-|-
UUID | Semplice | Bassa | Input | Deve essere valido |
CAP | Semplcie | Bassa | Input | Non più di 5 caratteri
IP | Semplice | Bassa | Input | Deve essere un IP valido
Porta | Semplice | Bassa | Input | Intero compreso tra 1024 e 65535

**Storico: Tabella Informazioni/ Flusso** 
Informazione | Tipo | Livello Protezione/ Privacy | Input/ Oputput | Vincoli
-|-|-|-|-|-
**Evento** composto da: | Composto | Alta | Input | |
Topic | Semplice | Media | Input | Stringa |
Type | Semplice | Media | Input | Stringa |Version | Semplice | Media | Input | Intero maggiore di zero
Payload | Composto | Media | Input | |

**Scrittura Log: Tabella Informazioni/ Flusso** 
Informazione | Tipo | Livello Protezione/ Privacy | Input/ Oputput | Vincoli
-|-|-|-|-|-
IP | Semplice | Alta | Input | |
Azione | Semplice | Alta | Input | |
Ora | Semplice | Media | Input | |
Data | Semplice | Media | Input | |