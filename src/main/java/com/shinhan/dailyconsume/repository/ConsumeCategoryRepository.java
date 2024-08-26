package com.shinhan.dailyconsume.repository;

import com.shinhan.dailyconsume.domain.ConsumeCategoryEntity;
import com.shinhan.dailyconsume.domain.DiscountingInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsumeCategoryRepository extends JpaRepository<ConsumeCategoryEntity, Long> {
    ConsumeCategoryEntity findByConsumeName(String consumeName);
}
