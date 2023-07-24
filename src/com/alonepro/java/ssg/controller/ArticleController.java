package com.alonepro.java.ssg.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.alonepro.java.ssg.dto.Article;
import com.alonepro.java.ssg.util.util;

//article의 역활을 하는 기능들을 articleController에 모음

public class ArticleController extends Controller {

	private List<Article> articles; // 전체적으로 aritcles를 쓰기 때문에 하나 만들어준다
	private Scanner sc; // 전체적으로 Scanner 쓰기 때문에 만들어준다
	private String command; 
	private String actionMethodName;

	public ArticleController(Scanner sc) { // articleController 생성자 생성

		this.sc = sc;
		articles = new ArrayList<>(); //app에 있던 articles를 지우고 controller에서 쓸 수있게 리모콘을 새로 만든다.

	}

	public void doAction(String command, String actionMethodName) {

		this.command = command;
		this.actionMethodName = actionMethodName;

		switch (actionMethodName) { //commandBits[1]글자가 맞으면 실행이 된다

		case "wrtie":
			doWrite();
			break;
		case "delete":
			doDelete();
			break;
		case "modify":
			doModify();
			break;
		case "list":
			showList();
			break;
		case "detail":
			showDetail();
			break;

		}

	}

	// aritcle write 기능
	private void doWrite() {

		int id = articles.size() + 1; // maketestdata가 만들어지면서 게시물번호가 test다음 번호 이어야 되는데 1번으로 초기화가 되어
		// lastArticleId를 지우고 게시믈 크기인 articles.size()로 게시글번호를 측정을 한다

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
		return;

	}

	// article list 기능
	private void showList() { // command를 넘겨준다

		if (articles.size() == 0) {// articles의 내용물이 없을 때

			System.out.println("게시글이 없습니다");
			System.out.println("");
			return; // 반복문이 아니기 때문에 break,continue는 못 쓰기 때문에 return을 해준다. if문에서 빠져나온다는 내용이다

		}
		// article list를 제외하고 그 뒤에 내용이 있다면 잘나내서 searchKeyword에 넣는다
		// searchKeyword에는 article list 뒤에 검색내용만 들어가게 된다
		String searchKeyword = command.substring("article list".length()).trim();

		// forListArticles는 출력문에 있는 forListArticles가 사용한다
		List<Article> forListArticles = articles;

		// 게시물 찾기
		// 만약에 searchKeyword가 있다면
		if (searchKeyword.length() > 0) {
			// forListArticles의 새 창고를 짓는다
			forListArticles = new ArrayList<>();
			for (Article article : articles) {

				// 입력된 키워드에 일치하는 것은 담아서 저장을 한다
				if (article.title.contains(searchKeyword)) {
					forListArticles.add(article);
				}

			}
			if (articles.size() == 0) {

				System.out.println("검색된 게시글이 존재하지 않습니다");
				return;

			}
		}

		System.out.println("번호   | 제목  | 내용  | 조회수 ");

		for (int i = forListArticles.size() - 1; i >= 0; i--) { // 번호를 최신순으로 하는 함수
			Article article = forListArticles.get(i);// Article article를 불러오고 맨위 article객체 articles안에 내용물을 불러온다

			System.out.printf("%4d  | %4s  |%4s  | %4d\n", article.id, article.title, article.body, article.hit);
			// 포멧앞에 숫자를 붙이면 그 만큼 공간을 만든다는 의미이다!
		}

//		 startsWith 란? startsWith(commnad)에서 시작하는 
//		String 문자열부터 입력한 command에 문자열까지 맞으면 true , 틀리면 false return값을 돌려준다

	}

	// article delete 기능
	private void doDelete() { // command를 넘겨준다

		String[] commandBits = command.split(" ");
		int id = Integer.parseInt(commandBits[2]);

		int foundIndex = getArticleInedxById(id); // foundIndex를 초기화 , -1이라고 하는 거는 foundIndex안에 내용물이 없다

		if (foundIndex == -1) {

			System.out.printf("%d번 게시글이 없습니다\n", id);
			System.out.println();

		}

		articles.remove(foundIndex); // articles안에 foundIndex를 하나씩 삭제한다
		System.out.printf("%d번 게시글이 삭제되었습니다", id);
		System.out.println();

	}

	// article detail 기능
	private void showDetail() {

		String[] commandBits = command.split(" "); // commandBits란 command에서 " " 공백을 기준으로 문자를 나눈 덩어리다, 한무장에서 여러
		// 문장으로 되기 때문에 String앞에 []배열을 써준다
		int id = Integer.parseInt(commandBits[2]); // commandBits[2]에 오는 문자 '1'을 정수 1로 바꿔준다
		//commandBits[0] >> article
		//commandBits[1] >> detail
		//commandBits[2] >> "1" 문자이끼때문에 위에서 정수로 치환해준다
		//article detail로 시작하면서 뒤에 숫자가 오면 실행을 해준다
		String regDate = util.getNowDateStr(); // 현재 날짜는 util에서 끌어서 쓴다
		Article foundArticle = getArticleById(id); // Article안에 foundArticle 만들고 null로 초기화

		if (foundArticle == null) { // foundArticle이 null이 된다면 실행이 된다
			System.out.printf("%d번 게시글이 없습니다\n", id);
			System.out.println();
			return; // continue는 실행을 멈추고 위에서부터 다시 실행이 된다
		}

		foundArticle.increasehit(); // 조건문이 다 끝나고 출력이 될때 조회수 출력, hit++함수가 포함이 되어있기 때문에 조회할때마다 1씩 증가

		System.out.println("번호 : " + foundArticle.id);
		System.out.println("제목 : " + foundArticle.title);
		System.out.println("내용 : " + foundArticle.body);
		System.out.println("작성자 : 익명 ");
		System.out.println("날짜 :  " + regDate);
		System.out.println("조회수 : " + foundArticle.hit);
		System.out.println();

	}

	// article modify기능
	private void doModify() {

		String[] commandBits = command.split(" ");
		int id = Integer.parseInt(commandBits[2]);

		Article foundArticle = getArticleById(id);

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

	// foundIndexid를 찾는 함수
	private int getArticleInedxById(int id) {

		int i = 0;
		for (Article article : articles) {

			if (article.id == id) {

				return i; // foundIndex를 i로 인덱스로 초기화, i는 indext다!

			}
			i++;
		}
		return -1;
	}

	// articles 내용을 찾는 함수
	private Article getArticleById(int id) {

//		for (int i = 0; i < articles.size(); i++) { // article.size()크기까지 순회하는 함수
//			Article article = articles.get(i);
//
//			if (article.id == id) { // ar	ticle.id가 detail에서 선언한 id와 같으면 foundArticle이 null에서 article이랑 같아진다
//				
//				return article;
//			
//			} // article.id == id 가 실행이 되면 나가서 detail에 내용이 출력이 된다
//		}
//		return null;
//	}

		// 1번 방법
		// articles에 내용이 article에 들어가 밑에 조건문, 출력문이 실행이 된다
		// Article article : articles 에는
		// int i = o; i < articles.size(); i++
		// Article article = articles.get(i);이 포함이 되어 있다
		// 말 그래도 article의 1번 게시물, 2번 게시물, 더 많은 게시물을 하나하나씩 찾는 다는 내용이다

		// 2번방법
		// for문이 반복이 되는게 있기 때문에 getArticleIndexId 함수를 이용한다
//			if (article.id == id) {
//				return article;
//			}
//		}
//		return null;
//	}

		int index = getArticleInedxById(id);

		if (index != -1) {
			return articles.get(index);
		}

		return null;
	}
	
	public void makeTestData() {
		System.out.println("프로그램 시작시 실행됩니다.");
		articles.add(new Article(1, "제목 1", "내용 1", util.getNowDateStr(), 11));
		articles.add(new Article(2, "제목 2", "내용 2", util.getNowDateStr(), 22));
		articles.add(new Article(3, "제목 3", "내용 3", util.getNowDateStr(), 33));
	}

}
