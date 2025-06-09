package com.shinhan.dailyconsume.controller;

import com.shinhan.dailyconsume.dto.mypage.MemberInfoDTO;
import com.shinhan.dailyconsume.service.myapge.MypageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MypageController {

    private final MypageService mService;

    //마이페이지 메인화면 회원정보 가져오기
    @GetMapping("/mypage/{memberId}")
    public ResponseEntity<Object> getMemberInfo(@PathVariable("memberId") String memberId) {
        MemberInfoDTO memberInfoDTO = mService.getMemberInfo(memberId);
        if(memberInfoDTO==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(memberInfoDTO);
    }
}
