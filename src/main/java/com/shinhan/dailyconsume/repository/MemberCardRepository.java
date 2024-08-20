package com.shinhan.dailyconsume.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shinhan.dailyconsume.domain.MemberCardEntity;
import com.shinhan.dailyconsume.domain.MemberEntity;

@Repository
public interface MemberCardRepository extends JpaRepository<MemberCardEntity, String>{
	
	List<MemberCardEntity> findByMember(MemberEntity member);
	
	
}
