package User;

import Database.DatabaseHelper;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Balance {
    private int summa;

    public Balance() {
    }

    public Balance(int summa) {
        this.summa = summa;
    }

    public int getSumma() {
        return summa;
    }

    public void setSumma(int summa) {
        this.summa = summa;
    }

    public void topUpBalance(){
        DatabaseHelper DBHelper = new DatabaseHelper();
        Scanner input = new Scanner(System.in);
        Balance balanceForUpdate = new Balance();

        System.out.println("Введите сумму: ");
        try {
            int balanceInput = input.nextInt();
            if (balanceInput >= 0) {
                balanceForUpdate.setSumma(balanceInput + DBHelper.currentBalance());
                DBHelper.updateBalance(balanceForUpdate);
                System.out.println("Операция выполнена успешно, теперь у вас " + DBHelper.currentBalance() + "тг");
            } else {
                System.out.println("Введите корректную сумму");
                topUpBalance();
            }
        } catch (InputMismatchException e) {
            System.out.println("Введите корректную сумму");
            topUpBalance();
        }
    }
}