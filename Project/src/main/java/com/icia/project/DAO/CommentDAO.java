package com.icia.project.DAO;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.icia.project.DTO.CommentDTO;

@Repository
public class CommentDAO {

	@Autowired
	private SqlSessionTemplate sql;
	
	public int commentinsert(CommentDTO cDTO) {
		return sql.insert("Comment.commentinsert", cDTO);
	}

	public List<CommentDTO> commentlist(int bnumber) {
		return sql.selectList("Comment.commentlist", bnumber);
	}

	public int commentdelete(int cnumber) {
		return sql.delete("Comment.commentdelete", cnumber);
	}

}
