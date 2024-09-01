package com.shinhan.dailyconsume.repository;


import com.shinhan.dailyconsume.domain.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import com.shinhan.dailyconsume.domain.WeeklyConsumeEntity;

import java.sql.Timestamp;
import java.util.List;

public interface WeeklyConsumeRepository extends JpaRepository<WeeklyConsumeEntity, Long>{

    List<WeeklyConsumeEntity> findByMemberOrderByEndDateDesc(MemberEntity member);

    List<WeeklyConsumeEntity> findByEndDate(Timestamp endDate);
}
