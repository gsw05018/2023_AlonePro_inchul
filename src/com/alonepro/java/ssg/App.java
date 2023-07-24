package com.alonepro.java.ssg;

import java.util.Scanner;

import com.alonepro.java.ssg.controller.ArticleController;
import com.alonepro.java.ssg.controller.Controller;
import com.alonepro.java.ssg.controller.MemberController;

public class App {

	public void start() {
		System.out.println("== 프로그램 시작 ==");

	

		Scanner sc = new Scanner(System.in); // 스캐너 선언

		MemberController memberController = new MemberController(sc); // 생성자를 받아준다
		ArticleController articleController = new ArticleController(sc);
		
		articleController.makeTestData(); //articleController안에 넣는다
		memberController.makeTestData();

		while (true) {// true일 때 계속 반복

			System.out.printf("검색어 : ");
			String command = sc.nextLine();
			command = command.trim(); // command를 쓸 때 띄어쓰기가 있을 수 있으니 양 옆 띄어씌기 없애주는 trimg함수 쓰기
			System.out.println();

			if (command.length() == 0) {

				continue;

			} else if (command.equals("system exit")) {

				System.out.println();

				System.err.println("화면을 닫겠습니다");

				break;

			}

			String[] commandBits = command.split(" "); //command를 공백을 기준으로 쪼갠다

			if (commandBits.length == 1) { //commandBits가 1이다 
				System.out.println("존재하지 않는 명령어 입니다.");
				continue;
			}

			String controllerName = commandBits[0]; // ex) article, memeber
			String actionMethodName = commandBits[1]; // ex) list, join

			Controller controller = null;

			if (controllerName.equals("article")) { 
				controller = articleController;
			} else if (controllerName.equals("member")) {
				controller = memberController;
			} else {
				System.out.println("존재하지 않는 명령어 입니다.");
				continue;
			}

			controller.doAction(command, actionMethodName); // 제대로 된 행동은 각자 controlelr에서 실행이 된다
		}
		sc.close(); // 스캐너 선언시 같이 써야됨
	}


}
