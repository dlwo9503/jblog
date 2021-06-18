<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">

<script src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.9.0.js" type="text/javascript"></script> <!-- js 소스를 땡겨와서 이부분에서 사용할거임 -->
<script>
$(function(){ // $가 붙으면 jQuery라고 생각하면 됨
	btn = $('#btn-check'); // #을 붙여주면 id를 찾음
	btn.click(function(){ // btn을 click하면 함수를 실행해라
		var id = $("#id").val();
		if(id == ""){
			return;
		}
		$.ajax({
			url: "/jblog04/user/api/checkid?id=" + id,
			type: "get",
			dateType: "json",
			error: function(xhr,status, e){
				console.error(status, e);
			},
			success: function(response){
				console.log(response);
				if(response.result != "success"){
					console.error(response.message);
					return;
				}
				
				if(response.data){
					alert("이미 존재하는 아이디 입니다. 다른 아이디를 사용하세요");
					$("#id").val(""); // 해당 id를 찾아 지우고
					$("#id").focus(); // 해당 id를 찾아 포커싱
					return;
				}
				
				$("#btn-check").hide(); // 숨기기
				$("#img-check").show(); // 보이기
			}
		});
	});
});
</script>

</head>
<body>
	<div class="center-content">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<form:form
			modelAttribute="userVo"
			class="join-form" 
			id="join-form" 
			method="post" 
			action="${pageContext.request.contextPath }/user/join">
			<label class="block-label" for="name">이름</label>
			<form:input path="name" />
			<!-- <input id="name"name="name" type="text" value=""> -->
			<p style="color:#f00; text-align:left; padding-left:0px">
				<form:errors path="name" />
			</p>
			
			<label class="block-label" for="id">아이디</label>
			<form:input path="id" />
			<!-- <input id="blog-id" name="id" type="text">  -->
			<input id="btn-check" type="button" value="id 중복체크">
			<img id="img-check" style="display: none;" src="${pageContext.request.contextPath}/assets/images/check.png">
			<p style="color:#f00; text-align:left; padding-left:0px">
				<form:errors path="id" />
			</p>

			<label class="block-label" for="password">패스워드</label>
			<form:input path="password" />
			<!-- <input id="password" name="password" type="password" /> -->
			<p style="color:#f00; text-align:left; padding-left:0px">
				<form:errors path="password" />
			</p>

			<fieldset>
				<legend>약관동의</legend>
				<input id="agree-prov" type="checkbox" name="agreeProv" value="y">
				<label class="l-float">서비스 약관에 동의합니다.</label>
			</fieldset>

			<input type="submit" value="가입하기">

		</form:form>
	</div>
</body>
</html>
