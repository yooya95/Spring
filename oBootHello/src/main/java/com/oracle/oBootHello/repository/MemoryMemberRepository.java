package com.oracle.oBootHello.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.oracle.oBootHello.domain.Member1;

@Repository
public class MemoryMemberRepository implements MemberRepository {
	//                   key  value
	private static Map<Long, Member1> store = new HashMap<Long, Member1>();
	// >> store: 회원정보를 저장하는데 사용되는 map<long, memeber1>타입의 변수
	// HashMap은 Java에서 제공하는 해시 맵 자료 구조로, 키-값 쌍을 저장하고 검색하는 데 사용됩니다.
	// Long 타입의 키와 Member1 객체를 저장할 수 있도록 생성된 빈 HashMap 객체를 store 변수에 할당
	
	private static Long sequence = 0L;
	// >>회원의 고유 ID 값을 생성하기 위한 변수로, 회원이 추가될 때마다 1씩 증가
	@Override
	public Member1 save(Member1 member1) {
		member1.setId(++sequence);
		store.put(member1.getId(), member1);
		System.out.println("MemoryMemberRepository sequence ->"+sequence);
		System.out.println("MemoryMemberRepository memeber.getName()->"+member1.getName());
		
		return member1;
	}

	@Override
	public List<Member1> findAll() {
		System.out.println("MemoryMemberRepository findAll start...");
		//store의  value(Member1)
		List<Member1> listMember = new ArrayList<>(store.values()); //store.values - memeber1
		
		System.out.println("MemoryRepository findAll listMember.size()->"+listMember.size());
		return listMember;
	}

}
