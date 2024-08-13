package com.shinhan.dailyconsume.service;

import org.springframework.stereotype.Service;

import com.shinhan.dailyconsume.domain.TestEntity;
import com.shinhan.dailyconsume.dto.TestDTO;
import com.shinhan.dailyconsume.repository.TestRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class TestServiceImpl implements TestSerivce{

	final TestRepository tRepo;
	
	@Override
	public TestDTO test() {

		return null;
	}

}
