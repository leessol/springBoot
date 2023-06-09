package com.shinhan.education.security;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.shinhan.education.repository.MemberRepository;
import com.shinhan.education.vo.MemberDTO;

@Service
public class MemberService implements UserDetailsService {
	@Autowired
	private HttpSession httpSession;

	@Autowired
	private MemberRepository mrepo;

	@Autowired //암호화하기 위해서 필요하다. 
	PasswordEncoder passwordEncoder; // security config에서 Bean생성
    
	
	// 회원가입
	//@Transactional
	public MemberDTO joinUser(MemberDTO member) {
        // 비밀번호 암호화...암호화되지않으면 로그인되지않는다.
		//만약 1234로 하면 mpassword:1234 --> encode에 의해 암호화
		String pwd = passwordEncoder.encode(member.getMpassword()); //진짜 암호화 하는 과정. 
		member.setMpassword(pwd); 
		System.out.println("암호화된 pass:" + pwd);
		return mrepo.save(member); // 다시 암호화된 정보를 넣기 위해서 mrepo 필요 . 
	}

    //!!!!반드시 구현해야한다. implements 받은 메소드니깐 
	//로그인 인증 메소드 (로그인 시도)
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String mid) throws UsernameNotFoundException {
		System.out.println("loadUserByUsername mid:" + mid);
		//filter는 조건에 맞는것만 선택
		//map: 변형한다. 

		UserDetails member = mrepo.findById(mid)
				.filter(m -> m != null)
				.map(m -> new SecurityUser(m)) //우리가 가지고 있던 memberDTO를 --> securityUser 타입으로 바꾸어야 한다. map이용
				.get();
		System.out.println("UserDetails member:" + member);
		httpSession.setAttribute("user", member); //페이지가 이동되더다도 계속 그 정보를 유지시키기 위해 
		httpSession.setAttribute("member", mrepo.findById(mid).get()); //페이지가 이동되더다도 계속 그 정보를 유지시키기 위해 
		return member;
	}

}
