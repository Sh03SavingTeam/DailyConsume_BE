package com.shinhan.dailyconsume.controller;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shinhan.dailyconsume.service.map.RecommendService;

@Controller
@RequestMapping("/api/home")
public class HomeController {
	
	@Autowired
	RecommendService recomendService;

	@GetMapping("/payhistory")
	public ResponseEntity<Object> getPayHistory(@RequestParam String memId) {
		
		return ResponseEntity.ok(recomendService.getPayHistory(memId));
	}
}
