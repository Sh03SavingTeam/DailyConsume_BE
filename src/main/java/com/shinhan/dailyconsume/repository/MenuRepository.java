package com.shinhan.dailyconsume.repository;

import com.shinhan.dailyconsume.domain.MenuEntity;
import com.shinhan.dailyconsume.domain.StoreEntity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<MenuEntity, Long> {
	List<MenuEntity> findByStoreInfo(StoreEntity storeInfo);
}
