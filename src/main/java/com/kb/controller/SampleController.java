package com.kb.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kb.domain.SampleDTO;
import com.kb.domain.TodoDTO;

import lombok.extern.log4j.Log4j;

@RequestMapping("/sample/*")//도메인다음에 sample/나옴 클래스에다 해줌view안의 폴더sample필요
@Controller
@Log4j
public class SampleController {	
	
	@RequestMapping("")
	public void basic() {
		log.info("--------");
	}
	//@getMapping
	@PostMapping("/basicOnlyGet") //포스트방식일경우 못찾으면 콘솔창에 로그안뜸
	public void basicGet() {
		log.info("--------Get");
	}
	
	@GetMapping("/ex01")
	public String ex01(SampleDTO dto) {
		log.info(dto);
		return "ex01";
	}
	
	@GetMapping("/ex02")//name2으로 넘어와도 name이 받을수잇게함(String name)에 넣어줌
	public String ex02(@RequestParam("name") String name, @RequestParam("age") int age){
		//넘어오는값은 string이지만 int로 변환
		log.info(name);
		log.info(age);
		return "ex02";
	}
	
	@GetMapping("/ex03") //체크박스. 중복체크 가능하니 배열 getparametervalues로.
	public String ex03(@RequestParam("name") ArrayList<String> names){		
		log.info(names);		
		return "ex03";
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
		binder.registerCustomEditor(java.util.Date.class, new CustomDateEditor(dateFormat, false));
	}
	
	@GetMapping("/ex04")
	public String ex04(TodoDTO todo){		
		log.info(todo);		
		return "ex04";
	}
	
	@GetMapping("/ex05")//모델 어트리뷰트로 페이지값을 넘겨줌(컨트롤러에서 넘겨줄때)
	public String ex05(SampleDTO dto,@ModelAttribute("page") int page){
		//샘플디티오는 겟셋이 잇으니가
		log.info(dto);
		log.info(page);
		return "sample/ex05";
	}
	
	@GetMapping("/ex06")
	public String ex06(String name, int age, RedirectAttributes rttr){
		rttr.addFlashAttribute("name", name);
		rttr.addFlashAttribute("age", age);	
		//플래시어트리뷰트는 새로고침하면 값 사라짐
		return "redirect:/";
	}
	
	@GetMapping("/ex07")
	public String ex07(RedirectAttributes rttr){
		rttr.addFlashAttribute("name", "홍");
		rttr.addFlashAttribute("age", 19);	
		//값은 전달되지만 개발자도구에는 뜨지않음,한글은깨지므로 jsp에 문자인코딩 필요
		return "redirect:/";
	}
	
	@GetMapping("/ex08")
	public @ResponseBody SampleDTO ex08() {
		log.info("/ex08....");
		SampleDTO dto = new SampleDTO();
		dto.setName("홍");
		dto.setAge(19);
		//컨트롤러에서 리턴값 스트링은 해당 jsp파일로
		//void 리턴값은 매핑되는 이름의 jsp로
		//json(잭슨 databind 라이브러리 필요)로 전환	
		return dto;
	}
	@GetMapping("/ex09")
	public ResponseEntity<String> ex09(){
		//개발자도구 status code 값 200(httpstatus.OK)은 정상적으로 호출됨을 의미.
		String msg = "{\"name\":\"홍\",\"age\":19}";//역슬래시는 ""값때문
		//org.springframework.http.HttpHeaders 사용
		HttpHeaders header = new HttpHeaders();
		header.add("content-type", "application/json;charset=UTF-8");
		//컨트롤러값 리턴
		return new ResponseEntity<String>(msg, header, HttpStatus.OK);
	}
}
