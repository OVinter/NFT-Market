package com.Market.NFT.NftPackage;

import com.Market.NFT.NftPackage.Nft;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NftRepository extends JpaRepository<Nft, Long> {

}
