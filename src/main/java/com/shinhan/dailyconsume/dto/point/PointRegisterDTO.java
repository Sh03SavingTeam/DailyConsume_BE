package com.shinhan.dailyconsume.dto.point;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PointRegisterDTO {
    private String memberId;
    private String comment;
    private int point;
}
