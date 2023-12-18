package edu.miu.cs.cs544.service;

import edu.miu.cs.cs544.domain.User;
import edu.miu.cs.cs544.domain.UserType;
import edu.miu.cs.cs544.dto.LoggedInUserDTO;
import edu.miu.cs.cs544.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void createUser(User user) {
        userRepository.save(user);
    }

    public LoggedInUserDTO getLoggedInUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String role = authentication.getAuthorities().iterator().next().getAuthority();
            return new LoggedInUserDTO(
                    authentication.getName(),
                    UserType.valueOf(role)
            );
        }
        return null;
    }
}
