package com.alonepro.java.ssg;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		System.out.println("== 프로그램 시작 ==");

		List<Article> articles = new ArrayList<>(); // Article객체 생성
		Scanner sc = new Scanner(System.in); // 스캐너 선언
		int lastArticleId = 0; // 게시글 번호 선언 초기화

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

				int id = lastArticleId + 1; // lastArticleId는 0이니까 1을 더해 반보이 더해질때마다 1이 더해진다.
				lastArticleId = id; // id를 바뀐 lastArticleId로 선언

				
				System.out.println("< 게시글 >");

				System.out.printf("제목 : ");
				String title = sc.nextLine();
				System.out.printf("내용 : ");
				String body = sc.nextLine();

				System.out.printf("%d번글이 생성되었습니다\n", id); // 바뀐 id를 적용 lastArticleId = 1, 한번 더 반복이 되면 2가 된다
				System.out.println();

				Article article = new Article(id, title, body); // article wirte를 담기 위한 객체 생성
				articles.add(article); // 위 article의 내용을 main 맨위에 있는 article객체 articles에 넣는다
				continue;

			} else if (command.equals("article list")) {

				if (articles.size() == 0) {// articles의 내용물이 없을 때

					System.out.println("게시글이 없습니다");
					System.out.println("");
					continue;
				}

				System.out.println("번호 | 제목 | 제목");

				for (int i = articles.size() - 1; i >= 0; i--) { //번호를 최신순으로 하는 함수
					Article article = articles.get(i);// Article article를 불러오고 맨위 article객체 articles안에 내용물을 불러온다

					System.out.printf("%d  | %s  |%s\n", article.id, article.title, article.body);

//				for(int i = 0; i < aritcles.size(); i++) { //번호를 1번부터 나타나게 하는 함수
//					Article article = aritcles.get(i);
//				}

				}

			} else if (command.equals("article delete")) {

				System.out.println("삭제");

			} else if (command.equals("article detail")) {

				System.out.println("삭제");

			} else {

				System.out.println(command +"는 존재하지 않는 명령어입니다");
				System.out.println("다시 입력해주세요");
				System.out.println();

			}

		}
		//sc.close(); // 스캐너 선언시 같이 써야됨
	}

}

class Article { //Article객체를 담을 클래스 생성

	int id;
	String title;
	String body;

	public Article(int id, String title, String body) { //Article 객체의 내용물을 담을 생성자 생성
		super();
		this.id = id;
		this.title = title;
		this.body = body;
	}
}
