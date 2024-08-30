package com.shinhan.dailyconsume.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.shinhan.dailyconsume.domain.StoreEntity;


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
}
