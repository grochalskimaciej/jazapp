package pl.edu.pjwstk.jaz.register;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

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
