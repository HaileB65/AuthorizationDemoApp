package AuthorizationDemo.AuthorizationDemoApp.services;

import AuthorizationDemo.AuthorizationDemoApp.models.UserMeta;
import AuthorizationDemo.AuthorizationDemoApp.models.UserPrincipal;
import AuthorizationDemo.AuthorizationDemoApp.repositories.UserMetaRepo;
import AuthorizationDemo.AuthorizationDemoApp.repositories.UserPrincipalRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserService implements UserDetailsService {

    @Autowired
    UserPrincipalRepo userPrincipalRepo;

    @Autowired
    UserMetaRepo userMetaRepo;

    @Override
    public UserPrincipal loadUserByUsername(String username) throws UsernameNotFoundException {
        return userPrincipalRepo.findByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException("User not found with username or email : " + username)
        );
    }

    public UserMeta updateUserMeta(UserMeta userToUpdate) {
        //save the user to the database
        UserMeta updatedUser = userMetaRepo.save(userToUpdate);

        //after the user has been saved to the database the UserPrincipal object stored in the security context has become stale
        //it also needs to be updated. Especially since we are using the SecurityContext to return user data in the UserController.
        UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        userPrincipal.setUserMeta(userToUpdate);
        return updatedUser;
    }
}
