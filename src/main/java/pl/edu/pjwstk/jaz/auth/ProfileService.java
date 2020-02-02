package pl.edu.pjwstk.jaz.auth;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

@ApplicationScoped
public class ProfileService {

    @Inject
    private ProfileRepository profileRepository;

    @Inject
    private HttpServletRequest request;

    public boolean logIn(String username, String password) {

        if (isUsernameAndPasswordCorrect(username, password)) {
            var session = request.getSession(true);
            session.setAttribute("username", username);

            return true;
        }
        return false;
    }
    private boolean isUsernameAndPasswordCorrect(String username, String password) {
        var userOptional = profileRepository.findUserByUsername(username);

        if (userOptional.isEmpty()) {
            return false;
        }
        var user = userOptional.get();

        return user.getUsername().equals(username) && user.getPassword().equals(password);
    }

    public boolean doesUserExist(String username) {
        var userOptional = profileRepository.findUserByUsername(username);
        return userOptional.isPresent();
    }

    public boolean doesAnyUserExist() {
        return profileRepository.doesAnyUserExist();
    }

    public void addUser(String firstName, String lastName, String username, String password, String email, String birthday) {
        var user = new User(firstName, lastName, username, password, email, birthday);
        profileRepository.addUser(user);
    }

    public void addAdmin(String firstName, String lastName, String username, String password, String email, String birthday) {
        var admin = new User(firstName, lastName, username, password, email, birthday);
        profileRepository.addAdmin(admin);
    }

    public void logout() {
        var session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
    }
}
