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


    // 특정 회원의 특정 월의 결제 내역을 조회하는 메소드
    List<PayHistoryEntity> findByCardMemberIdAndPayDateBetween(String memberId, Timestamp startDate, Timestamp endDate);

    // 특정 가맹점에서 결제한 내역을 조회하는 메소드
    List<PayHistoryEntity> findByStoreStrRegNum(String strRegNum);

    // 특정 카테고리에서 결제한 내역을 조회하는 메소드
    List<PayHistoryEntity> findByConsumeCategoryConsumeId(Long consumeId);

    // 특정 회원의 특정 월에 해당하는 결제 내역을 조회하는 메소드
    @Query("SELECT p FROM PayHistoryEntity p WHERE p.card.member.memberId = :memberId AND MONTH(p.payDate) = :month AND YEAR(p.payDate) = :year")
    List<PayHistoryEntity> findByMemberIdAndMonth(@Param("memberId") String memberId, @Param("month") int month, @Param("year") int year);

}