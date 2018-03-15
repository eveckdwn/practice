<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h4>Welcome to Spring</h4>
<ul class="nav nav-pills nav-stacked">
	<li class="active"><a href="/">Home</a></li>
	<li><a href="/login">SignIn</a></li>
	<li><a href="/join">SignUp</a></li>
</ul>
<br>
<div class="input-group">
	<input type="text" class="form-control" placeholder="Search Blog..">
	<span class="input-group-btn">
		<button class="btn btn-default" type="button">
			<span class="glyphicon glyphicon-search"></span>
		</button>
	</span>
</div>