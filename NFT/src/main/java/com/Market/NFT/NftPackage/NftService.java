package com.Market.NFT.NftPackage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NftService {

    private final NftRepository nftRepository;

    @Autowired
    public NftService(NftRepository nftRepository) {
        this.nftRepository = nftRepository;
    }
}
