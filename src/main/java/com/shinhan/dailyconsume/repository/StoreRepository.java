package com.shinhan.dailyconsume.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.shinhan.dailyconsume.domain.StoreEntity;
import com.shinhan.dailyconsume.dto.map.RecommendDTO;
import com.shinhan.dailyconsume.dto.map.WeeklyConsumeProjection;


public interface StoreRepository extends JpaRepository<StoreEntity, String>{
	
	@Query("SELECT s "
			+ " FROM StoreEntity s "
			+ " JOIN s.storeCate sc"
			+ " WHERE sc.storeCateSeq NOT IN :excludedCategories")
	List<StoreEntity> getExcludedCategories(List<Long> excludedCategories);
	
	@Query(value = "SELECT ts.*, ST_Distance_Sphere(POINT(:longitude, :latitude), POINT(ts.store_lony, ts.store_latx)) AS distance " +
            "FROM t_store ts " +
            "WHERE ST_Distance_Sphere(POINT(:longitude, :latitude), POINT(ts.store_lony, ts.store_latx)) <= :distance", 
    nativeQuery = true)
	List<StoreEntity> findStoresWithinDistance(@Param("longitude") Double longitude, 
                                  @Param("latitude") Double latitude, 
                                  @Param("distance") Double distance);
	
	@Query(value = """
			       SELECT ts.store_reg_num AS storeRegNum, ts.store_name AS storeName, 
	               ts.store_addr AS storeAddr, ts.store_phone AS storePhone, 
	               ts.store_latx AS storeLatx, ts.store_lony AS storeLony, 
	               ts.store_img AS storeImg, tsc.cate_name AS cate
			       FROM t_store ts
			       JOIN t_menu tm ON (ts.store_reg_num = tm.store_reg_num)
			       JOIN t_store_category tsc ON (ts.store_cate_seq = tsc.store_cate_seq)
			       WHERE tm.menu_price < (SELECT 
				   NVL(((twc.weekly_money - (SELECT SUM(pay_amount)
				   FROM t_pay_history tph
				   JOIN t_member_card tmc ON (tph.card_num = tmc.card_num)
				   JOIN t_member tm ON (tmc.member_id = tm.member_id)
				   WHERE tph.pay_date BETWEEN twc.start_date AND twc.end_date
				   AND tm.member_id = :memberId
				   ))/ DATEDIFF(twc.end_date, CURDATE())),twc.weekly_money)
				   FROM t_weekly_consume twc
				   WHERE twc.member_id = :memberId
				   AND CURDATE() BETWEEN twc.start_date AND twc.end_date)
			       AND tm.menu_price > 5000
			       AND ts.store_latx IS NOT NULL
			       GROUP BY ts.store_reg_num, ts.store_name, ts.store_addr, ts.store_phone, 
	               ts.store_latx, ts.store_lony, ts.store_img, tsc.cate_name
	        """, nativeQuery = true)
	    List<WeeklyConsumeProjection> findStoreWeeklyConsume(@Param("memberId") String memberId);
	
	@Query(value = """
				  SELECT  ts.store_reg_num AS storeRegNum, ts.store_name AS storeName, 
	              ts.store_addr AS storeAddr, ts.store_phone AS storePhone, 
	              ts.store_latx AS storeLatx, ts.store_lony AS storeLony, 
	              ts.store_img AS storeImg, tsc.cate_name AS cate,
	              tr.review_id AS reviewId
				  FROM t_store ts
				  JOIN t_store_category tsc ON (ts.store_cate_seq = tsc.store_cate_seq)
				  JOIN t_pay_history tph ON (ts.store_reg_num = tph.str_reg_num)
				  JOIN t_member_card tmc ON (tmc.card_num = tph.card_num)
				  JOIN t_member tm ON (tm.member_id = tmc.member_id)
				  LEFT OUTER JOIN t_review tr ON (ts.store_reg_num  = tr.store_reg_num)
				  WHERE tm.member_id = 'm001'
				  AND tph.pay_date BETWEEN DATE_SUB(CURDATE(), INTERVAL 7 DAY) AND CURDATE()
				  GROUP BY ts.store_reg_num, ts.store_name, ts.store_addr, ts.store_latx, ts.store_lony, ts.store_img, ts.store_phone, tsc.cate_name
			""", nativeQuery = true)
	List<WeeklyConsumeProjection> findStorePayHistory(@Param("memberId") String memberId);
}
