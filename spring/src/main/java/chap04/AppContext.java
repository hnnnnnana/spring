package chap04;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration // spring 설정파일 표시
@ComponentScan(basePackages = {"chap04"}) // basePackages의 배열값(패키지)를 스캔해서 빈등록
public class AppContext {

/*
	// MemberController 빈으로 등록
	@Bean
	public MemberController memberController() { 
		// memberService객체를 주입
		MemberController con = new MemberController();
//		con.setService(memberService());
		return con;
	}
	
	// MemberDao 빈으로 등록
	@Bean
	public MemberDao memberDao() {
		return new MemberDao();
	}
	
	// MemberService 빈으로 등록
	@Bean
	public MemberService memberService() {
//		// MemberDao객체를 주입
//		MemberService service = new MemberService();
//		service.setDao(memberDao());
//		return service;
//		return new MemberService().setDao(memberDao());
		return new MemberService();
	}
*/	
}
