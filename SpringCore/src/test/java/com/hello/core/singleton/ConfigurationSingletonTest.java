package com.hello.core.singleton;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.hello.core.AutoAppConfig;
import com.hello.core.member.MemberRepository;
import com.hello.core.member.MemberServiceImpl;
import com.hello.core.order.OrderServiceImpl;

public class ConfigurationSingletonTest {
	
	@Test
	public void configurationTest() {
		
		@SuppressWarnings("resource")
		ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);
		
		MemberServiceImpl memberService = ac.getBean("memberServiceImpl", MemberServiceImpl.class);
		OrderServiceImpl orderService = ac.getBean("orderServiceImpl", OrderServiceImpl.class);
		MemberRepository memberRepository = ac.getBean("memoryMemberRepository", MemberRepository.class);
		
		// 모두 같은 인스턴스를 참고하고 있다.
		System.out.println("memberService -> memberRepository = " + memberService.getMemberRepository());
		System.out.println("orderService -> memberRepository = " + orderService.getMemberRepository());
		System.out.println("memberRepository = " + memberRepository);

		assertThat(memberService.getMemberRepository()).isSameAs(memberRepository);
		assertThat(orderService.getMemberRepository()).isSameAs(memberRepository);
	}
	
	@Test
	public void configurationDeep() {
		@SuppressWarnings("resource")
		ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);
		
		// AppConfig도 스프링 빈으로 등록된다.
		AutoAppConfig bean = ac.getBean(AutoAppConfig.class); 
		// AppConfig bean <- (@Configuration) AppConfig@CGLIB 

		// bean : class com.hello.core.AppConfig$$****SpringCGLIB****$$0
		// without @Configuration : bean : class com.hello.core.AppConfig
		System.out.println("bean : " + bean.getClass());
	}
	
}
