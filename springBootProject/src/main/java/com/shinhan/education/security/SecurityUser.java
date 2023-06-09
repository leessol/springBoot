package com.shinhan.education.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.shinhan.education.vo.MemberDTO;

import groovy.transform.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class SecurityUser extends User{
	private static final long serialVersionUID = 1L;
	private static final String ROLE_PREFIX="ROLE_";
    private MemberDTO member;   //password가 암호화 되지 않으면 user가 될 수 없다. 
	public SecurityUser(String name, String password, Collection<? extends GrantedAuthority> authorities) {
		super(name, password, authorities);
	}
	//security user 생성 
	public SecurityUser(MemberDTO member) {	
		//super로 가니깐 생성자 호출될때 무조건 User로 간다. 최상위 부모 
		//마지막 인자는 collection으로 받기때문에 여러개의 role를 가질 수 있다. 
		super(member.getMid(), member.getMpassword(), makeRole(member)  );
		this.member = member;
		System.out.println("SecurityUser member:" + member);
	}
	//Role을 여러개 가질수 있도록 되어있음 
	private static List<GrantedAuthority> makeRole(MemberDTO member) {
		List<GrantedAuthority> roleList = new ArrayList<>();
		roleList.add(new SimpleGrantedAuthority(ROLE_PREFIX + member.getMrole())); //반드시 "ROLE_" 가 앞에 붙어야 한다. 
		return roleList;
	}
	 
	//User class에서 username필드가 있지만 google 인증시 사용되는 필드는 name
	//이를 맞추기위해 함수추가함 
//	public String getName() {
//		// TODO Auto-generated method stub
//		return super.getUsername();
//	}	
}

