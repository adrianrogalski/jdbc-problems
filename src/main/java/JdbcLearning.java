import java.sql.*;

public class JdbcLearning {
    public static void main(String[] args) {
        // proba nawiazania polaczenia z baza danych poprzez try with resources aby mieć pewnośc ze polaczenie
        // zostanie zawsze zamkniete
        // definicja polaczenia do bazy danychg za pomoca obiektu klasy Connection i metody statycznej getConnection z komponentu DriverMenager
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels",
                "root", "Peyotl99")) {
            String query = "SELECT * FROM classicmodels.products;";

            // Tworze obiekt "opakowanie" ktory jest gotowy na wykonanie zapytania
            Statement stmt = conn.createStatement();

            // wykonanie zapytania i przypisanie zwroconej wartosci tabeli products do setu
            ResultSet result = stmt.executeQuery(query);

            //alternatywna PreparedStatement stmt = conn.prepareStatement(query);
            // stmt.executeQuery()

            // result zbior wszystkich rekorodow bazy albo jak w tym przypadku zbior rekordow kolumny products
            // pojedynczy result.next() -> pojedynczy wiersz bbazy danych
            while (result.next()) {
                // TU MOGE WYKONYWAC OPERACJE NA DANYCH
                System.out.println(result.getString("productCode") + " " + result.getString("productName"));
                // alternatywnie System.out.println(result.getString(1) + " " + result.getString("productName"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
