package com.Market.NFT.Kafka;

import com.Market.NFT.Kafka.ModelDto.NftAddDto;
import com.Market.NFT.Kafka.ModelDto.NftDeleteDto;
import com.Market.NFT.NftPackage.Nft;
import com.Market.NFT.NftPackage.NftRepository;
import com.Market.NFT.UserPackage.User;
import com.Market.NFT.UserPackage.UserRepository;
import com.google.gson.Gson;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CountDownLatch;

@Component
public class KafkaConsumer {

  private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer.class);

  private CountDownLatch latch = new CountDownLatch(1);
  private String payload = null;
  private final NftRepository nftRepository;
  private final UserRepository userRepository;

  @Autowired
  public KafkaConsumer(NftRepository nftRepository, UserRepository userRepository) {
    this.nftRepository = nftRepository;
    this.userRepository = userRepository;
  }

  @KafkaListener(topics = "addNft", groupId = "test")
  public void receiveAddNft(ConsumerRecord<?, ?> consumerRecord) {
    LOGGER.info("received payload='{}'", consumerRecord.toString());
    System.out.println(consumerRecord.value());
    Gson gson = new Gson();
    NftAddDto nftAddDto = gson.fromJson((String)consumerRecord.value(), NftAddDto.class);
    System.out.println(nftAddDto.getNft().getNftName());
    System.out.println(nftAddDto.getIdUser());

    Optional<User> u = userRepository.findById(nftAddDto.getIdUser());
    User user;
    List<Nft> nfts = new ArrayList<Nft>();
    if(u.isPresent()) {
      user = u.get();
      if (!user.getNfts().isEmpty()) {
        nfts = user.getNfts();
      }
      Nft nft = nftAddDto.getNft();
      nft.setIdUser(nftAddDto.getIdUser());
      nfts.add(nft);
      user.setNfts(nfts);
      System.out.println(nft.toString());
      nftRepository.save(nft);
      userRepository.save(user);
    }
    latch.countDown();
  }

  @KafkaListener(topics = "deleteNft", groupId = "test")
  public void receiveDeleteNft(ConsumerRecord<?, ?> consumerRecord) {
    LOGGER.info("received payload='{}'", consumerRecord.toString());
    System.out.println(consumerRecord.value());
    Gson gson = new Gson();
    NftDeleteDto nftDeleteDto = gson.fromJson((String)consumerRecord.value(), NftDeleteDto.class);
    Optional<User> u = userRepository.findById(nftDeleteDto.getIdUser());
    Optional<Nft> p;
    Nft nft;
    User user;
    List<Nft> nfts;
    if (u.isPresent()) {
      user = u.get();
      p = nftRepository.findById(nftDeleteDto.getIdNft());
      if (p.isPresent()) {
        nft = p.get();
        nfts = user.getNfts();
        nfts.remove(nft);
        user.setNfts(nfts);
        userRepository.save(user);
        nftRepository.delete(nft);
      }
    }
    System.out.println("receiveDeleteNft");
    latch.countDown();
  }

  public CountDownLatch getLatch() {
    return latch;
  }

  public String getPayload() {
    return payload;
  }

  private void setPayload(String payload) {
    this.payload = payload;
  }

}
