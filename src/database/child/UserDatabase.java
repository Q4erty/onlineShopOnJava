package database.child;

import database.Database;
import database.constant.Const;
import model.Balance;
import model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDatabase extends Database {

    public ResultSet getAllUsers() {
        return getAll(Const.USER_TABLE);
    }

    /**
     * Найти кого-то по логину и паролью
     **/
    public ResultSet findUser(User user) {
        String select = "SELECT * FROM " + Const.USER_TABLE +
                " WHERE " + Const.USER_TABLE_LOGIN + "=? AND " + Const.USER_TABLE_PASSWORD + "=?";
        try {
            PreparedStatement form = getDbConnection().prepareStatement(select);
            form.setString(1, user.getLoginUser());
            form.setString(2, user.getPasswordUser());
            return form.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Регистрация
     **/
    public void signUpDatabase(User user) {
        String insert = "INSERT INTO " + Const.USER_TABLE + "(" + Const.USER_TABLE_NAME + "," +
                Const.USER_TABLE_LOGIN + "," + Const.USER_TABLE_PASSWORD + ")" + "VALUES(?,?,?)";
        try {
            PreparedStatement form = getDbConnection().prepareStatement(insert);
            form.setString(1, user.getNameUser());
            form.setString(2, user.getLoginUser());
            form.setString(3, user.getPasswordUser());
            form.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Изменение баланса
     **/
    public void updateBalance(Balance balance) {
        User userDatabase = new User();
        String newBalance = "UPDATE " + Const.USER_TABLE + " SET " + Const.USER_TABLE_BALANCE + "=? WHERE " + Const.USER_TABLE_ID + "=?";
        try {
            PreparedStatement formForBalance = getDbConnection().prepareStatement(newBalance);
            formForBalance.setInt(1, balance.getSumma());
            formForBalance.setInt(2, userDatabase.getIdUser());

            formForBalance.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Получить актуальный баланс
     **/
    public int currentBalance() {
        User user = new User();

        String select = "SELECT " + Const.USER_TABLE_BALANCE + " FROM " + Const.USER_TABLE + " WHERE " + Const.USER_TABLE_ID + "=?";
        try {
            PreparedStatement form = getDbConnection().prepareStatement(select);
            form.setInt(1, user.getIdUser());

            ResultSet result = form.executeQuery();
            result.next();
            return result.getInt(Const.USER_TABLE_BALANCE);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Добавление в историю покупку
     **/
    public void addToHistory(int id, String name) {
        java.time.LocalDate currentDate = java.time.LocalDate.now();
        java.time.LocalTime currentTime = java.time.LocalTime.now();

        String insert = "INSERT INTO " + Const.HISTORY_TABLE + "(" + Const.HISTORY_TABLE_ID + "," +
                Const.HISTORY_TABLE_TEXT + ")" + "VALUES(?,?)";
        try {
            PreparedStatement form = getDbConnection().prepareStatement(insert);
            form.setInt(1, id);
            form.setString(2, name + ". Дата: " + currentDate + ". Время: " + currentTime);
            form.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Посмотреть историю(для пользователя)
     **/
    public List<String> viewHistory() {
        List<String> history = new ArrayList<>();
        String select = "SELECT * FROM " + Const.HISTORY_TABLE + " WHERE " + Const.HISTORY_TABLE_ID + "=?";
        try {
            PreparedStatement form = getDbConnection().prepareStatement(select);
            form.setString(1, String.valueOf(getIdUser()));
            ResultSet resultSet = form.executeQuery();
            while (resultSet.next()) {
                history.add(resultSet.getString(Const.HISTORY_TABLE_TEXT));
            }
            return history;
        } catch (SQLException e) {
            System.out.println("Ничего не найдено");
            return null;
        }
    }

    public int getIdUser() {
        User user = new User();
        return user.getIdUser();
    }
}