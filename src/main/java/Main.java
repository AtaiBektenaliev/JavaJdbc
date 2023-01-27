import java.sql.*;

public class Main {
    private final String URL = "jdbc:postgresql://localhost:5432/jdbc2";
    private final String USER = "postgres";
    private final String PASSWORD = "postgres";

    public Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }


    public void getVisitorsCount() {
        String SQL = "select cafe.name, dishes.name from orders\n" +
                "join dishes on dishes.id = orders.dishes_id\n" +
                "join cafe on cafe.id = orders.cafe_id where dishes.id = 2;\n";

        try (Connection conn = connect()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);


            while (rs.next()){
                System.out.printf(rs.getString(1) + " - ");
                System.out.println(rs.getString(2));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void getNameVisitors() {
        String SQL = "select dishes.name, dishes.price from dishes order by price desc";

        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SQL)) {
            while (rs.next()){
                System.out.printf(rs.getString(1) + " - ");
                System.out.println(rs.getInt(2));
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }


    public String setNewVisitors() {
        String SQL = "select dishes.name from dishes where id = 1;";
        String count = "";
        try (Connection conn = connect()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);
            rs.next();
            count = rs.getString(1);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return count;
    }

    public static void main(String[] args) {
        Main main = new Main();
        System.out.println();

        main.getVisitorsCount();

        main.getNameVisitors();

        System.out.println(main.setNewVisitors());
    }
}
