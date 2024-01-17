package com.oracle.oBootHello.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.oracle.oBootHello.domain.Member1;
import com.oracle.oBootHello.service.MemberService;

@Controller
public class MemberController {
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	//전통적
	//MemberService memberService = new MemberService();
	
	//DI 방식 @Autowried 의존성주입 final(다시는 변경하지 않겠다는 의미로 넣음) 
	private final MemberService memberService;
	
	@Autowired
	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}
	
	@GetMapping(value="members/memberForm")
	public String memberForm() {
		System.out.println("MemberController / members/memberForm Start..");
		//Prefix -> templates
		//suffix -> .html
		
		return "members/memberForm";
	}
	
	@PostMapping(value="/members/save")
	public String save(Member1 member1) {
		System.out.println("MemberController /members/save Start...");
		System.out.println("MemberController /members/save member1.getName()->"+member1.getName());
		Long id = memberService.memberSave(member1);
		System.out.println("MemberController /members/save id->"+id);
		return "redirect:/";

	}
	//조회는 getmapping권장!
	@GetMapping(value="/members/memberList")	
	public String memberList(Model model) {
		logger.info("memberList start..." );
		List<Member1>memberLists = memberService.allMembers();
		model.addAttribute("memberLists",memberLists);
		logger.info("memberList.size()-> {}", memberLists.size());
		return "members/memberList";
	
	}
	
	
}	
