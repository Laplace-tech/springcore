package com.hello.core.discount;

import org.springframework.stereotype.Component;

import com.hello.core.member.Grade;
import com.hello.core.member.Member;
//@Qualifier("fixDiscountPolicy")
@Component
public class FixDiscountPolicy implements DiscountPolicy {

	private int discountFixAmount = 1000;
	
	@Override
	public int discount(Member member, int price) {
		return member.getGrade() == Grade.VIP ? discountFixAmount : 0;
	}

}
