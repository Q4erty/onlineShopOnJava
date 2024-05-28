package service;

import console.ConsoleConsumer;
import console.ConsoleSeller;
import database.child.UserDatabase;
import database.constant.Const;
import model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class UserFunctions {
    Scanner input = new Scanner(System.in);
    UserDatabase userDatabase = new UserDatabase();

    public UserFunctions() {
    }

    public User logIn(User user) {
        ConsoleSeller consoleSeller = new ConsoleSeller();
        ConsoleConsumer consoleConsumerUser = new ConsoleConsumer();
        int counterLogIn = 0;

        ResultSet resultSet = userDatabase.findUser(user);

        try {
            while (resultSet.next()) {
                counterLogIn++;
                user.setIdUser(resultSet.getInt(Const.USER_TABLE_ID));
                user.setSeller(resultSet.getBoolean(Const.USER_TABLE_SELLER));
            }
            if (counterLogIn >= 1) {
                System.out.println("Вы вошли");
                if (!user.isSeller()) consoleConsumerUser.mainWindow();
                else consoleSeller.sellerMainWindow();
            } else {
                System.out.println("Не найдено такого пользователя");
                consoleConsumerUser.registration();
            }
            return user;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void signUp() {
        User userForSignUp = new User();

        System.out.print("Имя: ");
        userForSignUp.setNameUser(input.nextLine());

        System.out.print("Логин: ");
        userForSignUp.setLoginUser(input.next());

        System.out.print("Пароль: ");
        userForSignUp.setPasswordUser(input.next());

        userDatabase.signUpDatabase(userForSignUp);
    }

    public void showHistory(){
        List<String> histories = userDatabase.viewHistory();
        for (String history : histories){
            System.out.println(history);
        }
    }

    public void allHistory(){
        ResultSet resultSet = userDatabase.getAll(Const.HISTORY_TABLE);
        try {
            while (resultSet.next()){
                System.out.print(resultSet.getInt(Const.HISTORY_TABLE_ID) + "-ID. ");
                System.out.println(resultSet.getString(Const.HISTORY_TABLE_TEXT));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
