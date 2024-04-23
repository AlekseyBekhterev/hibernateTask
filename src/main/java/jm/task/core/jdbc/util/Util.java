package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Util {
    private static final String MY_URL = "jdbc:mysql://localhost:3306/users";
    private static final String MY_USER = "root";
    private static final String MY_PASSWORD = "";

    //создали подключение к базе данных
    public static Connection getConnection() {
        Connection connection = null;
        Logger logger = Logger.getLogger(Util.class.getName());
        if (Objects.isNull(connection)) {
            try {
                connection = DriverManager.getConnection(MY_URL, MY_USER, MY_PASSWORD);
                logger.log(Level.INFO, "соединение с базой данных установленно");
            } catch (ClassCastException | SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return connection;
    }
}
