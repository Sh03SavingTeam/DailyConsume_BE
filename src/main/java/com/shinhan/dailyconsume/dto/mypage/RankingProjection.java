package com.shinhan.dailyconsume.dto.mypage;

public interface RankingProjection {
    String getMemberId();
    String getMemberName();
    int getRankId(); 
    int getTotalAmount();
    int getRankNum();
}
