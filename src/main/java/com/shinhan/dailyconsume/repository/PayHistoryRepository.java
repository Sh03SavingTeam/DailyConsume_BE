package com.shinhan.dailyconsume.repository;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.shinhan.dailyconsume.domain.MemberCardEntity;
import com.shinhan.dailyconsume.domain.PayHistoryEntity;

public interface PayHistoryRepository extends JpaRepository<PayHistoryEntity, Long> {

	List<PayHistoryEntity> findByMemberCard(MemberCardEntity memberCard);
	
    // 사용자의 카테고리별 지출 금액을 합산하는 쿼리
    @Query("SELECT p.consumeCategory.consumeName, p.consumeCategory.consumeId, SUM(p.payAmount) " +
            "FROM PayHistoryEntity p " +
            "WHERE p.memberCard.member.memberId = :memberId " +
            "GROUP BY p.consumeCategory.consumeName " +
            "ORDER BY SUM(p.payAmount) DESC")
    List<Object[]> findCategoryPayByMember(@Param("memberId") String memberId);


}