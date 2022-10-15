package collection;

import java.sql.*;

public class DbConnector {
    private Connection connection;
    private static final String URL = "jdbc:postgresql://127.0.0.1:5432/studs";
    private static final String USER = "s336423";
    private static final String PASSWORD = "85pUuddUFa3sac42";

    public DbConnector() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.err.println("Error while connecting to DB: " + e.getMessage());
            System.exit(1);
        }
    }

    public PreparedStatement getStatement(String sql) throws SQLException {
        return connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
    }

    public static long getInsertId(PreparedStatement statement) throws SQLException {
        try (ResultSet keys = statement.getGeneratedKeys()) {
            if (keys.next()) {
                return keys.getInt(keys.findColumn("id"));
            } else {
                throw new SQLException("Creating failed, no id obtained!");
            }
        }
    }
}
