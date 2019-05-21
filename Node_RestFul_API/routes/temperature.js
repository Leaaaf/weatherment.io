const Pool = require('pg').Pool
const db = require('../config/database');
const pool = new Pool({
  user: db.username,
  host: db.host,
  database: db.database,
  password: db.password,
  port: db.port,
})

const getTemperatures = (request, response) => {
  pool.query('SELECT * FROM tempreatures ORDER BY timestamp ASC', (error, results) => {
    if (error) {
      throw error
    }
    response.status(200).json(results.rows)
  })
}

module.exports = {
  getTemperatures
}
