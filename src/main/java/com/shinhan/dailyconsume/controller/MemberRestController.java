package com.shinhan.dailyconsume.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shinhan.dailyconsume.config.JwtUtil;
import com.shinhan.dailyconsume.dto.LoginResponseDTO;
import com.shinhan.dailyconsume.dto.MemberDTO;
import com.shinhan.dailyconsume.service.MemberService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@CrossOrigin
@RestController
@RequestMapping("/api/member")
public class MemberRestController {
	
	@Autowired
	MemberService memberService;
	
	@Autowired
    private JwtUtil jwtUtil;

	//회원가입
	@PostMapping("/memberRegister")
	public MemberDTO memberRegister(@RequestBody MemberDTO memberDTO) {
		
		System.out.println("memberDTO :" +memberDTO);
		
		String memberId = memberDTO.getMemberId();
		String fileName = "member_"+memberId+".jpg";
		memberDTO.setMemberImg(fileName);
		
		String memberID = memberService.register(memberDTO);
		return MemberDTO.builder().build();
	}
	
	@PostMapping("/memberLogin")
	public LoginResponseDTO memberLogin(@RequestBody MemberDTO memberDTO,HttpServletResponse response) {
		System.out.println(memberDTO);
		
		MemberDTO dbMember = memberService.findMember(memberDTO.getMemberId());
		
		if(dbMember!=null&&dbMember.getMemberPw().equals(memberDTO.getMemberPw())) {
			 String token = jwtUtil.createAccessToken(memberService.dtoToEntity(dbMember));
			 return LoginResponseDTO.builder()
					 .message("loginSuccess")
					 .token(token)
					 .build();
		}else {
			return LoginResponseDTO.builder()
					 .message("loginSuccess")
					 .token(null)
					 .build();
		}
	}
	
	// 세션 정보 확인 (JWT 기반으로 변환 필요)
    @GetMapping("/memberSession")
    public MemberDTO memberSession(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7); // "Bearer " 제거
            if (jwtUtil.validateToken(token)) {
                String memberId = jwtUtil.getUserId(token);
                System.out.println("token에 따른 memberID:"+memberId);
                return memberService.findMember(memberId);
            }
        }
        return null;
    }
	
	
	// 로그아웃
    @PostMapping("/memberLogout")
    public String memberLogout(HttpServletResponse response) {
    	response.setHeader("Authorization", "");
        return "logoutSuccess";
    }
}
