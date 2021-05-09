package com.Market.NFT;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class NftApplication {

	public static void main(String[] args) {
		SpringApplication.run(NftApplication.class, args);
	}

}
