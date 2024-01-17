package com.oracle.mvc02;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	//로깅을 위한 logger 인스턴스 생성, 이를 통해 이플리케이션의 로그를 기록할 수 있음
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	// 홈메서드, 
	//RequestMapping 어노테이션은 http 요청 경로 및 메서드를 매핑하고 이 메서드가 해당 요청을 처리한다는 것을 나타냄
	// '/' 경로로 http get 요청을 처리함
	@RequestMapping(value = "/", method = RequestMethod.GET)
	
	//"Welcome home! The client locale is {locale}" 형식으로 출력
	//Locale 객체와 Model 객체를 메서드의 매개변수로 받음
	public String home(Locale locale, Model model) {
		
		//{locale}는 실제로 전달된 locale 매개변수의 값으로 대체된다. logger는 sysout과 같음
		logger.info("Welcome home! The client locale is {}.", locale);
		logger.info("context / start");
		
		//현재 시간 및 날짜정보를 가져오고 locale에 맞게 포맷팅, 포맷된 날짜 및 시간은 formattedDate 문자열에 저장
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		System.out.println("formattedDate:"+formattedDate);
		
		// 모델 객체에 serverTime이라는 속성을 추가하고, 속성 값으로 포맷된 날 짜 시간 문자열을 설정
		model.addAttribute("serverTime", formattedDate );
		//뷰 반환
		//view 지정   선행자         viewreserver가 해줌      
		///WEB-INF/views/ +home + .jsp
		return "home";
	}
	
}