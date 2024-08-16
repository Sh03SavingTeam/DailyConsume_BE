package com.shinhan.dailyconsume.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import com.shinhan.dailyconsume.domain.CardEntity;


public interface CardRepository extends JpaRepository<CardEntity, Long>, QuerydslPredicateExecutor<CardEntity>{

}
