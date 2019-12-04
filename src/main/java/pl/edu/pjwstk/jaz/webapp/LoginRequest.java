package pl.edu.pjwstk.jaz.webapp;

import pl.edu.pjwstk.jaz.auth.ProfileService;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.Entity;
import javax.servlet.http.HttpSession;

@Named
@RequestScoped
public class LoginRequest{

    @Inject
    private ProfileService profileService;

    private String username;
    private String password;

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

//    //validate login
//    public String ValidateUsernameAndPassword() {
//        boolean valid = LoginController.validate(username, password);
//        if (valid) {
//            HttpSession session = SessionUtils.getSession();
//            session.setAttribute("username", username);
//            return "index";
//        } else {
//            FacesContext.getCurrentInstance().addMessage(
//                    null,
//                    new FacesMessage(FacesMessage.SEVERITY_WARN,
//                            "Incorrect Username and Passowrd",
//                            "Please enter correct username and Password"));
//        }
//        return "login";
//    }

    //logout event, invalidate session
    public String logout() {
        profileService.logout();

        return "login.xhtml?faces-redirect=true";
    }
}


