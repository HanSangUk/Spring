package com.icia.project.Controller;

import java.io.IOException;
import java.text.ParseException;

import javax.servlet.http.HttpSession;

import org.codehaus.jackson.JsonNode;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.scribejava.core.model.OAuth2AccessToken;
import com.icia.project.DTO.MemberDTO;
import com.icia.project.Service.MemberService;
import com.icia.project.api.KakaoJoinApi;
import com.icia.project.api.KakaoLoginApi;
import com.icia.project.api.NaverJoinApi;
import com.icia.project.api.NaverLoginApi;

@Controller
public class MemberController {
	
	@Autowired
	private MemberService ms;
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private NaverJoinApi naverJoinApi;
	
	@Autowired
	private NaverLoginApi naverLoginApi;
	
	@Autowired
	private KakaoJoinApi kakaoJoinApi;
	
	@Autowired
	private KakaoLoginApi kakaoLoginApi;
	
	private ModelAndView mav;
	
	//회원가입 페이지 이동
	@RequestMapping(value="/memberjoin")
	public String MemberJoin() {
		return "memberv/MemberJoin";
	}
	
	//회원가입 아이디 중복체크
	@RequestMapping(value="/idoverlap")
	public @ResponseBody String idOverlap(@RequestParam("mid") String mid) {
		String idresult = ms.idoverlap(mid);
		return idresult;
	}
	
	//회원가입
	@RequestMapping(value="/memberjoinform")
	public ModelAndView MemberJoinForm(MemberDTO mDTO) throws IllegalStateException, IOException {
		mav = ms.memberjoinform(mDTO);
		return mav;
	}
	
	//로그인페이지 이동
	@RequestMapping(value="/login")
	public String Login() {
		return "memberv/Login";
	}
	
	//로그인
	@RequestMapping(value="/loginform")
	public ModelAndView LoginForm(@ModelAttribute MemberDTO memberlogin) {
		mav = ms.loginform(memberlogin);
		return mav;
	}
	
	//로그아웃
	@RequestMapping(value="/logout")
	public String Logout() {
		session.invalidate();
		return "home";
	}
	
	//회원목록페이징
	@RequestMapping(value="/memberlistpaging")
	public ModelAndView memberlist(@RequestParam(value="page", required=false, defaultValue="1")int page) {
		mav = ms.memberlist(page);
		return mav;
	}
	
	//회원정보 상세조회
	@RequestMapping(value="/memberview")
	public ModelAndView memberView(@RequestParam("mid") String mid, @RequestParam(value="page", required=false, defaultValue="1")int page) {
		mav = ms.memberview(mid, page);
		return mav;
	}
	
	//회원정보 수정페이지 이동
	@RequestMapping(value="/memberupdate")
	public ModelAndView MemberUpdate(@RequestParam("mid") String mid) {
		mav = ms.memberupdate(mid);
		return mav;
	}
	
	//회원정보수정
	@RequestMapping(value="/memberupdateform")
	public ModelAndView MemberUpdateFrom(@ModelAttribute MemberDTO update) {
		mav = ms.memberupdateform(update);
		return mav;
	}
	//회원삭제
	@RequestMapping(value="/memberdelete")
	public ModelAndView Memberdelete(@RequestParam("mid") String mid, @RequestParam(value="page", required=false, defaultValue="1")int page) {
		mav = ms.memberdelete(mid, page);
		return mav;
	}
	
	// 카카오 서버 로그인 
	@RequestMapping(value="/kakaojoin")
	public ModelAndView kakaoJoin(HttpSession session) {
		String kakaoUrl = kakaoJoinApi.getAuthorizationUrl(session);
		mav = new ModelAndView();
		mav.addObject("kakaoUrl", kakaoUrl);
		mav.setViewName("KakaoLogin");
		return mav;
	}
	
	// 카카오 서버 인증 통과 후 처리 
	@RequestMapping(value="/kakaojoinok")
	public ModelAndView kakaoJoinOK(@RequestParam("code") String code,
			HttpSession session) {
		JsonNode token = kakaoJoinApi.getAccessToken(code);
		JsonNode profile = kakaoJoinApi.getKakaoUserInfo(token.path("access_token"));
		System.out.println("profile "+profile);
		// profile에 담긴 id 값을 가지고 MemberJoin.jsp로 이동 
		String kakaoId = profile.get("id").asText();
		mav = new ModelAndView();
		mav.addObject("kakaoId", kakaoId);
		mav.setViewName("memberv/MemberJoin");
		return mav;
	}
	
	// 카카오로그인
	@RequestMapping(value="/kakaologin")
	public ModelAndView kakaoLogin(HttpSession session) {
		String kakaoUrl = kakaoLoginApi.getAuthorizationUrl(session);
		mav = new ModelAndView();
		mav.addObject("kakaoUrl", kakaoUrl);
		mav.setViewName("KakaoLogin");
		return mav;
	}
	
	//카카오로그인
	@RequestMapping(value="/kakaologinok")
	public ModelAndView kakaoLoginOK(@RequestParam("code") String code) {
		JsonNode token = kakaoLoginApi.getAccessToken(code);
		JsonNode profile = kakaoLoginApi.getKakaoUserInfo(token.path("access_token"));
		
		mav = ms.kakaoLogin(profile); 
		return mav;
	}

	//네이버
	@RequestMapping(value="/naverjoin")
	public ModelAndView naverJoin(HttpSession session) {
		String naverUrl = naverJoinApi.getAuthorizationUrl(session);
		mav = new ModelAndView();
		mav.addObject("naverUrl", naverUrl);
		mav.setViewName("NaverLogin");
		return mav;
	}
	
	@RequestMapping(value="/naverjoinok")
	public ModelAndView naverJoinOK(@RequestParam("code") String code,
			@RequestParam("state") String state, HttpSession session) throws IOException, ParseException, org.json.simple.parser.ParseException {
		mav = new ModelAndView();
		OAuth2AccessToken oauthToken = naverJoinApi.getAccessToken(session, code, state);
		String profile = naverJoinApi.getUserProfile(oauthToken);
		JSONParser parser = new JSONParser();
		
		Object obj = parser.parse(profile);
		
		JSONObject naverUser = (JSONObject)obj;
		JSONObject userInfo = (JSONObject)naverUser.get("response");
		String naverId = (String) userInfo.get("id");
		
		mav.addObject("naverId", naverId);
		mav.setViewName("memberv/MemberJoin");
		
		return mav;
	}
	
	@RequestMapping(value="/naverlogin")
	public ModelAndView naverLogin(HttpSession session) {
		String naverUrl = naverLoginApi.getAuthorizationUrl(session);
		mav = new ModelAndView();
		mav.addObject("naverUrl", naverUrl);
		mav.setViewName("NaverLogin");
		return mav;
	}
	
	@RequestMapping(value="/naverloginok")
	public ModelAndView naverLoginOK(@RequestParam("code") String code,
			@RequestParam("state") String state, HttpSession session) throws IOException, ParseException, org.json.simple.parser.ParseException {
		OAuth2AccessToken oauthToken = naverLoginApi.getAccessToken(session, code, state);
		String profile = naverJoinApi.getUserProfile(oauthToken);
		mav = ms.naverLogin(profile);
		return mav;
		
	}
}
