- implementa una lista 
- Fa vedere del codice e ti chiede quale sicuramente NON implementa il Singleton--> quello con il costruttore Pubblico
- Quale operazione NON Ë supportata dai casi d'uso? Aggregazione
- Definizione di requisito : definizione di funzionale e non funzionale 
- Quale affermazione è falsa sugli Assembly? Nella cache non vengono salvati in codice nativo
- public event Qualcosa a. Cos'è 'Qualcosa' ? Un delegato
- public event Qualcosa a. Quale operazione non è ammessa ? a = new Qualcosa(..); 
- Quale operazione non si può sviluppare ?   +(20+30) + 100
9-10. Dal diagramma al codice e dal codice al diagramma 
- Dai codice al diagramma di sequenza appropriata 
- Schema dell'interfaccia Segregation ==> Identificarne il Pattern
- Quale pattern non è strutturale ? Visitor 
- Quale Pattern utilizza necessariamente la delega ? ADAPTER
- Quale delle seguenti affermazioni non è vera sui Pattern ? I pattern strutturali si occupano della creazioni degli oggetti. 
- Cosa NON fa il GC ? Distruzione Deterministica 
- Quale Pattern si utilizza per oggetti che cambiano runtime il loro comportamento ? STATE
- ``` 
    Public MyClass {
        internal myClass2 { 
            ... }
        ... }
  ```
    chi vede myClass2 ? Assembly contenitore. 
- Vedi foto , MyClass eredita due metodi close e ne aggiunge un terzo chiamato close1(), il codice che non compila è quello in cui si fa un' istanza di MyClass e le si chiede di invocare close (quale delle due invocherebbe ? )
- In che cosa consiste la manutenzione adattiva ?  AGGIUNGERE FUNZIONALITA' 
- Rigidità del codice: quella scritta sulle slides 
- Individuare l'informazione errata sulle interfacce: possono gestire l'istanziazione delle classi che la implementano
- Individuare un affermazione vera su attività-azioni : un'attività è una computazione non atomica legata ad uno stato
-  Individuare l'affermazione falsa su pre/post condizioni + invarianti : un'invariante deve essere verificato durante tutta l'esecuzione di un'operazione  
- Quale di queste NON Ë una caratteristica esterna? : Modularità
- ```
  public ? point { x y }
    public ? rettangolo {point p1 point p2 }   ===> point : struct ;
    ```    
     rettangolo è una classe invece
- Domanda tecnica su chi implementa cosa in un pattern visitors
- Dato un UML dire fra le opzioni quella che può funzionare
- Dire quale fra un numero di pattern non è di un certo tipo
- Quali sono i pattern comportamentali, quali costruttivi e quali strutturali (trucchetto: A-F sono strutturali / G-Z sono comportamentali / Factory e Singleton sono Creazionali)
- Dato del codice capire quale pattern è
- Domanda su .NET Assembly come funzionano i metadati
- Domanda su CLS di .NET
- Date le definizioni di alcune classi determinare quale è l'UML
- Dato un UML dire quale codice corrisponde
- Dire di che tipo deve essere il type di un event
- Trovare quale è fra diversi codici il flyweight
- Domanda su classe astratta
- Dire quale dei seguenti pattern usa necessariamente la delega
- Quale pattern NON usa la composizione e delega
- Domanda sui rischi tecnologici
- Definizione di design pattern
- Scopo del modello evolutivo
- Domanda su tipi valore e tipi di riferimento
- Domanda su visibilità di una classe soggetta a modificatori (internal e protected)
- Domanda su le possibili operazioni che si possono fare su un evento
- Cosa si intende per fragilità del codice
- Pre e post condizioni nel sub classing
- Domanda sulla qualità del software
- Domanda sugli attori
- Domanda sul riconoscimento di codice in base a un diagramma di sequenza
- Domanda su definizione di un diagramma di sequenza
- Dato un testo che descrive un poligono determinare l'umlgiusto (aggregazione)
- A partire dal diagramma UML, quale principio di design è stato applicato
- Quale dei seguenti pattern è strutturale
- Diagramma UML di un albero binario
- EventHandler è un delegato/struct/enum/...
- Pattern flyweight (5 asserti di cui 1 falso)
- Cos'è un requisito
- Cosa si intende per rigidità del software
- Ereditarietà multipla di interfaccia, quale pezzo di codice non compila
- Garbage Collector .NET (5 asserti di cui 1 falso-finalize, reference counting, ...)  
- Garbage collector come funziona, cosa invoca e cosa no
- Relazione fra distruttore e finalize con il GC
- Ci sono due classi generiche (rectangle e point), dopo l'inizializzazione la situazione in memoria deve essere "..." (1 in stack, 1 in heap), dire di quali tipi devono essere gli oggetti (cosa alloco nello stack e cosa nell’heap)
- Se il comportamento deve essere dipendente dallo stato, quale pattern usare?
- MVC (5 asserti, di cui 1 falso)
- Eredità tra attori (es: attore che eredita da due attori -> trovare asserto falso)
- In un diagramma dei casi d'uso, cosa non è possibile fare in un diagramma UML? (generalizzazione, aggregazione, ...)
- Dati due schemi uml definire quale principio era stato utilizzato (dip)
- Precondizioni, Postcondizioni definizione precisa
- Invariante di classe come funziona e definizione precisa
- Diagrammi di stato
- Diagrammi dei messaggi da collegare ad UML
