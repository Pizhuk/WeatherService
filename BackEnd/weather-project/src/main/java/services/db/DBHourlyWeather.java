package services.db;

import models.City;
import services.db.connection.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import static constants.DBQueryConstants.*;
import static constants.DBExtraConstants.*;

public class DBHourlyWeather {
    private DBConnection dbConnection;
    private City city;

    public DBHourlyWeather(DBConnection dbConnection, City city) {
        this.dbConnection = dbConnection;
        this.city = city;
    }

    public int insertHourlyWeatherInfo(String jsonInfo){
        int operationCode = OPERATION_CODE;

        DBCities dbCities = new DBCities(dbConnection);
        int id = dbCities.getIdOfCity(city);

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement statement = conn.prepareStatement(String.format(INSERT_HOURLY_WEATHER_INFO_QUERY, id,
                     System.currentTimeMillis()), Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, jsonInfo);
            statement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
            operationCode = Integer.parseInt(ex.getSQLState());
        }finally {
            try {
                dbConnection.getConnection().close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return operationCode;
    }
}
