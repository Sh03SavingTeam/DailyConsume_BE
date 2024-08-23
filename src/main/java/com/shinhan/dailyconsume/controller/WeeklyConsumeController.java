package com.shinhan.dailyconsume.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.shinhan.dailyconsume.service.WeeklyConsumeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/mypage")
@RequiredArgsConstructor
public class WeeklyConsumeController {
	
	@Autowired
	WeeklyConsumeService weekService;
	
	@PostMapping("/myweeklymoney")
	public ResponseEntity<String> weeklyMoneyRegister(@RequestParam String memberId, @RequestParam int weeklyMoney) {
		String result = weekService.save(memberId, weeklyMoney);
		if(result == null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		return ResponseEntity.ok(result);
	}
}
