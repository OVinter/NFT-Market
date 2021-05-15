package com.Market.NFT.Kafka;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Component
public class KafkaConsumer {

  private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer.class);

  private CountDownLatch latch = new CountDownLatch(1);
  private String payload = null;

  @KafkaListener(topics = "test", groupId = "test")
  public void receive(ConsumerRecord<?, ?> consumerRecord) {
    LOGGER.info("received payload='{}'", consumerRecord.toString());
    System.out.println(consumerRecord.value());
    Gson gson = new Gson();
    TestDto testDto = gson.fromJson((String)consumerRecord.value(), TestDto.class);
    System.out.println(testDto.toString());
    System.out.println(testDto.getMess());
    System.out.println(testDto.getName());
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
