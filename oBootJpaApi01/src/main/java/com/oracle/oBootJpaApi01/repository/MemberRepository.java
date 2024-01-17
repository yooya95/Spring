package com.oracle.oBootJpaApi01.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.oracle.oBootJpaApi01.domain.Member;


public interface MemberRepository {
	Long  		  save(Member member);
	List<Member>  findAll();
	int           updateByMember(Member member);
	Member 		  findByMember(Long memberId);
}
