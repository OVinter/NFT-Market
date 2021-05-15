package com.Market.NFT.Kafka;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;


public class TestDto {

  @Getter @Setter
  private String mess;
  @Getter @Setter
  private String name;

}
