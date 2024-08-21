package com.shinhan.dailyconsume.repository;

import com.shinhan.dailyconsume.domain.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {
//    @Query("SELECT avg(r.rating) FROM t_review r WHERE r.store_reg_num = :store_reg_num")
//    double getRatingByStore(@Param("storeRegNum") String storeRegNum);
}