package com.shinhan.dailyconsume.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import com.shinhan.dailyconsume.domain.CardEntity;



public interface CardRepository extends JpaRepository<CardEntity, String>, QuerydslPredicateExecutor<CardEntity>{

	List<CardEntity> findByCardType(String cardType);
	
	CardEntity findByCardName(String cardName);
}
