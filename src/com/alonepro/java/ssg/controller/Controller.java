package com.alonepro.java.ssg.controller;

import com.alonepro.java.ssg.dto.Member;

public abstract class Controller {

	public abstract void doAction(String command, String actionMethodName);

	public abstract void makeTestData();

	//loginedMember, islogined를 article에서 사용할 수 있게 controller에서 상속을 받게 할라고 static, public를 사용한다
	public static Member loginedMember;

	public static boolean islogined() {

		return loginedMember != null; // != >> null에 반대는 내용물이 있다. 즉 로그인이 되어있다

	}

}