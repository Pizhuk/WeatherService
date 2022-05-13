package dto;

import org.json.JSONObject;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static constants.WeatherConstants.*;

public class TomorrowWeatherDTO {
    private JSONObject object;
    /**
     * InputJSON is string we get from OpenWeatherApi. In constructor we transform it to JSONObject*/
    public TomorrowWeatherDTO(String inputJSON) {
        this.object = new JSONObject(inputJSON);
    }

    /**
     * Takes a HashMap from private method below and returns the info about weather
     * @return the information about current weather in JSON-format
     */
    public String getCurrentWeatherJson(){
        Map map = getMapListOfWeatherComponents();
        String infoJson = String.format("{\"day_temp\":\"%s\", \"night_temp\":\"%s\", \"eve_temp\":\"%s\", \"morn_temp\":\"%s\", " +
                        "\"wind\":\"%s\", \"humidity\":\"%s\", \"pressure\":\"%s\", \"sunrise\":\"%s\", \"sunset\":\"%s\", \"dt\":\"%s\"," +
                        " \"description\":\"%s\"}",
                map.get(DAY_TEMP_STRING), map.get(NIGHT_TEMP_STRING), map.get(EVENING_TEMP_STRING), map.get(MORNING_TEMP_STRING), map.get(WIND_SPEED_STRING),
                map.get(HUMIDITY_STRING), map.get(PRESSURE_STRING), map.get(SUNRISE_STRING), map.get(SUNSET_STRING),
                map.get(DATE_STRING), map.get(MAIN_STRING));
        return infoJson;
    }

    /**Takes JSONObject from constructor and use it to get data about weather*/
    private Map getMapListOfWeatherComponents(){
        JSONObject tomorrow = object.getJSONArray(DAILY_STRING).getJSONObject(1);
        JSONObject temp = tomorrow.getJSONObject(TEMP_STRING);
        JSONObject weather = tomorrow.getJSONArray(WEATHER_STRING).getJSONObject(0);

        HashMap<String, Object> map = new HashMap<>(11);

        map.put(DAY_TEMP_STRING, (int)(temp.getDouble(DAY_TEMP_STRING)));
        map.put(NIGHT_TEMP_STRING, (int)(temp.getDouble(NIGHT_TEMP_STRING)));
        map.put(EVENING_TEMP_STRING, (int)(temp.getDouble(EVENING_TEMP_STRING)));
        map.put(MORNING_TEMP_STRING, (int)(temp.getDouble(MORNING_TEMP_STRING)));
        map.put(MAIN_STRING, weather.getString(MAIN_STRING));
        map.put(PRESSURE_STRING, tomorrow.getInt(PRESSURE_STRING));
        map.put(HUMIDITY_STRING, tomorrow.getInt(HUMIDITY_STRING));
        map.put(WIND_SPEED_STRING, tomorrow.getDouble(WIND_SPEED_STRING));

        long sunriseTime = tomorrow.getLong(SUNRISE_STRING);
        Date expiry1 = new Date(sunriseTime * 1000);
        map.put(SUNRISE_STRING, String.format(TIME_STRING, expiry1.getHours(), expiry1.getMinutes()));

        long sunsetTime = tomorrow.getLong(SUNSET_STRING);
        Date expiry2 = new Date(sunsetTime * 1000);
        map.put(SUNSET_STRING, String.format(TIME_STRING, expiry2.getHours(), expiry2.getMinutes()));

        long date = tomorrow.getLong(DATE_STRING);
        Date expiry3 = new Date(date * 1000);
        map.put(DATE_STRING, expiry3.toString());

        return map;
    }
}
