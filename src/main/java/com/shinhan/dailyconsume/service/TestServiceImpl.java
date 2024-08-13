package com.shinhan.dailyconsume.service;

import org.springframework.stereotype.Service;

import com.shinhan.dailyconsume.Domain.TestEntity;
import com.shinhan.dailyconsume.dto.TestDTO;
import com.shinhan.dailyconsume.repository.TestRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class TestServiceImpl implements TestSerivce{

	final TestRepository tRepo;
	
	@Override
	public TestDTO test() {
		TestEntity entity = tRepo.findById(1L).orElse(null);
		return TestDTO.builder()
				.str(entity.getStr())
				.build();
	}

}
