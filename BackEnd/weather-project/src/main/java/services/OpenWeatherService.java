package services;

import dto.CurrentWeatherDTO;
import dto.HourlyWeatherDTO;
import dto.TomorrowWeatherDTO;
import dto.WeekWeatherDTO;
import models.City;
import services.db.DBCities;
import services.db.connection.DBConnection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLException;

public class OpenWeatherService {
    private static final String CITY_NOT_FOUND = "{\"info\": \"not found\"}";

    public String getCurrentInfo(String city){
        CurrentWeatherDTO currentWeatherDTO = null;
        String info = null;
        String url = "https://api.openweathermap.org/data/2.5/weather?q="+city+"&appid=444202ce18bdacb315ba884ecae1366d&units=metric";
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("GET");
            connection.addRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");
            if (connection.getResponseCode() == 200){
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine = in.readLine();
                in.close();

                currentWeatherDTO = new CurrentWeatherDTO(inputLine);
                info = currentWeatherDTO.getCurrentWeatherJson();
                currentWeatherDTO.makeNoteIntoDB();
            }
            else {
                info = CITY_NOT_FOUND;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return info;
    }

    public String getHourlyInfo(String cityName){
        DBConnection dbConnection = new DBConnection();
        DBCities dbCities = new DBCities(dbConnection);
        City city = dbCities.getCity(cityName);
        String info = "";
        String url = "https://api.openweathermap.org/data/2.5/onecall?lat="+city.getLat()+"&lon="+city.getLon()+"&exclude={daily}&appid=4f61be7f5e756f1438326c4513a0a723&units=metric";
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("GET");
            connection.addRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine = in.readLine();
            HourlyWeatherDTO hourlyWeatherDTO = new HourlyWeatherDTO(inputLine);
            info = hourlyWeatherDTO.getHourlyWeatherJson();
            in.close();
            dbConnection.getConnection().close();
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
        return info;
    }

    public String getTomorrowInfo(String cityName){
        DBConnection dbConnection = new DBConnection();
        DBCities dbCities = new DBCities(dbConnection);
        City city = dbCities.getCity(cityName);
        String info = "";
        String url = "https://api.openweathermap.org/data/2.5/onecall?lat="+city.getLat()+"&lon="+city.getLon()+"&exclude={daily}&appid=4f61be7f5e756f1438326c4513a0a723&units=metric";
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("GET");
            connection.addRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine = in.readLine();
            TomorrowWeatherDTO tomorrowWeatherDTO = new TomorrowWeatherDTO(inputLine);
            info = tomorrowWeatherDTO.getCurrentWeatherJson();
            in.close();
            dbConnection.getConnection().close();
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
        return info;
    }

    public String getWeekInfo(String cityName){
        DBConnection dbConnection = new DBConnection();
        DBCities dbCities = new DBCities(dbConnection);
        City city = dbCities.getCity(cityName);
        String info = "";
        String url = "https://api.openweathermap.org/data/2.5/onecall?lat="+city.getLat()+"&lon="+city.getLon()+"&exclude={daily}&appid=4f61be7f5e756f1438326c4513a0a723&units=metric";
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("GET");
            connection.addRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine = in.readLine();
            WeekWeatherDTO weekWeatherDTO = new WeekWeatherDTO(inputLine);
            info = weekWeatherDTO.getWeekWeatherJson();
            in.close();
            dbConnection.getConnection().close();
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
        return info;
    }
}
