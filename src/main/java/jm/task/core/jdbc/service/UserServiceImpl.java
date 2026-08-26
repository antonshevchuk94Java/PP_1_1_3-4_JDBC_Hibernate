package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {
    UserDao userDao = new UserDaoJDBCImpl();
    UserDaoHibernateImpl userHibernate = new UserDaoHibernateImpl();

    public void createUsersTable() throws SQLException {
        userHibernate.createUsersTable();

    }

    public void dropUsersTable() {
        userHibernate.dropUsersTable();

    }

    public void saveUser(String name, String lastName, byte age) {
        userHibernate.saveUser(name,lastName,age);
        System.out.println("User c именем - " + name + " Добавлен в базу");

    }

    public void removeUserById(long id) {
        userHibernate.removeUserById(id);

    }

    public List<User> getAllUsers() {
        return  userHibernate.getAllUsers();
    }

    public void cleanUsersTable() {
        userHibernate.cleanUsersTable();

    }
}
