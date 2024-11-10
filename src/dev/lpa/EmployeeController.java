package dev.lpa;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EmployeeController {

    public static void employeeMenu() {
        boolean isRunning = true;
        while (isRunning) {
            int userChoice = getUserChoice();

            if (userChoice != -1) {
                switch (userChoice) {
                    case 1 -> viewEmployeeList();
                    case 2 -> createEmployeeAccount();
                    case 3 -> updateEmployeeAccount();
                    case 4 -> deleteEmployeeAccount();
                    case 5 -> isRunning = false;
                    default -> System.out.println("Invalid choice. Please try again.");
                }
            }
        }
    }

    private static int getUserChoice() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel label = new JLabel(
                "<html>[1] - View list of employees<br>" +
                        "[2] - Create an employee account<br>" +
                        "[3] - Update an employee account<br>" +
                        "[4] - Delete an employee account<br>" +
                        "[5] - Log out<br>Enter Choice:</html>");
        JTextField choiceField = new JTextField(3);

        panel.add(label);
        panel.add(choiceField);

        int option = JOptionPane.showConfirmDialog(
                null,
                panel,
                "Menu",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);

        if (option == JOptionPane.OK_OPTION) {
            try {
                return Integer.parseInt(choiceField.getText());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter a number.");
            }
        }
        return -1;
    }

    private static void viewEmployeeList(){}

    private static void createEmployeeAccount() {
        try (Connection connection = Utils.getConnection()) {
            String sql = "INSERT INTO employees (employee_name, employee_birthdate, employee_role, employee_password) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);

            // Collecting data from the user
            String name = JOptionPane.showInputDialog("Enter employee name:");
            String birthday = JOptionPane.showInputDialog("Enter birthday (YYYY-MM-DD):");
            String role = JOptionPane.showInputDialog("Enter role (admin or employee):");
            String password = JOptionPane.showInputDialog("Enter password:");

            // Setting the values for the prepared statement
            statement.setString(1, name);
            statement.setString(2, birthday);
            statement.setString(3, role);
            statement.setString(4, password);

            // Execute the insert
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Employee account created successfully.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void updateEmployeeAccount() {
        try (Connection connection = Utils.getConnection()) {
            String sql = "UPDATE employees SET employee_name = ?, employee_birthdate = ?, employee_role = ?, employee_password = ? WHERE employee_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            // Collecting data from the user
            int id = Integer.parseInt(JOptionPane.showInputDialog("Enter employee ID to update:"));
            String name = JOptionPane.showInputDialog("Enter new employee name:");
            String birthday = JOptionPane.showInputDialog("Enter new birthday (YYYY-MM-DD):");
            String role = JOptionPane.showInputDialog("Enter new role (admin or employee):");
            String password = JOptionPane.showInputDialog("Enter new password:");

            // Setting the values for the prepared statement
            statement.setString(1, name);
            statement.setString(2, birthday);
            statement.setString(3, role);
            statement.setString(4, password);
            statement.setInt(5, id);

            // Execute the update
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Employee account updated successfully.");
            } else {
                System.out.println("Employee not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void deleteEmployeeAccount() {
        try (Connection connection = Utils.getConnection()) {
            String sql = "DELETE FROM employees WHERE employee_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            // Collecting data from the user
            int id = Integer.parseInt(JOptionPane.showInputDialog("Enter employee ID to delete:"));

            // Setting the value for the prepared statement
            statement.setInt(1, id);

            // Execute the delete
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Employee account deleted successfully.");
            } else {
                System.out.println("Employee not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
