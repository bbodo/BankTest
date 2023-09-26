<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<%@ include file="/WEB-INF/view/layout/header.jsp"%>

<div class="col-sm-8">
	<h2>계좌 생성 페이지(인증)</h2>
	<h5>어서오세요 환영합니다</h5>
	<div class="bg-light p-md-5 h-75"></div>
	<form action="" method="post">
		<div class="form-grup">
			<label for="number">계좌 번호:</label> 
			<input type="text" id="number"
				class="form-control" placeholder="생성 계좌 번호 입력" name="number">
		</div>
		<div class="form-grup">
			<label for="number">계좌 비밀 번호:</label> 
			<input type="text" id="password"
				class="form-control" placeholder="계좌 비밀 번호 입력" name="password">
		</div>
		<br>
		<div class="form-grup">
			<label for="balance">임금 금액:</label> 
			<input type="text" id="balance"
				class="form-control" placeholder="입금" name="balance">
		</div>
		<br>
		<button type="submit" class="btn btn-primary">계좌 생성</button>
		
	</form>
</div>


<%@ include file="/WEB-INF/view/layout/footer.jsp"%>