### Formato del file di log

Il file di log deve contenere tutte le azioni che avvengono nelle componenti del sistema che si occupano di ricevere e salvare gli eventi, per le ragioni di sicurezza discusse nelle sezioni precedenti. Il file è formato in questo modo:

```json
log: {
    timestamp: <timestamp>,
    ip: <ip>,
    action: <action>
}  
<timestamp> <ip> <action>
<timestamp> <ip> <action>

```

#### Protezione dei file di log

I file di log possono essere crittografati per avere una maggiore sicurezza nel caso in cui un attaccante riesca ad accedervi, non potendo ottenere quindi informazioni. Questo influisce in modo abbastanza significativo sulle performance del sistema, quindi a seconda dell'esigenza può essere abilitata o meno. Vengono inoltre eseguiti backup periodici in un server remoto in maniera da garantirne la disponibilità anche dopo molto tempo.