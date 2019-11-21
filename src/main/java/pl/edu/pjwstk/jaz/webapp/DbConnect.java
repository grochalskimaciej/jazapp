package pl.edu.pjwstk.jaz.webapp;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnect {

    public static Connection getConnection() {
        Connection c = null;

        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
              .getConnection("jdbc:postgresql://localhost:5432/jazapp", "jazapp", "jazapp!98");
            return c;
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+" : "+e.getMessage());
            return null;
        }
    }

    public static void close(Connection con) {
        try {
            con.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
