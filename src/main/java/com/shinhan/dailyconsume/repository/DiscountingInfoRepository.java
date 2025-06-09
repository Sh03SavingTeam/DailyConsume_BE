package com.shinhan.dailyconsume.repository;

import java.util.List;

import com.shinhan.dailyconsume.domain.ConsumeCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import com.shinhan.dailyconsume.domain.DiscountingInfoEntity;

public interface DiscountingInfoRepository extends JpaRepository<DiscountingInfoEntity, Long>{
    List<DiscountingInfoEntity> findByConsumeCategory(ConsumeCategoryEntity consumeCategory);
}
