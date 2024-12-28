package com.hello.core.scan;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.hello.core.AutoAppConfig;
import com.hello.core.member.MemberService;
import com.hello.core.member.MemberServiceImpl;

@SpringBootTest
public class AutoAppConfigTest {

	@Test
	public void basicScan() {
		ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);
		
		String[] beanDefinitionNames = ac.getBeanDefinitionNames();
		
		Arrays.stream(beanDefinitionNames).map(
				beanDefinitionName -> "name : " + beanDefinitionName + " object : " + ac.getBean(beanDefinitionName))
				.forEach(System.out::println);
		
		MemberService memberService = ac.getBean(MemberService.class);
		
		assertThat(memberService).isInstanceOf(MemberService.class);
		assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
	}

}
