const express = require('express')
const bodyParser = require('body-parser')
const app = express()
const port = 3000

//////////
// ROUTING MODULES IMPORT 
//////////
const temperatures = require("./routes/temperature")
const mock = require("./routes/mock");

app.use(bodyParser.json())
app.use(
  bodyParser.urlencoded({
    extended: true,
  })
)



//////////
// START MOCK ROUTING
//////////

app.get('/mock/temperatures', mock.getTemperatures);
app.get('/mock/boardState', mock.getBoardState);
app.get('/mock/error', mock.error);
app.get('/mock/unauthorized', mock.unauthorizedError)

//////////
// END MOCK ROUTING
//////////



//////////
// START TEMPERATURE ROUTING
//////////

app.get('/temperature/{zipcode}/{date}', temperatures.getTemperatures);

//////////
// END TEMPERATURE ROUTING
//////////



app.listen(port, () => {
  console.log(`WatherMent.Io Application running on ${port}.`)
})
