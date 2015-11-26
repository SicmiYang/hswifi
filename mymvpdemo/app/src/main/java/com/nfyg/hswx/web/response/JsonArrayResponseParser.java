package com.nfyg.hswx.web.response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Json Array response parser
 */
public abstract class JsonArrayResponseParser<T> {
    public ArrayList<T> parse(JSONArray jsonArray) throws JSONException {
        ArrayList<T> output = new ArrayList<T>();
        int arraySize = jsonArray.length();

        for(int i = 0; i < arraySize; i++){
            output.add(parseElement(jsonArray.getJSONObject(i)));
        }

        return output;
    }

    public abstract T parseElement(JSONObject jsonObject) throws JSONException;
}
