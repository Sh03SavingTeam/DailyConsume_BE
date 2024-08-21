package com.shinhan.dailyconsume.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shinhan.dailyconsume.domain.StoreEntity;

public interface StoreRepository extends JpaRepository<StoreEntity, String>{

}
