package com.Market.NFT.NftPackage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class NftService {

//  private final OrderRepository orderRepository;
  private final NftRepository nftRepository;
  // private KafkaTemplate<String, Order> kafkaTemplate;

    @Autowired
    public NftService(NftRepository nftRepository) {
        this.nftRepository = nftRepository;
    }

    public List<Nft> getAllNfts() {
      return nftRepository.findAll();
    }

    public Nft getNft(Long id) {
      Optional<Nft> p = nftRepository.findById(id);
      if(p.isPresent()) {
        return p.get();
      }
      throw new IllegalStateException("nft with id: " + id + " not exist");
    }

//  public Order order(Order order) {
//    if (order.getNumberOfLines() == 0) {
//      throw new IllegalArgumentException("No order lines!");
//    }
//    order.setUpdated(new Date());
//    Order result = orderRepository.save(order);
//    fireOrderCreatedEvent(order);
//    return result;
//  }

//  private void fireOrderCreatedEvent(Order order) {
//    kafkaTemplate.send("order", order.getId() + "created", order);
//  }
//
//  public double getPrice(long orderId) {
//    return orderRepository.findById(orderId).get().totalPrice();
//  }
}
