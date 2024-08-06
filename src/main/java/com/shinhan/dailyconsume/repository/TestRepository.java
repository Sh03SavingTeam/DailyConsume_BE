package com.shinhan.dailyconsume.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shinhan.dailyconsume.Domain.TestEntity;

public interface TestRepository extends JpaRepository<TestEntity, Long>{

}
