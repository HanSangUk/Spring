package com.army.choo.Controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.army.choo.DTO.MemberDTO;
import com.army.choo.Service.MemberService;


@Controller
public class MemberController {
	
	@Autowired
	private MemberService ms;
	
	@Autowired
	private HttpSession session;
	
	private ModelAndView mav;
	//회원가입선택(일반or기업)
	@RequestMapping(value="/memberinsertpick")
	public String memberInsertPick() {
		return "ComMember/MemberInsertPick";
	}
	//기업회원가입 페이지 이동
	@RequestMapping(value="/commemberinsertgo")
	public String comMemberJoin() {
		return "ComMember/ComMemberJoin";
	}
	//기업회원가입
	@RequestMapping(value="/commemberinsertform")
	public ModelAndView comMemberInsertForm(MemberDTO mDTO) {
		mav = ms.comMemberInsertForm(mDTO);
		return mav;
	}
	//로그인 선택(일반or기업)
	@RequestMapping(value="/loginpick")
	public String loginPick() {
		return "ComMember/LoginPick";
	}
	//기업로그인 페이지 이동
	@RequestMapping(value="/commemberlogin")
	public String comMemberLogin() {
		return "ComMember/ComLogin";
	}
	//기업로그인
	@RequestMapping(value="/comlogin")
	public ModelAndView comLoignForm(MemberDTO mDTO) {
		mav = ms.comLoignForm(mDTO);
		return mav;
	}
	//로그아웃
	@RequestMapping(value="/logout")
	public String logOut() {
		session.invalidate();
		return "Main";
	}
	//마이페이지 이동
	@RequestMapping(value="/mypage")
	public ModelAndView myPage(@RequestParam(value="comnumber") String comnumber) {
		mav = ms.comMyPage(comnumber);
		return mav;
	}
	//기업 회원정보수정 페이지 이동
	@RequestMapping(value="/commemberupdate")
	public ModelAndView comMemberUpdate(@RequestParam(value="comnumber") String comnumber) {
		mav = ms.comMemberUpdate(comnumber);
		return mav;
	}
	//기업회원정보수정
	@RequestMapping(value="/commemberupdateform")
	public ModelAndView comMemberUpdateForm(MemberDTO mDTO) {
		mav = ms.comMemberUpdateForm(mDTO);
		return mav;
	}
	//회원탈퇴 페이지 이동
	@RequestMapping(value="/commemberout")
	public String comMemberOut() {
		return "ComMember/ComMemberOut";
	}
	//회원탈퇴
	@RequestMapping(value="/commemberoutform")
	public ModelAndView comMemberOutForm(MemberDTO mDTO) {
		mav = ms.comMemberOutForm(mDTO);
		session.invalidate();
		return mav;
	}
}
