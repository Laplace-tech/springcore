package com.hello.core;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hello.core.discount.DiscountPolicy;
import com.hello.core.member.Grade;
import com.hello.core.member.Member;

@SpringBootTest
public class RateDiscountPolicyTest {
	
	@Autowired
//	@Qualifier("mainDiscountPolicy") // xxxDiscountPolicy를 주입
//	@Primary 있잖음 ㅋㅋ
//  @MainDiscountPolicy
	private DiscountPolicy discountPolicy;

	@Test
	@DisplayName("VIP는 10% 할인이 적용되어야 한다")
	void vip_o() { 
		
		//given
		Member member = new Member(1L, "G-DRAGON", Grade.VIP);
		
		//when
		int discount = discountPolicy.discount(member, 30000);
		
		//then
		assertThat(discount).isEqualTo(3000);
	}
	
	@Test
	@DisplayName("VIP가 아니면 할인이 적용되지 않아야 한다.")
	void vip_x() {
		//given
		Member member = new Member(2L, "G-DRAGON", Grade.BASIC);
		
		//when
		int discount = discountPolicy.discount(member, 30000);
		
		//then
		assertThat(discount).isEqualTo(0);
	}
}
