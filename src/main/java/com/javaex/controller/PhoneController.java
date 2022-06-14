// servlet 아니고 class 파일로 생성 

package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.PhoneService;
import com.javaex.vo.PersonVo;

@Controller
// 공통된 주소는 이렇게 따로 뺄 수 있고 -> 주소 : http://localhost:8088/phonebook3/guest/test
//@RequestMapping(value="/guest")
public class PhoneController {

	// 필드
	// PhoneDao phoneDao = new PhoneDao();가 밑에 반복으로 쓰이니 공통으로 올려두기
	// private PhoneDao phoneDao = new PhoneDao();

	@Autowired
	// private PhoneDao phoneDao; // new PhoneDao(); 주입해줘
	private PhoneService phoneService;

	// 생성자

	// 메소드 - gs

	// 메소드 - 일반
	// 전화번호 리스트
	@RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST })
	public String list(Model model) {
		System.out.println("PhoneController>list()");

		// Dao를 통해서 personList(주소)를 가져온다
		// PhoneDao phoneDao = new PhoneDao();
		// List<PersonVo> personList = phoneDao.getPersonList();
		// 이제 다오가 아닌 sesrvice를 통해서 personList(주소)를 가져온다
		List<PersonVo> personList = phoneService.getPersonList();
		// System.out.println();

		// ds 데이터 보내기 -> request attribute 에 넣기
		// model.addAttribute("어트리뷰트에 넣을이름 정하기", 값);
		model.addAttribute("personList", personList);
		// model.addAttribute("add", 25); model 여러개 담을 수 있음

		// return "/WEB-INF/views/list.jsp";
		// spring-servlet 파일에서 View Resolver 설정해서 아래처럼 주소가 짧아짐 앞에 /WEB-INF/views/이게 붙고
		// 뒤에 .jsp 자동으로 붙여줘서
		return "list";
	}

	// 전화번호 등록 : writeForm
	@RequestMapping(value = "/writeForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String writeForm() {
		System.out.println("PhoneController>writeForm()");
		// Dao
		// http://localhost:8088/phonebook3/writeForm 호출시 writeForm.jsp 파일 화면 보임

		// return "/WEB-INF/views/writeForm.jsp";
		return "writeForm";

	}

	// 전화번호 등록1 : @ModelAttribute 방법 | 아래랑 비교해보기
	// @ModelAttribute : Http 요청 파라미터를 객체에 담을때 사용
	// @RequestParam : 파라미터 매핑 -> Http 요청 파라미터를 메소드 파라미터에 넣어주는 어노테이션
	@RequestMapping(value = "/write", method = { RequestMethod.GET, RequestMethod.POST })
	public String write(@ModelAttribute PersonVo personVo) {
		System.out.println("PhoneController>write()");

		// 파라미터 꺼내기
//				System.out.println(name);
//				System.out.println(hp);
//				System.out.println(company);

		// vo로 묶기
		// PersonVo personVo = new PersonVo(name, hp, company);
		System.out.println(personVo);

		// 파라미터 꺼내기 + vo로 묶기를 DS해서 메소드의 파라미터로 보내준다

		// Dao로 저장하기
		// PhoneDao phoneDao = new PhoneDao();
		// int count = phoneDao.personInsert(personVo);
		// dao로 저장했던것을 바꿈 -> service를 통해서 저장한다.
		int count = phoneService.personInsert(personVo);
		System.out.println(count);

		// 리다이렉트로 처리하기
		// 리스트로 리다이렉트 시킬 예정

		// http://localhost:8088/phonebook3/list 호출시 write.jsp 파일 화면 보임
		// return "포워드자리";
		return "redirect:/list";
		// return "/WEB-INF/views/write.jsp";
	}

	// 전화번호 등록2 : 기존 방법 | 위에랑 비교해보기
	// @RequestParam : 파라미터 매핑 -> Http 요청 파라미터를 메소드 파라미터에 넣어주는 어노테이션
	@RequestMapping(value = "/write2", method = { RequestMethod.GET, RequestMethod.POST })
	public String write2(@RequestParam("name") String name, @RequestParam("hp") String hp,
			@RequestParam("company") String company) {
		System.out.println("PhoneController>write()");

		// 파라미터 꺼내기
//			System.out.println(name);
//			System.out.println(hp);
//			System.out.println(company);

		// vo로 묶기
		PersonVo personVo = new PersonVo(name, hp, company);
		System.out.println(personVo);

		// Dao로 저장하기
		// PhoneDao phoneDao = new PhoneDao();
		// int count = phoneDao.personInsert(personVo);
		// dao로 저장했던것을 바꿈 -> service를 통해서 저장한다.
		int count = phoneService.personInsert(personVo);
		System.out.println(count);

		// 리다이렉트로 처리하기
		// 리스트로 리다이렉트 시킬 예정

		// http://localhost:8088/phonebook3/list 호출시 write.jsp 파일 화면 보임
		// return "포워드자리";
		return "redirect:/list";
		// return "/WEB-INF/views/write.jsp";
	}

	// 전화번호 삭제 1 방법 : @PathVariable
	// @PathVariable : URL에 쿼리스트링 대신 URL패스로 풀어쓰는 방식
	@RequestMapping(value = "/delete2/{no}/{id}", method = { RequestMethod.GET, RequestMethod.POST })
	public String delete2(@PathVariable("no") int num, @PathVariable("id") String id) {
		System.out.println("PhoneController>delete2()");

		// 주소에서 값 꺼내기
		System.out.println(num);
		System.out.println(id);

		// Dao로 처리하기 (삭제)
		// PhoneDao phoneDao = new PhoneDao();
		// int count = phoneDao.personDelete(num);
		// dao로 저장했던것을 바꿈 -> service를 통해서 삭제한다.
		int count = phoneService.personDelete(num);
		System.out.println(count);

		return "redirect:/list";
		// return "";
	}

	// 전화번호 삭제 2 방법
	@RequestMapping(value = "/delete", method = { RequestMethod.GET, RequestMethod.POST })
	public String delete(@RequestParam("no") int no) {
		System.out.println("PhoneController>delete()");

		// 파라미터 꺼내기
		System.out.println(no);

		// Dao로 처리하기 (삭제)
		// PhoneDao phoneDao = new PhoneDao();
		// int count = phoneDao.personDelete(no);
		// dao로 저장했던것을 바꿈 -> service를 통해서 삭제한다.
		int count = phoneService.personDelete(no);
		//System.out.println(count);

		return "redirect:/list";
		// return "";
	}

	// 전화번호 수정폼
	@RequestMapping(value = "/updateForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String updateForm(Model model, @RequestParam("no") int no) {
		System.out.println("PhoneController>updateForm()");

		// 주소에서 값 꺼내기
		System.out.println(no);

		// Service를 통해서 1명정보 가져오기
		PersonVo personVo = phoneService.getPerson(no);

		// ds 데이터보내기 -->request attribute에 넣는다
		model.addAttribute("personVo", personVo);

		return "updateForm";
	}

	// 전화번호 수정
	@RequestMapping(value = "/update", method = { RequestMethod.GET, RequestMethod.POST })
	public String updateForm(Model model, @ModelAttribute PersonVo personVo) {
		System.out.println("PhoneController>update()");

		// 파라미터 꺼내기+vo로 묶기를 DS해서 메소드의 파라미터로 보내준다
		System.out.println(personVo);

		// Service를 통해서 수정하기
		int count = phoneService.personUpdate(personVo);

		return "redirect:/list";
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

		// return "/WEB-INF/views/test.jsp";
		return "test";
	}
	// 등록 메소드
	// 수정폼 메소드
	// 삭제 메소드
	// 리스트메소드

}
