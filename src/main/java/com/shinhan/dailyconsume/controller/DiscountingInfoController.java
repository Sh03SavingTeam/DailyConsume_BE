package com.shinhan.dailyconsume.controller;

import com.shinhan.dailyconsume.dto.DiscountingInfoDTO;
import com.shinhan.dailyconsume.service.DiscountingInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class DiscountingInfoController {

    final DiscountingInfoService dService;

    @GetMapping("/discountinginfo/{memberId}")
    public ResponseEntity<Object> getDiscountingInfos(@PageableDefault(size=5) Pageable pageable, @PathVariable("memberId") String memberId){
        return ResponseEntity.ok(dService.getDiscountingInfos(pageable, memberId));
    }
}
