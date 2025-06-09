package com.shinhan.dailyconsume.repository;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.shinhan.dailyconsume.domain.MemberCardEntity;
import com.shinhan.dailyconsume.domain.PayHistoryEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PayHistoryRepository extends JpaRepository<PayHistoryEntity, Long> {

	List<PayHistoryEntity> findByMemberCard(MemberCardEntity memberCard);

	@Query("SELECT SUM(p.payAmount) FROM PayHistoryEntity p WHERE p.memberCard.member.memberId = :memberId AND p.payDate BETWEEN :startDate AND :endDate")
	int sumPayAmountBetweenDates(@Param("memberId") String memberId, @Param("startDate") Timestamp startDate, @Param("endDate") Timestamp endDate);

	@Query("select ph, m from PayHistoryEntity ph inner join ph.memberCard mc inner join mc.member m inner join ph.stores s where m.memberId = :memId order by ph.payDate desc")
    List<PayHistoryEntity> findByMemberId(String memId, Pageable pageable);
}