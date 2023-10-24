import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQLExample {

    public static void main(String[] args) {
        String jdbcURL = "jdbc:mysql://localhost:3306/your_database_name";
        String username = "your_username";
        String password = "your_password";

        try {
            // Connect to the MySQL database
            Connection connection = DriverManager.getConnection(jdbcURL, username, password);

            // Create a table
            String createTableSQL = "CREATE TABLE IF NOT EXISTS users (id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(255), email VARCHAR(255))";
            PreparedStatement createTableStatement = connection.prepareStatement(createTableSQL);
            createTableStatement.execute();

            // Insert data into the table
            String insertDataSQL = "INSERT INTO users (name, email) VALUES (?, ?)";
            PreparedStatement insertDataStatement = connection.prepareStatement(insertDataSQL);
            insertDataStatement.setString(1, "John Doe");
            insertDataStatement.setString(2, "johndoe@example.com");
            insertDataStatement.executeUpdate();

            // Retrieve data from the table
            String retrieveDataSQL = "SELECT * FROM users";
            PreparedStatement retrieveDataStatement = connection.prepareStatement(retrieveDataSQL);
            ResultSet resultSet = retrieveDataStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                System.out.println("ID: " + id + ", Name: " + name + ", Email: " + email);
            }

            // Close the resources
            resultSet.close();
            retrieveDataStatement.close();
            insertDataStatement.close();
            createTableStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
