package com.Market.NFT.NftPackage;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.Instant;

@Entity
@Embeddable
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Nft {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "Bid price is required") // test
    private Long bidPrice;
    @NotBlank(message = "Nft name is required") // test
    private String nftName;
    @NotBlank(message = "Media file is required") // test
    private String mediaFile;
    private Long idUser;


    public Nft(Long bidPrice, String nftName, String mediaFile) {
        this.bidPrice = bidPrice;
        this.nftName = nftName;
        this.mediaFile = mediaFile;
    }

    @Override
    public String toString() {
        return "Nft{" +
                "nftId=" + id +
                ", bidPrice=" + bidPrice +
                ", nftName='" + nftName + '\'' +
                ", mediaFile='" + mediaFile + '\'' +
                '}';
    }
}
