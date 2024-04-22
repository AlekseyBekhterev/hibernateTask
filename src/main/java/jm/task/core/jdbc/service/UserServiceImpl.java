package jm.task.core.jdbc.service;


import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserServiceImpl extends Util implements UserService {
    Logger logger = Logger.getLogger(getClass().getName());
    Connection connection = getConnection();
    Statement statement = null;

    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS person (id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(45), lastName VARCHAR(45) , age TINYINT)";
        try {
            statement = connection.createStatement();
            statement.executeUpdate(sql);
            logger.log(Level.INFO,"таблица создана");

        } catch (SQLException e) {
            logger.log(Level.WARNING,"произошла ошибка создания таблицы");
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
        String sql = "DROP TABLE IF EXISTS person";
        try {
            statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        logger.log(Level.INFO,"таблица удалена");

    }

    public void saveUser(String name, String lastName, byte age) {
        PreparedStatement preparedStatement;
        String sql = "INSERT INTO person (name, lastName, Age) VALUES (?, ?, ?)";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            logger.log(Level.INFO,"создан пользователь {0} {1} {2}", new Object[]{name,lastName,age});

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        String sql = "DELETE FROM person WHERE id = (?)";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            logger.log(Level.INFO,"удален пользователь с id {0}", new Object[]{id});

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public List<User> getAllUsers() {
        List<User> listUser = new ArrayList<>();
        String sql = "SELECT * FROM person";
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                User user = new User();
                user.setId((long) resultSet.getInt(1));
                user.setName(resultSet.getString(2));
                user.setLastName(resultSet.getString(3));
                user.setAge(resultSet.getByte(4));
                listUser.add(user);
                logger.log(Level.INFO,"получен пользователь {0}", new Object[]{listUser});
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listUser;
    }

    public void cleanUsersTable() {
        String sql = "DELETE FROM person";
        try {
            statement = connection.createStatement();
            statement.executeUpdate(sql);
            logger.log(Level.INFO,"таблица очищенна");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
