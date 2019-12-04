package pl.edu.pjwstk.jaz.register;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Named
@RequestScoped
public class RegisterRequest {

        private String firstName;
        private String lastName;
        private String username;
        private String password;
        private String email;
        private String date;

        private String confirmPassword;

        public String getFirstName() { return firstName; }
        public void setFirstName(String firstName) { this.firstName = firstName; }

        public String getLastName() { return lastName; }
        public void setLastName(String lastName) { this.lastName = lastName; }

        public String getUsername() { return username; }
        public void setUsername(String username) { this.username = username; }

        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }

        public String getConfirmPassword() { return confirmPassword; }
        public void setConfirmPassword(String confirmPassword) { this.confirmPassword = confirmPassword; }

        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }

        public String getDate() { return date; }
        public void setDate(String date) { this.date = date; }
}

//        //Funkcja sprawdza czy podane hasla sa identyczne i wywoluje funkcje wpisywania do bazy danych
//        public String validatePassword(){
//
//                if(password.equals(confirmPassword)){
//                        //Wpisywanie danych z logowania do bazy
//                        boolean check = RegisterController.insertData(firstName,lastName,username,password,email,date);
//                        //W przypadku powodzenia wpisywania danych do bazy przeniesie do strony login.xhtml i wyswietli komunikat
//                        if(check){
//                                FacesContext.getCurrentInstance().addMessage(
//                                        null,
//                                        new FacesMessage(FacesMessage.SEVERITY_INFO,
//                                                "Register done",
//                                                "Now you can log in"));
//                                return "login";
//                        }
//                        else {
//                                FacesContext.getCurrentInstance().addMessage(
//                                        null,
//                                        new FacesMessage(FacesMessage.SEVERITY_WARN,
//                                                "Could not register new user",
//                                                "DB problems"));
//                                return "register";
//                        }
//                }else {
//                    //Wypisze komunikat jesli hasla sie roznia
//                    FacesContext.getCurrentInstance().addMessage(
//                            null,
//                            new FacesMessage(FacesMessage.SEVERITY_WARN,
//                                        "Passwords are different",
//                                        "Please enter your password correctly"));
//                        return "register";
//                }
//        }
//    }
