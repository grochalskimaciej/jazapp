package pl.edu.pjwstk.jaz.register;

import pl.edu.pjwstk.jaz.auth.ProfileService;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;


@Named
@RequestScoped
public class RegisterController {

    @Inject
    private RegisterRequest registerRequest;

    @Inject
    private ProfileService profileService;

    public String register(){
        if(profileService.doesAnyUserExist()){
            if(profileService.doesUserExist(registerRequest.getUsername())){
                FacesContext.getCurrentInstance().getExternalContext().getFlash()
                        .put("already exists", "Username already exists.");
                return "register";
            }

            if(!registerRequest.getPassword().equals(registerRequest.getConfirmPassword())){
                FacesContext.getCurrentInstance().getExternalContext().getFlash()
                        .put("wrong password", "Your password does not match");
                return "register";
            }

            profileService.addUser(
                    registerRequest.getFirstName(),
                    registerRequest.getLastName(),
                    registerRequest.getUsername(),
                    registerRequest.getPassword(),
                    registerRequest.getEmail(),
                    registerRequest.getDate()
            );

            return "login";
        } else {
            profileService.addAdmin(
                    registerRequest.getFirstName(),
                    registerRequest.getLastName(),
                    registerRequest.getUsername(),
                    registerRequest.getPassword(),
                    registerRequest.getEmail(),
                    registerRequest.getDate()
            );

            return "login";
        }
    }
}

