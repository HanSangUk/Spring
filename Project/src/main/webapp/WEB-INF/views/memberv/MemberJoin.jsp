<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script>
function sample6_execDaumPostcode() {
    new daum.Postcode({
        oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 각 주소의 노출 규칙에 따라 주소를 조합한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var addr = ''; // 주소 변수
            var extraAddr = ''; // 참고항목 변수

            //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                addr = data.roadAddress;
            } else { // 사용자가 지번 주소를 선택했을 경우(J)
                addr = data.jibunAddress;
            }

            // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
            if(data.userSelectedType === 'R'){
                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if(extraAddr !== ''){
                    extraAddr = ' (' + extraAddr + ')';
                }
                // 조합된 참고항목을 해당 필드에 넣는다.
                document.getElementById("sample6_extraAddress").value = extraAddr;
            
            } else {
                document.getElementById("sample6_extraAddress").value = '';
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('sample6_postcode').value = data.zonecode;
            document.getElementById("sample6_address").value = addr;
            // 커서를 상세주소 필드로 이동한다.
            document.getElementById("sample6_detailAddress").focus();
        }
    }).open();
}

	function idOverlap(){
		var inputId = document.getElementById("mid").value;
		var idcheck = document.getElementById("idcheck");
		$.ajax({
			type : "post",
			url : "idoverlap",
			data : {"mid" : inputId},
			dataType : "text",
			success : function(result){
				if(result=="OK"){
					idcheck.style.color = "green";
					idcheck.innerHTML = "사용가능한 ID";
				} else {
					idcheck.style.color = "red";
					idcheck.innerHTML = "중복 다시";
				}
			},
			error : function(){
				alert("오류");
				console.log("오류");
			}
		});
	}
	function formCheck(){
		var mpassword = document.getElementById("mpassword").value;
		var mpasswordch = document.getElementById("mpasswordch").value;
		if(mpassword != mpasswordch){
			alert("비밀번호가 일치 하지 않습니다.");
			return false;
		}
		
		joinform.submit();
	}
</script>
</head>
<body>
	<h1>회원가입</h1>
	<form action="memberjoinform" method="post" name="joinform" enctype="multipart/form-data">
		프로필사진 <input type="file" name="mfile" id="mfile"><br>
		<c:choose>
			<c:when test="${kakaoId ne null}">
				아이디 : <input type="text" name="mid" id="mid" onkeyup="idOverlap()">
				<input type="hidden" name="kakaoId" value="${kakaoId}"><br>
				<span id="idcheck"></span> 
			</c:when>
			<c:when test="${naverId ne null}">
				아이디 : <input type="text" name="mid" id="mid" onkeyup="idOverlap()">
				<input type="hidden" name="naverId" value="${naverId}"><br>
				<span id="idcheck"></span> 
			</c:when>
			<c:otherwise>
				아이디 : <input type="text" name="mid" id="mid" onkeyup="idOverlap()">
				<span id="idcheck"></span> 	
			</c:otherwise>
		</c:choose><br>
		비밀번호 : <input type="password" id="mpassword" name="mpassword"><br>
		비밀번호 중복확인 <input type="password" id="mpasswordch" name="mpasswordch"><br>
		이름 : <input type="text" id="mname" name="mname"><br>
		생년월일 : <input type="date" id="mbirth" name="mbirth"><br>
		이메일 : <input type="text" id="memail" name="memail"><br>
		전화번호 : <input type="text" id="mphone" name="mphone"><br>
		주소 <br><div class="maddress">
		 	<input type="text" id="sample6_postcode" name="maddress1" placeholder="우편번호">
			<input type="button" onclick="sample6_execDaumPostcode()"  value="우편번호 찾기"><br>
			<input type="text" id="sample6_address" name="maddress2" placeholder="주소"><br>
			<input type="text" id="sample6_detailAddress" name="maddress3" placeholder="상세주소">
			<input type="text" id="sample6_extraAddress" name="maddress4" placeholder="참고항목">
			</div>
		<button type="button" onclick="formCheck()">회원가입</button>
 	</form>
 	
</body>
</html>