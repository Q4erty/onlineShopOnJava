package database.child;

import database.Database;
import database.constant.Const;
import model.Balance;
import model.Product;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductDatabase extends Database {
    /**
     * Для получчения всез продуктов
     **/
    public ResultSet displayAllProduct() {
        return getAll(Const.PRODUCT_TABLE);
    }

    /**
     * Добавления продукта в базу данных
     **/
    public void addProduct(Product product) {
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
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Для обновления количества продукта после покупки
     **/
    public void updateLostProduct(int ID, int quantity) {
        String newBalance = "UPDATE " + Const.PRODUCT_TABLE + " SET " + Const.PRODUCT_TABLE_LEFT + "=? WHERE " + Const.PRODUCT_TABLE_ID + "=?";
        try {
            PreparedStatement formForBalance = getDbConnection().prepareStatement(newBalance);
            formForBalance.setInt(1, quantity);
            formForBalance.setInt(2, ID);
            formForBalance.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Найти продукт по имени
     **/
    public ResultSet findProduct(String request) {
        String select = "SELECT * FROM " + Const.PRODUCT_TABLE + " WHERE " + Const.PRODUCT_TABLE_NAME + " LIKE ?";
        try {
            PreparedStatement form = getDbConnection().prepareStatement(select);
            form.setString(1, "%" + request + "%");

            return form.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Найти продукт по id
     **/
    public Product findProduct(int ID) {
        Product product = new Product();
        String select = "SELECT * FROM " + Const.PRODUCT_TABLE + " WHERE " + Const.PRODUCT_TABLE_ID + "=?";
        try {
            PreparedStatement form = getDbConnection().prepareStatement(select);
            form.setString(1, String.valueOf(ID));
            ResultSet resultSet = form.executeQuery();

            resultSet.next();
            product.setId(resultSet.getInt(Const.PRODUCT_TABLE_ID));
            product.setNameProduct(resultSet.getString(Const.PRODUCT_TABLE_NAME));
            product.setDescriptionProduct(resultSet.getString(Const.PRODUCT_TABLE_DESCRIPTION));
            product.setCost(resultSet.getInt(Const.PRODUCT_TABLE_COST));
            product.setLeft(resultSet.getInt(Const.PRODUCT_TABLE_LEFT));

            return product;
        } catch (SQLException e) {
            System.out.println("Ничего не найдено");
            return null;
        }
    }

    /**
     * Покупака продукта
     **/
    public void buyProduct(int ID) throws SQLException, ClassNotFoundException {
        UserDatabase userDatabase = new UserDatabase();
        Balance balanceForBuy = new Balance();
        Product product = findProduct(ID);

        if (product.getCost() <= userDatabase.currentBalance()) {
            if (product.getLeft() > 0) {
                balanceForBuy.setSumma(userDatabase.currentBalance() - product.getCost());
                userDatabase.updateBalance(balanceForBuy);
                updateLostProduct(ID, product.getLeft() - 1);
                System.out.println("Вы успешно купили телефон");
                userDatabase.addToHistory(userDatabase.getIdUser(), product.getNameProduct() + " был куплен за " + product.getCost());
            } else {
                System.out.println("Нет в наличий");
            }
        } else {
            System.out.println("У вас не хватает средств");
        }
    }

    /**
     * Для редактирование продукта
     **/
    public void editProductString(int id, String attributeName, String newValue) {
        String updateQuery = "UPDATE product SET " + attributeName + " = ? WHERE idProduct = ?";
        try {
            PreparedStatement statement = getDbConnection().prepareStatement(updateQuery);
            statement.setString(1, newValue);
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Удаление продукта из базы данных
     **/
    public void deleteProduct(int id) {
        String delete = "DELETE FROM product WHERE idProduct = ?";
        try {
            PreparedStatement statement = getDbConnection().prepareStatement(delete);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}