package com.shinhan.dailyconsume.dto.point;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PointDTO {
    private Long totalPoint;
    private int historyCount;
    private List<PointHistoryDTO> pointHistories;
}
