package com.shinhan.dailyconsume.calendar;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.shinhan.dailyconsume.domain.PayHistoryEntity;

public interface CalendarRepository extends JpaRepository<PayHistoryEntity, Long>{
	
	// 특정 월과 연도의 결제 내역 조회 쿼리
	@Query("SELECT p FROM PayHistoryEntity p WHERE p.memberCard.member.memberId = :memberId AND FUNCTION('MONTH', p.payDate) = :month AND FUNCTION('YEAR', p.payDate) = :year")
	List<PayHistoryEntity> findByMemberAndMonthAndYear(@Param("memberId") String memberId, @Param("month") int month, @Param("year") int year);

	// 특정 연도, 월, 일의 결제 내역 조회 쿼리
    @Query("SELECT p FROM PayHistoryEntity p WHERE p.memberCard.member.memberId = :memberId AND FUNCTION('DAY', p.payDate) = :day AND FUNCTION('MONTH', p.payDate) = :month AND FUNCTION('YEAR', p.payDate) = :year")
    List<PayHistoryEntity> findByMemberAndDayAndMonthAndYear(@Param("memberId") String memberId, @Param("day") int day, @Param("month") int month, @Param("year") int year);
    
    // 일별 결제 내역 상세 조회 쿼리
    @Query("SELECT p FROM PayHistoryEntity p WHERE p.memberCard.member.memberId = :memberId AND p.payId = :payId")
    PayHistoryEntity findByMemberIdAndPayId(@Param("memberId") String memberId, @Param("payId") Long payId);

    // myPayCheck 값을 업데이트하는 메서드 (쿼리로 할 경우)
    // @Modifying
    // @Query("UPDATE PayHistoryEntity p SET p.myPayCheck = :myPayCheck WHERE p.memberCard.member.memberId = :memberId AND p.payId = :payId")
    // void updateMyPayCheck(@Param("memberId") String memberId, @Param("payId") Long payId, @Param("myPayCheck") Integer myPayCheck);
    // 단순히 엔티티를 가져와 수정하는 방법을 사용할 경우 위의 @Modifying 쿼리는 필요 없습니다.
    
}
