package com.example.demo.Controller;

import com.example.demo.Model.Event;
import com.example.demo.MyException;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class EventsController {

    public EventsController() {
        super();
    }

    @RequestMapping(value = "events/{topic}/{type}/{version}", method = RequestMethod.POST, produces = "application/json")
    String event(@RequestBody @Validated JsonNode payload, BindingResult result,
                  @PathVariable UUID topic, @PathVariable String type, @PathVariable int version) {
        Event event;
        try {
            event = Event.fromJson(topic, type, version, payload);
        } catch (MyException e) {
            return "{success:false}";
        }

        //event.save();
        return "{success : true}";
    }
}