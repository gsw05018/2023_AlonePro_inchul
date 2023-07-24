package com.alonepro.java.ssg.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.alonepro.java.ssg.dto.Member;
import com.alonepro.java.ssg.util.util;

//articleController이랑 똑같다

public class MemberController extends Controller {

	private List<Member> members;
	private Scanner sc;
	private String command;
	private String actionMethodName;

	public MemberController(Scanner sc) {

		members = new ArrayList<>();
		this.sc = sc;
	}

	public void doAction(String command, String actionMethodName) {

		this.actionMethodName = actionMethodName;
		this.command = command;

		switch (actionMethodName) {

		case "join":
			dojoin();
			break;

		}

	}

	private void dojoin() {

		int id = members.size() + 1;

		System.out.println("< 회원가입 >");

		String loginId = null; // while안에 있으면 while밖에 있는 loginId는

		String regDate = util.getNowDateStr();

		while (true) { // login가 틀리면 계속 반복이다

			System.out.printf("아이디  : ");
			loginId = sc.nextLine();

			if (isjoincheckLoginId(loginId) == false) {
				System.out.printf("%s은(는) 이미 사용중인 아이디입니다\n", loginId);
				continue;
			}
			break;
		}

		String loginPw = null;
		String loginPwCheck = null;

		while (true) {

			System.out.printf("비밀번호 : ");
			loginPw = sc.nextLine();
			System.out.printf("비밀번호 확인 : ");
			loginPwCheck = sc.nextLine();

			if (loginPw.equals(loginPwCheck) == false) {

				System.out.println("비밀번호를 다시 입력해주세요");
				continue;

			}

			break;

		}

		System.out.println("이름 : ");
		String name = sc.nextLine();

		System.out.printf("%d번 회원이 생성되었습니다\n", id); // 바뀐 id를 적용 lastArticleId = 1, 한번 더 반복이 되면 2가 된다
		System.out.println();

		Member member = new Member(id, loginId, loginPw, name, regDate); // member join를 담기 위한 객체 생성
		members.add(member); // 위 member의 내용을 main 맨위에 있는 memeber객체 members에 넣는다

	}

	// loginId index찾는 함수
	private boolean isjoincheckLoginId(String loginId) {

		int index = getMemberIndexByLoginId(loginId); // loginId를 주는 index 생성

		if (index == -1) { // -1은 없다는 뜻

			return true;

		}

		return false;

	}

	// loginId index찾는 함수
	private int getMemberIndexByLoginId(String loginId) {
		int i = 0;

		for (Member member : members) { // get(i)를 찾는 함수 생성

			if (member.loginId.equals(loginId)) {

				return i;

			}
			i++;

		}
		return -1;

	}

}
