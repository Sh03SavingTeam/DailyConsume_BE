package com.shinhan.dailyconsume.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shinhan.dailyconsume.domain.PayHistoryEntity;
import com.shinhan.dailyconsume.dto.PayHistoryDTO;
import com.shinhan.dailyconsume.repository.PayHistoryRepository;

@Service
public class PayHistoryServiceImpl {
	@Autowired
    private PayHistoryRepository payHistoryRepo;
    
    @Override
    public List<PayHistoryDTO> selectByMemberIdAndMonth(String memberId, int month, int year) {
        List<PayHistoryEntity> payHistoryEntities = payHistoryRepo.findByMemberIdAndMonth(memberId, month, year);

        // PayHistoryEntity를 PayHistoryDTO로 변환
        return payHistoryEntities.stream()
                .map(this::entityToDTO)
                .collect(Collectors.toList());
    }
}
