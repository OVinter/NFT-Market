package com.Market.NFT.Kafka;

import lombok.Getter;
import lombok.Setter;

public class NestedDto {

  @Getter @Setter
  private TestDto testDto;
  @Getter @Setter
  private Long id;

}
