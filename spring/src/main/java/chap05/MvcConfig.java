package chap05;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration // spring 설정파일
@ComponentScan(basePackages= {"chap05"}) // basePackages의 배열값(패키지)를 스캔해서 빈등록
@EnableWebMvc // spring mvc 활성화
public class MvcConfig implements WebMvcConfigurer {
	
	// html, css... 처리 설정
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer cnf) {
		cnf.enable(); // 설정 키기
	}
	// view 설정
	@Override
	public void configureViewResolvers(ViewResolverRegistry reg) {
		reg.jsp("/WEB-INF/view/", ".jsp"); // 설정 실행
	}
	
	// 비즈니스로직이 필요없는(디자인 요소만 있는 페이지) url과 jsp 매핑
	@Override
	public void addViewControllers(ViewControllerRegistry reg) {
		reg.addViewController("/test8.do").setViewName("test8");
	
	}
}
