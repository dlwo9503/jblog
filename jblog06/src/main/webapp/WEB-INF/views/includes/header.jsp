<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div>
	<a href="${pageContext.request.contextPath }">
		<h1 class="logo"style="background-image:url(${pageContext.request.contextPath}/assets/images/logo.jpg)">JBlog</h1>
	</a>
	<ul class="menu">
		<c:choose>
			<c:when test="${empty authUser }">
				<li><a href="${pageContext.request.contextPath }/user/login">로그인</a><li>
				<li><a href="${pageContext.request.contextPath }/user/join">회원가입</a><li>
			</c:when>
			<c:otherwise>
				<li>( ${authUser.name }님 로그인 )</li>
				<li><a href="${pageContext.request.contextPath }/user/logout">로그아웃</a><li>
				<li><a href="${pageContext.request.contextPath }/${authUser.id }/${categoryVo.no }/${postVo1.no }">내블로그</a><li>
			</c:otherwise>
		</c:choose>
	</ul>
</div>