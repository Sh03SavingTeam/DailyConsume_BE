package com.shinhan.dailyconsume.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shinhan.dailyconsume.domain.MemberEntity;



public interface MemberRepository extends JpaRepository<MemberEntity, String>{
	MemberEntity findByMemberId(String MemberId);
}
