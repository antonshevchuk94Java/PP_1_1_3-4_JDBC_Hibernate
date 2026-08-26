package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private  SessionFactory sessionFactory = new Util().getSessionFactory();
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        try (Session session = sessionFactory.openSession()) { // открываем ресурс (сессию)
            Transaction transaction = session.beginTransaction(); // начинаем транзакцию
            try {
                session.createNativeQuery("CREATE TABLE IF NOT EXISTS users (" +
                                "id BIGINT PRIMARY KEY AUTO_INCREMENT, " +
                                "name VARCHAR(50), " +
                                "last_name VARCHAR(50), " +
                                "age TINYINT)")
                        .executeUpdate(); // выполнияем SQL запрос
                transaction.commit(); // фиксируем транзакцию
            } catch (Exception e) {
                transaction.rollback(); // откатываем при ошибки выполнения запроса
                e.printStackTrace(); // выводи информацию об ошибки
            }
        }
        }

    @Override
    public void dropUsersTable() {
            try (Session session = sessionFactory.openSession()) { // открываем ресурс (сессию)
                Transaction transaction = session.beginTransaction(); // начинаем транзакцию
                try {
                    session.createNativeQuery("DROP TABLE IF EXISTS users ")
                            .executeUpdate(); // выполнияем SQL запрос
                    transaction.commit(); // фиксируем транзакцию
                } catch (Exception e) {
                    transaction.rollback(); // откатываем при ошибки выполнения запроса
                    e.printStackTrace(); // выводи информацию об ошибки
                }
            }
        }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        User user = new User(name,lastName,age);
        try (Session session = sessionFactory.openSession()) { // открываем ресурс (сессию)
            Transaction transaction = session.beginTransaction(); // начинаем транзакцию
            try {
                session.save(user);

                transaction.commit(); // фиксируем транзакцию
            } catch (Exception e) {
                transaction.rollback(); // откатываем при ошибки выполнения запроса
                e.printStackTrace(); // выводи информацию об ошибки
            }
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = sessionFactory.openSession()) { // открываем ресурс (сессию)
            Transaction transaction = session.beginTransaction(); // начинаем транзакцию
            try {
                User user = session.get(User.class, id);
                session.delete(user);

                transaction.commit(); // фиксируем транзакцию
            } catch (Exception e) {
                transaction.rollback(); // откатываем при ошибки выполнения запроса
                e.printStackTrace(); // выводи информацию об ошибки
            }
        }
    }

    @Override
    public List<User> getAllUsers() {
            try (Session session = sessionFactory.openSession()) { // открываем ресурс (сессию)
                Transaction transaction = session.beginTransaction(); // начинаем транзакцию
                try {
                   List<User> users = session.createQuery("FROM User", User.class).list();
                    transaction.commit(); // фиксируем транзакцию
                    return users;
                } catch (Exception e) {
                    transaction.rollback(); // откатываем при ошибки выполнения запроса
                    e.printStackTrace(); // выводи информацию об ошибки
                }
            }
            return null;
        }

    @Override
    public void cleanUsersTable() {      try (Session session = sessionFactory.openSession()) { // открываем ресурс (сессию)
        Transaction transaction = session.beginTransaction(); // начинаем транзакцию
        try {
            session.createNativeQuery("DELETE FROM users").executeUpdate();
            transaction.commit(); // фиксируем транзакцию
        } catch (Exception e) {
            transaction.rollback(); // откатываем при ошибки выполнения запроса
            e.printStackTrace(); // выводи информацию об ошибки
        }
    }
    }
}
