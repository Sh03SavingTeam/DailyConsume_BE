package com.shinhan.dailyconsume.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shinhan.dailyconsume.domain.TestEntity;

public interface TestRepository extends JpaRepository<TestEntity, Long>{

}
