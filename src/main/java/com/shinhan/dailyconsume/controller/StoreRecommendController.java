package com.shinhan.dailyconsume.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shinhan.dailyconsume.service.map.RecommendService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/recommand")
public class StoreRecommendController {
	
	private final RecommendService recommenService;
	
	@GetMapping
	public ResponseEntity<Object> recommendApiTest() {
		return ResponseEntity.ok(recommenService.recommendTest());
	}
}
