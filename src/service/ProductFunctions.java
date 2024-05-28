package service;

import database.child.ProductDatabase;
import database.constant.Const;
import model.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProductFunctions {
    ProductDatabase database = new ProductDatabase();
    Scanner input = new Scanner(System.in);

    /**Автоматический заполнения данных с SQL в объект Product**/
    public Product autoAddElements(ResultSet resultSet){
        Product product = new Product();
        try {
            product.setId(resultSet.getInt(Const.PRODUCT_TABLE_ID));
            product.setNameProduct(resultSet.getString(Const.PRODUCT_TABLE_NAME));
            product.setDescriptionProduct(resultSet.getString(Const.PRODUCT_TABLE_DESCRIPTION));
            product.setCost(resultSet.getInt(Const.PRODUCT_TABLE_COST));
            product.setLeft(resultSet.getInt(Const.PRODUCT_TABLE_LEFT));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return product;
    }

    /**Вывод всех продуктов в виде массива**/
    public List<Product> productAsArray() {
        List<Product> productAsArray = new ArrayList<>();
        ResultSet resultSet = database.getAll(Const.PRODUCT_TABLE);
        try {
            while (resultSet.next()) {
                productAsArray.add(autoAddElements(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return productAsArray;
    }

    /**Результат пойска продукта в виде массива**/
    public List<Product> lookForAsArray(String name) {
        List<Product> foundProducts = new ArrayList<>();
        ResultSet resultSet = database.findProduct(name);
        try {
            while (resultSet.next()) {
                foundProducts.add(autoAddElements(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return foundProducts;
    }

    /**Очевидно для чего**/
    public void buyProduct(int id){
        try {
            database.buyProduct(id);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**Добавление продукта продавцом**/
    public void addProduct(){
        Product productForAdd = new Product();

        System.out.print("Названия продукта: ");
        productForAdd.setNameProduct(input.nextLine());

        System.out.print("Процессор: ");
        productForAdd.setDescriptionProduct(input.nextLine());

        System.out.print("Цена: ");
        productForAdd.setCost(input.nextInt());

        System.out.print("Количество: ");
        productForAdd.setLeft(input.nextInt());

        database.addProduct(productForAdd);
    }

    /**Для определения какую таблицу изменить, если таблица будеть расширяться нужно вручную добавлять**/
    public void editProduct(int id, int tableId, String thing){
        String tableName = null;
        switch (tableId){
            case 1:
                tableName = Const.PRODUCT_TABLE_NAME;
                database.editProductString(id, tableName, thing);
                break;
            case 2:
                tableName = Const.PRODUCT_TABLE_DESCRIPTION;
                database.editProductString(id, tableName, thing);
                break;
            case 3:
                tableName = Const.PRODUCT_TABLE_COST;
                database.editProductString(id, tableName, thing);
                break;
            case 4:
                tableName = Const.PRODUCT_TABLE_LEFT;
                database.editProductString(id, tableName, thing);
                break;
        }
    }

    public void deleteProduct(int id){
        database.deleteProduct(id);
    }
}