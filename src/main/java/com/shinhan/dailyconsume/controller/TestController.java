package com.shinhan.dailyconsume.controller;

import com.shinhan.dailyconsume.service.WeeklyScheduler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shinhan.dailyconsume.dto.TestDTO;
import com.shinhan.dailyconsume.service.TestSerivce;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class TestController {

	private final TestSerivce tService;

	private final WeeklyScheduler weeklyScheduler;

	
	@GetMapping
	public TestDTO test() {
		return tService.test();
	}
	
	@GetMapping("/test11")
	public ResponseEntity<Object> f1() {
		boolean error = true;
		
		if (error)
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("실패했습니다.");
		
		return ResponseEntity.status(HttpStatus.ACCEPTED).body("승인되었습니다");
	}
}
