package constants;

public class DBQueryConstants {
    public static final String INSERT_CITY_QUERY = "INSERT INTO cities(name, lat, lon) VALUES(?,%s,%s)";
    public static final String INSERT_CURRENT_WEATHER_INFO_QUERY = "INSERT INTO current_weather(city_id, json_info, dt) VALUES(%s,?,%s)";
    public static final String INSERT_HOURLY_WEATHER_INFO_QUERY = "INSERT INTO hourly_weather(city_id, json_info, dt) VALUES(%s,?,%s)";
    public static final String FIND_OUT_CITY_ID_QUERY = "select id from cities where name = ?";
    public static final String FIND_OUT_CITY_INFO_QUERY = "select lat, lon from cities where name = ?";
}
