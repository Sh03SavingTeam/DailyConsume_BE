package com.shinhan.dailyconsume;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class DailyconsumeApplication {

	public static void main(String[] args) {
		SpringApplication.run(DailyconsumeApplication.class, args);
	}

}
