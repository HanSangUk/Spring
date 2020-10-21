package com.icia.project.DAO;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.icia.project.DTO.BoardDTO;
import com.icia.project.DTO.MemberDTO;
import com.icia.project.DTO.PageDTO;

@Repository
public class MemberDAO {
	
	@Autowired
	private SqlSessionTemplate sql;
	
	
	public String idoverlap(String mid) {
		return sql.selectOne("Member.idovarlap", mid);
	}


	public int memberjoinform(MemberDTO mDTO) {
		if(mDTO.getKakaoId() != null) {
			return sql.insert("Member.kakaoMemberJoin", mDTO);
		} else if(mDTO.getNaverId() != null) {
			return sql.insert("Member.naverMemberJoin", mDTO);
		} else {
			return sql.insert("Member.memberjoinform", mDTO);
		}
	}


	public String loginform(MemberDTO memberlogin) {
		return sql.selectOne("Member.login", memberlogin);
	}

	//회원목록
	public List<MemberDTO> memberlist(PageDTO paging) {
		return sql.selectList("Member.memberlist", paging);
	}

	//회원수
	public int memberCount() {
		return sql.selectOne("Member.Count");
	}


	public MemberDTO memberview(String mid) {
		return sql.selectOne("Member.memberview", mid);
	}


	public String kakaoLogin(String kakaoId) {
		return sql.selectOne("Member.kakaoLogin", kakaoId);
	}


	public String naverLogin(String naverId) {
		return sql.selectOne("Member.naverLogin", naverId);
	}

	//회원정보수정
	public int memberupdateform(MemberDTO update) {
		return sql.update("Member.memberupdate", update);
	}


	public int memberdelete(String mid) {
		return sql.delete("Member.memberdelete", mid);
	}



}
