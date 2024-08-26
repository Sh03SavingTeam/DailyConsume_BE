package com.shinhan.dailyconsume.dto.mypage;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberInfoDTO {
    private String memberImg;
    private String memberName;
    private int weeklyMoney;
    private String endDate;
}
