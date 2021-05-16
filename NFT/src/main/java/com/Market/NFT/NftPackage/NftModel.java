package com.Market.NFT.NftPackage;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class NftModel {

  private Long bidPrice;
  private String nftName;
  private String mediaFile;

}
