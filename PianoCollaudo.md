### Piano di Collaudo 

Per garantire il corretto funzionamento del sistema sono necessari una diversi test unitari che permettono di verificare il corretto funzionamento delle diverse parti che lo compongono.

```java
@TestEvent
ObjectMapper mapper = new ObjectMapper();
JsonNode payload = null;
try {
    String json = "{\"boardId\": \"cafebafe-cafebabe-cafebabe\", \"boardOffset\": 3 , \"bar\": \"5\", \"zipcode\": \"\40125", \"emittedAt\": 857671257612 }";
    payload = mapper.readTree(json);
} catch (IOException e) {
        Assertions.fail();
}
try {
    Event sut = Event.fromJson(topic, type, version, payload);
} catch (MyException e) {
    Assertions.fail();
}

Assertions.assertEquals(sut.getBoardId(), "cafebafe-cafebabe-cafebabe");
Assertions.assertEquals(sut.getBoardOffset(), 3);
Assertions.assertEquals(sut.getPressure(), "5");
Assertions.assertEquals(sut.getZipCode(), "40125");
Assertions.assertEquals(sut.getEmittedAt(), "857671257612");
```

```js
@Test
describe('/GET mock boardstate', () => {
  it('it should get the BoardState', (done) => {
    server
      .get('/mock/boardState')
      .end((err, res) => {
        res.should.have.status(200);
        res.body.zipCode.should.equal(64100);
        res.body.wind.speed.should.equal(10);
        res.body.pollution.CO2.should.equal(10);
        res.body.pressure.pressure.should.equal(10);
        res.body.weatherState.state.should.equal("STATE_ENUM");
        done();
      });
  });
});

describe('/GET mock temperature', () => {
  it('it should get the temperatures of 64100 zipCode', (done) => {
    server
      .get('/mock/temperatures')
      .end((err, res) => {
        res.should.have.status(200);
        res.body.temperatures.should.be.a('array');
        res.body.zipCode.should.equal(64100);
        res.body.temperatures[0].value.should.equal(18);
        done();
      });
  });
});

describe('/GET generic error', () => {
  it('it should return response with status 500', (done) => {
    server
      .get('/mock/error')
      .end((err, res) => {
        res.should.have.status(500);
        done();
      });
  });
});

describe('/GET unauthorized error', () => {
  it('it should return response with status 500', (done) => {
    server
      .get('/mock/unauthorized')
      .end((err, res) => {
        res.should.have.status(401);
        done();
      });
  });
});
```