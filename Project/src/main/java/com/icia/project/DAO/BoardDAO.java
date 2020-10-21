package com.icia.project.DAO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.icia.project.DTO.BoardDTO;
import com.icia.project.DTO.PageDTO;

@Repository
public class BoardDAO {
	
	@Autowired
	private SqlSessionTemplate sql;
	//게시글 작성 DAO
	public int boardinsert(BoardDTO bDTO) {
		return sql.insert("Board.boardinsert", bDTO);
	}
	//게시글 수
	public int boardlistcount() {
		return sql.selectOne("Board.boardlistcount");
	}
	//게시글 리스트(목록)
	public List<BoardDTO> boardlist(PageDTO paging) {
		return sql.selectList("Board.boardlist", paging);
	}

	public BoardDTO boardview(int bnumber) {
		return sql.selectOne("Board.boardview", bnumber);
	}

	public int boardhits(int bnumber) {
		return sql.update("Board.boardhits", bnumber);
	}
	//게시글 수정 DAO
	public int boardupdate(BoardDTO bDTO) {
		return sql.update("Board.boardupdate", bDTO);
	}

	public int boarddelete(int bnumber) {
		return sql.delete("Board.boarddelete", bnumber);
	}

	public int searchcount(String searchtype, String keyword) {
		Map<String, String> searchMap = new HashMap<String, String>();
		searchMap.put("type", searchtype);
		searchMap.put("word", keyword);
		return sql.selectOne("Board.Searchcount", searchMap);
	}

	public List<BoardDTO> searchlist(PageDTO paging) {
		return sql.selectList("Board.boardSearch", paging);
	}
	
	//마이페이지 본인글
	public List<BoardDTO> mypagelist(String bwriter) {
		return sql.selectList("Board.mypage", bwriter);
	}
	
	//마이페이지 본인글 카운트
	public int mypagecount(String bwriter) {
		return sql.selectOne("Board.mypagecount", bwriter);
	}

}
