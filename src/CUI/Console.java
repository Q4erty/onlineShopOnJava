package CUI;

import java.sql.SQLException;
import java.util.Scanner;
import Database.DatabaseHelper;
import Product.Product;
import User.User;
import User.Balance;

public class Console {
    private int choose;
    private boolean loop = true;

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

    public void CUI() throws SQLException, ClassNotFoundException {
        Scanner input = new Scanner(System.in);
        DatabaseHelper db = new DatabaseHelper();
        Balance balance = new Balance();

        while (getLoop()){
            System.out.println("""
                     1. Список всех телефонов
                     2. Баланс
                     3. Выйти\
                    """);

            System.out.print("Выберите: ");
            setChoose(input.nextInt());

            switch (getChoose()){
                case 1:
                    Product product = new Product();
                    product.windowProduct();
                    break;
                case 2:
                    System.out.println("""
                                     1. Посмотреть баланс
                                     2. Пополнить баланс
                                     3. Назад""");
                    switch (input.nextInt()){
                        case 1:
                            System.out.println("У вас в балансе: " + db.currentBalance() + "тг");
                            break;
                        case 2:
                            balance.topUpBalance();
                            break;
                        case 3:
                            CUI();
                            break;
                    }
                    break;
                case 3:
                    setLoop(false);
                    break;
            }
        }
    }

    public void registration(){
        User userConsole = new User();
        Scanner input = new Scanner(System.in);

        System.out.println(" 1. Вход\n 2. Регистрация");
        System.out.print("Выберите: ");
        switch (input.nextInt()){
            case 1:
                userConsole.logIn();
                break;
            case 2:
                userConsole.signUp();
                break;
        }
    }
}