package services.db.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import static constants.DBConstants.*;

public class DBConnection {
    public Connection getConnection(){
        Connection conn = null;
        try {
            Properties props = new Properties();
            props.setProperty(USER_STRING, USER);
            props.setProperty(PASSWORD_STRING, PASSWORD);
            conn = DriverManager.getConnection(URL, props);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
