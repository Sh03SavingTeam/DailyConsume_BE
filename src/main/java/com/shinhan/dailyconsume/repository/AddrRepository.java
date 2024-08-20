package com.shinhan.dailyconsume.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.shinhan.dailyconsume.domain.AddressEntity;
import com.shinhan.dailyconsume.domain.MemberEntity;

public interface AddrRepository extends JpaRepository<AddressEntity, Long>, QuerydslPredicateExecutor<AddressEntity> {

	List<AddressEntity> findByMember(MemberEntity member);
}
