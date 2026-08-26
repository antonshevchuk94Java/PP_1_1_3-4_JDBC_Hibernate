package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Jack","Fisher",(byte) 9 );
        userService.saveUser("Jon","Week",(byte) 37 );
        userService.saveUser("Bob","Silent",(byte) 27 );
        userService.saveUser("Tony","Soprano",(byte) 21);
        userService.getAllUsers().forEach(System.out::println);
        userService.removeUserById(11);
        userService.cleanUsersTable();
        userService.dropUsersTable();
        }

    }

