package com.example.demo.Validator;

import com.fasterxml.jackson.databind.jsonschema.JsonSchema;

import java.util.Map;
import java.util.UUID;

public class PayloadValidator {
    static PayloadValidator instance;
    Map<UUID, Map<String, JsonSchema[]>> validatorMap;


    /* ESEMPIO JSON PER RIEMPIRE LA MAPPA validatorMap
    "topics : {
       "topic_id" : <UUID>,
       "types" : {
            "type_id" : <String>,
            "schemas" : <[JsonSchema]>
       }
    }
    **
    */

    private PayloadValidator() {
    }

    public JsonSchema getSchema(UUID topic, String type, int version) {
        try {
            JsonSchema result = validatorMap.get(topic).get(type)[version - 1];
            return result;
        } catch (NullPointerException e) {
            return null;
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    public static PayloadValidator getInstance() {
        if (instance == null)
            return new PayloadValidator();
        else return instance;
    }
}
