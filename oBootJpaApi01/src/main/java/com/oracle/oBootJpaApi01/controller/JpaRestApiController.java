package com.oracle.oBootJpaApi01.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
//Controller + ResponseBody ajax 와 api 작업할때 쓰여지는 어노테이션

import com.oracle.oBootJpaApi01.domain.Member;
import com.oracle.oBootJpaApi01.service.MemberService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@RestController
@RequiredArgsConstructor
@Slf4j //logger
public class JpaRestApiController {
		
	private final MemberService memberService;	
	
	// postman ---> Body --> raw---> JSON	 
    //  예시    {	    "name" : "kkk222"	    }
	// @RequestBody : Json(member)으로 온것을  --> Member member Setting
	
	//@RequestBody 어노테이션을 명시함으로써 MessageConverter를 통한 데이터 변환 과정을 적용
	@PostMapping("/restApi/v1/memberSave")                  //유효성체크
	public CreateMemberResponse saveMemberV1( @RequestBody @Valid Member member) {
		System.out.println("JpaRestApiController /api/v1/memberSave member.getId()->"+member.getId());
		log.info("member.getName()->{}", member.getName());
		log.info("member.getSal()->{}", member.getSal());
		
		Long id = memberService.saveMember(member);
		return new CreateMemberResponse(id);
		
	}
	@Data
	static class CreateMemberRequest {
		@NotEmpty
		private String name;
		private Long sal;
	}
	@Data // 내장클라스를 만듬 (다른 곳에서 불러줄 이유가 없음)
	class CreateMemberResponse {
		private final Long id;
		/*
		 * public CreateMemberResponse(Long id) { this.id = id; }
		 */
	}
	//Bad API
	@GetMapping("/restApi/v1/members")
	public List<Member> memberVer1(){
		System.out.println("JpaRestApiController / restApi/v1/members start..");
		List<Member> listMember = memberService.getListAllMember();
		return listMember;
	}
	//T는 인스턴스를 생성할 때 구체적인 타입으로 변경 -->유연성
	@Data
	@AllArgsConstructor
	class Result<T>{
		private final int totCount; // 총인원수증가
		private final T data;
	}
	
	@Data
	@AllArgsConstructor
	class MemberRtnDto{
		private String name;
		private Long sal;
	}
	
	//Good API Easy Version
	// 목표 : 이름 & 급여만 전송
	@GetMapping("/restApi/v21/members")
	public Result membersVer21() {
		List<Member> findMembers = memberService.getListAllMember();
		System.out.println("JpaRestApiController restApi/v21/members findMembers.size()->"+findMembers.size());
		
		List<MemberRtnDto> resultList = new ArrayList<MemberRtnDto>();
		//List<Member> findMembers를 --> List<MemberRtnDto> resultList 이전
		//이전 목적: 반드시 필요한 data(이름,급여)만 보여준다(외부 노출 최대한 금지)
		
		for(Member member : findMembers) {
			MemberRtnDto memberRtnDto = new MemberRtnDto(member.getName(), member.getSal());
			System.out.println("/restApi/v21/members getName->"+memberRtnDto.getName());
			System.out.println("/restApi/v21/members getSal->"+memberRtnDto.getSal());
			resultList.add(memberRtnDto);
			
		}
		System.out.println("/restApi/v21/members resultList.size()->"+resultList.size());
	//	return new Result(resultList.size(),resultList);
		return new Result(resultList.size(),resultList);
	}
	//Good API Easy Version
	// 목표 : 이름 & 급여만 전송
	@GetMapping("/restApi/v22/members")
	public Result membersVer22() {
		List<Member> findMembers = memberService.getListAllMember();
		System.out.println("JpaRestApiController restApi/v21/members findMembers.size()->"+findMembers.size());
		
		/* List<MemberRtnDto> resultList = new ArrayList<MemberRtnDto>(); */
		/*
		 * //List<Member> findMembers를 --> List<MemberRtnDto> resultList 이전 //이전 목적: 반드시
		 * 필요한 data(이름,급여)만 보여준다(외부 노출 최대한 금지)
		 * 
		 * for(Member member : findMembers) { MemberRtnDto memberRtnDto = new
		 * MemberRtnDto(member.getName(), member.getSal());
		 * System.out.println("/restApi/v22/members getName->"+memberRtnDto.getName());
		 * System.out.println("/restApi/v22/members getSal->"+memberRtnDto.getSal());
		 * resultList.add(memberRtnDto);
		 * 
		 * }
		 */
		
	//  자바 8에서 추가한 스트림(Streams)은 람다를 활용할 수 있는 기술 중 하나
		List<MemberRtnDto> memberCollect = findMembers.stream()
													  .map(m-> new MemberRtnDto(m.getName(),m.getSal()))
													  .collect(Collectors.toList())
													  ;
		
		System.out.println("/restApi/v22/members memberCollect.size()->"+memberCollect.size());
		
		return new Result(memberCollect.size(), memberCollect);

	}
	@Data
	static class UpdateMemberRequest{
		private String name;
		private Long sal;
	}
	@Data
	@AllArgsConstructor
	class UpdateMemberResponse {
		private Long id;
		private String name;
		private Long sal;
		
	}
	/*
	 *   수정 API
	 *   PUT 방식을사용했는데, PUT은 전체 업데이트를 할 때 사용
	 *   URI 상에서 '{ }' 로 감싸여있는 부분과 동일한 변수명을 사용하는 방법
	 *   해당 데이터가 있으면 업데이트를 하기에 
	 *   PUT요청이 여러번 실행되어도 해당 데이터는 같은 상태이기에 멱등
	 */
	
	//수정 putmapping
	@PutMapping("/restApi/v21/members/{id}")
	public UpdateMemberResponse updateMemberV21(@PathVariable("id") Long id,
												@RequestBody @Valid UpdateMemberRequest uMember) {
		memberService.updateMember(id, uMember.getName(), uMember.getSal());
		Member findMember = memberService.findByMember(id);
		return new UpdateMemberResponse(findMember.getId(), findMember.getName(), findMember.getSal());
	}
	
	
}
