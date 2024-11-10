package dev.lpa;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SignIn {

    public static boolean signIn() {
        boolean result = false;
        try (var connection = Utils.getConnection();
             Statement statement = connection.createStatement()) {
            result = signInAdmin(statement);
           
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Database connection failed: " + e.getMessage());
        }
        return result;
    }

    private static boolean signInAdmin(Statement statement) throws SQLException {
        String username = JOptionPane.showInputDialog(null, "Enter username");
        JPasswordField pf = new JPasswordField();
        int okCxl = JOptionPane.showConfirmDialog(null, pf, "Enter password", JOptionPane.OK_CANCEL_OPTION);

        if (okCxl == JOptionPane.OK_OPTION) {
            String password = new String(pf.getPassword());
            String query = "SELECT employee_role FROM employees WHERE employee_name = '" + username + "' AND employee_password = '" + password + "'";

            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                String role = resultSet.getString("employee_role");
                if(role.equals("admin")) return true;
                else JOptionPane.showMessageDialog(null, "You are not authorized to access this feature.");
            } else {
                JOptionPane.showMessageDialog(null, "Invalid username or password.");
            }
        }
        return false;
    }
}
