package pl.edu.pjwstk.jaz.auth;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "jazapp")
public class ProfileEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Long id;

    @NotNull
    @Column(name="firstName")
    private String firstName;

    @NotNull
    @Column(name="lastName")
    private String lastName;

    @NotNull
    @Column(name="username")
    private String username;

    @NotNull
    @Column(name="password")
    private String password;

    @NotNull
    @Column(name="email")
    private String email;

    @NotNull
    @Column(name="birthDate")
    private String birthDate;

    @NotNull
    @Column(name="isAdmin")
    private boolean isAdmin;

    public ProfileEntity() { }
    public ProfileEntity(String firstName, String lastName, String username, String password, String email, String birthDate, boolean isAdmin) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.email = email;
        this.birthDate = birthDate;
        this.isAdmin = isAdmin;
    }

    public Long getId() { return id; }
    public String getFirstName() { return firstName; }
    public String getLastname() { return lastName; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getEmail() { return email; }
    public String getBirthDate() { return birthDate; }
    public boolean getIsAdmin() {return isAdmin;}
}
