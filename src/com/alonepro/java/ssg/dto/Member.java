package com.alonepro.java.ssg.dto;

public class Member extends Dto {
	
	
	public String loginId;
	public String loginPw;
	public String  name;
	
	
	
	public Member(int id, String loginId, String loginPw, String name, String regDate) {
		super();
		
		this.id = id;
		this.regDate = regDate;
		this.loginId = loginId;
		this.loginPw = loginPw;
		this.name = name;
	}
	
}
