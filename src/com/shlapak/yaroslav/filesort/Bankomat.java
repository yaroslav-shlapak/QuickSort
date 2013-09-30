package com.shlapak.yaroslav.filesort;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Bankomat implements ActionListener {

    //компоненты GUI
    private JFrame frame;
    private JPanel mainPanel;
    private JLabel jlblBalanceCaption, jlblBalance, jlblMoneyCaption;
    private JTextField jtxtMoney;
    private JButton jbtnWithdraw, jbtnDeposit;
    
    //изначально баланс равен 100
    private Account account = new Account(100.00);

    //конструктор
    public Bankomat() {
        //контейнеры
        frame = new JFrame("Банкомат");
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(3, 2)); //3 строки 2 столбца

        frame.getContentPane().add(mainPanel);

        addComponents();

        //свойства фрейма
        frame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(200, 150);
        frame.setLocation(300, 300);
        frame.setVisible(true);

        //показать баланс в формате в текстовом окне
        displayBalance();
    }

    // добавление всех необходимых компонентов
    private void addComponents() {
        jlblBalanceCaption = new JLabel("Баланс: \u20AC  ");// \u20AC - символ валюты Евро
        jlblBalance = new JLabel(" ");
        jlblMoneyCaption = new JLabel("Деньги: \u20AC  ");
        jtxtMoney = new JTextField();
        jbtnWithdraw = new JButton("Снять");
        jbtnDeposit = new JButton("Пополнить");

        //выравнивание
        jlblBalanceCaption.setHorizontalAlignment(JLabel.RIGHT);
        jlblMoneyCaption.setHorizontalAlignment(JLabel.RIGHT);

        //зеленый цвет на черном фоне
        jlblBalance.setForeground(Color.GREEN);
        jlblBalance.setBackground(Color.BLACK);
        jlblBalance.setOpaque(true);

        //добавление компонентов на панель
        mainPanel.add(jlblBalanceCaption);
        mainPanel.add(jlblBalance);
        mainPanel.add(jlblMoneyCaption);
        mainPanel.add(jtxtMoney);
        mainPanel.add(jbtnWithdraw);
        mainPanel.add(jbtnDeposit);

        //слушатели событий
        jbtnWithdraw.addActionListener(this);
        jbtnDeposit.addActionListener(this);
    }

    //перехват нажатия кнопки
    @Override
    public void actionPerformed(ActionEvent e) {

        //нажали кнопку "Снять"
        if (e.getSource() == jbtnWithdraw) {
            withdraw();

            //нажали кнопку "Пополнить"
        } else if (e.getSource() == jbtnDeposit) {
            deposit();
        }

    }

    //показывает баланс
    private void displayBalance() {
        //конвертировать число в строку
        String balance = String.format("%.2f", account.getBalance());

        //показать результат в текстовом поле
        jlblBalance.setText(balance);
    }

    //пополнение
    private void deposit() {
        // проверка - пополнять можно минимум 100
        if (isValidDepsoit()) {
            //получить введенные данные
            double money = Double.parseDouble(jtxtMoney.getText());

            //добавить деньги на счет
            account.deposit(money);

            //сообщение пользователю
            JOptionPane.showMessageDialog(frame, "Пополнение прошло успешно", "Сообщение", JOptionPane.INFORMATION_MESSAGE);

            // пересчет баланса
            displayBalance();
        }
    }

    //снятие
    private void withdraw() {
        // проверка на снятие суммы
        if (isValidWithdrawal()) {
            //получить введенные данные
            int money = Integer.parseInt(jtxtMoney.getText());

            //вычесть деньги со счета
            account.withdraw(money);

            //сообщение пользователю
            JOptionPane.showMessageDialog(frame, "Снятие прошло успешно", "Сообщение", JOptionPane.INFORMATION_MESSAGE);

            // пересчет баланса
            displayBalance();
        }
    }

    //проверка суммы для пополнения
    private boolean isValidDepsoit() {
        String message = "";

        //если не число
        if (!isDouble(jtxtMoney.getText())) {
            message = "Введите число";

            //если < 100
        } else if (Double.parseDouble(jtxtMoney.getText()) < 100.0) {
            message = "Минимум для пополнения: 100";

            //если > 500
        } else if (Double.parseDouble(jtxtMoney.getText()) > 500.0) {
            message = "Максимум для пополнения: 500";

            // если все ок
        } else {
            return true;
        }

        //сообщение пользователю об ошибке
        JOptionPane.showMessageDialog(frame, message, "Ошибка", JOptionPane.WARNING_MESSAGE);
        jtxtMoney.requestFocus();
        jtxtMoney.selectAll();
        return false;
    }

    //проверку на сумму снятия
    private boolean isValidWithdrawal() {
        String message = "";

        //если не число
        if (!isInteger(jtxtMoney.getText())) {
            message = "Введите число";

            //если < 10
        } else if (Integer.parseInt(jtxtMoney.getText()) < 10) {
            message = "Минимум для снятия: 10";

            //если > 250
        } else if (Integer.parseInt(jtxtMoney.getText()) > 250) {
            message = "Максимум для снятия: 250";

            // если не кратно 10
        } else if (Integer.parseInt(jtxtMoney.getText()) % 10 != 0) {
            message = "Сумма должна быть кратной 10";

            // недостаточно средств
        } else if (account.getBalance() - Integer.parseInt(jtxtMoney.getText()) < 0) {
            message = "Недостаточно средств";

            //если все ок
        } else {
            return true;
        }

        //сообщение пользователю
        JOptionPane.showMessageDialog(frame, message, "Ошибка", JOptionPane.WARNING_MESSAGE);
        jtxtMoney.requestFocus();
        jtxtMoney.selectAll();
        return false;
    }

    //проверка строки на тип Double
    private boolean isDouble(String num) {
        try {
            Double.parseDouble(num);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    //проверка строки на тип Integer
    private boolean isInteger(String num) {
        try {
            Integer.parseInt(num);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
