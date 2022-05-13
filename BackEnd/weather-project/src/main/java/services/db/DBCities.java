package services.db;

import models.City;
import services.db.connection.DBConnection;
import static constants.DBQueryConstants.*;
import static constants.DBExtraConstants.*;

import java.sql.*;

public class DBCities {
    private DBConnection dbConnection;
    private City city;

    public DBCities(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public DBCities(DBConnection dbConnection, City city) {
        this.dbConnection = dbConnection;
        this.city = city;
    }

    public int insertCity(){
        int operationCode = OPERATION_CODE;
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement statement = conn.prepareStatement(String.format(INSERT_CITY_QUERY, city.getLat(), city.getLon()),
                     Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, city.getName());
            statement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
            operationCode = Integer.parseInt(ex.getSQLState());
        }
        return operationCode;
    }

    public int getIdOfCity(City city){
        int info = -1;
        try (Connection connection = dbConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(FIND_OUT_CITY_ID_QUERY)) {
            ps.setString(1, city.getName());
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                info = rs.getInt(ID_STRING);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return info;
    }

    public City getCity(String nameOfCity){
        City city = null;
        try (Connection connection = dbConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(FIND_OUT_CITY_INFO_QUERY)) {
            ps.setString(1, nameOfCity);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                double lat = rs.getDouble(LAT_STRING);
                double lon = rs.getDouble(LON_STRING);
                city = new City(nameOfCity, lat, lon);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return city;
    }
}
