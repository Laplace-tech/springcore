package com.hello.core.scope;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.inject.Provider;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class PrototypeProviderTest {

	@Test
	public void providerTest() {
		ApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);
	
		ClientBean clientBean1 = ac.getBean(ClientBean.class);
		int count1 = clientBean1.logic();
		assertThat(count1).isEqualTo(1);
		
		ClientBean clientBean2 = ac.getBean(ClientBean.class);
		int count2 = clientBean2.logic();
		assertThat(count2).isEqualTo(1);
		
		assertThat(clientBean1).isEqualTo(clientBean2);
	}
	
	@RequiredArgsConstructor
	@Scope("singleton")
	static class ClientBean {
		
//		1. 병신 : private final ApplicationContext ac;
		
//		2. private final ObjectProvider<PrototypeBean> prototypeBeanProvider;
		
		private final Provider<PrototypeBean> provider;
		
		public int logic() {
//			ac.getBean()을 통해서 항상 새로운 프로토타입 빈이 생성됨.
//			PrototypeBean prototypeBean = ac.getBean(PrototypeBean.class);
		
//			PrototypeBean prototypeBean = prototypeBeanProvider.getObject();
			
			PrototypeBean prototypeBean = provider.get();
			prototypeBean.addCount();
			return prototypeBean.getCount();
		}
		
	}
	
	@Getter
	@Scope("prototype")
	static class PrototypeBean {
		
		private int count = 0;
		
		public void addCount() {
			count++;
		}
		
		@PostConstruct
		public void init() {
			System.out.println("PrototypeBean.init : " + this);
		}
		
		@PreDestroy
		public void destroy() {
			System.out.println("PrototypeBean.destroy : " + this);
		}
		
	}
	
}
