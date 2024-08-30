package com.shinhan.dailyconsume.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.shinhan.dailyconsume.domain.RankHistoryEntity;
import com.shinhan.dailyconsume.dto.mypage.AddressRankingDTO;
import com.shinhan.dailyconsume.dto.mypage.AddressRankingProjection;
import com.shinhan.dailyconsume.dto.mypage.RankingDTO;
import com.shinhan.dailyconsume.dto.mypage.RankingProjection;

public interface RankHistoryRepository extends JpaRepository<RankHistoryEntity, Long> {

	@Query(value = "SELECT SUM(r.amount) AS total_amount " + "FROM t_rank_history r "
			+ "WHERE MONTH(r.rank_reg_date) = MONTH(CURDATE()) " + "AND YEAR(r.rank_reg_date) = YEAR(CURDATE()) "
			+ "AND r.member_id = :memberId", nativeQuery = true)
	int getTotalScore(@Param("memberId") String memberId);

	@Query(value = "SELECT " + "    rh.member_id AS memberId, " + "    m.member_name AS memberName, "
			+ "    m.rank_id AS rankId, " + "    SUM(rh.amount) AS totalAmount, "
			+ "    RANK() OVER (ORDER BY SUM(rh.amount) DESC) AS rankNum " + "FROM t_rank_history rh "
			+ "JOIN t_member m ON rh.member_id = m.member_id " + "WHERE MONTH(rh.rank_reg_date) = MONTH(CURDATE()) "
			+ "  AND YEAR(rh.rank_reg_date) = YEAR(CURDATE()) " + "GROUP BY rh.member_id, m.member_name, m.rank_id "
			+ "ORDER BY totalAmount DESC " + "LIMIT 5 ", nativeQuery = true)
	List<RankingProjection> getAllRanking();

	@Query(value = """
			SELECT
			    m.member_id AS memberId,
			    m.member_name AS memberName,
			    r.rank_id AS rankId,
			    SUM(rh.amount) AS totalAmount,
			    a.addr_id AS addrId,
			    a.addr_detail AS addrDetail,
			    ROW_NUMBER() OVER (ORDER BY SUM(rh.amount) DESC) AS rankNum
			FROM
			    t_member m
			    INNER JOIN t_address a ON m.member_id = a.member_id
			    INNER JOIN t_rank_history rh ON m.member_id = rh.member_id
			    INNER JOIN t_rank r ON m.rank_id = r.rank_id
			WHERE
			    a.addr_detail = (
			        SELECT addr_detail
			        FROM t_address
			        WHERE member_id = :memberId
			        AND addr_default = 1
			        LIMIT 1
			    )
			    AND a.addr_default = 1
			    AND DATE_FORMAT(rh.rank_reg_date, '%Y-%m') = DATE_FORMAT(NOW(), '%Y-%m')
			GROUP BY
			    m.member_id,
			    m.member_name,
			    r.rank_id,
			    r.rank_name,
			    a.addr_id,
			    a.addr_detail
			ORDER BY
			    totalAmount DESC
			LIMIT 5
			""", nativeQuery = true)
	List<AddressRankingProjection> getRankingByAddress(@Param("memberId") String memberId);

	@Query(value = "SELECT COUNT(*) FROM t_rank_history r " + "WHERE r.member_id = :memberId "
			+ "AND DATE_FORMAT(r.rank_reg_date, '%Y-%m') = DATE_FORMAT(CURRENT_DATE(), '%Y-%m') "
			+ "AND r.cmt = '출석체크'", nativeQuery = true)
	int countAttendanceCheckByMemberId(@Param("memberId") String memberId);

	@Query(value = "SELECT CASE WHEN COUNT(*) > 0 THEN 1 ELSE 0 END FROM t_rank_history r "
			+ "WHERE r.member_id = :memberId " + "AND DATE(r.rank_reg_date) = CURRENT_DATE()", nativeQuery = true)
	int checkIfRankExistsForToday(@Param("memberId") String memberId);

}
