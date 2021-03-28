package com.Market.NFT.UserPackage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

//    public ResponseEntity<Object> getAllUsers() {
//        try {
//            return ResponseEntity.status(HttpStatus.OK)
//                    .body(userRepository.findAll());
//        } catch (Exception e) {
//            return  ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                    .body(new HttpClientErrorException(HttpStatus.BAD_REQUEST));
//        }
//
//    }
//
//    public ResponseEntity<Object> getUser(@PathVariable Long id) {
//        try {
//            return ResponseEntity.status(HttpStatus.OK)
//                    .body(userRepository.findById(id));
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                    .body(new HttpClientErrorException(HttpStatus.BAD_REQUEST));
//        }
//    }
//
//    public ResponseEntity<Object> addUser(@PathVariable User user) {
//        try {
//            User u = new User();
//            u.setUserName(user.getUserName());
//            u.setEmail(user.getEmail());
//            return ResponseEntity.status(HttpStatus.OK)
//                    .body(userRepository.save(u));
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                    .body(new HttpClientErrorException(HttpStatus.BAD_REQUEST));
//        }
//    }

//    public EntityModel<User> modifyUser(@RequestBody User user, @PathVariable Long id) {
//        try {
//            User u = userRepository.findById(id)
//                    .orElseThrow(() -> new UserNotFoundException(id));
//            return EntityModel.of(u, )
//        }
//
//    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public List<User> helloWorld() {
        return List.of(
                new User("aaa", "aaa@aaa", null, "123456")
        );
    }

    public User addUser(User user) {
        Optional<User> u = userRepository.findUserByEmail(user.getEmail());
        if(u.isPresent()) {
            throw new IllegalStateException("user already exist with this email");
        }
        return userRepository.save(user);
    }
}
