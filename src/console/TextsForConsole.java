package console;

import database.child.UserDatabase;
import model.Product;

/**Тексты для консолья**/
public class TextsForConsole {
    static UserDatabase db = new UserDatabase();
    public static void cuiCase1(){
        System.out.println("""
                     1. Список всех телефонов
                     2. Баланс
                     3. История
                     4. Выйти\
                    """);

        System.out.print("Выберите: ");
    }
    public static void cuiCase2(){
        System.out.println("""
                            1. Посмотреть баланс
                            2. Пополнить баланс
                            3. Назад""");
    }
    public static void cuiCase3() {
        System.out.println("У вас в балансе: " + db.currentBalance() + "тг");
    }
    public static void registrationCase1(){
        System.out.println(" 1. Вход\n 2. Регистрация");
        System.out.print("Выберите: ");
    }

    public static void windowProductCase1(){
        System.out.print("""
                1. Купить телефон
                2. Пойск по названию
                3. Назад
                """);
        System.out.print("Выберите: ");
    }

    public static void productCase1(Product product){
        System.out.printf("%s. Name product: %s\n   CPU: %s\n   Cost: %s\n   Left: %s\n\n",
                product.getId(),
                product.getNameProduct(),
                product.getDescriptionProduct(),
                product.getCost(),
                product.getLeft());
    }

    public static void sellerMainWindowCase1(){
        System.out.println("""
                    Добро пожаловать в магазин
                     1. Посмотреть список продкутов
                     2. Добавить продукт
                     3. Изменить продукт
                     4. Удалить продукт
                     5. История
                     6. Выйти""");
        System.out.print("Выберите: ");
    }
    public static void sellerEditWindow(){
        System.out.println("""
                Атрибут какой таблицы хотите изменить:
                  1. Имя
                  2. Описание
                  3. Цена
                  4. Количество""");
    }
}
