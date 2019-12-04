package pl.edu.pjwstk.jaz.auth;

public class User{

    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String email;
    private String birthDate;

    public User(String firstName, String lastName, String username, String password, String email, String birthDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.email = email;
        this.birthDate = birthDate;
    }

    public String getFirstName() { return firstName; }

    public String getLastName() { return lastName; }

    public String getUsername() { return username; }

    public String getPassword() { return password; }

    public String getEmail() { return email; }

    public String getBirthDate() { return birthDate; }
}
