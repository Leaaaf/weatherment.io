const express = require('express')
const bodyParser = require('body-parser')
const app = express()
const port = 3000

const temperatures = require("./routes/temperature")

app.use(bodyParser.json())
app.use(
  bodyParser.urlencoded({
    extended: true,
  })
)

app.get('/', (request, response) => {
  response.json({ info: 'WeatherMent.IO get TEST' })
})

app.listen(port, () => {
  console.log(`WatherMent.Io Application running on ${port}.`)
})

//////////
// START TEMPERATURE ROUTING
//////////

app.get('/temperature/{zipcode}/{date}',temperatures.getTemperatures);

//////////
// END TEMPERATURE ROUTING
//////////