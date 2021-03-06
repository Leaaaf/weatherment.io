package com.example.demo;

import com.example.demo.Model.Event;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.UUID;

public class TestMainServer {

    ///////////////////////////////////////////////////////////////////////////
    // MOCK TEST TO COMPLETE
    // INSERT JSON VALIDATION WITH JSON SCHEMA
    // SET CONTROL FLOW FOR topic/type/version
    ///////////////////////////////////////////////////////////////////////////

    public static void main(String[] args) {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode payload = null;
        try {
            String json = "{\"board_id\": \"cafebafe-cafebabe-cafebabe\", \"board_offset\": 3 , \"bar\": \"5\", \"emitted_at\": 857671257612 }";
            payload = mapper.readTree(json);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("TEST ERROR");
            System.exit(0);
        }

        UUID topic = null;
        String type = null;
        int version = 0;


        try {
            Event event = Event.fromJson(topic, type, version, payload);
        } catch (MyException e) {
            e.printStackTrace();
            System.out.println("JSON PAYLOAD VALIDATOR FAILURE");
        }

        //topic = ;
        // type = ;
        //version = ;

        try {
            Event event = Event.fromJson(topic, type, version, payload);
        } catch (MyException e) {
            e.printStackTrace();
            System.out.println("JSON PAYLOAD VALIDATOR FAILURE");
        }

        System.out.println("JSON PAYLOAD VALIDATION OK");
        System.exit(1);
    }
}
