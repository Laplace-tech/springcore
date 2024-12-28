package com.hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class StatefulServiceTest {

	@Test
	public void statefulServiceSingleton() {
		@SuppressWarnings("resource")
		ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
		
		StatefulService statefulService1 = ac.getBean("statefulService", StatefulService.class);
		StatefulService statefulService2 = ac.getBean("statefulService", StatefulService.class);
	
		statefulService1.order("userA", 10000);
		statefulService2.order("userB", 30000);

		System.out.println("price : " + statefulService1.getPrice());
		
		Assertions.assertThat(statefulService1.getPrice()).isEqualTo(30000);
	}
	
	@Configuration
	static class TestConfig {
		
		@Bean
		public StatefulService statefulService() {
			return new StatefulService();
		}
	}
	
}
