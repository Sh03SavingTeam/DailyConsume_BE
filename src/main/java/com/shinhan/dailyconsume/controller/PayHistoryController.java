package com.shinhan.dailyconsume.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shinhan.dailyconsume.dto.ChartDTO;
import com.shinhan.dailyconsume.dto.PayHistoryDTO;
import com.shinhan.dailyconsume.service.PayHistoryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/mypage")
@RequiredArgsConstructor
public class PayHistoryController {

	@Autowired
	PayHistoryService payService;
	
	//개인 카테고리별 지출내역 조회
	@GetMapping("/mycardHistory") 
	public ResponseEntity<Object> getMyHistoryList(@RequestParam String memberId) {
		List<PayHistoryDTO> payHistory = payService.getPayHistory(memberId);
		
		if(payHistory == null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		return ResponseEntity.ok(payHistory);
	}

	//또래 카테고리별 지출내역 평균 조회
	@GetMapping("/peercardHistory")
	public ResponseEntity<Object> getPeerHistoryList(@RequestParam String memberId) {
		ChartDTO chart = payService.getPeerPayHistory(memberId);
		
		if(chart == null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		
		return ResponseEntity.ok(chart);
	}
}
