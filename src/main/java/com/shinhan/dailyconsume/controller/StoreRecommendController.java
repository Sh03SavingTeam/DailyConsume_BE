package com.shinhan.dailyconsume.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shinhan.dailyconsume.service.map.RecommendService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/recommend")
public class StoreRecommendController {
	
	private final RecommendService recommenService;
	
	@GetMapping("/store")
	public ResponseEntity<Object> recommendStores() {
		return ResponseEntity.ok(recommenService.recommendStores());
	}
	
	@GetMapping("/detail")
	public ResponseEntity<Object> StoreDetail(@RequestParam String storeRegNum) {
		return ResponseEntity.ok(recommenService.getStoreDetail(storeRegNum));
	}
}
