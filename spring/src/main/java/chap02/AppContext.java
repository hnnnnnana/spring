package chap02;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // spring 설정파일 표시
public class AppContext {
	
	// MemberController 빈으로 등록
	@Bean
	public MemberController memberController() { 
		// memberService객체를 주입
		MemberController con = new MemberController();
		con.setService(memberService());
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
		return new MemberService().setDao(memberDao());
	}
}
