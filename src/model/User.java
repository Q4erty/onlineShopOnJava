package model;

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
}