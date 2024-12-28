package com.hello.core.beanTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hello.core.discount.DiscountPolicy;
import com.hello.core.discount.FixDiscountPolicy;
import com.hello.core.discount.RateDiscountPolicy;

public class ApplicationContextExtendsFindTest {
	
	ApplicationContext ac 
		= new AnnotationConfigApplicationContext(TestConfig.class);

	@Test
	@DisplayName("부모 타입으로 조회시, 자식이 둘 이상 있으면, 중복 오류가 발생한다.")
	public void findBeanByParentTypeDuplicate() {
		Executable generateError = () -> ac.getBean("xxxx", DiscountPolicy.class);
		assertThrows(NoUniqueBeanDefinitionException.class, generateError);
	}

	@DisplayName("부모 타입으로 조회시, 자식이 둘 이상 있으면, 빈 이름을 지정하면 된다")
	public void findBeanByParentTypeBeanName() {
		DiscountPolicy rateDiscountPolicy = ac.getBean("rateDiscountPolicy", DiscountPolicy.class);
		assertThat(rateDiscountPolicy).isInstanceOf(DiscountPolicy.class);
	}
	
	@Test
	@DisplayName("특정 하위 타입으로 조회 (병신)")
	public void findBeanBySubType() {
		DiscountPolicy rateDiscountPolicy = ac.getBean(RateDiscountPolicy.class);
		assertThat(rateDiscountPolicy).isInstanceOf(DiscountPolicy.class);
	}
	
	@Test
	@DisplayName("부모 타입으로 모두 조회하기")
	public void findAllBeanByParentType() {
		Map<String, DiscountPolicy> beansOfType = ac.getBeansOfType(DiscountPolicy.class);
		assertThat(beansOfType.size()).isEqualTo(2);
		beansOfType.keySet().stream()
				.map(key -> String.format("[key:%s, value:%s]", key, beansOfType.get(key)))
				.forEach(System.out::println);
	}
	
	@Test
	@DisplayName("부모 타입으로 모두 조회하기 - Object.class")
	public void findAllBeanByObjectType() {
		Map<String, Object> beansOfType = ac.getBeansOfType(Object.class);
		beansOfType.keySet().stream()
			.map(key -> String.format("[key:%s, value:%s]", key, beansOfType.get(key)))
			.forEach(System.out::println);
	}

    @Configuration
    static class TestConfig {
		@Bean
		DiscountPolicy rateDiscountPolicy() {
			return new RateDiscountPolicy();
		}
		
		@Bean
		DiscountPolicy fixDiscountPolicy() {
			return new FixDiscountPolicy();
		}
	}
	
}
