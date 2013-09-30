package com.shlapak.yaroslav.filesort;


/*
 * класс "Счет" - хранит деньги
 */
public class Account {

    private double balance;

    //конструктор
    public Account(double bal) {
        balance = bal;
    }

    //получить баланс
    public double getBalance() {
        return balance;
    }

    //добавить деньги
    public void deposit(double money) {
        balance = balance + money;
    }

    // снять деньги
    public void withdraw(int money) {
        balance = balance - money;
    }
}
