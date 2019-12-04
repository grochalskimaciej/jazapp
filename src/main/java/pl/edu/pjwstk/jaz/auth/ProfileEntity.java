package pl.edu.pjwstk.jaz.auth;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "jazapp")
public class ProfileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    public ProfileEntity() { }
    public ProfileEntity(String firstName) { this.firstName = firstName; }
    public ProfileEntity(String firstName, String lastName, String username, String password, String email, String birthDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.email = email;
        this.birthDate = birthDate;
    }

    public Long getId() { return id; }
    public String getName() { return firstName; }
}
