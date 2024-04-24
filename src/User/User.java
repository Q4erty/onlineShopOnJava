package User;

import CUI.ConsoleSeller;
import Database.Const;
import Database.DatabaseHelper;
import CUI.Console;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class User {
    private String nameUser;
    private String loginUser;
    private String passwordUser;
    private static int idUser;
    private int balanceUser;
    private static boolean seller;

    public User() {
    }
    public User(String loginUser, String passwordUser) {
        this.loginUser = loginUser;
        this.passwordUser = passwordUser;
    }

    public User(String nameUser, String loginUser, String passwordUser) {
        this.loginUser = loginUser;
        this.passwordUser = passwordUser;
    }

    public String getNameUser() {
        return nameUser;
    }
    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }
    public String getLoginUser() {
        return loginUser;
    }
    public void setLoginUser(String loginUser) {
        this.loginUser = loginUser;
    }
    public String getPasswordUser() {
        return passwordUser;
    }
    public void setPasswordUser(String passwordUser) {
        this.passwordUser = passwordUser;
    }
    public int getIdUser() {
        return idUser;
    }
    public void setIdUser(int idUser) {
        User.idUser = idUser;
    }
    public int getBalanceUser() {
        return balanceUser;
    }
    public void setBalanceUser(int balanceUser) {
        this.balanceUser = balanceUser;
    }

    public static boolean isSeller() {
        return seller;
    }

    public static void setSeller(boolean seller) {
        User.seller = seller;
    }

    public void signUp(){
        Scanner input = new Scanner(System.in);
        DatabaseHelper db = new DatabaseHelper();
        User userForSignUp = new User();

        System.out.print("Имя: ");
        userForSignUp.setNameUser(input.next());

        System.out.print("Логин: ");
        userForSignUp.setLoginUser(input.next());

        System.out.print("Пароль: ");
        userForSignUp.setPasswordUser(input.next());

        db.signUpDatabase(userForSignUp);
    }
    public void logIn(){
        Scanner input = new Scanner(System.in);
        DatabaseHelper db = new DatabaseHelper();
        Console consoleUser = new Console();
        ConsoleSeller selCUI = new ConsoleSeller();
        int counterLogIn = 0;

        System.out.print("Логин: ");
        setLoginUser(input.next());

        System.out.print("Пароль: ");
        setPasswordUser(input.next());

        User userLogIn = new User(getLoginUser(), getPasswordUser());
        ResultSet resultSet = db.findUser(userLogIn);

        try {
            while (resultSet.next()){
                counterLogIn++;
                setIdUser(resultSet.getInt(Const.USER_TABLE_ID));
                setSeller(resultSet.getBoolean(Const.USER_TABLE_SELLER));
            }if(counterLogIn >= 1) {
                System.out.println("Вы вошли");
                if(!isSeller()) consoleUser.CUI();
                else selCUI.sellerCUI();
            }else{
                System.out.println("Не найдено такого пользователя");
                consoleUser.registration();
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}