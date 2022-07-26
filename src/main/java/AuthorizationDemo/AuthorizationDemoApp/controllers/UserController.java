package AuthorizationDemo.AuthorizationDemoApp.controllers;

import AuthorizationDemo.AuthorizationDemoApp.models.UserMeta;
import AuthorizationDemo.AuthorizationDemoApp.models.UserPrincipal;
import AuthorizationDemo.AuthorizationDemoApp.services.CustomUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    CustomUserService userService;

    @PostMapping("/update-user")
    public UserMeta updateUser(@RequestBody UserMeta userToUpdate) {
        return userService.updateUserMeta(userToUpdate);
    }

    @GetMapping("/current-user")
    public UserMeta getUser(@CurrentSecurityContext /* Tell Spring to inject the user's Authentication Token */Authentication authentication) {
        //use the token to retrieve the UserMeta object
        UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();
        return principal.getUserMeta();
    }
}
