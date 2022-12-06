package com.yb.mongodb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

@SpringBootApplication //(exclude = {MongoAutoConfiguration.class})
//@EnableAutoConfiguration(exclude = {MongoAutoConfiguration.class})
public class MongodbApplication {

	public static void main(String[] args) {
		SpringApplication.run(MongodbApplication.class, args);
	}

}
