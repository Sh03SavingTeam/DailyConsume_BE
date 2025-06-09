package com.shinhan.dailyconsume.service.myapge;

import com.shinhan.dailyconsume.dto.mypage.MemberInfoDTO;

public interface MypageService {
    MemberInfoDTO getMemberInfo(String memberId);
}
