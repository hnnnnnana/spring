package chap06;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration // spring 설정파일
@ComponentScan(basePackages= {"chap06"}) // basePackages의 배열값(패키지)를 스캔해서 빈등록
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
	
	// DataSource 객체 등록
	// DB접속정보 설정
	@Bean
	public BasicDataSource dataSource() {
		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
		ds.setUrl("jdbc:mysql://localhost:3306/board");
		ds.setUsername("root");
		ds.setPassword("root1234");
		return ds;
		
	}
	
	// SqlSessionFactory : 객체를 생성해주는 빌더
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean ssfb = new SqlSessionFactoryBean();
		ssfb.setDataSource(dataSource()); // 객체 주입
		
		// sql이 들어있는 xml경로 설정
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		ssfb.setMapperLocations(resolver.getResources("classpath:/mapper/**/*.xml")); // 클래스패스의 mapper(패키지)폴더 밑의 모든 xml -> 경로 설정
		return ssfb.getObject();
	}
	
	// SqlSessionTemplate
	@Bean
	public SqlSessionTemplate sqlSessionTemplate() throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory());
	}
	
	// 파일업로드
	@Bean
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver cmr = new CommonsMultipartResolver();
		cmr.setDefaultEncoding("UTF-8");
		cmr.setMaxUploadSize(1024*1024*10); // 최대업로드 사이즈(바이트)
		return cmr;
	}
}
