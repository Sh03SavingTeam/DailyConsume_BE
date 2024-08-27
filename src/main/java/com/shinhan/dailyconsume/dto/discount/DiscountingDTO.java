package com.shinhan.dailyconsume.dto.discount;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class DiscountingDTO {
    private String category;
    private int totalCount;
    private List<DiscountingInfoDTO> discountingInfoList;
}
