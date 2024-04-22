package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Util {
//    Logger logger = Logger.getLogger(getClass().getName());
    // реализуйте настройку соеденения с БД
    //url для моей базы
    private static final String MY_URL = "jdbc:mysql://localhost:3306/users";
    //user для моей базы
    private static final String MY_USER = "root";
    //password для моей базы
    private static final String MY_PASSWORD = "";

    //создали подключение к базе данных
    public static Connection getConnection() {
        Logger logger = Logger.getLogger(Util.class.getName());
        Connection connection;
        try {
            connection = DriverManager.getConnection(MY_URL,MY_USER, MY_PASSWORD);
            logger.log(Level.INFO,"соединение с базой данных установленно");
        } catch (ClassCastException | SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }
}
