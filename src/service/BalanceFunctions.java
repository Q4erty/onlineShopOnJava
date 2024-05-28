package service;

import database.child.UserDatabase;
import model.Balance;

import java.util.InputMismatchException;
import java.util.Scanner;

public class BalanceFunctions {
    UserDatabase userDatabase = new UserDatabase();
    Scanner input = new Scanner(System.in);


    /**Метод для пополнения баланса**/
    public void topUpBalance(int summa) {
        Balance balanceForUpdate = new Balance();
        try {
            if (summa >= 0) {
                balanceForUpdate.setSumma(summa + userDatabase.currentBalance());
                userDatabase.updateBalance(balanceForUpdate);
                System.out.println("Операция выполнена успешно, теперь у вас " + userDatabase.currentBalance() + "тг");
            } else {
                System.out.println("Введите корректную сумму");
                topUpBalance(input.nextInt());
            }
        } catch (InputMismatchException e) {
            System.out.println("Введите корректную сумму");
            topUpBalance(input.nextInt());
        }
    }
}
