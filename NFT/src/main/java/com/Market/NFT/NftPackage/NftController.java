package com.Market.NFT.NftPackage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(path = "/api/nfts")
public class NftController {

    private final NftService nftService;

    @Autowired
    public NftController(NftService nftService) {
        this.nftService = nftService;
    }

    @GetMapping
    public List<Nft> getAllNfts() {
      return nftService.getAllNfts();
    }

    @GetMapping("{id}")
    public Nft getProduct(@PathVariable Long id) {
      return nftService.getNft(id);
    }
}
