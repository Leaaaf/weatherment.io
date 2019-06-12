package com.weatherment.io.middlleserver.Parser;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.weatherment.io.middlleserver.Projections.Temperature;
import javassist.NotFoundException;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;

public class Parser {

    private static Parser instance;
    private Gson gson;

    public static Parser getInstance() {
        if (null == instance) instance = new Parser();
        return instance;
    }

    private Parser() {
        this.gson = new Gson();
    }

    public Object parse(String parameter) throws JSONException, NotFoundException, NullPointerException {
        JSONObject jsonObject = new JSONObject(parameter);
        JSONObject payloadObject = jsonObject.getJSONObject("payload");
        Object obj = gson.fromJson(String.valueOf(payloadObject), formatClass(jsonObject.getString("type")));
        if(null==obj) throw new NullPointerException();
        return obj;
    }

    private Type formatClass(String type) throws NotFoundException {
        switch(type) {
            case "TemperatureChanged":
                return Temperature.class;
        }

        throw new NotFoundException("Not found class");
    }
}
