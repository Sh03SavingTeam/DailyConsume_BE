package com.shinhan.dailyconsume.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.shinhan.dailyconsume.domain.ReviewEntity;

public interface ReviewRepository extends JpaRepository<ReviewEntity, Long>, QuerydslPredicateExecutor<ReviewEntity> {

}
