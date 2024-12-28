package com.hello.core.member;

public interface MemberService {
	abstract public void join(Member member);
	abstract public Member findMember(Long memberId);
}
