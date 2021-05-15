package com.Market.NFT.Kafka;

import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Serializer;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Map;

@Component
public class JavaSerializer implements Serializer<TestDto> {
  @Override
  public void configure(Map<String, ?> configs, boolean isKey) {
    Serializer.super.configure(configs, isKey);
  }

  @Override
  public byte[] serialize(String s, TestDto testDto) {
    try {
      ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
      ObjectOutputStream objectStream = new ObjectOutputStream(byteStream);
      objectStream.writeObject(testDto);
      objectStream.flush();
      objectStream.close();
      return byteStream.toByteArray();
    }
    catch (IOException e) {
      throw new IllegalStateException("Can't serialize object: " + testDto, e);
    }
  }

  @Override
  public byte[] serialize(String topic, Headers headers, TestDto data) {
    return Serializer.super.serialize(topic, headers, data);
  }

  @Override
  public void close() {
    Serializer.super.close();
  }
}
