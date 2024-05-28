package console;

import model.Product;
import model.User;
import service.BalanceFunctions;
import service.ProductFunctions;
import service.UserFunctions;

import java.util.List;
import java.util.Scanner;

public class ConsoleConsumer {
    Scanner input = new Scanner(System.in);
    BalanceFunctions balanceFunctions = new BalanceFunctions();
    ProductFunctions productFunctions = new ProductFunctions();
    UserFunctions userFunctions = new UserFunctions();
    private int choose;
    private boolean loop = true;

    public ConsoleConsumer() {
    }

    public int getChoose() {
        return choose;
    }

    public void setChoose(int choose) {
        this.choose = choose;
    }

    public boolean getLoop() {
        return loop;
    }

    public void setLoop(boolean loop) {
        this.loop = loop;
    }

    /**
     * Для регистрации пользователя
     **/
    public void registration() {
        User user = new User();
        TextsForConsole.registrationCase1();
        switch (input.nextInt()) {
            case 1:
                System.out.print("Логин: ");
                user.setLoginUser(input.next());

                System.out.print("Пароль: ");
                user.setPasswordUser(input.next());

                userFunctions.logIn(user);
                break;
            case 2:
                userFunctions.signUp();
                this.registration();
                break;
            default:
                System.out.println("Ошибка");
                this.registration();
        }
    }

    /**
     * Основное окно, вызывается сразу после registration();
     **/
    public void mainWindow() {
        while (getLoop()) {
            TextsForConsole.cuiCase1();
            setChoose(input.nextInt());
            switch (getChoose()) {
                case 1:
                    windowProduct();
                    break;
                case 2:
                    TextsForConsole.cuiCase2();
                    switch (input.nextInt()) {
                        case 1:
                            TextsForConsole.cuiCase3();
                            break;
                        case 2:
                            System.out.print("Введите сумму: ");
                            balanceFunctions.topUpBalance(input.nextInt());
                            break;
                        case 3:
                            this.mainWindow();
                            break;
                        default:
                            System.out.println("Ошибка");
                            this.mainWindow();
                    }
                    break;
                case 3:
                    userFunctions.showHistory();
                    this.mainWindow();
                    break;
                case 4:
                    setLoop(false);
                    break;
                default:
                    System.out.println("Ошибка");
                    this.mainWindow();
            }
        }
    }

    /**
     * Один из основных окон, назначен для продуктов, необходим для работы mainWindow();
     **/
    public void windowProduct() {
        this.viewProducts();
        TextsForConsole.windowProductCase1();
        switch (input.nextInt()) {
            case 1:
                System.out.println("Напишите ID: ");
                this.buyProduct(input.nextInt());
                break;
            case 2:
                System.out.print("Напишите: ");
                this.lookForProduct(input.next());
                System.out.print("Что бы выйти назад нажмите любую кнопку: ");
                input.next();
                this.windowProduct();
                break;
            case 3:
                this.mainWindow();
                break;
            default:
                System.out.println("Ошибка");
                this.windowProduct();
        }
    }

    /**
     * Метод для просмотра все продуктов, необходим для работы метода windowProduct();
     **/
    public void viewProducts() {
        List<Product> products = productFunctions.productAsArray();
        for (Product product : products)
            TextsForConsole.productCase1(product);
    }

    /**
     * Метод для покупки продукта, необходим для работы метода windowProduct();
     **/
    public void buyProduct(int id) {
        productFunctions.buyProduct(id);
    }


    /**
     * Метод для пойска продукта, необходим для работы метода windowProduct();
     **/
    public void lookForProduct(String name) {
        List<Product> foundProducts = productFunctions.lookForAsArray(name);
        for (Product product : foundProducts)
            TextsForConsole.productCase1(product);
    }
}