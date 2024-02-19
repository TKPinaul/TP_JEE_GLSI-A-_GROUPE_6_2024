package glsia6.com.compteManagement.auth;

import glsia6.com.compteManagement.config.JwtService;
import glsia6.com.compteManagement.entity.User;
import glsia6.com.compteManagement.enums.Role;
import glsia6.com.compteManagement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {

        var user = User.builder()
                .nom(request.getNom())
                .sexe(request.getSexe())
                .courriel(request.getCourriel())
                .dateNaissance(request.getDateNaissance())
                .telephone(request.getTelephone())
                .password(passwordEncoder.encode(request.getPassword()))
                .adresse(request.getAdresse())
                .nationalite(request.getNationalite())
                .role(Role.CLIENT)
                .build();
        repository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getCourriel(),
                        request.getPassword()
                )
        );
        var user = repository.findByCourriel(request.getCourriel())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
