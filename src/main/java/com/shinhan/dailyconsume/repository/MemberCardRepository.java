package com.shinhan.dailyconsume.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import com.shinhan.dailyconsume.domain.MemberCardEntity;

public interface MemberCardRepository extends JpaRepository<MemberCardEntity, String>, 
		QuerydslPredicateExecutor<MemberCardEntity> {
	
}
