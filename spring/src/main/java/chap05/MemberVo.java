package chap05;

public class MemberVo {
	private String name;
	private String email;
	private Integer no; // int -> 값이 없거나, 문자열이면 에러/ Integer -> 에러 x
	private String[] hobby; // 배열 hobby
	
	public String[] getHobby() {
		return hobby;
	}
	public void setHobby(String[] hobby) {
		this.hobby = hobby;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getNo() {
		return no;
	}
	public void setNo(Integer no) { // 무조건 int
		this.no = no;
	}
	
}
