package com.shinhan.dailyconsume.repository;


import com.shinhan.dailyconsume.domain.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import com.shinhan.dailyconsume.domain.WeeklyConsumeEntity;

public interface WeeklyConsumeRepository extends JpaRepository<WeeklyConsumeEntity, Long>{

    WeeklyConsumeEntity findByMember(MemberEntity member);
}
