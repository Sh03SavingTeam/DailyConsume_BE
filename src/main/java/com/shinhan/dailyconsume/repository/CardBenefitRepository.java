package com.shinhan.dailyconsume.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.shinhan.dailyconsume.domain.CardBenefitEntity;

import java.util.List;

public interface CardBenefitRepository extends JpaRepository<CardBenefitEntity, Long>, QuerydslPredicateExecutor<CardBenefitEntity>{

	List<CardBenefitEntity> findByCardName(String cardName);
}
