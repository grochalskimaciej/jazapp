package pl.edu.pjwstk.jaz.auth;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@ApplicationScoped
public class ProfileService {
    @Inject
    private ProfileRepository profileRepository;

    @Inject
    private HttpServletRequest request;

//    private final DateFormat dateFormat =  new SimpleDateFormat("dd/MM/yyyy");

    public boolean logIn(String username, String password) {
//        //TODO odkomentowac zakomentowane wywalic reszte
//        if (isUsernameAndPasswordCorrect(username, password)) {
//            var session = request.getSession(true);
//            session.setAttribute("username", username);
//
//            return true;
//        }
//        return false;

        var session = request.getSession(true);
        session.setAttribute("username", username);
        return true;
    }

    private boolean isUsernameAndPasswordCorrect(String username, String password) {
        var userOptional = profileRepository.findUserByUsername(username);
        if (userOptional.isEmpty()) {
            return false;
        }
        var user = userOptional.get();

//        var passwordEncoder = new BCryptPasswordEncoder();
//        String pass = user.getPassword();
//
//        if(passwordEncoder.matches(pass, password)){
//            return user.getUsername().equals(username);
//        }
//
//        if(passwordEncoder.encode(pass).equals(password)){
//            return user.getUsername().equals(username);
//        }
//        return false;

        return user.getUsername().equals(username) && user.getPassword().equals(password);
    }

    public boolean doesUserExist(String username) {
        var userOptional = profileRepository.findUserByUsername(username);
        return userOptional.isPresent();
    }

    public void addUser(String firstName, String lastName, String username, String password, String email, String birthday) {
//        String BCryptPassw = BCrypt.hashpw(password,BCrypt.gensalt());

        var user = new User(firstName, lastName, username, password, email, birthday);
        profileRepository.addUser(user);
    }

//    private LocalDate parseDate(String dateAsText) {
//        try {
//            var parsedDate = dateFormat.parse(dateAsText);
//
//            return parsedDate.toInstant()
//                    .atZone(ZoneId.systemDefault())
//                    .toLocalDate();
//        } catch (ParseException e) {
//            throw new RuntimeException(e);
//        }
//    }

//    @PostConstruct
//    public void addTest() {
//        var user = new User("Admin", "Admin","admin", "adminA1",
//                "admin@admin.pl", LocalDate.parse("15-06-2000"));
//        profileRepository.addUser(user);
//    }

    public void logout() {
        var session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
    }
}
