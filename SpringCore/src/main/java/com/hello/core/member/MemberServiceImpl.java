package com.hello.core.member;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class MemberServiceImpl implements MemberService {

	private final MemberRepository memberRepository;

//	@Autowired // (likewise):ac.getBean(MemberRepository.class)
//	public MemberServiceImpl(MemberRepository memberRepository) {
//		this.memberRepository = memberRepository;
//	}
	
	public MemberRepository getMemberRepository() {
		return this.memberRepository;
	}
	
	@Override
	public void join(Member member) {
		this.memberRepository.save(member);
	}

	@Override
	public Member findMember(Long memberId) {
		return memberRepository.findById(memberId);
	}

}
