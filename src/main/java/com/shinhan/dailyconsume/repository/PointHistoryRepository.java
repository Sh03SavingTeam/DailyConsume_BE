package com.shinhan.dailyconsume.repository;

import com.shinhan.dailyconsume.domain.MemberEntity;
import com.shinhan.dailyconsume.domain.PointHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PointHistoryRepository extends JpaRepository<PointHistoryEntity, Long> {

    @Query("SELECT SUM(p.amount) FROM PointHistoryEntity p WHERE p.member = :member and p.divNum=0")
    Long getTotalPointByMember(@Param("member") MemberEntity member);

    List<PointHistoryEntity> findPointHistoryByMember(MemberEntity member);
}
