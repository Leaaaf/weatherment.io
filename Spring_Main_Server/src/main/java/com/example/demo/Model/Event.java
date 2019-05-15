package com.example.demo.Model;

/////////////////////////////////////////////////////////////////////////// EXAMPLE OF EVENT CURL
// curl -X POST \
//  http://localhost:8080/new \
//  -H 'cache-control: no-cache' \
//  -H 'content-type: application/json' \
//  -d '{
//    # id
//    "type": "PressureChanged",
//    "event_schema_revision": 1,
//    "payload": {
//        "board_id": "cafebafe-cafebabe-cafebabe",
//        "board_offset": 3
//        "bar": 5,
//        "emitted_at": 857671257612
//    }
//    "topic_id": "UUID",
//    # topic_height
//    # received_at
//}'
///////////////////////////////////////////////////////////////////////////

import com.example.demo.MyException;
import com.example.demo.Validator.PayloadValidator;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.UUID;

public final class Event {
    private JsonNode data;

    private Event(JsonNode data) {
        this.data = data;
    }

    public static Event fromJson(UUID topic, String type, int version, JsonNode data) throws MyException {
        if (PayloadValidator.getInstance().validateSchema(topic, type, version) != null)
            return new Event(data);
        else throw new MyException();
    }


}
