package dev.lpa;


import javax.swing.*;

public class EmployeeController {

    public static void employeeMenu(){
        boolean isRunning = true;
        while (isRunning){
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

            JLabel label = new JLabel(
                    "<html>[1] - Create an employee account<br>" +
                    "[2] - Update an employee account<br>" +
                    "[3] - Delete an employee account<br>" +
                    "[4] - Log out<br>Enter Choice:</html>");
            JTextField choiceField = new JTextField(3);

            panel.add(label);
            panel.add(choiceField);

            int option = JOptionPane.showConfirmDialog(
                    null,
                    panel,
                    "Menu",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE);

            int userChoice;
            if (option == JOptionPane.OK_OPTION) {
                userChoice = Integer.parseInt(choiceField.getText());

                switch(userChoice){
                    case 1 -> createEmployeeAccount();
                    case 2 -> updateEmployeeAccount();
                    case 3-> deleteEmployeeAccount();
                    case 4-> isRunning = false;
                    default -> System.out.println("Invalid choice. Please try again.");
                }
            }
        }
    }

    public static void createEmployeeAccount(){
        System.out.println("CREATED AN ACCOUNT");
    }

    public static void updateEmployeeAccount(){
        System.out.println("UPDATED AN ACCOUNT");
    }

    public static void deleteEmployeeAccount(){
        System.out.println("DELETED AN ACCOUNT");
    }
}
