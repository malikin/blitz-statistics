package malikin.github.io.blitz.statistics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class BlitzStatisticsApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlitzStatisticsApplication.class, args);
	}
}
