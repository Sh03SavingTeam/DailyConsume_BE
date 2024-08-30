package com.shinhan.dailyconsume.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shinhan.dailyconsume.dto.store.StoreDTO;
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
	
	@PutMapping("/updateStore")
	public ResponseEntity<Object> storeUpdate(@RequestBody StoreDTO storeDto) {
		recommenService.updateStore(storeDto);
		return ResponseEntity.ok("갱신 완료");
	}
	
	@GetMapping("/consume")
	public ResponseEntity<Object> consumeRecommend(@RequestParam Double lon, @RequestParam Double lat) throws InterruptedException {
		Thread.sleep(5000);
		return ResponseEntity.ok(recommenService.consumeRecommend());
	}
	
	@GetMapping("/peer")
	public ResponseEntity<Object> peerRecommend(@RequestParam Double lon, @RequestParam Double lat)  throws InterruptedException {
		Thread.sleep(5000);
		return ResponseEntity.ok(recommenService.peerRecommend());
	}
	
	@GetMapping("/daypattern")
	public ResponseEntity<Object> dayPatternRecommend(@RequestParam Double lon, @RequestParam Double lat) throws InterruptedException {
		Thread.sleep(7000);
		return ResponseEntity.ok(recommenService.dayPatternRecommend());
	}
	
	@GetMapping("/all")
	public ResponseEntity<Object> allPatternRecommend(@RequestParam Double lon, @RequestParam Double lat) throws InterruptedException {
		Thread.sleep(7000);
		return ResponseEntity.ok(recommenService.allPatternRecommend());
	}
}
