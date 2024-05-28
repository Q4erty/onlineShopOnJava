package console;

import service.ProductFunctions;
import service.UserFunctions;

import java.util.Scanner;

public class ConsoleSeller {
    Scanner input = new Scanner(System.in);
    ProductFunctions productFunctions = new ProductFunctions();
    UserFunctions userFunctions = new UserFunctions();
    ConsoleConsumer consoleConsumer = new ConsoleConsumer();

    /**
     * Основнон окно продовца
     **/
    public void sellerMainWindow() {
        boolean loop = true;
        while (loop) {
            TextsForConsole.sellerMainWindowCase1();
            switch (input.nextInt()) {
                case 1:
                    consoleConsumer.viewProducts();
                    break;
                case 2:
                    addProductCUI();
                    break;
                case 3:
                    editProductCUI();
                    sellerMainWindow();
                    break;
                case 4:
                    deleteProduct();
                    sellerMainWindow();
                    break;
                case 5:
                    userFunctions.allHistory();
                    sellerMainWindow();
                case 6:
                    loop = false;
                    break;
                default:
                    System.out.println("Ошибка");
                    this.sellerMainWindow();
            }
        }
    }

    /**Для добавления продукта**/
    public void addProductCUI() {
        productFunctions.addProduct();
        System.out.println("Продукт успешно добавлен");
    }

    /**Редактирование продукта**/
    public void editProductCUI() {
        consoleConsumer.viewProducts();
        System.out.print("Напишите ID продукта: ");
        int id = input.nextInt();
        TextsForConsole.sellerEditWindow();
        int tableId = input.nextInt();
        System.out.println("Напишите новый атрибут: ");
        productFunctions.editProduct(id, tableId, input.next());
        System.out.println("Продукт упешно изменен");
    }

    /**Удаление продукта**/
    public void deleteProduct() {
        System.out.print("Напишите ID: ");
        productFunctions.deleteProduct(input.nextInt());
        System.out.println("Продукт успешно удален");
    }


}
