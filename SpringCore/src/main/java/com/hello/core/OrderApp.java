package com.hello.core;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.hello.core.member.Grade;
import com.hello.core.member.Member;
import com.hello.core.member.MemberService;
import com.hello.core.order.Order;
import com.hello.core.order.OrderService;

public class OrderApp {

	public static void main(String[] args) {

		@SuppressWarnings("resource")
		ApplicationContext applicationContext 
			= new AnnotationConfigApplicationContext(AutoAppConfig.class);
		
		MemberService memberService = applicationContext.getBean("memberServiceImpl", MemberService.class);
		OrderService orderService = applicationContext.getBean("orderServiceImpl", OrderService.class);
		
		Member member = new Member(1L, "Anna", Grade.VIP);
		memberService.join(member);
		
		Order order = orderService.createOrder(member.getId(), "서광", 20000);
		System.out.println(order);
		System.out.println(order.calculatePrice());
	}
	
}
