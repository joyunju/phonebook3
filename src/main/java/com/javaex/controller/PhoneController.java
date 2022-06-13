// servlet 아니고 class 파일로 생성 

package com.javaex.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.dao.PhoneDao;
import com.javaex.vo.PersonVo;

@Controller
// 공통된 주소는 이렇게 따로 뺄 수 있고 -> 주소 : http://localhost:8088/phonebook3/guest/test
//@RequestMapping(value="/guest")
public class PhoneController {

	// 필드

	// 생성자

	// 메소드 - gs

	// 메소드 - 일반
	
	// 전화번호 리스트 
	@RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST })
	public String list(Model model) {
		System.out.println("PhoneController>list()");
		
		// Dao를 통해서 personList(주소)를 가져온다
		PhoneDao phoneDao = new PhoneDao();
		List<PersonVo> personList = phoneDao.getPersonList();
		//System.out.println();
		
		// ds 데이터 보내기 -> request attribute 에 넣기 
		//model.addAttribute("어트리뷰트에 넣을이름 정하기", 값);
		model.addAttribute("personList", personList);
		//model.addAttribute("add", 25); model 여러개 담을 수 있음 
		
		return "/WEB-INF/views/list.jsp";
	}
	
	
	// 전화번호 등록
	// @RequestParam : 파라미터 매핑 -> Http 요청 파라미터를 메소드 파라미터에 넣어주는 어노테이션
	@RequestMapping(value = "/write", method = { RequestMethod.GET, RequestMethod.POST })
	public String write(@RequestParam("name") String name, @RequestParam("hp") String hp,
			@RequestParam("company") String company) {
		System.out.println("PhoneController>write()");

		// 파라미터 꺼내기
//		System.out.println(name);
//		System.out.println(hp);
//		System.out.println(company);

		// vo로 묶기
		PersonVo personVo = new PersonVo(name, hp, company);
		System.out.println(personVo);

		// Dao로 저장하기
		PhoneDao phoneDao = new PhoneDao();
		int count = phoneDao.personInsert(personVo);
		System.out.println(count);

		// 리다이렉트로 처리하기
		// 리스트로 리다이렉트 시킬 예정 
		
		
		//http://localhost:8088/phonebook3/list 호출시 write.jsp 파일 화면 보임
		//return "포워드자리";
		return "redirect:/phonebook3/list";
		// return "/WEB-INF/views/write.jsp";
	}

	// 전화번호 등록
	@RequestMapping(value = "/writeForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String writeForm() {
		System.out.println("PhoneController>writeForm()");
		// Dao
		// http://localhost:8088/phonebook3/writeForm 호출시 writeForm.jsp 파일 화면 보임
		return "/WEB-INF/views/writeForm.jsp";
	}

	// 테스트 메소드
	// @RequestMapping : 메소드 단독 매핑
	// localhost:8088/phonebook3/test
	@RequestMapping(value = "/test", method = { RequestMethod.GET, RequestMethod.POST })
	// value는 요청받을 url을 설정하게 된다.
	// method는 어떤 요청으로 받을지 정의하게 된다.(GET, POST, PUT, DELETE 등)
	public String test() {

		System.out.println("PhoneController>test()");
		// Dao
		// http://localhost:8088/phonebook3/test 호출시 test.jsp 파일 화면 보임
		return "/WEB-INF/views/test.jsp";
	}

	// 등록 메소드
	// 수정폼 메소드
	// 삭제 메소드
	// 리스트메소드

}
