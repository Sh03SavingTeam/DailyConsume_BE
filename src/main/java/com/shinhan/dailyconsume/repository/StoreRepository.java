package com.shinhan.dailyconsume.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.shinhan.dailyconsume.domain.StoreEntity;


public interface StoreRepository extends JpaRepository<StoreEntity, String>{
	
	@Query("SELECT s "
			+ " FROM StoreEntity s "
			+ " JOIN s.storeCate sc"
			+ " WHERE sc.storeCateSeq NOT IN :excludedCategories")
	List<StoreEntity> getExcludedCategories(List<Long> excludedCategories);
}
