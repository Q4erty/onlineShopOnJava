package Database;

import java.sql.*;
import CUI.Console;
import CUI.ConsoleSeller;
import Product.Product;
import User.User;
import User.Balance;

public class DatabaseHelper extends Configs{
    public Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String connectionAddress = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(connectionAddress, dbUser, dbPass);
    }

    public ResultSet displayAllProduct() throws ClassNotFoundException, SQLException{
        Connection connection = getDbConnection();
        Statement statement = connection.createStatement();
        return statement.executeQuery("SELECT * FROM product");
    }

    public ResultSet findUser(User user){
        String select = "SELECT * FROM " + Const.USER_TABLE +
                " WHERE " + Const.USER_TABLE_LOGIN + "=? AND " + Const.USER_TABLE_PASSWORD + "=?";
        try {
            PreparedStatement form = getDbConnection().prepareStatement(select);
            form.setString(1, user.getLoginUser());
            form.setString(2, user.getPasswordUser());
            return form.executeQuery();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void signUpDatabase(User user){
        Console consoleSignUpDatabase = new Console();
        String insert = "INSERT INTO " + Const.USER_TABLE + "(" + Const.USER_TABLE_NAME + "," +
                Const.USER_TABLE_LOGIN + "," + Const.USER_TABLE_PASSWORD + ")" + "VALUES(?,?,?)";
        try {
            PreparedStatement form = getDbConnection().prepareStatement(insert);
            form.setString(1, user.getNameUser());
            form.setString(2, user.getLoginUser());
            form.setString(3, user.getPasswordUser());
            form.executeUpdate();

            System.out.println("Вы успешно зарегистривовались, теперь вам нужно войти");
            consoleSignUpDatabase.registration();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void addProduct(Product product){
        ConsoleSeller consoleForAdd = new ConsoleSeller();
        String insert = "INSERT INTO " + Const.PRODUCT_TABLE + "(" + Const.PRODUCT_TABLE_NAME + "," +
                Const.PRODUCT_TABLE_DESCRIPTION + "," + Const.PRODUCT_TABLE_COST + "," + Const.PRODUCT_TABLE_LEFT +
                ")" + "VALUES(?,?,?,?)";
        try {
            PreparedStatement form = getDbConnection().prepareStatement(insert);
            form.setString(1, product.getNameProduct());
            form.setString(2, product.getDescriptionProduct());
            form.setInt(3, product.getCost());
            form.setInt(4, product.getLeft());
            form.executeUpdate();

            consoleForAdd.sellerCUI();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void updateBalance(Balance balance){
        User userDatabase = new User();
        String newBalance = "UPDATE " + Const.USER_TABLE + " SET " + Const.USER_TABLE_BALANCE + "=? WHERE " + Const.USER_TABLE_ID + "=?";
        try {
            PreparedStatement formForBalance = getDbConnection().prepareStatement(newBalance);
            formForBalance.setInt(1, balance.getSumma());
            formForBalance.setInt(2, userDatabase.getIdUser());

            formForBalance.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public int currentBalance(){
        User user = new User();

        String select = "SELECT " + Const.USER_TABLE_BALANCE + " FROM " + Const.USER_TABLE + " WHERE " + Const.USER_TABLE_ID + "=?";
        try {
            PreparedStatement form = getDbConnection().prepareStatement(select);
            form.setInt(1, user.getIdUser());

            ResultSet result = form.executeQuery();
            result.next();
            return result.getInt(Const.USER_TABLE_BALANCE);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int[] dataProduct(int ID){
        int costProduct = 0;
        int leftProduct = 0;
        String select = "SELECT * FROM " + Const.PRODUCT_TABLE + " WHERE " + Const.PRODUCT_TABLE_ID + "=?";
        try {
            PreparedStatement form = getDbConnection().prepareStatement(select);
            form.setInt(1, ID);

            ResultSet result = form.executeQuery();
            while (result.next()) {
                costProduct = result.getInt(Const.PRODUCT_TABLE_COST);
                leftProduct = result.getInt(Const.PRODUCT_TABLE_LEFT);
            }
            return new int[]{costProduct, leftProduct};
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateLostProduct(int ID, int quantity){
        String newBalance = "UPDATE " + Const.PRODUCT_TABLE + " SET " + Const.PRODUCT_TABLE_LEFT + "=? WHERE " + Const.PRODUCT_TABLE_ID + "=?";
        try {
            PreparedStatement formForBalance = getDbConnection().prepareStatement(newBalance);
            formForBalance.setInt(1, quantity);
            formForBalance.setInt(2, ID);
            formForBalance.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ResultSet lookForProductDB(String request){
        String select = "SELECT * FROM " + Const.PRODUCT_TABLE + " WHERE " + Const.PRODUCT_TABLE_NAME + " LIKE ?";
        try {
            PreparedStatement form = getDbConnection().prepareStatement(select);
            form.setString(1,"%" + request + "%");

            return form.executeQuery();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}