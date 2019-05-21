const getTemperatures = (request, response) => {
  response.status(200).json({
    "zipCode": 64100,
    "temperatures": [{
        "value": 18,
        "timestamp": 1345679803
      },
      {
        "value": 18,
        "timestamp": 1345679802
      },
      {
        "value": 20,
        "timestamp": 12345678901
      }
    ]
  });
}

const getBoardState = (request, response) => {
  response.status(200).json({
    "zipCode": 64100,
    "wind": {
      "speed": 10,
      "emitted_at": 1234567890
    },
    "pollution": {
      "CO2": 10,
      "emitted_at": 1234567890
    },
    "pressure": {
      "pressure": 10,
      "emitted_at": 1234567890
    },
    "umidity": {
      "value": 10,
      "emitted_at": 1234567890
    },
    "weatherState": {
      "state": "STATE_ENUM",
      "emitted_at": 1234567980
    }
  })
}


const error = (request, response) => {
  response.status(500).json({error : 'Something went wrong', status : 500});
}

const unauthorizedError = (request,response) => {
  response.status(401).json({error : 'Unauthorized', status : 401});
}

module.exports = {
  getTemperatures,
  getBoardState,
  error,
  unauthorizedError
}
