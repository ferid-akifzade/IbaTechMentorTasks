package org.farid.security.userdetails;

import lombok.extern.slf4j.Slf4j;
import org.farid.libs.User;
import org.farid.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public static UserDetails mapper(User user) {
        return new CustomUserDetails(user.getId(), user.getEmail(), user.getPassword());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info(String.format(">>>>>>> UserDetails.loadUserByUsername:%s", username));
        return userRepository.findByEmail(username)
                .map(CustomUserDetailsService::mapper)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User with %s not found", username)));
    }

    public UserDetails loadUserById(Long id) throws UsernameNotFoundException {
        log.info(String.format(">>>>>>> UserDetails.loadUserById:%d", id));
        return userRepository.findById(id)
                .map(CustomUserDetailsService::mapper)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User with %s not found", id)));
    }


}
