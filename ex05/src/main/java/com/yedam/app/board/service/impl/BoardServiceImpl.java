package com.yedam.app.board.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.app.board.mapper.BoardMapper;
import com.yedam.app.board.service.BoardService;
import com.yedam.app.board.service.BoardVO;

@Service
public class BoardServiceImpl implements BoardService{

	@Autowired
	BoardMapper boardMapper;
	
	@Override
	public List<BoardVO> getBoardList() {
		return boardMapper.selectBoardAll();
	}

	@Override
	public BoardVO getBoard(BoardVO board) {
		return boardMapper.selectBoardInfo(board);
	}

	@Override
	public int insertBoard(BoardVO board) {
		int result = boardMapper.insertBoardInfo(board);
		
		if(result == 1) {
			return board.getBno();		// selectKey
		} else {
			return -1;
		}
	}

	@Override
	public Map<String, Object> updateBoard(BoardVO board) {
		boolean result = false;
		int count = boardMapper.updateBoardInfo(board);

		Map<String, Object> datas = new HashMap<>();
		if(count == 1) {
			result = true;
		}
		datas.put("Result", result);
		datas.put("Target Bno", board.getBno());
		return datas;
	}

	@Override
	public Map<String, Object> deleteBoard(List<Integer> boardList) {
		Map<String, Object> datas = new HashMap<>();
		boolean result = false;
		int count = 0;
		List<Integer> successList = new ArrayList<>();
		for(Integer target : boardList) {
			int res = boardMapper.deleteBoardInfo(target);
			if(res == 1) {
				count++;
				successList.add(target);
			}
		}
		if(count > 0) {
			result = true;
		}
		
		datas.put("Result", result);
		datas.put("Target Bnos", boardList);
		datas.put("Success List", successList);
		return datas;
	}

}
