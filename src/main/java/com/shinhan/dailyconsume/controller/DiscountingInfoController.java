package com.shinhan.dailyconsume.controller;

import com.shinhan.dailyconsume.dto.discount.DiscountingDTO;
import com.shinhan.dailyconsume.service.discount.DiscountingInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DiscountingInfoController {

    final DiscountingInfoService dService;

    // 회원별 맞춤 할인 정보 가져오기
    @GetMapping("/mypage/discountinginfo/{memberId}")
    public ResponseEntity<Object> getDiscountingInfos(@PageableDefault(size=5) Pageable pageable, @PathVariable("memberId") String memberId){
        DiscountingDTO discountingDTO = dService.getDiscountingInfos(pageable, memberId);
//        if(discountingDTO==null){
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
//        }
        return ResponseEntity.ok(discountingDTO);
    }
}
