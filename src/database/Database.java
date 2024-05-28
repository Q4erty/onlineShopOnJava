package database;

import database.constant.Configs;
import database.constant.Const;
import model.Product;

import java.sql.*;

public class Database extends Configs implements InterfaceForDatabase {
    public Connection getDbConnection() {
        try {
            String connectionAddress = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(connectionAddress, dbUser, dbPass);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public ResultSet getAll(String nameTable) {
        Connection connection = getDbConnection();
        try {
            String request = "SELECT * FROM " + nameTable + ";";
            PreparedStatement preparedStatement = connection.prepareStatement(request);
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //todo дженерики
//    public <T> T getSomethingFromProductAsKortezh(String tableName, int ID){
//        Product product = new Product();
//        String select = "SELECT * FROM " + tableName + " WHERE " + Const.PRODUCT_TABLE_ID + "=?";
//        try {
//            PreparedStatement form = getDbConnection().prepareStatement(select);
//            form.setString(1, String.valueOf(ID));
//            ResultSet resultSet = form.executeQuery();
//
//            resultSet.next();
//            product.setId(resultSet.getInt(Const.PRODUCT_TABLE_ID));
//            product.setNameProduct(resultSet.getString(Const.PRODUCT_TABLE_NAME));
//            product.setDescriptionProduct(resultSet.getString(Const.PRODUCT_TABLE_DESCRIPTION));
//            product.setCost(resultSet.getInt(Const.PRODUCT_TABLE_COST));
//            product.setLeft(resultSet.getInt(Const.PRODUCT_TABLE_LEFT));
//
//            return product;
//        } catch (SQLException e) {
//            System.out.println("Ничего не найдено");
//            return null;
//        }
//        return T;
//    }
}
