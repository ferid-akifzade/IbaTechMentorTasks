package org.farid.api.service;

import org.farid.libs.User;
import org.farid.repositories.UserRepository;
import org.farid.security.jwt.Const;
import org.farid.security.jwt.JwtTokenService;
import org.farid.security.userdetails.CustomUserDetails;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LogRegService {

    private final AuthenticationManager manager;
    private final JwtTokenService tokenService;
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public LogRegService(AuthenticationManager manager, JwtTokenService tokenService, UserRepository userRepository, PasswordEncoder encoder) {
        this.manager = manager;
        this.tokenService = tokenService;
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    public Optional<String> login(String email, String password, boolean remember) {
        return Optional.of(manager.authenticate(new UsernamePasswordAuthenticationToken(email, password)))
                .filter(Authentication::isAuthenticated)
                .map(a -> {
                    SecurityContextHolder.getContext().setAuthentication(a);
                    return a;
                })
                .map(a -> (CustomUserDetails) a.getPrincipal())
                .map(customUserDetails -> tokenService.generateToken(customUserDetails.getId(), remember))
                .map(token -> String.format("%s%s", Const.AUTH_BEARER, token));
    }

    public boolean register(String email, String password) {
        Optional<User> byId = userRepository.findByEmail(email);

        if (!byId.isPresent()) {
            userRepository.save(new User(email, encoder.encode(password)));
        }
        return !byId.isPresent();
    }

}
