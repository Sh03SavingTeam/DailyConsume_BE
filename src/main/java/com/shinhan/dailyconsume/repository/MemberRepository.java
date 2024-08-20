package com.shinhan.dailyconsume.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.shinhan.dailyconsume.domain.MemberEntity;

public interface MemberRepository extends JpaRepository<MemberEntity, String>,QuerydslPredicateExecutor<MemberEntity>{
  MemberEntity findByMemberId(String MemberId);
}
