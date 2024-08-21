package com.shinhan.dailyconsume.repository;

import com.shinhan.dailyconsume.domain.MenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<MenuEntity, Long> {
}
