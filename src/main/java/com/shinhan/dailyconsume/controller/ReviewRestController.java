package com.shinhan.dailyconsume.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.shinhan.dailyconsume.dto.point.PointRegisterDTO;
import com.shinhan.dailyconsume.service.myapge.PointHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shinhan.dailyconsume.dto.ReviewDTO;
import com.shinhan.dailyconsume.service.ReviewService;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/review")
public class ReviewRestController {

	private final ReviewService reviewService;
	private final PointHistoryService phService;

	//리뷰 등록 + 포인트 지급
	@PostMapping("/reviewRegister")
	public ReviewDTO reviewRegister(@RequestBody ObjectNode reviewAndPointObj) throws JsonProcessingException {
		//System.out.println(reviewDTO);
		ObjectMapper mapper = new ObjectMapper();
		ReviewDTO reviewDTO = mapper.treeToValue(reviewAndPointObj.get("reviewDTO"), ReviewDTO.class);
		PointRegisterDTO pointRegisterDTO = mapper.treeToValue(reviewAndPointObj.get("pointRegisterDTO"), PointRegisterDTO.class);

		System.out.println("=================================== reviewDTO: " + reviewDTO);

		Long reviewId = reviewService.register(reviewDTO);

		System.out.println("=================================== reviewDTO: " + reviewId);

		phService.register(pointRegisterDTO);
		
		return ReviewDTO.builder().build();
	}

}
