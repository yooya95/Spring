package com.oracle.oBootDBConnect.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oracle.oBootDBConnect.domain.Member1;
import com.oracle.oBootDBConnect.repository.MemberRepository;


@Service
public class MemberService {
   private final MemberRepository memberRepository;
   @Autowired
      public MemberService(MemberRepository memberRepository) {
         this.memberRepository = memberRepository;
   
   }
      // 회원가입
      public Long memberSave(Member1 member) {
         System.out.println("MemberService memberSave start...");
         memberRepository.save(member);
         return member.getId();
      }
      
      // 회원조회
      public List<Member1> findMembers(){
         System.out.println("MemberService allMembers start...");
         List<Member1> memList = null;
         memList = memberRepository.findAll();
         System.out.println("memList.size()->"+memList.size());
         return memList;
      }
   }