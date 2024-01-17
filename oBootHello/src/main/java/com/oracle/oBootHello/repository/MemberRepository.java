package com.oracle.oBootHello.repository;



import java.util.List;

import com.oracle.oBootHello.domain.Member1;

public interface MemberRepository {
	Member1 	  save(Member1 member1); //member1의 객체를 받아서 저장하고, 저장한 멤버를 반환
	List<Member1> findAll();			 //findall() 모든 Mmember1객체를 검색하는 메서드, member1객체의 목록을 반환한다.
}
