package pl.edu.pjwstk.jaz.webapp;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Named
@RequestScoped
public class RegisterRequest{

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;

//        @Column(name="forename")
        @NotNull
        private String name;

//        @Column(name="surname", nullable=false)
        @NotNull
        private String surname;

//        @Column(name="username", nullable=false)
        @NotNull
        private String username;

//        @Column(name="pass", nullable=false)
        @NotNull
        private String password;

//        @Column(name="email", nullable=false)
        @NotNull
        private String email;

//        @Column(name="registerdate", nullable=false)
        @NotNull
        private String date;

        private String confirmPassword;

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }

        public String getSurname() { return surname; }
        public void setSurname(String surname) { this.surname = surname; }

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


        //Funkcja sprawdza czy podane hasla sa identyczne i wywoluje funkcje wpisywania do bazy danych
        public String validatePassword(){

                if(password.equals(confirmPassword)){
                        //Wpisywanie danych z logowania do bazy
                        boolean check = RegisterController.insertData(name,surname,username,password,email,date);
                        //W przypadku powodzenia wpisywania danych do bazy przeniesie do strony login.xhtml i wyswietli komunikat
                        if(check){
                                FacesContext.getCurrentInstance().addMessage(
                                        null,
                                        new FacesMessage(FacesMessage.SEVERITY_INFO,
                                                "Register done",
                                                "Now you can log in"));
                                return "login";
                        }
                        else {
                                FacesContext.getCurrentInstance().addMessage(
                                        null,
                                        new FacesMessage(FacesMessage.SEVERITY_WARN,
                                                "Could not register new user",
                                                "DB problems"));
                                return "register";
                        }
                }else {
                    //Wypisze komunikat jesli hasla sie roznia
                    FacesContext.getCurrentInstance().addMessage(
                            null,
                            new FacesMessage(FacesMessage.SEVERITY_WARN,
                                        "Passwords are different",
                                        "Please enter your password correctly"));
                        return "register";
                }
        }
    }
