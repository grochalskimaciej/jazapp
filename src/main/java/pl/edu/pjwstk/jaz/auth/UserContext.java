package pl.edu.pjwstk.jaz.auth;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

@Named
@RequestScoped
public class UserContext {
    @Inject
    private HttpServletRequest request;

    @Inject
    private ProfileRepository profileRepository;

    public String getUsername() {
        var session = request.getSession(false);
        var usernameObj = session.getAttribute("username");
        if (usernameObj == null) {
            throw new IllegalStateException("No session/User not logged in.");
        }

        return (String) usernameObj;
    }

    public String getFullName() {
        var username = getUsername();

        var user = profileRepository.requireUser(username);

        return String.format("%s %s", user.getFirstName(), user.getLastName());
    }
}
