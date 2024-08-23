package com.shinhan.dailyconsume.calendar;

import java.util.List;
import java.util.Map;

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
    
    @Query(value = "SELECT ph.pay_id, \n"
    		+ "            ph.pay_amount, \n"
    		+ "            ph.pay_date, \n"
    		+ "            cc.consume_name, \n"
    		+ "            mc.card_num, \n"
    		+ "            mc.card_name, \n"
    		+ "            se.store_reg_num, \n"
    		+ "            se.store_name, \n"
    		+ "            ph.my_pay_check, \n"
    		+ "            me.menu_name \n"
            + "FROM t_pay_history ph " 
            + "JOIN t_consume_category cc ON ph.consume_id = cc.consume_id " 
            + "JOIN t_member_card mc ON ph.card_num = mc.card_num " 
            + "JOIN t_store se ON ph.str_reg_num = se.store_reg_num " 
            + "JOIN t_menu me ON se.store_reg_num = me.store_reg_num " 
            + "WHERE ph.pay_id = :payId AND mc.member_id = :memberId "  
            + "LIMIT 1 ",            
            nativeQuery = true)
    Map<String, Object> findPayHistoryDetailWithMenu(@Param("payId") Long payId, @Param("memberId") String memberId);

    @Query("SELECT p FROM PayHistoryEntity p WHERE p.memberCard.member.memberId = :memberId AND p.payId = :payId")
    PayHistoryEntity findByMemberCardMemberMemberIdAndPayId(@Param("memberId") String memberId, @Param("payId") Long payId);
}
