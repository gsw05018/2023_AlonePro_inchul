package com.alonepro.java.ssg.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.alonepro.java.ssg.dto.Member;
import com.alonepro.java.ssg.util.util;

//articleController이랑 똑같다

public class MemberController extends Controller {

	//class가 끝나도 실행이 되기 위해서 맨 위에 작성하는 거임
	private List<Member> members;
	private Scanner sc;
	private String command;
	private String actionMethodName;
	private Member loginedMember;

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
		case "login":
			dologin();
			break;
		default:
			System.out.println("존재하지 않는 명령어입니다");
			break;

		}

	}

	private void dologin() {

		String regDate = util.getNowDateStr();

		System.out.println("< 로그인 >");
		System.out.println();

		System.out.printf("아이디 : ");
		String loginId = sc.nextLine();
		System.out.println();
		System.out.printf("비밀번호 : ");
		String loginPw = sc.nextLine();

		//logind찾는 함수 
		Member member = getMemberByLoginId(loginId);

		if (member == null) {

			System.out.println("해당되는 회원이 없습니다");
			return;

		}
		if (member.loginPw.equals(loginPw) == false) {

			System.out.println("비밀번호가 틀렸습니다");
			return;

		}

		//여기에 Member loginedMember를 만들면 안됨 그럼 기억하는게 함수끝나면 사라짐
		loginedMember = member;
		
		System.out.printf("로그인 성공! %s님 환영합니다\n", loginedMember.name);

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

	private Member getMemberByLoginId(String loginId) {

		int index = getMemberIndexByLoginId(loginId); // loginId를 주는 index 생성

		if (index == -1) { // -1은 없다는 뜻

			return null;

		}

		return members.get(index);
		//member로 return이 끝나기때문에 void를 뺴고 member로 해야함
		

	}

}
