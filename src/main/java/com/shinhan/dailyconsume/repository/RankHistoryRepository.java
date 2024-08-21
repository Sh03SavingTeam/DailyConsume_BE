package com.shinhan.dailyconsume.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.shinhan.dailyconsume.domain.RankHistoryEntity;
import com.shinhan.dailyconsume.dto.mypage.RankingDTO;
import com.shinhan.dailyconsume.dto.mypage.RankingProjection;

public interface RankHistoryRepository extends JpaRepository<RankHistoryEntity, Long> {

	@Query(value = "SELECT SUM(r.amount) AS total_amount " + "FROM t_rank_history r "
			+ "WHERE MONTH(r.rank_reg_date) = MONTH(CURDATE()) "
			+ "AND YEAR(r.rank_reg_date) = YEAR(CURDATE()) "
			+ "AND r.member_id = :memberId", nativeQuery = true)
	int getTotalScore(@Param("memberId") String memberId);
	
	
	@Query(value = "SELECT " +
	        "    rh.member_id AS memberId, " +
	        "    m.member_name AS memberName, " +
	        "    m.rank_id AS rankId, " +
	        "    SUM(rh.amount) AS totalAmount, " +
	        "    RANK() OVER (ORDER BY SUM(rh.amount) DESC) AS rankNum " +
	        "FROM t_rank_history rh " +
	        "JOIN t_member m ON rh.member_id = m.member_id " +
	        "WHERE MONTH(rh.rank_reg_date) = MONTH(CURDATE()) " +
	        "  AND YEAR(rh.rank_reg_date) = YEAR(CURDATE()) " +
	        "GROUP BY rh.member_id, m.member_name, m.rank_id " +
	        "ORDER BY totalAmount DESC", 
	        nativeQuery = true)
	List<RankingProjection> getAllRanking();

}
