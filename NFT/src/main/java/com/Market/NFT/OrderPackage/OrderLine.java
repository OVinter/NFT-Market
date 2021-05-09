package com.Market.NFT.OrderPackage;

import com.Market.NFT.NftPackage.Nft;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
public class OrderLine {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Getter @Setter
  private Long ID;

  @Getter @Setter
  private int count;

  @Getter @Setter
  private Nft nft;

  public OrderLine(int count, Nft nft) {
    this.count = count;
    this.nft = nft;
  }

  public OrderLine() {
  }

  @Override
  public String toString() {
    return "OrderLine{" +
      "ID=" + ID +
      ", count=" + count +
      ", nft=" + nft +
      '}';
  }
}
