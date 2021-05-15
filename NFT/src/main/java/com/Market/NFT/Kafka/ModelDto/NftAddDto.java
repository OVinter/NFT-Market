package com.Market.NFT.Kafka.ModelDto;

import com.Market.NFT.NftPackage.Nft;
import lombok.Getter;
import lombok.Setter;

public class NftAddDto {

  @Getter @Setter
  private Long idUser;
  @Getter @Setter
  private Nft nft;

  @Override
  public String toString() {
    return "NftAddDto{" +
      "idUser=" + idUser +
      ", nft=" + nft +
      '}';
  }
}
