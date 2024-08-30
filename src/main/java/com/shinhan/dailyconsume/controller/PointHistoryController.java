package com.shinhan.dailyconsume.controller;

import com.shinhan.dailyconsume.domain.PointHistoryEntity;
import com.shinhan.dailyconsume.dto.point.PointAccountDTO;
import com.shinhan.dailyconsume.dto.point.PointDTO;
import com.shinhan.dailyconsume.dto.point.PointRegisterDTO;
import com.shinhan.dailyconsume.service.myapge.PointHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class PointHistoryController {

    private final PointHistoryService phService;

    // 멤버 포인트 내역 가져오기
    @GetMapping("/mypage/point/{memberId}")
    public ResponseEntity<Object> getPointByMember(@PageableDefault(size=4) Pageable pageable, @PathVariable("memberId") String memberId) {
        PointDTO pointDTO = phService.getPointByMember(pageable, memberId);
        if(pointDTO==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(pointDTO);
    }

    // 멤버 포인트 양, 계좌 정보 가져오기
    @GetMapping("/mypage/refund/{memberId}")
    public ResponseEntity<Object> getPointAccount(@PathVariable("memberId") String memberId){
        PointAccountDTO pointAccountDTO = phService.getPointAccount(memberId);
        if(pointAccountDTO==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(pointAccountDTO);
    }

    // 포인트 환급화
    @PutMapping("/mypage/refund/{memberId}")
    public ResponseEntity<String> pointToCash(@PathVariable("memberId") String memberId, @RequestParam int point){
        String answer = phService.pointToCash(memberId, point);
        if(answer==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(answer);
    }

    // 포인트 지급
    @PostMapping("/point")
    public ResponseEntity<String> pointToCash(@RequestBody PointRegisterDTO pointRegisterDTO){
        PointHistoryEntity result = phService.register(pointRegisterDTO);
        if(result==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok("지급 성공");
    }
}
