package com.yedam.app.board.service;

import java.util.List;
import java.util.Map;

public interface BoardService {
	// 전체조회
	public List<BoardVO> getBoardList();
	
	// 단건조회
	public BoardVO getBoard(BoardVO board);
	
	// 등록
	public int insertBoard(BoardVO board);
	
	// 수정
	public Map<String, Object> updateBoard(BoardVO board);
		
	// 삭제
	public Map<String, Object> deleteBoard(List<Integer> boardList);
}
