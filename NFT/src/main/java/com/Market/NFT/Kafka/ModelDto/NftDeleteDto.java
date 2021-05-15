package com.Market.NFT.Kafka.ModelDto;

import lombok.Getter;
import lombok.Setter;

public class NftDeleteDto {

  @Getter @Setter
  private Long idUser;
  @Getter @Setter
  private Long idNft;

  @Override
  public String toString() {
    return "NftDeleteDto{" +
      "idUser=" + idUser +
      ", idNft=" + idNft +
      '}';
  }
}
