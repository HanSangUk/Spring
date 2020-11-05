<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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

function pwdeqFn(){
	   var pwd = document.getElementById("compassword").value;
	   var pwdch = document.getElementById("compasswordch").value;
	   var eqMsg = document.getElementById("pwdEq");
	   if (pwd == pwdch){
		   eqMsg.style.color = "green";
		   eqMsg.innerHTML = "맞음";
	   } else {
		   eqMsg.style.color = "red";
		   eqMsg.innerHTML = "틀렸습니다.";
		   }
	   }

function pwdCheck(){
	   var exp = /^[a-zA-z0-9]{4,12}$/;
	   var pwd = document.getElementById("compassword").value;
	   var pwdch = document.getElementById("pwdch");
	   if(pwd.match(exp)){
		   pwdch.style.color = "green";
		   pwdch.innerHTML = "OK"
	   } else {
		   pwdch.style.color = "red";
		   pwdch.innerHTML = "형식이 틀렸습니다"
	   }
}

function phCheck(){
   	var regExp = /^\d{3}-\d{3,4}-\d{4}$/;
   	var mph = document.getElementById("comphone").value;
   	var mphch = document.getElementById("phch");
   	if(mph.match(regExp)){
   		mphch.style.color = "green";
   		mphch.innerHTML = "OK"
   	} else {
   		mphch.style.color = "red";
   		mphch.innerHTML = "형식이 틀렸습니다"
   	}
}

function comupdate(){
	var passwordcheck = document.getElementById("compassword").value;
	var password2check = document.getElementById("compasswordch").value;
	var phonecheck = document.getElementById("comphone").value;
	var officecheck = document.getElementById("comoffice").value;
	
	if((passwordcheck.length) == ""){
		alert("비밀번호를 입력해 주세요.");
		passwordcheck.focus();
		return false;
	}
	
 	if((password2check.length) == ""){
		alert("비밀번호를 입력해 주세요.");
		password2check.focus();
		return false;
	}
 	
 	if((phonecheck.length)==""){
		   alert("전화번호를 입력하지 않았습니다.");
		   phonecheck.focus();
		   return false;
	}
	
	if((officecheck.length)==""){
		   alert("지점을 입력하지 않았습니다.");
		   phonecheck.focus();
		   return false;
	}
	
	comupdateform.submit();
}
</script>
</head>
<body>

	<h2>회원정보수정</h2>
	<form action="commemberupdateform" method="post" name="comupdateform">
		사업자번호 <input type="text" id="comnumber" name="comnumber" value="${comupdate.comnumber}" readonly><br>
		비밀번호 <input type="password" id="compassword" name="compassword" onkeyup="pwdCheck()" placeholder="비밀번호를 입력하세요.">
		<span id="pwdch"></span><br>
		비밀번호체크 <input type="password" id="compasswordch" name="compasswordch" onkeyup="pwdeqFn()" placeholder="비밀번호를 입력하세요.">
		<span id="pwdEq"></span><br>
		전화번호 <input type="text" id="comphone" name="comphone" value="${comupdate.comphone}">
 		<span id="phch"></span><br>
 		지점명 <input type="text" id="comoffice" name="comoffice" value="${comupdate.comoffice}"><br>
		주소 
		<br><div class="maddress">
			<input type="text" id="sample6_postcode" name="comaddress1" placeholder="우편번호" value="${comupdate.comaddress1}">
			<input type="button" onclick="sample6_execDaumPostcode()"  value="우편번호 찾기"><br>
			<input type="text" id="sample6_address" name="comaddress2" placeholder="주소" value="${comupdate.comaddress2}"><br>
			<input type="text" id="sample6_detailAddress" name="comaddress3" placeholder="상세주소" value="${comupdate.comaddress3}">
			<input type="text" id="sample6_extraAddress" name="comaddress4" placeholder="참고항목" value="${comupdate.comaddress4}">
		</div><br>
		<button type="button" onclick="comupdate()">수정</button>
	</form>
</body>
</html>