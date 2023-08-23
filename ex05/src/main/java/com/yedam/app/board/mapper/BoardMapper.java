package com.yedam.app.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yedam.app.board.service.BoardVO;

public interface BoardMapper {
	
	// 전체조회
	public List<BoardVO> selectBoardAll();
	
	// 단건조회
	public BoardVO selectBoardInfo(BoardVO board);
	
	// 등록
	public int insertBoardInfo(BoardVO board);
	
	// 수정
	public int updateBoardInfo(BoardVO board);
	
	// 삭제
	public int deleteBoardInfo(@Param("no") int bno);
}
