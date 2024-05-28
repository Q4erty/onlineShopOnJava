import console.ConsoleConsumer;
import database.constant.Const;

/**
 * Запуск приложение
 **/
public class Main {
    public static void main(String... args) {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        System.out.println(Const.CUI_DETAIL_1);
        System.out.println("Добро пожаловать в магазин телефонов!");
        ConsoleConsumer consoleConsumer = new ConsoleConsumer();
        consoleConsumer.registration();
    }
}