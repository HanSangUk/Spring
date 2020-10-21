package com.icia.project;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = 
{"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
@Slf4j

public class BoardControllerTest {
	@Autowired
	private WebApplicationContext wac;
	
	// MockMvc는 서블릿과 동일하게 동작함.
	private MockMvc mock;
	
	// mock 객체를 만들어 주기 위해
	@Before  //테스트하기전 세팅
	public void setup() {
		System.out.println("setup 메소드 호출");
		this.mock = MockMvcBuilders.webAppContextSetup(wac).build();
	}
	
	
	@Test
	@Transactional //실제 DB에 들어갈수있게 하는 어노테이션
	@Rollback(false) 
	public void boardWriterTest() throws Exception{
		System.out.println("글쓰기 테스트 호출");
		log.info("글쓰기 테스트");
		log.info(mock.perform(MockMvcRequestBuilders.post("/boardinsert")
				.param("bwriter", "testwriter")
				.param("btitle", "testtitle")
				.param("bcontents", "testcontents"))
				.andReturn().getModelAndView().getViewName());
				System.out.println("글쓰기 테스트 호출 종료");
	}
}
