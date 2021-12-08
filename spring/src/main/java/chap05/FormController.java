package chap05;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FormController {
	
	@GetMapping("/form.do")
	public String form() {
		return "form";
	}
	
	/*
	 * 파라미터를 받는 방법
	 * 1. HttpServletRequest 객체
	 * 2. @RequestParam
	 * 3. 커맨드객체 
	 * - 파라미터의 이름과 동일한 필드에 자동으로 값을 넣어줌
	 */
	
	
	@PostMapping("/send.do")
	public String send(HttpServletRequest req, @RequestParam("email") String email, // @RequestParam("email") String email -> ("email")의 값을 email에 자동으로 저장시킨다.
						@RequestParam(value="tel", required=false) String tel, // @RequestParam("tel") String tel -> form에 없는 객체 tel을 담으려고하면 에러가 발생한다 
																			   // 이때 value, required을 사용해서 에러가 발생하지 않도록 할 수 있다.-> @RequestParam(value="tel", required=false) String tel) 
						MemberVo vo) { // MemberVo vo -> MemberVo 객체를 자동으로 vo 안에 넣어준다. (따로 vo.setName(name);... 작성 X
		String name = req.getParameter("name");
		req.setAttribute("msg", name+"님, 안녕하세요");
		req.setAttribute("msg2", "이메일 : "+email);
		req.setAttribute("tel", tel);
		
//		MemberVo vo = new MemberVo();
//		vo.setName(name);
//		vo.setEmail(email);
		
		System.out.println(vo.getName() + " " + vo.getEmail() + " " + vo.getNo());
		if (vo.getHobby() != null) { // getHobby가 null이 아닐때만 출력
		// hobby 배열 출력 방법1
			for(int i=0; i<vo.getHobby().length; i++) {
				System.out.println(vo.getHobby()[i]);
			}
				
			// hobby 배열 출력 방법2 (향상된 for문)
			for (String hobby : vo.getHobby()) {
				System.out.println(hobby);
			}
		}
		
		// 커멘드객체 없이 취미값을 vo2의 hobby에 저장하려면?
		// 배열을 다 만든 후 set에 저장해야한다.
		MemberVo vo2 = new MemberVo();
		// 방법 1
		vo2.setHobby(req.getParameterValues("hobby"));
		// 방법2
//		String[] hobbys = new String[req.getParameterValues("hobby").length];
//		for (int i=0; i<req.getParameterValues("hobby").length; i++) {
//			hobbys[i] = req.getParameterValues("hobby")[i];
//		}
		
		return "send";
	}
	
	/*
	 * 뷰에서 사용하기 위한 값 컨트롤러에서 저장하는 방법
	 * - request에 set
	 * - session에 set
	 * - model에 add
	 * - ModelAndVie에 add
	 */

	// ModelAndView 객체 - > model + view	
	@GetMapping("/test9.do")
	public ModelAndView test8() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("name", "홍길동");
		mav.setViewName("test9");
		return mav;
	}
}

