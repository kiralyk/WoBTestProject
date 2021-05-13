package com.kiraly93.wobdemo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.kiraly93.wobdemo.Service.DataFetcher;
import com.kiraly93.wobdemo.Service.ListingsReportMaker;

@SpringBootApplication
public class WobdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(WobdemoApplication.class, args);

	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Bean
	public CommandLineRunner run(DataFetcher dataFetcher, ListingsReportMaker reportMaker) throws Exception {
		return args -> {
			dataFetcher.initializeData();
			reportMaker.makeReport();
		};
	}

}
