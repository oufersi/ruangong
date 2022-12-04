package com.example.forum;
import com.example.forum.dao.*;
import com.example.forum.entities.*;

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
	CommandLineRunner demo(AdministraterRepository administraterRepository,NormalUserRepository nUserRepository, PostContentRepository passageRepository){
		return args -> {
			Administrater adm = new Administrater("administrater", "12345");
			administraterRepository.save(adm);
			passageRepository.save(new PostContent("Hello", "Hello everyone!", adm));
			log.info("add administrater account and hello passage");

			passageRepository.save(new PostContent("Rules", "Don't use rude statements", adm));
			log.info("add rules");

			NormalUser user = new NormalUser("wangpeng", "12345");
			nUserRepository.save(user);

			user = new NormalUser("zhanghui", "12cdx");
			nUserRepository.save(user);

			user = new NormalUser("zhangsan", "idjf");
			nUserRepository.save(user);
			
			user = new NormalUser("zhouchang", "bccda");
			nUserRepository.save(user);

			log.info("add 4 normal users to database");
		};
	}
}
