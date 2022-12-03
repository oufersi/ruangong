package com.example.forum;
import com.example.forum.Repositories.*;
import com.example.forum.Entities.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ForumApplication {

	private final Logger log = LoggerFactory.getLogger(ForumApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(ForumApplication.class, args);
	}

	@Bean
	CommandLineRunner demo(UserRepository userRepository, PassageRepository passageRepository){
		return args -> {
			NormalUser user = new NormalUser("administrater", "12345");
			userRepository.save(user);
			passageRepository.save(new Passage("Hello", "Hello everyone!", user));
			log.info("add administrater account and hello passage");

			passageRepository.save(new Passage("Rules", "Don't use rude statements", user));
			log.info("add rules");

			user = new NormalUser("wangpeng", "12345");
			userRepository.save(user);

			user = new NormalUser("zhanghui", "12cdx");
			userRepository.save(user);

			user = new NormalUser("zhangsan", "idjf");
			userRepository.save(user);
			
			user = new NormalUser("zhouchang", "bccda");
			userRepository.save(user);

			log.info("add 4 normal users to database");
		};
	}
}
