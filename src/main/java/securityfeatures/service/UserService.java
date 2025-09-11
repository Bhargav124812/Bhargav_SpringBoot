package securityfeatures.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import securityfeatures.exceptions.ResourceNotFoundException;
import securityfeatures.repo.UserRepo;

@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByEmail(username).orElseThrow(() ->new ResourceNotFoundException("There Is No user With username"));
    }
}
