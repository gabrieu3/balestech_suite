package com.balestech.scrapping.tradingview;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@EnableScheduling
@EnableAsync
@EnableFeignClients
@SpringBootApplication
public class TradingviewApplication {

	public static void main(String[] args) {
		SpringApplication.run(TradingviewApplication.class, args);
	}

	@Bean
	public ThreadPoolTaskExecutor taskExecutor() {
		System.out.println("GABRIEL ThreadPoolTaskExecutor taskExecutor");
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(1000);
		executor.setMaxPoolSize(1000);
		executor.setQueueCapacity(1000);
		executor.setThreadNamePrefix("AsyncThread-");
		executor.initialize();
		return executor;
	}
}
