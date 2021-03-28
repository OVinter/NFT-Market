package com.Market.NFT.NftPackage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/api/nfts")
public class NftController {

    private final NftService nftService;

    @Autowired
    public NftController(NftService nftService) {
        this.nftService = nftService;
    }
}
