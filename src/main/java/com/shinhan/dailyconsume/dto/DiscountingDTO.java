package com.shinhan.dailyconsume.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class DiscountingDTO {
    private String category;
    private List<DiscountingInfoDTO> discountingInfoList;
}
