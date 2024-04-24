package Product;

import CUI.Console;
import Database.Const;
import Database.DatabaseHelper;
import User.Balance;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Product {
    private String nameProduct;
    private String descriptionProduct;
    private int cost;
    private int left;

    public Product() {
    }

    public Product(String nameProduct, String descriptionProduct, int cost, int left) {
        this.nameProduct = nameProduct;
        this.descriptionProduct = descriptionProduct;
        this.cost = cost;
        this.left = left;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public String getDescriptionProduct() {
        return descriptionProduct;
    }

    public void setDescriptionProduct(String descriptionProduct) {
        this.descriptionProduct = descriptionProduct;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getLeft() {
        return left;
    }

    public void setLeft(int left) {
        this.left = left;
    }

    public void windowProduct(){
        Console console = new Console();
        Scanner input = new Scanner(System.in);

        try {
            viewProducts();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

        System.out.print("""
                             1. Купить телефон
                             2. Пойск по названию
                             3. Назад
                             """);
        System.out.print("Выберите: ");

        switch (input.nextInt()){
            case 1:
                System.out.println("Напишите ID: ");
                try {
                    buyProduct(input.nextInt());
                } catch (ClassNotFoundException | SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            case 2:
                System.out.print("Напишите: ");
                lookForProduct(input.next());

                System.out.print("Что бы выйти назад нажмите любую кнопку: ");
                input.next();
                windowProduct();
                break;
            case 3:
                try {
                    console.CUI();
                } catch (ClassNotFoundException | SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
        }
    }

    public void viewProducts() throws SQLException, ClassNotFoundException {
        DatabaseHelper DBHelper = new DatabaseHelper();
        ResultSet resultSet = DBHelper.displayAllProduct();

        while (resultSet.next()){
            System.out.printf("%d. Name product: %s\n   CPU: %s\n   Cost: %d\n   Left: %d\n\n",
                    resultSet.getInt(Const.PRODUCT_TABLE_ID),
                    resultSet.getString(Const.PRODUCT_TABLE_NAME),
                    resultSet.getString(Const.PRODUCT_TABLE_DESCRIPTION),
                    resultSet.getInt(Const.PRODUCT_TABLE_COST),
                    resultSet.getInt(Const.PRODUCT_TABLE_LEFT));
        }
    }

    public void buyProduct(int ID) throws SQLException, ClassNotFoundException {
        DatabaseHelper DBHelper = new DatabaseHelper();
        Balance balanceForBuy = new Balance();
        int[] array2 = DBHelper.dataProduct(ID);

        if (array2[0] <= DBHelper.currentBalance()) {
            if (array2[1] > 0) {
                balanceForBuy.setSumma(DBHelper.currentBalance() - array2[0]);
                DBHelper.updateBalance(balanceForBuy);
                DBHelper.updateLostProduct(ID, array2[1] - 1);
                System.out.println("Вы успешно купили телефон");
            } else {
                System.out.println("Нет в наличий");
            }
        }
        else {
            System.out.println("У вас не хватает средств");
        }
    }

    public void lookForProduct(String name){
        DatabaseHelper databaseHelper = new DatabaseHelper();
        ResultSet result = databaseHelper.lookForProductDB(name);
        try {
            while (result.next()){
                System.out.printf("%d. Name product: %s\n   CPU: %s\n   Cost: %d\n   Left: %d\n\n",
                        result.getInt(Const.PRODUCT_TABLE_ID),
                        result.getString(Const.PRODUCT_TABLE_NAME),
                        result.getString(Const.PRODUCT_TABLE_DESCRIPTION),
                        result.getInt(Const.PRODUCT_TABLE_COST),
                        result.getInt(Const.PRODUCT_TABLE_LEFT));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}