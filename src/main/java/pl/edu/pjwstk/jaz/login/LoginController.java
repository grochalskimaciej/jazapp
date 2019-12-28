package pl.edu.pjwstk.jaz.login;

import pl.edu.pjwstk.jaz.auth.ProfileService;
import pl.edu.pjwstk.jaz.login.LoginRequest;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class LoginController{

    @Inject
    private LoginRequest loginRequest;

    @Inject
    private ProfileService profileService;

    public String login(){
        if(profileService.logIn(loginRequest.getUsername(), loginRequest.getPassword())){
            return "index";
        } else {
            System.out.println("ntu");
            FacesContext.getCurrentInstance().getExternalContext().getFlash()
                    .put("error message", "Incorrect username or password");
            return "/login.xhtml?faces-redirect=true";
        }
    }

//    static boolean validate(String username, String password) {
//
//            Statement stat;
//            Connection c = DbConnect.getConnection();
//
//            var passwordEncoder = new BCryptPasswordEncoder();
//
//            try {
//                assert c != null;
//                stat = c.createStatement();
//                ResultSet rs = stat.executeQuery("select * from jazapp");
//                while(rs.next()){
//                    String user = rs.getString("username");
//                    String pass = rs.getString("pass");
//
//                    if(passwordEncoder.matches(password, pass) && user.equals(username)){
//                        rs.close();
//                        c.close();
//                        return true;
//                    }
//                }
//                rs.close();
//                stat.close();
//                c.close();
//                return false;
//
//            } catch (Exception e) {
//                e.printStackTrace();
//                System.err.println(e.getClass().getName()+" : "+e.getMessage());
//            }
//            return false;
//    }
}