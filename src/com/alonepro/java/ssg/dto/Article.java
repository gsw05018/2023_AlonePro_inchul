package com.alonepro.java.ssg.dto;

public class Article extends Dto { // Article객체를 담을 클래스 생성

	public int memberId;
	public String title;
	public String body;
	public int hit;

	public Article(int id, int memberId,String title, String body, String regDate) { // Article 객체의 내용물을 담을 생성자 생성

		this(id, memberId, title, body, regDate, 0); // article 생서자에서 hit가 안들어간 버전이다. 0이 생략되었는데 조회수가 있을시 0이 저절로 들어간다

	}

	public Article(int id, int memberId, String title, String body, String regDate, int hit) { // Article 객체의 내용물을 담을 생성자 생성
		super();
		this.id = id;
		this.memberId = memberId;
		this.title = title;
		this.body = body;
		this.regDate = regDate;
		this.hit = hit;

	}

	public void increasehit() {
		hit++;
		// 1씩 증가를 한다!

	}
}
