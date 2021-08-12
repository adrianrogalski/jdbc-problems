import java.sql.*;

public class Movies {
    private final static String URL = "jdbc:mysql://localhost:3306/moviesdb";
    private final static String USER = "root";
    private final static String PASSWORD = "admin123";

    public static void main(String[] args) {
        try (
            Connection connection = DriverManager.getConnection(
                    URL, USER, PASSWORD)) {
            String deleteQuery = "DROP TABLE IF EXISTS movies";
            PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery);
            deleteStatement.execute();
            String createTableQuery = "CREATE TABLE IF NOT EXISTS movies (id INTEGER AUTO_INCREMENT PRIMARY KEY, title VARCHAR(255), genre VARCHAR(255), yearOfRelease INTEGER);";
            PreparedStatement statement = connection.prepareStatement(createTableQuery);
            statement.execute();


            String insertRecord = "INSERT INTO movies (title, genre, yearOfRelease) values(?, ?, ?);";
            PreparedStatement statement2 = connection.prepareStatement(insertRecord);
            statement2.setString(1, "Django");
            statement2.setString(2, "Drama");
            statement2.setInt(3, 2012);
            statement2.execute();
            statement2.setString(1, "Kill Bill");
            statement2.setString(2, "Drama");
            statement2.setInt(3, 2005);
            statement2.execute();
            statement2.setString(1, "Kill Bill 2");
            statement2.setString(2, "Drama");
            statement2.setInt(3, 2004);
            statement2.execute();

            String updateRecord = "UPDATE movies SET genre=? WHERE genre=?;";
            PreparedStatement statement3 = connection.prepareStatement(updateRecord);
            statement3.setString(1, "Action");
            statement3.setString(2, "Drama");
            statement3.executeUpdate();

            String deleteRecord = "DELETE FROM movies WHERE id=?";
            PreparedStatement statement4 = connection.prepareStatement(deleteRecord);
            statement4.setString(1, "3");
            statement4.executeUpdate();


            String showTable = "SELECT * FROM movies";
            PreparedStatement statement5 = connection.prepareStatement(showTable);
            ResultSet result = statement5.executeQuery();
            while (result.next()) {
                System.out.println(result.getString("title") + " " + result.getString("genre") + " " + result.getInt("yearOfRelease"));
            }
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
