package com.Market.NFT.UserPackage;

import com.Market.NFT.NftPackage.Nft;
import com.Market.NFT.UserPackage.UserRepository;
import com.Market.NFT.UserPackage.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@RestController
@RequestMapping(path = "/api/users")
public class UsersController {

    private final UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping
    public User addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @GetMapping("/{id}")
    public User findUserByID(@PathVariable Long id) {
      return userService.findUserByID(id);
    }

    @GetMapping("/{id}/nfts")
    public List<Nft> getAllUserProducts(@PathVariable Long id) {
      return userService.getAllUserNfts(id);
    }

    @PutMapping("/{id}")
    public User modifyUser(@PathVariable Long id, @RequestBody User user) {
      return userService.modifyUser(id, user);
    }

    @DeleteMapping("/{id}")
    public boolean deleteUser(@PathVariable Long id) {
      return userService.deleteUser(id);
    }

    @PostMapping("/{idUser}/nfts")
    public Nft addProduct(@PathVariable Long idUser, @RequestBody Nft nft) {
      return userService.addNft(idUser, nft);
    }

    @GetMapping("/{idUser}/nfts/{idNft}")
    public Nft getProduct(@PathVariable Long idUser, @PathVariable Long idNft) {
      return userService.getNft(idUser, idNft);
    }

    @PutMapping("/{idUser}/nfts/{idNft}")
    public Nft modifyProduct(@PathVariable Long idUser,
                                 @PathVariable Long idNft,
                                 @RequestBody Nft nft) {
      return userService.modifyNft(idUser, idNft, nft);
    }

    @DeleteMapping("/{idUser}/nfts/{idNft}")
    public boolean deleteProduct(@PathVariable Long idUser, @PathVariable Long idNft) {
      return userService.deleteNft(idUser, idNft);
    }
}
