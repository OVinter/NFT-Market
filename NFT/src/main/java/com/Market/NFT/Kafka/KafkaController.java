package com.Market.NFT.Kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/kafka")
public class KafkaController {

  KafkaProducer kafkaProducer;

  @Autowired
  KafkaController(KafkaProducer kafkaProducer) {
    this.kafkaProducer = kafkaProducer;
  }

  @GetMapping()
  public void getKafka(@RequestBody TestDto testDto) {
    kafkaProducer.send("test", testDto);
  }

}
