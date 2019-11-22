package pl.edu.pjwstk.jaz.webapp;

import org.springframework.security.crypto.bcrypt.BCrypt;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

@Named
@RequestScoped
public class RegisterController {

    //Funcja wpisujaca dane z formularza do bazy zwraca boolean (udalo sie lub nie)
    static boolean insertData(String n, String s, String u, String p, String e, String d) {

        boolean notExists = checkData(u);

        Statement stat;
        Connection c = DbConnect.getConnection();

        if(notExists)
        {
            String BCryptPassw = BCrypt.hashpw(p,BCrypt.gensalt());

            try {
                assert c != null;
                stat = c.createStatement();
                stat.executeUpdate("INSERT INTO jazapp VALUES ('"+n+"','"+s+"','"+u+"','"+BCryptPassw+"','"+e+"','"+d+"')");

                c.close();
                stat.close();
                return true;
            } catch (Exception er) {
                er.printStackTrace();
                System.err.println(er.getClass().getName() + " : " + er.getMessage());
            }
        }
        return false;
    }

    //Funkcja sprawdzi czy username podany w rejestracji jest juz w bazie danych, jesli jest to funkcja insertData()
    //nie wpisze go do bazy i wyswietli odpowiedni komunikat
    private static boolean checkData(String u){

        Statement stat;
        Connection c = DbConnect.getConnection();

        try {
            assert c != null;
            stat = c.createStatement();
            ResultSet rs = stat.executeQuery("select username from jazapp");

            while (rs.next()) {
                String username = rs.getString("username");

                if (username.equals(u)) {
                    rs.close();
                    c.close();

                    FacesContext.getCurrentInstance().addMessage(
                            null,
                            new FacesMessage(FacesMessage.SEVERITY_WARN,
                                    "User with this username already exists",
                                    "Please enter another username"));
                    return false;
                }
            }
            rs.close();
            stat.close();
            c.close();
            return true;
        }catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+" : "+e.getMessage());
        }
        return false;
    }
}

