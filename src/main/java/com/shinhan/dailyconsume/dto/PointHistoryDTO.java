package com.shinhan.dailyconsume.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PointHistoryDTO {
    private Long divNum;
    private Long amount;
    private String pointRegDate;
    private String cmt;
}
