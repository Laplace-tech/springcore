package com.hello.core.member;

public interface MemberRepository {
	
	abstract public void save(Member member);
	abstract public Member findById(Long id);
	
}
