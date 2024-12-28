package com.hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {

	/*
	 * 스프링 컨테이너 생성
	 *  
	 * -> 스프링 빈 생성 
	 * -> 의존관계 주입 
	 * -> 초기화 콜백 
	 * 
	 * -> 사용 
	 * 
	 * -> 소멸전 콜백 
	 * -> 스프링 종료
	 * 
	 * **두 줄 요약**
	 * 1. 만들자마자 한 번 부르고
	 * 2. 뒤지기 전에 한 번 부르고
	 */
	
	@Test
	public void lifeCycleTest() { 
		ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
		NetworkClient client = ac.getBean("networkClient", NetworkClient.class);
		ac.close(); // CALL : ac.close() -> destroy(); and CALL-BACK : ac.close() <- destroy(); 
	}
	
	@Configuration
	static class LifeCycleConfig {

		@Bean
		NetworkClient networkClient() {
			NetworkClient networkClient = new NetworkClient();
			networkClient.setUrl("http://hello-spring.dev");
			return networkClient;
		}
		
	}
	
}
