package com.hello.core.discount;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.hello.core.member.Grade;
import com.hello.core.member.Member;

@Component
@Primary
@Qualifier("mainDiscountPolicy")
//@MainDiscountPolicy // 굳이? 
public class RateDiscountPolicy implements DiscountPolicy {

	private final int discountPercent = 10;
	
	@Override
	public int discount(Member member, int price) {
		return member.getGrade() == Grade.VIP ? price * discountPercent / 100 : 0;
	}
	
}
