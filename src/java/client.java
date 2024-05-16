import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class client {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/your_database_name";
    private static final String JDBC_USER = "your_username";
    private static final String JDBC_PASSWORD = "your_password";

    public static void main(String[] args) {
        // Inserting client data into the database
        insertClient("John Doe", "john@example.com", "123-456-7890");

        // Fetching registered clients from the database
        List<ClientData> clients = fetchClients();
        clients.forEach(System.out::println);
    }

    public static void insertClient(String name, String email, String phone) {
        try {
            Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
            String sql = "INSERT INTO clients (name, email, phone) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, phone);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
            System.out.println("Client information saved successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<ClientData> fetchClients() {
        List<ClientData> clients = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM clients");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String phone = resultSet.getString("phone");
                clients.add(new ClientData(id, name, email, phone));
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clients;
    }

    public static class ClientData {
        private int id;
        private String name;
        private String email;
        private String phone;

        public ClientData(int id, String name, String email, String phone) {
            this.id = id;
            this.name = name;
            this.email = email;
            this.phone = phone;
        }

        // Getters and setters
        // You can also override toString() method to print client data in a more readable format
        // ...
    }
}
