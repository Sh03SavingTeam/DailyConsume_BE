package com.shinhan.dailyconsume.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shinhan.dailyconsume.domain.AddressEntity;
import com.shinhan.dailyconsume.domain.CardEntity;

public interface AddrRepository extends JpaRepository<AddressEntity, Long>{
	
}
