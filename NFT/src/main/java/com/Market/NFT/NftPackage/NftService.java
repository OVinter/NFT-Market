package com.Market.NFT.NftPackage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class NftService {

  private final NftRepository nftRepository;

    @Autowired
    public NftService(NftRepository nftRepository) {
        this.nftRepository = nftRepository;
    }

    public List<Nft> getAllNfts() {
      return nftRepository.findAll();
    }

    public Nft getNft(Long id) {
      Optional<Nft> p = nftRepository.findById(id);
      if(p.isPresent()) {
        return p.get();
      }
      throw new IllegalStateException("nft with id: " + id + " not exist");
    }
}
