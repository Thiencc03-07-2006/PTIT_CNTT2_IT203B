package flashsaleengine.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseConnectionManager {
    private static DatabaseConnectionManager instance;
    private static Connection connection;
    private static Properties properties;
    private static final Logger logger = Logger.getLogger(DatabaseConnectionManager.class.getName());

    private DatabaseConnectionManager() {
        loadProperties();
    }

    public static synchronized DatabaseConnectionManager getInstance() {
        if (instance == null) {
            instance = new DatabaseConnectionManager();
        }
        return instance;
    }

    private void loadProperties() {
        properties = new Properties();
        try (InputStream input = getClass().getClassLoader()
                .getResourceAsStream("database.properties")) {
            if (input == null) {
                logger.warning("Unable to find database.properties, using default values");
                setDefaultProperties();
            } else {
                properties.load(input);
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error loading database properties", e);
            setDefaultProperties();
        }
    }

    private void setDefaultProperties() {
        properties.setProperty("db.url", "jdbc:mysql://localhost:3307/flash_sale_db?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC");
        properties.setProperty("db.user", "root");
        properties.setProperty("db.password", "12345678");
        properties.setProperty("db.driver", "com.mysql.cj.jdbc.Driver");
    }

    public Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                Class.forName(properties.getProperty("db.driver"));
                connection = DriverManager.getConnection(
                        properties.getProperty("db.url"),
                        properties.getProperty("db.user"),
                        properties.getProperty("db.password")
                );
                logger.info("Database connection established successfully");
            } catch (ClassNotFoundException e) {
                logger.log(Level.SEVERE, "Database driver not found", e);
                throw new SQLException("Database driver not found", e);
            }
        }
        return connection;
    }

    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                logger.info("Database connection closed");
            } catch (SQLException e) {
                logger.log(Level.SEVERE, "Error closing database connection", e);
            }
        }
    }

    public void initializeDatabase() throws SQLException, IOException {
        try (Statement statement = getConnection().createStatement()) {
            // Read and execute SQL script
            InputStream inputStream = getClass().getClassLoader()
                    .getResourceAsStream("init.sql");
            if (inputStream == null) {
                throw new IOException("init.sql file not found");
            }

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(inputStream));
            StringBuilder sqlBuilder = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                sqlBuilder.append(line).append("\n");
                if (line.trim().endsWith(";")) {
                    String sql = sqlBuilder.toString();
                    if (!sql.trim().isEmpty()) {
                        try {
                            statement.execute(sql);
                        } catch (SQLException e) {
                            if (!e.getMessage().contains("already exists")) {
                                logger.log(Level.WARNING, "Error executing SQL: " + sql, e);
                            }
                        }
                    }
                    sqlBuilder = new StringBuilder();
                }
            }
            logger.info("Database initialized successfully");
        }
    }
}