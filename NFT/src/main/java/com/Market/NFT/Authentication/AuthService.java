package com.Market.NFT.Authentication;

import com.Market.NFT.DTO.AuthenticationResponse;
import com.Market.NFT.DTO.LoginRequest;
import com.Market.NFT.DTO.RegisterRequest;
import com.Market.NFT.Exceptions.SpringNftMarketException;
import com.Market.NFT.UserPackage.User;
import com.Market.NFT.UserPackage.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AuthService {


    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final VerificationTokenRepository verificationTokenRepository;
    private final MailService mailService;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;

    public boolean signup(RegisterRequest registerRequest) {
        Optional<User> optionalUser = userRepository.findUserByEmail(registerRequest.getEmail());
        Optional<User> optionalUser1 = userRepository.findByuserName((registerRequest.getUserName()));
        if(optionalUser.isPresent() || optionalUser1.isPresent()) {
            return false;
        }
        User user = new User();
        user.setUserName(registerRequest.getUserName());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setEmail(registerRequest.getEmail());
        user.setCreated(Instant.now());
        user.setEnabled(false);

        userRepository.save(user);

        String token = generateVerificationToken(user);
        mailService.sendMail(new NotificationEmail("Please activate your account",
                user.getEmail(), "Thank you, " + "please click on the below url to activate your account : " +
                "http://localhost:8080/api/auth/accountVerification/" + token));
        return true;
    }

    private String generateVerificationToken(User user) {
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(token);
        verificationToken.setUser(user);

        verificationTokenRepository.save(verificationToken);
        return token;
    }

    public void verifyAccount(String token) {
        Optional<VerificationToken> verificationToken = verificationTokenRepository.findByToken(token);
        verificationToken.orElseThrow(() -> new SpringNftMarketException("Invalid Token"));
        fetchUserAndEnable(verificationToken.get());
    }

    @Transactional
    void fetchUserAndEnable(VerificationToken verificationToken) {
        String userName = verificationToken.getUser().getUserName();
        User user = userRepository.findByuserName(userName).orElseThrow(
                () -> new SpringNftMarketException("User not found with name " + userName)
        );
        user.setEnabled(true);
        userRepository.save(user);
    }

    public AuthenticationResponse login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUserName(),loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.generateToken(authentication);
        return new AuthenticationResponse(token, loginRequest.getUserName());
    }
}
