package jm.task.core.jdbc;


import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
//        Util.getConnection();
        UserServiceImpl UserServiceImpl = new UserServiceImpl();

        UserServiceImpl.createUsersTable();

        UserServiceImpl.saveUser("Name1", "LastName1", (byte) 20);
        UserServiceImpl.saveUser("Name2", "LastName2", (byte) 25);
        UserServiceImpl.saveUser("Name3", "LastName3", (byte) 31);
        UserServiceImpl.saveUser("Name4", "LastName4", (byte) 38);

        UserServiceImpl.removeUserById(1);

        UserServiceImpl.removeUserById(1);
        UserServiceImpl.getAllUsers();
        UserServiceImpl.cleanUsersTable();
        UserServiceImpl.dropUsersTable();
    }

}
