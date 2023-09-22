<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<%@ include file="/WEB-INF/view/layout/header.jsp"%>


<div class="col-sm-8">
	<h2>회원 가입</h2>
	<h5>어서오세요 환영합니다</h5>
	<div class="bg-light p-md-5 h-75">
	  <form action="/user/signup-proc">
	  <div class="form-grpup>
		<label for="username">username:</label>
		<input type="text" class="form-control placeholder="Enter username" id="username" name="username">		
	</div>
	<div class="form-grpup>
		<label for="pwd">Password:</label>
		<input type="text" class="form-control placeholder="Enter username" id="username" name="username">		
	</div>
	<div class="form-grpup>
		<label for="fullname">Fullname:</label>
		<input type="text" class="form-control placeholder="Enter username" id="username" name="username">		
	</div>

</form>
</div>
</div>

</div>

<%@ include file="/WEB-INF/view/layout/footer.jsp"%>