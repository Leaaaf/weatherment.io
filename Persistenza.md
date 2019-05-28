## Persistenza

### Diagramma ER - Event Driven persistence
Il seguente diagramma ER rappresenta le entità e le relazioni appartenenti alla persistenza degli eventi.
![](resources/ER_EventStore.svg)

**Entità:**
- Topic: ogni topic è identificato da un ID (primary key) e presenta un solo attributo, il nome. Tale coppia deve essere unica all'interno del sistema.
- Type: ogni type è identificato da un ID (primary key) e presenta un solo attributo, il nome. La coppia name e chiave esterna che riferisce l'ID della tabella topic devono essere univoci all'interno del sistema.
- Revision: ogni revision è identificata da un ID (primary key) e presenta l'attributo version, il quale identifica il numero di versione e, l'attributo schema di tipo JSON. La coppia version e chiave esterna che riferisce l'ID della tabella Type devono essere univoci all'interno del sistema.
- TopicHeight: tiene traccia del numero di eventi relativi a quel topic, la primary key è rappresentata dalla chiave esterna che riferisce l'ID della tabella Topic.
- TypeRevision: tiene traccia dell'ultima version per ogni type, la primary key è data dalla chiave esterna che riferisce l'ID della tabella Type.
- Event: rappresenta l'evento emesso dalla stazione, la primary key viene identificata dalla coppia topicHeight e dalla Foreign Key del topic relativo all'evento scritto. Inoltre abbiamo l'attributo receivedAt il quale indica in formato timestamp il momento in cui è stata scritta la riga sul database e l'attributo Payload.

Gli ID (primary key e non) sono degli interi AUTO-INCREMENT.

**Relazioni:**
- Topic (1..1) - (1..N) Type: ogni type riferisce un solo Topic; un Topic può riferire da 1 ad N Type.
- Type (1..1) - (1..N) Revision: ogni Revision riferisce un solo Type; un Type può riferire da 1 ad N Revision.
- TopicHeight (1..1) - (1..N) Topic: ogni Topic riferisce un solo TopicHeight; un TopicHeight può riferire da 1 ad N Topic.
- TypeRevision (1..1) - (1..N) Type: ogni Type riferisce un solo TypeRevision; un TypeRevision può riferire da 1 ad N Type.
- Event (0..N) - (1..1) Topic: ogni Event riferisce un solo Topic; un Topic può riferire da 0 ad N Event.
- Event (0..N) - (1..1) Type: ogni Event riferisce un solo Type; un Type può riferire da 0 ad N Event.
- Event (0..N) - (1..1) Revision: ogni Event riferisce un solo Revision; un Revision può riferire da 0 ad N Event.

### Diagramma ER - Proiezioni
Il seguente diagramma ER rappresenta le entità e le relazioni appartenenti alla persistenza delle proiezioni.

![](resources/ER_Proiezioni.svg)

**Entità**
- WeatherNow: zipCode rappresenta la primary key della tabella in questione, la quale espone all'utente i relativi attributi in tempo reale.
- WindRecords: raccoglie tutti gli eventi inerenti alla velocità e direzione del vento, i cui valori sono espressi dall'attributo speed per quanto riguarda la velocità e dalla chiave esterna per quanto riguarda la direzione. L'attributo emittedAt è dato dalla scheda che ha registrato il cambiamento, in formato timestamp. La primary key è data dallo zipCode.
- WeatherState: raccoglie tutti gli eventi inerenti allo stato del meteo, il cui valore viene espresso dalla chiave esterna. L'attributo emittedAt è dato dalla scheda che ha registrato il cambiamento, in formato timestamp. La primary key è data dallo zipCode.
- PollutionRecords: raccoglie tutti gli eventi inerenti all'inquinamento atmosferico, il cui valore viene espresso dall'attributo CO2. L'attributo emittedAt è dato dalla scheda che ha registrato il cambiamento, in formato timestamp. La primary key è data dallo zipCode.
- UmidityRecords: raccoglie tutti gli eventi inerenti all'umidità presente nell'aria, il cui valore viene espresso dall'attributo umidity. L'attributo emittedAt è dato dalla scheda che ha registrato il cambiamento, in formato timestamp. La primary key è data dallo zipCode.
- TemperatureRecords: raccoglie tutti gli eventi inerenti alla temperatura ambientale, il cui valore viene espresso dall'attributo temperature. L'attributo emittedAt è dato dalla scheda che ha registrato il cambiamento, in formato timestamp. La primary key è data dallo zipCode.
- WeatherState: è una tabella di dettaglio dove, attraverso l'attributo state, vengono espressi tutti i possibili stati.
- WindDirection: è una tabella di dettaglio dove, attraverso l'attributo direction, vengono espresse tutte le possibili direzioni del vento.

Gli zipCode (primary key e non) sono delle stringhe composte da 5 caratteri.

**Relazioni**
- WeatherNow (0..N) - (1..1) WeatherState: ogni WeatherNow riferisce un solo WeatherState; un WeatherState può riferire da 0 ad N WeatherNow.
- WeatherNow (0..N) - (1..1) WindDirection: ogni WeatherNow riferisce un solo WindDirection; un WindDirection può riferire da 0 ad N WeatherNow.
- WindRecords (0..N) - (1..1) WindDirection: ogni WindRecords riferisce un solo WindDirection; un WindDirection può riferire da 0 ad N WindDirection.
- WeatherStateRecords (0..N) - (1..1) WeatherState: ogni WeatherStateRecords riferisce un solo WeatherState; un WeatherState può riferire un solo WeatherStateRecords.