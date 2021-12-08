<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script>
	function formCheck() {
		if ($("#id").val() == '') {
			alert('아이디를 입력하세요');
			$("#id").focus();
			return false;
		}
		// 아이디 중복체크
		// 실제 가입 직전에 중복된 id가 있는지 체크
		var con = true;
		$.ajax({ 
			url:"idcheck.do",
			data:{
				id:$("#id").val() 
			},
			async:false, /* async:false가 없으면 비밀번호 입력 -> 아이디 중복 확인으로 순서가 이상해짐 */
			method:"get", //controller에서 idcheck를 PostMapping로 했으면 동일하게 post로 작성
			success:function(r) { // 성공하면
				//console.log(r);
				if (r.trim() == '1') {
					alert('아이디가 중복되었습니다. 다른 아이디를 입력하세요');
					$("#id").val('');
					$("#id").focus();
					//return false; // 
					con = false;
				}
			}
		});
		if (con == false) return false;
			if ($("#pwd").val() == '') {
			alert('비밀번호를 입력하세요');
			$("#pwd").focus();
			return false;
		}
		if ($("#pwd").val() != $("#pwd2").val()) {
			alert('비밀번호 값을 확인하세요.')
			$("#pwd2").focus();
			return false;
		}
		if ($("#name").val() == '') {
			alert('이름을 입력하세요');
			$("#").focus();
			return false;
		}
		
		// 학교정보체크
		// $("input[name='school']")->선택자
		$("input[name='school']").each(function(idx){ // input태그의 name속성의 값이 school 선택자의 객체를 반복
			//console.log($("input[name='school']").eq(idx).val());
			if($("input[name='school']").eq(idx).val() == '') {
				alert(idx+1 + '번째 학교명를 입력해 주세요.');
				$("input[name='school']").eq(idx).focus();
				con = false;
				return false;
			}
			if($("input[name='year']").eq(idx).val() == '') {
				alert(idx+1 + '번째 졸업년도를 입력해 주세요.');
				$("input[name='year']").eq(idx).focus();
				con = false;
				return false;
			}
		});
		if (con == false) return false;
		return true;
	}
	
	function idcheck() {
		if ($("#id").val() == '') { 
			alert('아이디를 입력하세요.');
		} else {
			$.ajax({ // 18줄 {부터 ~ 34줄 }까지는 ajax 객체이다.
				url:"idcheck.do",
				data:{
					id:$("#id").val() // id가 id인 value값을 가져온다.
				},
				async:false,
				method:"get", //controller에서 idcheck를 PostMapping로 했으면 동일하게 post로 작성
				success:function(r) { // 성공했을때 seccess
					//console.log(r);
					if (r.trim() == '1') { // 참 -> db에 이미 해당 아이디가 있다.
						alert('아이디가 중복되었습니다. 다른 아이디를 입력하세요');
						$("#id").val(''); // alert 확인을 누르면 빈 문자열로 자동 입력
						$("#id").focus(); // alert 확인을 누르면 아이디 위치로 커서 이동
					} else {
						alert('사용가능한 아이디입니다.');
					}
				}
			});
		}
	}
	$(function(){
		$("#id").keyup(function(){
			$.ajax({
				url:"idcheck.do",
				data:{
					id:$("#id").val()
				},
				async:false,
				method:"get", //controller에서 idcheck를 PostMapping로 했으면 동일하게 post로 작성
				success:function(r) {
					//console.log(r);
					if (r.trim() == '1') {
						$("#duplicateMsg").html("사용불가");
					} else {
						$("#duplicateMsg").html("사용가능");
					}
				}
			});
		})
	});
</script>
</head>
<body>
<h2>회원 가입</h2>
<form action="regist.do" method="post" onsubmit="return formCheck();"> <!-- onsubmit="return false; -> 전송을 눌러도 전송이 안된다.-->
<table border="1">
	<tr>
		<td>아이디</td>
		<td>
			<input type="text" name="id" id="id">
			<input type="button" value="중복체크" onclick="idcheck();"><br>
			<span id="duplicateMsg"></span>
		</td>
	</tr>
	<tr>
		<td>비밀번호</td>
		<td><input type="password" name="pwd" id="pwd"></td>
	</tr>
	<tr>
		<td>비밀번호 확인</td>
		<td><input type="password" name="pwd2" id="pwd2"></td>
	</tr>
	<tr>
		<td>이름</td>
		<td><input type="text" name="name" id="name"></td>
	</tr>
	<tr>
		<td>학교</td>
		<td>
			<table>
				<tr>
					<td>학교명</td>
					<td>졸업년도</td>
				</tr>
				<tr>
					<td><input type="text" name="school"></td>
					<td><input type="text" name="year"></td>
				</tr>
				<tr>
					<td><input type="text" name="school"></td>
					<td><input type="text" name="year"></td>
				</tr>
				<tr>
					<td><input type="text" name="school"></td>
					<td><input type="text" name="year"></td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td colspan="2"><input type="submit" value="가입"></td>
	</tr>
</table>
</form>
</body>
</html>