package com.yedam.app.board.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class BoardVO {
	private Integer bno;			// null이 들어와야 할 경우 Integer 사용
	private String title;		
	private String content;
	private String writer;
	// java.util.Date 		: (Default) yyyy/MM/dd
	// <input type="date">	: yyyy-MM-dd
	// 값을 입력하는 경우 포맷 (출력과 무관함!!!)
	@DateTimeFormat(pattern = "yyyy-MM-dd")				
	private Date regdate;		// Date 대신 경우에 따라 String 사용
	private Date updatedate;	//
	private int replycnt;
}
