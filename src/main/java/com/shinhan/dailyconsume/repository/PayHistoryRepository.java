package com.shinhan.dailyconsume.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.shinhan.dailyconsume.domain.MemberCardEntity;
import com.shinhan.dailyconsume.domain.PayHistoryEntity;

public interface PayHistoryRepository extends JpaRepository<PayHistoryEntity, Long> {

	List<PayHistoryEntity> findByMemberCard(MemberCardEntity memberCard);

	@Query("select ph, m from PayHistoryEntity ph inner join ph.memberCard mc inner join mc.member m inner join ph.stores s where m.memberId = :memId order by ph.payDate desc")
    List<PayHistoryEntity> findByMemberId(String memId, Pageable pageable);
   
}