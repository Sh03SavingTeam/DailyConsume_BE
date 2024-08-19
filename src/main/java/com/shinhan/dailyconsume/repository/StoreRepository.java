package com.shinhan.dailyconsume.repository;

import com.shinhan.dailyconsume.domain.StoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<StoreEntity, String> {
    StoreEntity findByStoreRegNum(String storeRegNum);
}
