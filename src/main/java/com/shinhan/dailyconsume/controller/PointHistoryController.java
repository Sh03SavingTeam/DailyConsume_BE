package com.shinhan.dailyconsume.controller;

import com.shinhan.dailyconsume.dto.PointAccountDTO;
import com.shinhan.dailyconsume.dto.PointDTO;
import com.shinhan.dailyconsume.service.PointHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PointHistoryController {

    private final PointHistoryService phService;

    @GetMapping("/mypage/point/{memberId}")
    public ResponseEntity<Object> getPointByMember(@PageableDefault(size=5) Pageable pageable, @PathVariable("memberId") String memberId) {
        PointDTO pointDTO = phService.getPointByMember(pageable, memberId);
        if(pointDTO==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(pointDTO);
    }

    @GetMapping("/mypage/refund/{memberId}")
    public ResponseEntity<Object> getPointAccount(@PathVariable("memberId") String memberId){
        PointAccountDTO pointAccountDTO = phService.getPointAccount(memberId);
        if(pointAccountDTO==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(pointAccountDTO);
    }
}
