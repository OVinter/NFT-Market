package com.Market.NFT.Kafka;

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
  @Autowired
  private JavaSerializer javaSerializer;

  public void send(String topic, TestDto testDto) {
    LOGGER.info("sending payload='{}' to topic='{}'", testDto, topic);
    Gson gson = new Gson();
    String payload = gson.toJson(testDto);
    kafkaTemplate.send(topic, payload);
  }

}
