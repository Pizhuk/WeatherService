package dto;

import models.City;
import org.json.JSONObject;
import services.db.DBCities;
import services.db.DBCurrentWeather;
import services.db.connection.DBConnection;
import static constants.WeatherConstants.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CurrentWeatherDTO {
    private JSONObject object;
    /**
     * InputJSON is string we get from OpenWeatherApi. In constructor we transform it to JSONObject*/
    public CurrentWeatherDTO(String inputJSON) {
        this.object = new JSONObject(inputJSON);
    }

    /**
     * Takes a HashMap from private method below and returns the info about weather
     * @return the information about current weather in JSON-format
     */
    public String getCurrentWeatherJson(){
        Map map = getMapListOfWeatherComponents();
        String infoJson = String.format("{\"temp\":\"%s\", \"sunrise\":\"%s\", \"sunset\":\"%s\", \"visibility\":\"%s\", " +
                "\"wind\":\"%s\", \"humidity\":\"%s\", \"pressure\":\"%s\", \"feels_like\":\"%s\", \"description\":\"%s\"}",
                map.get(TEMP_STRING), map.get(SUNRISE_STRING), map.get(SUNSET_STRING), map.get(VISIBILITY_STRING), map.get(SPEED_STRING),
                map.get(HUMIDITY_STRING), map.get(PRESSURE_STRING), map.get(FEELS_LIKE_STRING), map.get(MAIN_STRING));
        return infoJson;
    }

    /**Insert data about city and weather into DB*/
    public int makeNoteIntoDB(){
        JSONObject coord = object.getJSONObject(COORD_STRING);
        String cityName = object.getString(CITY_NAME_STRING);
        double lat = coord.getDouble(LAT_STRING);
        double lon = coord.getDouble(LON_STRING);

        City city = new City(cityName, lat, lon);

        DBConnection connection = new DBConnection();
        DBCities dbCities = new DBCities(connection, city);
        DBCurrentWeather dbCurrentWeather = new DBCurrentWeather(connection, city);

        int operationCode1 = dbCities.insertCity();
        int operationCode2 = dbCurrentWeather.insertCurrentWeatherInfo(object.toString());

        return operationCode1-operationCode2;
    }
    /**Takes JSONObject from constructor and use it to get data about weather*/
    private Map getMapListOfWeatherComponents(){
        JSONObject main = object.getJSONObject(MAIN_STRING);
        JSONObject weather = object.getJSONArray(WEATHER_STRING).getJSONObject(0);
        JSONObject wind = object.getJSONObject(WIND_STRING);
        JSONObject sys = object.getJSONObject(SYS_STRING);

        HashMap<String, Object> map = new HashMap<>(9);

        map.put(TEMP_STRING, main.getInt(TEMP_STRING));
        map.put(FEELS_LIKE_STRING, (int)(main.getDouble(FEELS_LIKE_STRING)));
        map.put(MAIN_STRING, weather.getString(MAIN_STRING));
        map.put(PRESSURE_STRING, main.getInt(PRESSURE_STRING));
        map.put(HUMIDITY_STRING, main.getInt(HUMIDITY_STRING));
        map.put(SPEED_STRING, wind.getDouble(SPEED_STRING));
        map.put(VISIBILITY_STRING, object.getInt(VISIBILITY_STRING));

        long sunriseTime = sys.getLong(SUNRISE_STRING);
        Date expiry1 = new Date(sunriseTime * 1000);
        map.put(SUNRISE_STRING, String.format(TIME_STRING, expiry1.getHours(), expiry1.getMinutes()));

        long sunsetTime = sys.getLong(SUNSET_STRING);
        Date expiry2 = new Date(sunsetTime * 1000);
        map.put(SUNSET_STRING, String.format(TIME_STRING, expiry2.getHours(), expiry2.getMinutes()));

        return map;
    }
}
