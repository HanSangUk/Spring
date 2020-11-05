package com.army.choo.DAO;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.army.choo.DTO.MemberDTO;

@Repository
public class MemberDAO {

	@Autowired
	private SqlSessionTemplate sql;
	
	//기업 회원가입
	public int comMemberInsertForm(MemberDTO mDTO) {
		return sql.insert("ComMember.ComMemberInsert", mDTO);
	}
	
	//기업 로그인
	public String comLoginForm(MemberDTO mDTO) {
		return sql.selectOne("ComMember.ComLoginForm", mDTO);
	}
	
	//기업회원정보수정 정보불러오기(수정 페이지로 이동)
	public MemberDTO comMemberUpdate(String comnumber) {
		return sql.selectOne("ComMember.ComMemberUpdate", comnumber);
	}
	
	//기업회원정보수정
	public int comMemberUpdateForm(MemberDTO mDTO) {
		return sql.update("ComMember.ComMemberUpdateForm", mDTO);
	}

	public int comMemberOutForm(MemberDTO mDTO) {
		return sql.delete("ComMember.ComMemberDeleteForm", mDTO);
	}

	public MemberDTO comMyPage(String comnumber) {
		return sql.selectOne("ComMember.ComMyPage", comnumber);
	}

}
