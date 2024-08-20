package com.shinhan.dailyconsume.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shinhan.dailyconsume.domain.MenuEntity;

public interface MenuRepository extends JpaRepository<MenuEntity, Long>{

}
