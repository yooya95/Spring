package com.oracle.oBootJpaApi01.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oracle.oBootJpaApi01.domain.Member;
import com.oracle.oBootJpaApi01.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {
	private final MemberRepository memberRepository;
	
	//전체회원 조회 API
	public List<Member> getListAllMember(){
		List<Member>listMember = memberRepository.findAll();
		System.out.println("MemberService getListAllMember listMember.size()->"+listMember.size());
		return listMember;
	}
	//회원가입 API
	public Long saveMember(@Valid Member member) {
		System.out.println("MemberService join member.size()->"+member.getName());
		Long id = memberRepository.save(member);
		return id;
	}
	
	public void updateMember(Long id, String name, Long sal) {
		Member member = new Member();
		member.setId(id);
		member.setName(name);
		member.setSal(sal);
		System.out.println("MemberService updateMember member.getName()->"+member.getName());
		System.out.println("MemberService updateMember member.getSal()_>"+member.getSal());
		memberRepository.updateByMember(member);
		return;
	}
	public Member findByMember(Long memberId) {
		Member member = memberRepository.findByMember(memberId);
		System.out.println("MemberService findByMember member.get().getId()->"+member.getId());
		System.out.println("MemberService findByMember member.get().getName()->"+member.getName());
		return member;
	}
}
