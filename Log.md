### Formato del file di log

Il file di log degli eventi deve contenere gli eventi che avvengono nelle componenti del sistema che si occupano di ricevere e salvare gli eventi, per le ragioni di sicurezza discusse nelle sezioni precedenti. Il file è formato in questo modo:

```json
log: {
    "timestamp": "<timestamp>",
    "level": "<level>",
    "ip": "<ip>",
    "message": "<message>",
    "tag": "<tag>"
}  
```

Il file di log relativo alle proiezioni visualizzate dall'utente, è così composto:

```json
log: {
    "timestamp": "<timestamp>",
    "status": "<status>",
    "method": "<method>",
    "level": "<level>",
    "endpoint": "<endpoint>"
}
```

#### Protezione dei file di log

I file di log possono essere crittografati per avere una maggiore sicurezza nel caso in cui un attaccante riesca ad accedervi, non potendo ottenere quindi informazioni. Questo influisce in modo abbastanza significativo sulle performance del sistema, quindi a seconda dell'esigenza può essere abilitata o meno. Vengono inoltre eseguiti backup periodici in un server remoto in maniera da garantirne la disponibilità anche dopo molto tempo.
