package com.shinhan.dailyconsume.repository;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PayHistoryRepository extends CrudRepository<PayHistoryRepository, Long>, 
PagingAndSortingRepository<PayHistoryRepository, Long>,
QuerydslPredicateExecutor<PayHistoryRepository>{

		
}
