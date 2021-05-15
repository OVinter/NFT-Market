package com.Market.NFT.Kafka;

import com.Market.NFT.Kafka.ModelDto.NftAddDto;
import com.Market.NFT.Kafka.ModelDto.NftDeleteDto;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import springfox.documentation.spring.web.json.JsonSerializer;

@Component
public class KafkaProducer {

  private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducer.class);

  @Autowired
  private KafkaTemplate<String, String> kafkaTemplate;


  public void sendNestedDto(String topic, NestedDto testDto) {
    LOGGER.info("sending payload='{}' to topic='{}'", testDto, topic);
    Gson gson = new Gson();
    String payload = gson.toJson(testDto);
    kafkaTemplate.send(topic, payload);
  }

  public void sendTestDto(String topic, TestDto testDto) {
    LOGGER.info("sending payload='{}' to topic='{}'", testDto, topic);
    Gson gson = new Gson();
    String payload = gson.toJson(testDto);
    kafkaTemplate.send(topic, payload);
  }

  public void sendAddNft(String topic, NftAddDto nftAddDto) {
    LOGGER.info("sending payload='{}' to topic='{}'", nftAddDto, topic);
    Gson gson = new Gson();
    String payload = gson.toJson(nftAddDto);
    kafkaTemplate.send(topic, payload);
  }

  public void sendDeleteNft(String topic, NftDeleteDto nftDeleteDto) {
    LOGGER.info("sending payload='{}' to topic='{}'", nftDeleteDto, topic);
    Gson gson = new Gson();
    String payload = gson.toJson(nftDeleteDto);
    kafkaTemplate.send(topic, payload);
  }

}
