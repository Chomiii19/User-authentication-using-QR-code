package dev.lpa;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        boolean isRunning = true;

        while(isRunning){
            int userChoice = menu();

            switch (userChoice) {
                case 1 -> {
                    if (SignIn.signIn()) {
                        System.out.println("Successfully logged in!");
                        EmployeeController.employeeMenu();
                    }
                }
                case 2 -> System.out.println("Option 2 selected.");
                case 3 -> isRunning = false;
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static int menu() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel label = new JLabel(
                "<html>[1] - Manage Employee Accounts<br>" +
                        "[2] - Employee Authentication<br>" +
                        "[3] - Exit<br>Enter Choice:</html>");
        JTextField choiceField = new JTextField(3);

        panel.add(label);
        panel.add(choiceField);

        int option = JOptionPane.showConfirmDialog(
                null,
                panel,
                "Menu",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);

        String userChoice = "";

        if (option == JOptionPane.OK_OPTION) {
            userChoice = choiceField.getText();
        } else{
            userChoice = "3";
        }

        try {
            return Integer.parseInt(userChoice);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid number.");
            return -1;
        }
    }
}