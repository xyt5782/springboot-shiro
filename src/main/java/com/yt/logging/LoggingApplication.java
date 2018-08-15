package com.yt.logging;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.yt.logging.mapper")
public class LoggingApplication {
	public static void main(String[] args) {
		SpringApplication.run(LoggingApplication.class, args);
	}
}
