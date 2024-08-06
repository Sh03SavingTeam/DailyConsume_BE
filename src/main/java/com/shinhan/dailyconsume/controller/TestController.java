package com.shinhan.dailyconsume.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shinhan.dailyconsume.dto.TestDTO;
import com.shinhan.dailyconsume.service.TestSerivce;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class TestController {

	final TestSerivce tService;
	
	@GetMapping
	public TestDTO test() {
		return tService.test();
	}
}
