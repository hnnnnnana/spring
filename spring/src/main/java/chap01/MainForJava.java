package chap01;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainForJava {

	public static void main(String[] args) {
		// 설정 파일을 읽어와서 빈 등록 
		// greeter라느 이름으로 객체(빈)을 컨테이너에 등록(싱글톤으로 등록) 
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppContext.class);
		
		// 빈을 가져오기
		Greeter g = (Greeter)ctx.getBean("greeter");
		Greeter g2 = ctx.getBean("greeter", Greeter.class);
		
		System.out.println(g == g2); // 같은 객체(싱글톤으로 등록 후 가져오면 다 같은 객체) -> 같은 주소를 가르킨다.
	}

}
