package me.zinoviev.railway.auth;

import lombok.RequiredArgsConstructor;
import me.zinoviev.railway.auth.dto.AuthResponse;
import me.zinoviev.railway.auth.dto.LoginRequest;
import me.zinoviev.railway.auth.dto.RegisterRequest;
import me.zinoviev.railway.common.ConflictException;
import me.zinoviev.railway.repository.UserRepository;
import me.zinoviev.railway.user.Role;
import me.zinoviev.railway.user.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Transactional
    public AuthResponse register(RegisterRequest request){

        if (userRepository.existsByEmail(request.email())){
            throw new ConflictException("Email is already registered");
        }

        User user = new User();

        user.setEmail(request.email());
        user.setPasswordHash(passwordEncoder.encode(request.password()));
        user.setFullName(request.fullName());
        user.setRole(Role.USER);

        User savedUser = userRepository.save(user);

        AppUserDetails userDetails = new AppUserDetails(savedUser);

        String token = jwtService.generate(userDetails);

        return new AuthResponse(token, savedUser.getEmail(), savedUser.getRole().name());
    }

    public AuthResponse login(LoginRequest request){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.email(),
                request.password()));

        AppUserDetails userDetails = (AppUserDetails) authentication.getPrincipal();

        String token = jwtService.generate(userDetails);

        return new AuthResponse(token, userDetails.getUsername(), userDetails.getUser().getRole().name());
    }

}
