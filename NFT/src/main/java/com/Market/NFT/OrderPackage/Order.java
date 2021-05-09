package com.Market.NFT.OrderPackage;

import com.Market.NFT.NftPackage.Nft;
import com.Market.NFT.UserPackage.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long ID;

  @ManyToOne
  private User user;

  private Date updated;

  @OneToMany(cascade = CascadeType.ALL)
  private List<OrderLine> orderLine;

  public Order() {
    super();
    orderLine = new ArrayList<OrderLine>();
    updated();
  }

  private void updated() {
    updated = new Date();
  }

  public void setId(long id) {
    this.ID = id;
  }

  public long getId() {
    return ID;
  }

  public Date getUpdated() {
    return updated;
  }

  public void setUpdated(Date created) {
    this.updated = created;
  }

  public User getCustomer() {
    return user;
  }

  public void setCustomer(User user) {
    this.user = user;
    updated();
  }

  public List<OrderLine> getOrderLine() {
    return orderLine;
  }

  public Order(User user) {
    super();
    this.user = user;
    this.orderLine = new ArrayList<OrderLine>();
  }

  public void setOrderLine(List<OrderLine> orderLine) {
    this.orderLine = orderLine;
    updated();
  }

  public void addLine(int count, Nft nft) {
    this.orderLine.add(new OrderLine(count, nft));
    updated();
  }

  public int getNumberOfLines() {
    return orderLine.size();
  }

  public double totalPrice() {
    return orderLine.stream().map((ol) -> ol.getCount() * ol.getNft().getBidPrice()).reduce((long) 0.0, (d1, d2) -> d1 + d2);
  }

  @Override
  public String toString() {
    return "Order{" +
      "ID=" + ID +
      ", user=" + user +
      ", updated=" + updated +
      ", orderLine=" + orderLine +
      '}';
  }
}
