package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private final Connection connection = Util.getConnection();
    private String com;
    public UserDaoJDBCImpl() {

    }

    @Override
    public void createUsersTable() {

        com = "CREATE TABLE IF NOT EXISTS kata_test.duble (id INT PRIMARY KEY AUTO_INCREMENT , Name VARCHAR(60), lastName VARCHAR(60),age int)";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(com);
            System.out.println("Таблица создана");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {

        com = "DROP TABLE IF EXISTS kata_test.duble";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(com);
            System.out.println("Таблица удалена");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        com = ("INSERT INTO kata_test.duble (name, lastName, age) VALUES (?,?,?)");
        try (PreparedStatement preparedStatement = connection.prepareStatement(com)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            System.out.println("User с именем " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        com = "DELETE FROM kata_test.duble WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(com)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            System.out.println("User с ID под номером " + id + " Удален");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        com = "SELECT * FROM kata_test.duble";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(com);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastname"));
                user.setAge(resultSet.getByte("age"));
                list.add(user);
                System.out.println("Список всех " + user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void cleanUsersTable() {
        com = "TRUNCATE kata_test.duble";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(com);
            System.out.println("Очистка полей таблицы прошла успешно");
        } catch (SQLException e) {
            e.printStackTrace();

        }

    }
}
