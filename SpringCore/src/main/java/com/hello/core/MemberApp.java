package com.hello.core;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.hello.core.member.Grade;
import com.hello.core.member.Member;
import com.hello.core.member.MemberService;

public class MemberApp {

	public static void main(String[] args) {
		
		@SuppressWarnings("resource")
		ApplicationContext applicationContext 
			= new AnnotationConfigApplicationContext(AutoAppConfig.class);
		
		MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
		
		Member member = new Member(1L, "Anna", Grade.VIP);
		memberService.join(member);
		
		Member findMember = memberService.findMember(member.getId());
		System.out.println("new Member : " + member.getName());
		System.out.println("find Member : " + findMember.getName());
		
	}
	
}
