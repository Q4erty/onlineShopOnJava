package CUI;

import Database.DatabaseHelper;
import Product.Product;
import User.Seller;

import java.sql.SQLException;
import java.util.Scanner;

public class ConsoleSeller {
    public void sellerCUI() throws SQLException, ClassNotFoundException {
        Scanner input = new Scanner(System.in);
        Product product = new Product();
        boolean loop = true;
        while (loop) {
            System.out.println("""
                    Добро пожаловать в магазин
                     1. Посмотреть список продкутов
                     2. Добавить продукт
                     3. Изменить продукт
                     4. Выйти""");
            System.out.print("Выберите: ");
            switch (input.nextInt()){
                case 1:
                    product.viewProducts();
                    break;
                case 2:
                    addProductCUI();
                    break;
                case 3:
                    System.out.println("In process...");
                    break;
                case 4:
                    loop = false;
                    break;
            }
        }
    }

    public void addProductCUI(){
        Seller add = new Seller();
        Product productForAdd = new Product();
        DatabaseHelper dbhelper = new DatabaseHelper();
        Scanner writeProduct = new Scanner(System.in);

        System.out.print("Названия продукта: ");
        productForAdd.setNameProduct(writeProduct.nextLine());

        System.out.print("Процессор: ");
        productForAdd.setDescriptionProduct(writeProduct.nextLine());

        System.out.print("Цена: ");
        productForAdd.setCost(writeProduct.nextInt());

        System.out.print("Количество: ");
        productForAdd.setLeft(writeProduct.nextInt());

        dbhelper.addProduct(productForAdd);
        System.out.println("Продукт успешно добавлен");
    }
}
