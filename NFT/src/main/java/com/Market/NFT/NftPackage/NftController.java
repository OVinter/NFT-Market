package com.Market.NFT.NftPackage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
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

//    @PostMapping("bid")
//    public Order bidNft(Order order) { return nftService.order(order); }

}
