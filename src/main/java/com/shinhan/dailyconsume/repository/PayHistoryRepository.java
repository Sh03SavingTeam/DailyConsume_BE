package com.shinhan.dailyconsume.repository;

import org.springframework.stereotype.Repository;

import com.shinhan.dailyconsume.domain.MemberCardEntity;
import com.shinhan.dailyconsume.domain.PayHistoryEntity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface PayHistoryRepository extends JpaRepository<PayHistoryEntity, Long>{
	
	List<PayHistoryEntity> findByMemberCard(MemberCardEntity memberCard);
//	
//	@Query("")
//	Long countByMyHistory(@Param(""));
	
}
