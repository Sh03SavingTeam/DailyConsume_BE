package com.shinhan.dailyconsume.service;

import com.shinhan.dailyconsume.domain.MemberEntity;
import com.shinhan.dailyconsume.domain.PointHistoryEntity;
import com.shinhan.dailyconsume.dto.PointAccountDTO;
import com.shinhan.dailyconsume.dto.PointDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PointHistoryService {

    public PointDTO getPointByMember(Pageable pageable, String memberId);

    public PointAccountDTO getPointAccount(String memberId);
}
