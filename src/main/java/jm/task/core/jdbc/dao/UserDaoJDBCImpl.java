package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.mysql.cj.xdevapi.Type.BIGINT;
import static java.sql.JDBCType.TINYINT;

public class UserDaoJDBCImpl implements UserDao {
    private Util util = new Util(); // создаем обьект для работы с бд
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Connection sqlConnection = util.getConnection();
             Statement sqlStatement = sqlConnection.createStatement()) { // пробуем подключиться к БД

            String sql = "CREATE TABLE IF NOT EXISTS users (" +
                    "id BIGINT PRIMARY KEY AUTO_INCREMENT, " +
                    "name VARCHAR(50), " +
                    "last_name VARCHAR(50), " +
                    "age TINYINT)"; // делаем запрос на создание таблицы данных (id/name/last_name/age)

            sqlStatement.execute(sql); // пробуем выполнить sql запрос

        } catch (SQLException e) {
            e.printStackTrace(); // выводим информацию об ошибке
        }
    }



    public void dropUsersTable() {
        try (Connection sqlConnection = util.getConnection();
             Statement sqlStatement = sqlConnection.createStatement()) { // пробуем подключиться к БД

            String sql = "DROP TABLE IF EXISTS users ";

            sqlStatement.execute(sql); // пробуем выполнить sql запрос

        } catch (SQLException e) {
            e.printStackTrace(); // выводим информацию об ошибке
        }
    }


    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO users (name, last_name, age) VALUES (?, ?, ?)"; // sql запрос
        try (Connection sqlConnection = util.getConnection();
             PreparedStatement ps = sqlConnection.prepareStatement(sql)) {// пробуем подключиться к БД и передать SQL запрос
             ps.setString(1, name); // инициализация параметров
             ps.setString(2, lastName);
             ps.setByte(3,age);


            ps.execute(); // пробуем выполнить sql запрос

        } catch (SQLException e) {
            e.printStackTrace(); // выводим информацию об ошибке
        }
    }


    public void removeUserById(long id) {
        String sql = "DELETE FROM users WHERE id = ?";

        try (Connection sqlConnection = util.getConnection();
             PreparedStatement ps = sqlConnection.prepareStatement(sql)) {

            ps.setLong(1, id);

            int rows = ps.executeUpdate(); // пробуем выполнить sql запрос если да то получим 1 если нет 0

            if (rows == 0) {
                System.out.println("User c таким id не найден!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users";
        try (Connection sqlConnection = util.getConnection();
             Statement st = sqlConnection.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                User user = new User();
                user.setId(rs.getLong("id"));
                user.setName(rs.getString("name"));
                user.setLastName((rs.getString("last_name")));
                user.setAge(rs.getByte("age"));
                users.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void cleanUsersTable() {
        String sql = "DELETE FROM users"; // sql запрос
        try (Connection sqlConnection = util.getConnection();
        Statement ps = sqlConnection.createStatement()) {// пробуем подключиться к БД и передать SQL запрос
            ps.execute(sql); // пробуем выполнить sql запрос

        } catch (SQLException e) {
            e.printStackTrace(); // выводим информацию об ошибке
        }
    }
}
