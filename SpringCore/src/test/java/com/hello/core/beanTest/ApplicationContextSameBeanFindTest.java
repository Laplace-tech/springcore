package com.hello.core.beanTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hello.core.member.MemberRepository;
import com.hello.core.member.MemoryMemberRepository;

public class ApplicationContextSameBeanFindTest {

	ApplicationContext ac 
		= new AnnotationConfigApplicationContext(SameBeanConfig.class);

	@Test
	@DisplayName("타입으로 조회시 같은 타입이 둘 이상 있으면, 중복 오류가 발생한다")
	void findBeanByTypeDuplicate() {
		assertThrows(NoUniqueBeanDefinitionException.class,
				() -> ac.getBean(MemberRepository.class));
	}

	@Test
	@DisplayName("타입으로 조회시 같은 타입이 둘 이상 있으면, 빈 이름을 지정하면 된다")
	void findBeanByName() {
		MemberRepository memberRepository1 = ac.getBean("memberRepository1",
				MemberRepository.class);
		System.out.println("memberRepository1 : " + memberRepository1);
		System.out.println("memberRepository1.getClass() : " + memberRepository1.getClass());
	
//		MemberRepository memberRepository2 = ac.getBean("memberRepository2",
//				MemberRepository.class);
//		System.out.println("memberRepository2 : " + memberRepository2);
//		System.out.println("memberRepository2.getClass() : " + memberRepository2.getClass());
		
		assertThat(memberRepository1).isInstanceOf(MemberRepository.class);
	}

	@Test
	@DisplayName("특정 타입을 모두 조회하기")
	void findAllBeanByType() {
		Map<String, MemberRepository> beansOfType = ac.getBeansOfType(MemberRepository.class);
		beansOfType.keySet().stream()
				.map(key -> String.format("[key:%s, value:%s]", key, beansOfType.get(key)))
				.forEach(System.out::println);
		assertThat(beansOfType.size()).isEqualTo(2);
	}

	@Configuration
	static class SameBeanConfig {

		@Bean
		public MemberRepository memberRepository1() {
			return new MemoryMemberRepository();
		}

		@Bean
		public MemberRepository memberRepository2() {
			return new MemoryMemberRepository();
		}
	}
}
