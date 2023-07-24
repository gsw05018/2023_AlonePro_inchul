package com.alonepro.java.ssg;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.alonepro.java.ssg.controller.ArticleController;
import com.alonepro.java.ssg.controller.MemberController;
import com.alonepro.java.ssg.dto.Article;
import com.alonepro.java.ssg.dto.Member;
import com.alonepro.java.ssg.util.util;

public class App {

	public List<Article> articles; // Articl List 생성
	// static은 서로 staticl끼리 공유가 가능하기 때문에 static로 만들고
	// static생성자를 만들었다.
	public List<Member> members; // member에 List생성

	public App() {
		articles = new ArrayList<>(); // articles에 객체연결
		members = new ArrayList<>(); // memvers에 객체연결
	}

	public void start() {
		System.out.println("== 프로그램 시작 ==");

		makeTestData(); // 프로그램 시작 시 게시글 내용이 포함이 되어 원활하게 시험하기위해 만든다

		Scanner sc = new Scanner(System.in); // 스캐너 선언

		MemberController memberController = new MemberController(sc, members); //생성자를 받아준다
		ArticleController articleController = new ArticleController(sc, articles);

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

			} else if (command.equals("article write")) {

				articleController.doWrite(); // 내용을 집어넣을 함수를 만들어준다

			} else if (command.equals("article list")) {

				articleController.showList(command);

			} else if (command.startsWith("article delete")) {

				articleController.doDelete(command);

			} else if (command.startsWith("article detail")) {

				articleController.showDetail(command);

			} else if (command.startsWith("article modify")) {

				articleController.doModify(command);

			} else if (command.equals("member join")) { // article write랑 같은 맥락이다

				memberController.dojoin();

			} else {

				System.out.println(command + "는 존재하지 않는 명령어입니다");
				System.out.println("다시 입력해주세요");
				System.out.println();

			}
		}

		// sc.close(); // 스캐너 선언시 같이 써야됨
	}

	private void makeTestData() {
		System.out.println("프로그램 시작시 실행됩니다.");
		articles.add(new Article(1, "제목 1", "내용 1", util.getNowDateStr(), 11));
		articles.add(new Article(2, "제목 2", "내용 2", util.getNowDateStr(), 22));
		articles.add(new Article(3, "제목 3", "내용 3", util.getNowDateStr(), 33));
	}
}
