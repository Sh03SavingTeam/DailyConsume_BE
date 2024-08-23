package com.shinhan.dailyconsume.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shinhan.dailyconsume.dto.ReviewDTO;
import com.shinhan.dailyconsume.service.ReviewService;

@CrossOrigin
@RestController
@RequestMapping("/api/review")
public class ReviewRestController {
	
	@Autowired
	ReviewService reviewService;
	
	//리뷰 등록
	@PostMapping("/reviewRegister")
	public ReviewDTO reviewRegister(@RequestBody ReviewDTO reviewDTO) {
		System.out.println(reviewDTO);
		Long revewId = reviewService.register(reviewDTO);
		
		return ReviewDTO.builder().build();
	}
	
	

}
