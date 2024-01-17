package com.oracle.oBootJpaApi01.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.oracle.oBootJpaApi01.domain.Member;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor //지정된 속성들에 대해서만 생성자를 만들어줌 
public class JpaMemberRepository implements MemberRepository {
	
	private final EntityManager em;
	
	
	@Override
	public Long save(Member member) {
		System.out.println("JpaMemberRepository save before...");
		em.persist(member);
		return member.getId();
	}

	@Override
	public List<Member> findAll() {
		List<Member> memberList = em.createQuery("select m from Member m", Member.class)
									.getResultList();
		System.out.println("JpaMemberRepository findAll memberList.size()->"+memberList.size());
		return memberList;
	}

	@Override
	public int updateByMember(Member member) {
		int result = 0;
		Member member3 = em.find(Member.class, member.getId());
		if(member3 !=null) {
			//회원저장
			member3.setName(member.getName());
			member3.setSal(member.getSal());
			result=1;
			System.out.println("JpaMemberRepository updateByMember Update...");
		
		}else {
			result=0;
			System.out.println("JpaMemberRepository updateByMember No Exist...");
		}
			return result;
	}

	@Override
	public Member findByMember(Long memberId) {
		Member member = em.find(Member.class, memberId);
		return member;
	}

}
