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
	
	public int boardinsert(BoardDTO bDTO) {
		return sql.insert("Board.boardinsert", bDTO);
	}

	public int boardlistcount() {
		return sql.selectOne("Board.boardlistcount");
	}

	public List<BoardDTO> boardlist(PageDTO paging) {
		return sql.selectList("Board.boardlist", paging);
	}

	public BoardDTO boardview(int bnumber) {
		return sql.selectOne("Board.boardview", bnumber);
	}

	public int boardhits(int bnumber) {
		return sql.update("Board.boardhits", bnumber);
	}

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

}
