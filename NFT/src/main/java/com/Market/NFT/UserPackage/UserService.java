package com.Market.NFT.UserPackage;

import com.Market.NFT.NftPackage.Nft;
import com.Market.NFT.NftPackage.NftRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final NftRepository nftRepository;

    @Autowired
    public UserService(UserRepository userRepository, NftRepository nftRepository) {
        this.userRepository = userRepository;
        this.nftRepository = nftRepository;
    }

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

  public User findUserByID(Long id) {
    Optional<User> u = userRepository.findById(id);
    if(u.isPresent()) {
      return u.get();
    }
    throw new IllegalStateException("user with id: " + id + " not exist");
  }

  public List<Nft> getAllUserNfts(Long id) {
    Optional<User> u = userRepository.findById(id);
    if(u.isPresent()) {
      User user = u.get();
      return user.getNfts();
    }
    throw new IllegalStateException("user with id: " + id + " not exist");
  }

  public User modifyUser(Long id, User user) {
    Optional<User> u = userRepository.findById(id);
    if(u.isPresent()) {
      User userModify = u.get();
      if(user.getUserName() != null)
        userModify.setUserName(user.getUserName());
      if(user.getPassword() != null)
        userModify.setPassword(user.getPassword());
      return userRepository.save(userModify);
    }
    throw new IllegalStateException("user with id: " + id + " not exist");
  }

  public boolean deleteUser(Long id) {
    Optional<User> u = userRepository.findById(id);
    if(u.isPresent()) {
      User user = u.get();
      userRepository.delete(user);
      return true;
    }
    return false;
  }

  public Nft addNft(Long id, Nft nft) {
    Optional<User> u = userRepository.findById(id);
    User user;
    List<Nft> nfts = new ArrayList<Nft>();
    if(u.isPresent()) {
      user = u.get();
      if (!user.getNfts().isEmpty()) {
        nfts = user.getNfts();
      }
      nfts.add(nft);
      user.setNfts(nfts);
      return nftRepository.save(nft);
    }
    throw new IllegalStateException("user with id: " + id + " not exist");
  }


  public Nft getNft(Long idUser, Long idNft) {
    Optional<User> u = userRepository.findById(idUser);
    User user;
    Optional<Nft> n;
    if(u.isPresent()) {
      n = nftRepository.findById(idNft);
      if(n.isPresent())
        return n.get();
      throw new IllegalStateException("nft with id: " + idNft + " not exist");
    }
    throw new IllegalStateException("user with id: " + idUser + " not exist");
  }

  public Nft modifyNft(Long idUser, Long idNft, Nft nft) {
    Optional<User> u = userRepository.findById(idUser);
    User user;
    Nft modifyNft;
    Optional<Nft> n;
    if(u.isPresent()) {
      user = u.get();
      n = nftRepository.findById(idNft);
      if (n.isPresent()) {
        modifyNft = n.get();
        if (nft.getNftName() != null)
          modifyNft.setNftName(nft.getNftName());
        if (nft.getMediaFile() != null)
          modifyNft.setMediaFile(nft.getMediaFile());
        if (nft.getBidPrice() != null)
          modifyNft.setBidPrice(nft.getBidPrice());
        return nftRepository.save(modifyNft);
      }
      throw new IllegalStateException("nft with id: " + idNft + " not exist");
    }
    throw new IllegalStateException("user with id: " + idUser + " not exist");
  }

  public boolean deleteNft(Long idUser, Long idNft) {
    Optional<User> u = userRepository.findById(idUser);
    Optional<Nft> p;
    Nft nft;
    User user;
    List<Nft> nfts;
    if(u.isPresent()) {
      user = u.get();
      p = nftRepository.findById(idNft);
      if(p.isPresent()) {
        nft = p.get();
        nfts = user.getNfts();
        nfts.remove(nft);
        user.setNfts(nfts);
        userRepository.save(user);
        nftRepository.delete(nft);
        return true;
      }
      throw new IllegalStateException("nft with id: " + idNft + " not exist");
    }
    throw new IllegalStateException("user with id: " + idUser + " not exist");
  }
}
