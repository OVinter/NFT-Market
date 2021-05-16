package com.Market.NFT.security.rest;


import com.Market.NFT.UserPackage.User;
import com.Market.NFT.UserPackage.UserRepository;
import com.Market.NFT.security.jwt.JWTFilter;
import com.Market.NFT.security.jwt.TokenProvider;
import com.Market.NFT.security.model.Authority;
import com.Market.NFT.security.repository.AuthorityRepository;
import com.Market.NFT.security.rest.dto.AuthResponse;
import com.Market.NFT.security.rest.dto.LoginDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Controller to authenticate users.
 */
@RestController
@RequestMapping("/api")
public class AuthenticationRestController {

   private final TokenProvider tokenProvider;

   private final AuthenticationManagerBuilder authenticationManagerBuilder;

   private final UserRepository userRepository;

   private PasswordEncoder passwordEncoder;

   private AuthorityRepository authorityRepository;

   public AuthenticationRestController(TokenProvider tokenProvider,
                                       AuthenticationManagerBuilder authenticationManagerBuilder,
                                       UserRepository userRepository,
                                       PasswordEncoder passwordEncoder,
                                       AuthorityRepository authorityRepository) {
      this.tokenProvider = tokenProvider;
      this.authenticationManagerBuilder = authenticationManagerBuilder;
      this.userRepository = userRepository;
      this.passwordEncoder = passwordEncoder;
      this.authorityRepository = authorityRepository;

   }

   @PostMapping("/authenticate")
   public AuthResponse authorize(@Valid @RequestBody LoginDto loginDto) {

      if(userRepository.findByuserName(loginDto.getUserName()).isEmpty()) {
         User u = new User();
         Set<Authority> s = new HashSet<>();
         Authority a = new Authority();
         a.setName("ROLE_USER");
         s.add(a);
         u.setUserName(loginDto.getUserName());
         u.setPassword(passwordEncoder.encode(loginDto.getPassword()));
         u.setEmail(loginDto.getEmail());
         u.setAuthorities(s);
         u.setActivated(true);
         authorityRepository.save(a);
         userRepository.save(u);
      }
      Long id = userRepository.findByuserName(loginDto.getUserName()).get().getId();
      UsernamePasswordAuthenticationToken authenticationToken =
         new UsernamePasswordAuthenticationToken(loginDto.getUserName(), loginDto.getPassword());

      Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
      SecurityContextHolder.getContext().setAuthentication(authentication);

      //boolean rememberMe = (loginDto.isRememberMe() == null) ? false : loginDto.isRememberMe();
      String jwt = tokenProvider.createToken(authentication/*, rememberMe*/);


      HttpHeaders httpHeaders = new HttpHeaders();
      httpHeaders.add(JWTFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);
//      return new ResponseEntity<>(new JWTToken(jwt), httpHeaders, HttpStatus.OK);
       JWTToken j = new JWTToken(jwt);
       System.out.println(j.getIdToken());
      return new AuthResponse(new JWTToken(jwt), id);
   }

   /**
    * Object to return as body in JWT Authentication.
    */
   public static class JWTToken {

      private String idToken;

      JWTToken(String idToken) {
         this.idToken = idToken;
      }

      @JsonProperty("id_token")
      String getIdToken() {
         return idToken;
      }

      void setIdToken(String idToken) {
         this.idToken = idToken;
      }
   }
}
