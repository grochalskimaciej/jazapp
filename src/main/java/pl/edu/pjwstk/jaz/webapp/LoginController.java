package pl.edu.pjwstk.jaz.webapp;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.sql.*;

import java.sql.Statement;

@Named
@RequestScoped
public class LoginController{

    static boolean validate(String username, String password) {

            Statement stat;
            Connection c = DbConnect.getConnection();

            try {
                assert c != null;
                stat = c.createStatement();
                ResultSet rs = stat.executeQuery("select * from jazapp");
                while(rs.next()){
                    String user = rs.getString("username");
                    String pass = rs.getString("pass");

                    if (user.equals(username) && pass.equals(password)){
                        rs.close();
                        c.close();
                        return true;
                    }
                }
                rs.close();
                stat.close();
                c.close();
                return false;

            } catch (Exception e) {
                e.printStackTrace();
                System.err.println(e.getClass().getName()+" : "+e.getMessage());
            }
            return false;
    }
}