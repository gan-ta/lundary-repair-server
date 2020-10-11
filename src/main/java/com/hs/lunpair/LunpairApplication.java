package com.hs.lunpair;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class LunpairApplication {

	public static final String APPLICATION_LOCATIONS = "spring.config.location="
			+ "classpath:application.yml";

	public static void main(String[] args) {
		new SpringApplicationBuilder(LunpairApplication.class)
				.properties(APPLICATION_LOCATIONS)
				.run(args);
	}
}
