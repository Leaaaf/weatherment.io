let chai = require('chai');
let supertest = require("supertest");
let chaiHttp = require('chai-http');
let should = chai.should();
var server = supertest.agent("http://localhost:3000");
chai.use(chaiHttp);

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
