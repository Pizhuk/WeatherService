package dto;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static constants.WeatherConstants.*;
import static constants.WeatherConstants.THIRD_MAX_TEMP;

public class WeekWeatherDTO {
    private JSONObject object;

    /**
     * InputJSON is string we get from OpenWeatherApi. In constructor we transform it to JSONObject*/
    public WeekWeatherDTO(String inputJSON) {
        this.object = new JSONObject(inputJSON);
    }

    /**
     * Takes a HashMap from private method below and returns the info about weather
     * @return the information about hourly weather in JSON-format
     */
    public String getWeekWeatherJson(){
        Map map = getMapListOfWeatherComponents();
        String infoJson = String.format("{\"0\":{\"dt\":\"%s\", \"min\":\"%s\", \"max\":\"%s\", \"desc\":\"%s\"}," +
                        " \"1\":{\"dt\":\"%s\", \"min\":\"%s\", \"max\":\"%s\", \"desc\":\"%s\"}," +
                        " \"2\":{\"dt\":\"%s\", \"min\":\"%s\", \"max\":\"%s\", \"desc\":\"%s\"}," +
                        " \"3\":{\"dt\":\"%s\", \"min\":\"%s\", \"max\":\"%s\", \"desc\":\"%s\"}, " +
                        "\"4\":{\"dt\":\"%s\", \"min\":\"%s\", \"max\":\"%s\", \"desc\":\"%s\"}, " +
                        "\"5\":{\"dt\":\"%s\", \"min\":\"%s\", \"max\":\"%s\", \"desc\":\"%s\"}, " +
                        "\"6\":{\"dt\":\"%s\", \"min\":\"%s\", \"max\":\"%s\", \"desc\":\"%s\"}}",
                map.get(FIRST_TIME_STRING), map.get(FIRST_MIN_TEMP), map.get(FIRST_MAX_TEMP), map.get(DESCRIPTION1_STRING),
                map.get(SECOND_TIME_STRING), map.get(SECOND_MIN_TEMP), map.get(SECOND_MAX_TEMP), map.get(DESCRIPTION2_STRING),
                map.get(THIRD_TIME_STRING), map.get(THIRD_MIN_TEMP), map.get(THIRD_MAX_TEMP), map.get(DESCRIPTION3_STRING),
                map.get(FOURTH_TIME_STRING), map.get(FOURTH_MIN_TEMP), map.get(FOURTH_MAX_TEMP), map.get(DESCRIPTION4_STRING),
                map.get(FIFTH_TIME_STRING), map.get(FIFTH_MIN_TEMP), map.get(FIFTH_MAX_TEMP), map.get(DESCRIPTION5_STRING),
                map.get(SIXTH_TIME_STRING), map.get(SIXTH_MIN_TEMP), map.get(SIXTH_MAX_TEMP), map.get(DESCRIPTION6_STRING),
                map.get(SEVENTH_TIME_STRING), map.get(SEVENTH_MIN_TEMP), map.get(SEVENTH_MAX_TEMP), map.get(DESCRIPTION7_STRING));
        return infoJson;
    }

    /**Takes JSONObject from constructor and use it to get data about weather*/
    private Map getMapListOfWeatherComponents(){
        JSONArray daily = object.getJSONArray(DAILY_STRING);
        JSONObject first = daily.getJSONObject(1);
        JSONObject second = daily.getJSONObject(2);
        JSONObject third = daily.getJSONObject(3);
        JSONObject fourth = daily.getJSONObject(4);
        JSONObject fifth = daily.getJSONObject(5);
        JSONObject sixth = daily.getJSONObject(6);
        JSONObject seventh = daily.getJSONObject(7);

        HashMap<String, Object> map = new HashMap<>(28);

        long first_date = first.getLong(DT_STRING);
        Date expiry1 = new Date(first_date * 1000);
        System.out.println(expiry1);
        map.put(FIRST_TIME_STRING, expiry1.toString());
        map.put(FIRST_MIN_TEMP, first.getJSONObject(TEMP_STRING).getInt(MIN_STRING));
        map.put(FIRST_MAX_TEMP, first.getJSONObject(TEMP_STRING).getInt(MAX_STRING));
        map.put(DESCRIPTION1_STRING, first.getJSONArray(WEATHER_STRING).getJSONObject(0).getString(MAIN_STRING));

        long second_date = second.getLong(DT_STRING);
        Date expiry2 = new Date(second_date * 1000);
        map.put(SECOND_TIME_STRING, expiry2.toString());
        map.put(SECOND_MIN_TEMP, second.getJSONObject(TEMP_STRING).getInt(MIN_STRING));
        map.put(SECOND_MAX_TEMP, second.getJSONObject(TEMP_STRING).getInt(MAX_STRING));
        map.put(DESCRIPTION2_STRING, second.getJSONArray(WEATHER_STRING).getJSONObject(0).getString(MAIN_STRING));

        long third_date = third.getLong(DT_STRING);
        Date expiry3 = new Date(third_date * 1000);
        map.put(THIRD_TIME_STRING, expiry3.toString());
        map.put(THIRD_MIN_TEMP, third.getJSONObject(TEMP_STRING).getInt(MIN_STRING));
        map.put(THIRD_MAX_TEMP, third.getJSONObject(TEMP_STRING).getInt(MAX_STRING));
        map.put(DESCRIPTION3_STRING, third.getJSONArray(WEATHER_STRING).getJSONObject(0).getString(MAIN_STRING));

        long fourth_date = fourth.getLong(DT_STRING);
        Date expiry4 = new Date(fourth_date * 1000);
        map.put(FOURTH_TIME_STRING, expiry4.toString());
        map.put(FOURTH_MIN_TEMP, fourth.getJSONObject(TEMP_STRING).getInt(MIN_STRING));
        map.put(FOURTH_MAX_TEMP, fourth.getJSONObject(TEMP_STRING).getInt(MAX_STRING));
        map.put(DESCRIPTION4_STRING, fourth.getJSONArray(WEATHER_STRING).getJSONObject(0).getString(MAIN_STRING));

        long fifth_date = fifth.getLong(DT_STRING);
        Date expiry5 = new Date(fifth_date * 1000);
        map.put(FIFTH_TIME_STRING, expiry5.toString());
        map.put(FIFTH_MIN_TEMP, fifth.getJSONObject(TEMP_STRING).getInt(MIN_STRING));
        map.put(FIFTH_MAX_TEMP, fifth.getJSONObject(TEMP_STRING).getInt(MAX_STRING));
        map.put(DESCRIPTION5_STRING, fifth.getJSONArray(WEATHER_STRING).getJSONObject(0).getString(MAIN_STRING));

        long sixth_date = sixth.getLong(DT_STRING);
        Date expiry6 = new Date(sixth_date * 1000);
        map.put(SIXTH_TIME_STRING, expiry6.toString());
        map.put(SIXTH_MIN_TEMP, sixth.getJSONObject(TEMP_STRING).getInt(MIN_STRING));
        map.put(SIXTH_MAX_TEMP, sixth.getJSONObject(TEMP_STRING).getInt(MAX_STRING));
        map.put(DESCRIPTION6_STRING, sixth.getJSONArray(WEATHER_STRING).getJSONObject(0).getString(MAIN_STRING));

        long seventh_date = seventh.getLong(DT_STRING);
        Date expiry7 = new Date(seventh_date * 1000);
        map.put(SEVENTH_TIME_STRING, expiry7.toString());
        map.put(SEVENTH_MIN_TEMP, seventh.getJSONObject(TEMP_STRING).getInt(MIN_STRING));
        map.put(SEVENTH_MAX_TEMP, seventh.getJSONObject(TEMP_STRING).getInt(MAX_STRING));
        map.put(DESCRIPTION7_STRING, seventh.getJSONArray(WEATHER_STRING).getJSONObject(0).getString(MAIN_STRING));
        return map;
    }
}
