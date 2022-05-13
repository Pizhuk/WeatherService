package dto;

import org.json.JSONArray;
import org.json.JSONObject;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static constants.WeatherConstants.*;

public class HourlyWeatherDTO {
    private JSONObject object;

    /**
     * InputJSON is string we get from OpenWeatherApi. In constructor we transform it to JSONObject*/
    public HourlyWeatherDTO(String inputJSON) {
        this.object = new JSONObject(inputJSON);
    }

    /**
     * Takes a HashMap from private method below and returns the info about weather
     * @return the information about hourly weather in JSON-format
     */
    public String getHourlyWeatherJson(){
        Map map = getMapListOfWeatherComponents();
        String infoJson = String.format("{\"0\":{\"time\":\"%s\", \"temp\":\"%s\"}, \"1\":{\"time\":\"%s\", \"temp\":\"%s\"}, " +
                "\"2\":{\"time\":\"%s\", \"temp\":\"%s\"}, \"3\":{\"time\":\"%s\", \"temp\":\"%s\"}, \"4\":{\"time\":\"%s\", \"temp\":\"%s\"}, " +
                "\"5\":{\"time\":\"%s\", \"temp\":\"%s\"}}", map.get(FIRST_TIME_STRING), map.get(FIRST_TEMP_STRING), map.get(SECOND_TIME_STRING),
                map.get(SECOND_TEMP_STRING), map.get(THIRD_TIME_STRING), map.get(THIRD_TEMP_STRING), map.get(FOURTH_TIME_STRING), map.get(FOURTH_TEMP_STRING),
                map.get(FIFTH_TIME_STRING), map.get(FIFTH_TEMP_STRING), map.get(SIXTH_TIME_STRING), map.get(SIXTH_TEMP_STRING));
        return infoJson;
    }

    /**Takes JSONObject from constructor and use it to get data about weather*/
    private Map getMapListOfWeatherComponents(){
        JSONArray hourly = object.getJSONArray(HOURLY_STRING);
        JSONObject first = hourly.getJSONObject(0);
        JSONObject second = hourly.getJSONObject(3);
        JSONObject third = hourly.getJSONObject(6);
        JSONObject fourth = hourly.getJSONObject(9);
        JSONObject fifth = hourly.getJSONObject(12);
        JSONObject sixth = hourly.getJSONObject(15);

        HashMap<String, Object> map = new HashMap<>(12);

        long first_date = first.getLong(DT_STRING);
        Date expiry1 = new Date(first_date * 1000);
        map.put(FIRST_TIME_STRING, String.format(TIME_STRING, expiry1.getHours(), expiry1.getMinutes()));
        map.put(FIRST_TEMP_STRING, first.getInt(TEMP_STRING));

        long second_date = second.getLong(DT_STRING);
        Date expiry2 = new Date(second_date * 1000);
        map.put(SECOND_TIME_STRING, String.format(TIME_STRING, expiry2.getHours(), expiry2.getMinutes()));
        map.put(SECOND_TEMP_STRING, second.getInt(TEMP_STRING));

        long third_date = third.getLong(DT_STRING);
        Date expiry3 = new Date(third_date * 1000);
        map.put(THIRD_TIME_STRING, String.format(TIME_STRING, expiry3.getHours(), expiry3.getMinutes()));
        map.put(THIRD_TEMP_STRING, third.getInt(TEMP_STRING));

        long fourth_date = fourth.getLong(DT_STRING);
        Date expiry4 = new Date(fourth_date * 1000);
        map.put(FOURTH_TIME_STRING, String.format(TIME_STRING, expiry4.getHours(), expiry4.getMinutes()));
        map.put(FOURTH_TEMP_STRING, fourth.getInt(TEMP_STRING));

        long fifth_date = fifth.getLong(DT_STRING);
        Date expiry5 = new Date(fifth_date * 1000);
        map.put(FIFTH_TIME_STRING, String.format(TIME_STRING, expiry5.getHours(), expiry5.getMinutes()));
        map.put(FIFTH_TEMP_STRING, fifth.getInt(TEMP_STRING));

        long sixth_date = sixth.getLong(DT_STRING);
        Date expiry6 = new Date(sixth_date * 1000);
        map.put(SIXTH_TIME_STRING, String.format(TIME_STRING, expiry6.getHours(), expiry6.getMinutes()));
        map.put(SIXTH_TEMP_STRING, sixth.getInt(TEMP_STRING));
        return map;
    }
}
