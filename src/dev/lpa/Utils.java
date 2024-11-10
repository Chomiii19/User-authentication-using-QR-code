package dev.lpa;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;
import com.mysql.cj.jdbc.MysqlDataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class Utils {
    private static Connection connection = null;
    private Utils() {}

    public static Properties loadProperties(String filePath) {
        Properties props = new Properties();
        try {
            props.load(Files.newInputStream(Path.of(filePath)));
        } catch (IOException e) {
            throw new RuntimeException("Failed to load properties file.", e);
        }
        return props;
    }

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            Properties props = Utils.loadProperties("config.properties");
            MysqlDataSource dataSource = new MysqlDataSource();
            dataSource.setServerName(props.getProperty("serverName"));
            dataSource.setPort(Integer.parseInt(props.getProperty("port")));
            dataSource.setDatabaseName(props.getProperty("databaseName"));
            connection = dataSource.getConnection(props.getProperty("user"), props.getProperty("password"));
        }
        return connection;
    }

    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
