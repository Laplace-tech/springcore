package com.hello.core.autowired;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.lang.Nullable;

import com.hello.core.member.Grade;
import com.hello.core.member.Member;

public class AutowiredTest {

	@Test
	public void AutowiredOption() {
		ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);
	}
	
	
	static class TestBean {
		
		@Autowired(required = false) // 호출 안됨
		public void setNoBean1(Member member) {
			System.out.println("setNoBean1 : " + member);
		}
		
		@Autowired // null 호출
		public void setNoBean2(@Nullable Member member) {
			System.out.println("setNoBean2 : " + member);
		}
		
		@Autowired(required = false) // Optional.empty 호출
		public void setNoBean3(Optional<Member> member) {
			System.out.println("setNoBean3 : " + member);
		}
		
//		@Bean
//		public Member member() {
//			return new Member(1L, "Anna", Grade.BASIC);
//		}
	}
	
}
