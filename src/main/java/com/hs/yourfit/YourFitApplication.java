package com.hs.yourfit;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

//@EnableJpaAuditing//jpaAuditing 기증을 사용하기 위하여 추가
@SpringBootApplication
public class YourFitApplication {

	public static final String APPLICATION_LOCATIONS = "spring.config.location="
			+ "classpath:secret.yml,"
			+ "classpath:application.yml,"
			+ "/var/lib/jenkins/workspace/real-application.yml,"
			+ "/var/lib/jenkins/workspace/secret.yml";

	public static void main(String[] args) {
		new SpringApplicationBuilder(YourFitApplication.class)
				.properties(APPLICATION_LOCATIONS)
				.run(args);
	}
}
