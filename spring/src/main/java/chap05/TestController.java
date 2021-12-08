package chap05;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/member") // 전체 메서드에 공통 경로
public class TestController {

	@GetMapping("/test.do")
	public String test(Model model) {
		model.addAttribute("test", "테스트"); // request에 setAttribute 동일
		return "/test"; // view 경로 = prefix와 suffix 사이의 값
	}
	
	@PostMapping("/test2.do")
	public String test2(Model model) {
		model.addAttribute("test", "테스트"); // request에 setAttribute 동일
		return "/test"; // view 경로 = prefix와 suffix 사이의 값
	}
	
	/*
	 * url 매핑
	 * GetMapping : get방식만 가능
	 * PostMapping : post방식만 가능
	 * RequestMapping : 모든 방식 가능
	 */
	
	@RequestMapping("/test3.do")
	public String test3(Model model) {
		model.addAttribute("test", "테스트"); // request에 setAttribute 동일
		return "/test"; // view 경로 = prefix와 suffix 사이의 값
	}
	
	// RequestMapping method를 직접 지정 -> ()안에 값이 2개 이상이 되면 속성명을 별도로 지정해야한다.
	@RequestMapping(value="/test4.do", method = RequestMethod.POST)  // method = RequestMethod.POST -> request방식만 가능하다.
	public String test4(Model model) {
		model.addAttribute("test", "테스트"); // request에 setAttribute 동일
		return "/test"; // view 경로 = prefix와 suffix 사이의 값
	}
	
	/*
	 * 포워딩 / 리다이렉트
	 * forward
	 * - 하나의 요청
	 * - request 공유 O
	 * - 주소 안바뀜
	 * redirect
	 * - 두개의 요청
	 * - request 공유 X
	 * - return "redirect:/test4.do"; -> redirect:실제 접속하는 주소
	 * - 주소 바뀜
	 */
	
	// redirect
	// test5.do -> test.do
	@GetMapping("/test5.do")
	public String test5() {
		return "redirect:/test4.do";
	}
	
	// 리턴이 없으면(void) 매핑된 url과 동일한 경로의 jsp가 포워딩
	@GetMapping("/test6.do")
	public void test6() {
		
	}
	
	// 서블릿으로 응답
	@GetMapping("/test7.do")
	public void test7(HttpServletResponse res) throws IOException {
		res.setContentType("text/html; charset=utf-8");
		PrintWriter out = res.getWriter();
		out.println("<script>");
		out.println("alert('정상적으로 저장되었습니다.');");
		out.println("</script>");
	}
	
}
