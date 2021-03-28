package com.Market.NFT.NftPackage;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Embeddable
public class Nft {

    @Getter @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Getter @Setter
    private Long bidPrice;
    @Getter @Setter
    private String nftName;
    @Getter @Setter
    private String mediaFile;

    public Nft(Long id, Long bidPrice, String nftName, String mediaFile) {
        this.id = id;
        this.bidPrice = bidPrice;
        this.nftName = nftName;
        this.mediaFile = mediaFile;
    }

    public Nft(Long bidPrice, String nftName, String mediaFile) {
        this.bidPrice = bidPrice;
        this.nftName = nftName;
        this.mediaFile = mediaFile;
    }

    public Nft() {

    }


    @Override
    public String toString() {
        return "Nft{" +
                "id=" + id +
                ", bidPrice=" + bidPrice +
                ", nftName='" + nftName + '\'' +
                ", mediaFile='" + mediaFile + '\'' +
                '}';
    }
}
