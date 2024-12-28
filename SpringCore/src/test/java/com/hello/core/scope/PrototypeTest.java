package com.hello.core.scope;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

public class PrototypeTest {

	@Test
	public void prototypeBeanFind() {
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
		
		PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
		System.out.println("find prototypeBean1 : " + prototypeBean1);
		
		PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
		System.out.println("find prototypeBean2 : " + prototypeBean2);

		assertThat(prototypeBean1).isNotSameAs(prototypeBean2);
		ac.close();
	}
	
	@Scope("prototype")
	static class PrototypeBean {
		
		@PostConstruct // 빈 생성(Construct) 이후(Post) 
		public void init() {
			System.out.println("PrototypeBean.init : " + this);
		}
		
		// 호출 안 됨.
		@PreDestroy // 빈 소멸(Destroy) 전(Pre)
		public void destroy() {
			System.out.println("PrototypeBean.destroy : " + this);
		}
	}
	
}
