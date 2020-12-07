package com.hs.lunpair;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableCaching
@EnableJpaAuditing//jpaAuditing 기증을 사용하기 위하여 추가
public class LunpairApplication {

	public static final String APPLICATION_LOCATIONS = "spring.config.location="
			+ "classpath:secret.yml,"
			+ "classpath:application.yml,"
			+ "/var/lib/jenkins/workspace/real-application.yml,"
			+ "/var/lib/jenkins/workspace/secret.yml";

	public static void main(String[] args) {
		new SpringApplicationBuilder(LunpairApplication.class)
				.properties(APPLICATION_LOCATIONS)
				.run(args);
	}
}
