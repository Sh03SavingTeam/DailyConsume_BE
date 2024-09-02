package com.shinhan.dailyconsume.calendar;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.shinhan.dailyconsume.domain.PayHistoryEntity;
import com.shinhan.dailyconsume.domain.WeeklyConsumeEntity;

public interface CalendarRepository extends JpaRepository<PayHistoryEntity, Long> {

	// 특정 월과 연도의 결제 내역 조회 쿼리
	@Query("SELECT p FROM PayHistoryEntity p WHERE p.memberCard.member.memberId = :memberId AND FUNCTION('MONTH', p.payDate) = :month AND FUNCTION('YEAR', p.payDate) = :year")
	List<PayHistoryEntity> findByMemberAndMonthAndYear(@Param("memberId") String memberId, @Param("month") int month,
			@Param("year") int year);

	// 특정 연도, 월, 일의 결제 내역 조회 쿼리
	@Query("SELECT p FROM PayHistoryEntity p WHERE p.memberCard.member.memberId = :memberId AND FUNCTION('DAY', p.payDate) = :day AND FUNCTION('MONTH', p.payDate) = :month AND FUNCTION('YEAR', p.payDate) = :year")
	List<PayHistoryEntity> findByMemberAndDayAndMonthAndYear(@Param("memberId") String memberId, @Param("day") int day,
			@Param("month") int month, @Param("year") int year);

	// 일별 결제 내역 상세 조회 쿼리
	@Query("SELECT p FROM PayHistoryEntity p WHERE p.memberCard.member.memberId = :memberId AND p.payId = :payId")
	PayHistoryEntity findByMemberIdAndPayId(@Param("memberId") String memberId, @Param("payId") Long payId);

	// myPayCheck 값을 업데이트하는 메서드 (쿼리로 할 경우)
	@Query(value = "SELECT ph.pay_id, \n" + "            ph.pay_amount, \n" + "            ph.pay_date, \n"
			+ "            cc.consume_name, \n" + "            mc.card_num, \n" + "            mc.card_name, \n"
			+ "            se.store_reg_num, \n" + "            se.store_name, \n" + "            ph.my_pay_check \n"
			+              " FROM t_pay_history ph "
			+ " JOIN t_consume_category cc ON ph.consume_id = cc.consume_id "
			+ " JOIN t_member_card mc ON ph.card_num = mc.card_num "
			+ " JOIN t_store se ON ph.str_reg_num = se.store_reg_num "			
			+ " WHERE ph.pay_id = :payId AND mc.member_id = :memberId " , nativeQuery = true)
	Map<String, Object> findPayHistoryDetailWithMenu(@Param("payId") Long payId, @Param("memberId") String memberId);

	@Query("SELECT p FROM PayHistoryEntity p WHERE p.memberCard.member.memberId = :memberId AND p.payId = :payId")
	PayHistoryEntity findByMemberCardIdAndPayId(@Param("memberId") String memberId, @Param("payId") Long payId);

	@Query("SELECT wc FROM WeeklyConsumeEntity wc WHERE wc.member.memberId = :memberId AND :selectedDate BETWEEN wc.startDate AND wc.endDate")
	WeeklyConsumeEntity findWeeklyConsumeByDate(@Param("memberId") String memberId,
			@Param("selectedDate") Timestamp selectedDate);

	@Query(value = "SELECT " + "    wc.member_id, " + "    wc.start_date, " + "    wc.end_date, "
			+ "    wc.weekly_money AS 설정금액, " + "    IFNULL(SUM(ph.pay_amount), 0) AS 사용금액합, "
			+ "    (wc.weekly_money - IFNULL(SUM(ph.pay_amount), 0)) AS 잔여금액 " + "FROM " + "    t_weekly_consume wc "
			+ "LEFT JOIN " + "    t_member_card mc ON wc.member_id = mc.member_id " + "LEFT JOIN "
			+ "    t_pay_history ph ON mc.card_num = ph.card_num "
			+ "    AND ph.pay_date BETWEEN wc.start_date AND DATE_ADD(wc.end_date, INTERVAL 1 DAY) " + "WHERE "
			+ "    wc.member_id = :memberId " + "    AND :selectedDate BETWEEN wc.start_date AND wc.end_date "
			+ "GROUP BY " + "    wc.member_id, wc.start_date, wc.end_date, wc.weekly_money "
			+ "LIMIT 1", nativeQuery = true)
	Map<String, Object> findWeeklyConsumeSummary(@Param("memberId") String memberId,
			@Param("selectedDate") Timestamp selectedDate);

	// 특정 회원의 주간 설정 금액 데이터를 월별로 조회
	@Query(value = "SELECT " + "    wc.member_id AS 회원ID, " + "    wc.start_date AS 시작일, " + "    wc.end_date AS 종료일, "
			+ "    wc.weekly_money AS 설정금액, " + "    wc.weekly_check AS 달성여부 " + "FROM " + "    t_weekly_consume wc "
			+ "WHERE " + "    wc.member_id = :memberId "
			+ "    AND (MONTH(wc.start_date) = :month OR MONTH(wc.end_date) = :month) " + "ORDER BY "
			+ "    wc.start_date ASC", nativeQuery = true)
	List<Map<String, Object>> findWeeklyConsumeByMonth(@Param("memberId") String memberId, @Param("month") int month);
}