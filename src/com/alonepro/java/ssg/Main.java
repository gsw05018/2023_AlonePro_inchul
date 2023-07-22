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
				String regDate = util.getNowDateStr();

				System.out.printf("%d번글이 생성되었습니다\n", id); // 바뀐 id를 적용 lastArticleId = 1, 한번 더 반복이 되면 2가 된다
				System.out.println();

				Article article = new Article(id, title, body, regDate); // article wirte를 담기 위한 객체 생성
				articles.add(article); // 위 article의 내용을 main 맨위에 있는 article객체 articles에 넣는다
				continue;

			} else if (command.equals("article list")) {

				if (articles.size() == 0) {// articles의 내용물이 없을 때

					System.out.println("게시글이 없습니다");
					System.out.println("");
					continue;
				}

				System.out.println("번호 | 제목 | 제목");

				for (int i = articles.size() - 1; i >= 0; i--) { // 번호를 최신순으로 하는 함수
					Article article = articles.get(i);// Article article를 불러오고 맨위 article객체 articles안에 내용물을 불러온다

					System.out.printf("%d  | %s  |%s\n", article.id, article.title, article.body);

				}

//				 startsWith 란? startsWith(commnad)에서 시작하는 
//				String 문자열부터 입력한 command에 문자열까지 맞으면 true , 틀리면 false return값을 돌려준다

			} else if (command.startsWith("article delete")) {

				String[] commandBits = command.split(" ");
				int id = Integer.parseInt(commandBits[2]);

				int foundIndex = -1; // foundIndex를 초기화 , -1이라고 하는 거는 foundIndex안에 내용물이 없다

				for (int i = 0; i < articles.size(); i++) {
					Article article = articles.get(i);

					if (article.id == id) {

						foundIndex = i; // foundIndex를 i로 인덱스로 초기화, i는 indext다!

					}
				}
				if (foundIndex == -1) {

					System.out.printf("%d번 게시글이 없습니다\n", id);
					System.out.println();

				}

				articles.remove(foundIndex); // articles안에 foundIndex를 하나씩 삭제한다
				System.out.printf("%d번 게시글이 삭제되었습니다", id);
				System.out.println();

			} else if (command.startsWith("article detail")) {
				String[] commandBits = command.split(" "); // commandBits란 command에서 " " 공백을 기준으로 문자를 나눈 덩어리다, 한무장에서 여러
															// 문장으로 되기 때문에 String앞에 []배열을 써준다
				int id = Integer.parseInt(commandBits[2]); // commandBits[2]에 오는 문자 '1'을 정수 1로 바꿔준다
//				commandBits[0] >> article
//				commandBits[1] >> detail
//				commandBits[2] >> "1" 문자이끼때문에 위에서 정수로 치환해준다
// 				article detail로 시작하면서 뒤에 숫자가 오면 실행을 해준다

				Article foundArticle = null; // Article안에 foundArticle 만들고 null로 초기화
				String regDate = util.getNowDateStr(); // 현재 날짜는 util에서 끌어서 쓴다

				for (int i = 0; i < articles.size(); i++) { // article.size()크기까지 순회하는 함수
					Article article = articles.get(i);

					if (article.id == id) { // article.id가 detail에서 선언한 id와 같으면 foundArticle이 null에서 article이랑 같아진다
						foundArticle = article;
						break;
					} // article.id == id 가 실행이 되면 나가서 detail에 내용이 출력이 된다
				}
				if (foundArticle == null) { // foundArticle이 null이 된다면 실행이 된다
					System.out.printf("%d번 게시글이 없습니다\n", id);
					System.out.println();
					continue; // continue는 실행을 멈추고 위에서부터 다시 실행이 된다
				}
				System.out.println("번호 : " + foundArticle.id);
				System.out.println("제목 : " + foundArticle.title);
				System.out.println("내용 : " + foundArticle.body);
				System.out.println("작성자 : 익명 ");
				System.out.println("날짜 :  " + regDate);
				System.out.println();

			} else if (command.startsWith("article modify")) {
				String[] commandBits = command.split(" ");
				int id = Integer.parseInt(commandBits[2]);

				Article foundArticle = null;

				for (int i = 0; i < articles.size(); i++) {
					Article article = articles.get(i);

					if (article.id == id) {
						foundArticle = article;

					}
					if (foundArticle == null) {

						System.out.printf("%d번 글이 없습니다\n", id);

					}
					System.out.println("제목 : "); // detail이랑 달리 수정할 내용을 다시 써야되기 때문에 출력하는 내용을 스캐너로 해주면 된다
					String title = sc.nextLine();
					System.out.println("내용 : ");
					String body = sc.nextLine();

					foundArticle.title = title; // foundArticle이 위에서 article이랑 같다고 했기 때문에 article.title랑
												// foundArticle.title이랑 같게 된다
					foundArticle.body = body;

					System.out.printf("%d번 게시글이 수정되었습니다\n", id);
					// 상세보기랑 비슷하게 해주면 된다

				}

			}

			else {

				System.out.println(command + "는 존재하지 않는 명령어입니다");
				System.out.println("다시 입력해주세요");
				System.out.println();

			}

			sc.close(); // 스캐너 선언시 같이 써야됨
		}
	}

}

class Article { // Article객체를 담을 클래스 생성

	int id;
	String title;
	String body;
	String regDate;

	public Article(int id, String title, String body, String regDate) { // Article 객체의 내용물을 담을 생성자 생성
		super();
		this.id = id;
		this.title = title;
		this.body = body;
		this.regDate = regDate;
	}
}
