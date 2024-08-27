package com.shinhan.dailyconsume.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shinhan.dailyconsume.domain.MemberCardEntity;
import com.shinhan.dailyconsume.domain.PayHistoryEntity;

public interface PayHistoryRepository extends JpaRepository<PayHistoryEntity, Long> {

	List<PayHistoryEntity> findByMemberCard(MemberCardEntity memberCard);

   
}