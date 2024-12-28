package com.hello.core.autowired;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.hello.core.AutoAppConfig;
import com.hello.core.discount.DiscountPolicy;
import com.hello.core.member.Grade;
import com.hello.core.member.Member;

public class AllBeanTest {

	@Test
	public void findAllBean() {

		// 지정된 구성 클래스(AutoAppConfig 및 DiscountService)를 읽어서, Spring 컨테이너에 빈을 등록합니다.
		ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class, DiscountService.class);

		Stream.of(ac.getBeanDefinitionNames())
				.map(beanName -> String.format("Bean name : %s, Object : %s", beanName, ac.getBean(beanName)))
				.forEach(System.out::println);

		// given
		DiscountService discountService = ac.getBean(DiscountService.class);
		Member member = new Member(1L, "Angelia", Grade.VIP);

		// when
		int discountPrice = discountService.discount(member, 30000, "rateDiscountPolicy");

		// then
		assertThat(discountService).isInstanceOf(DiscountService.class);
		assertThat(discountPrice).isEqualTo(30000 / 10);
	}

	static class DiscountService {

		// map 의 Key 에 스프링 빈의 이름을 넣어주고, 그에 대한 Value 로
		// DiscountPolicy 타입으로 조회한 모든 스프링 빈을 담아준다.
		private final Map<String, DiscountPolicy> policyMap;
		// DiscountPolicy 타입으로 조회한 모든 스프링 빈을 담아준다.
		private final List<DiscountPolicy> policies;

		public DiscountService(Map<String, DiscountPolicy> policyMap, List<DiscountPolicy> policies) {
			this.policyMap = policyMap;
			this.policies = policies;
			System.out.println("policyMap : " + policyMap);
			System.out.println("policies : " + policies);
		}

		public int discount(Member member, int price, String discountKey) {

			DiscountPolicy discountPolicy = policyMap.get(discountKey);
			int discountPrice = discountPolicy.discount(member, price);

			System.out.println("discount Key : " + discountKey);
			System.out.println("discountPolicy : " + discountPolicy);
			System.out.println("original price : " + price);
			System.out.println("discount price : " + discountPrice);

			return discountPrice;
		}

	}
}
