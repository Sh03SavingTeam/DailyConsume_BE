package com.shinhan.dailyconsume.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import com.shinhan.dailyconsume.domain.MemberCardEntity;
import com.shinhan.dailyconsume.domain.MemberEntity;

import java.util.List;


public interface MemberCardRepository extends JpaRepository<MemberCardEntity, String>, 
		QuerydslPredicateExecutor<MemberCardEntity> {
	
	List<MemberCardEntity> findByMember(MemberEntity member);
	
	MemberCardEntity findByCardNum(String cardNum);
	
	
}
