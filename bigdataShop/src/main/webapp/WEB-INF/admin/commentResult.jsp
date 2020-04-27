<%@page import="kr.multi.bigdataShop.product.comment.CommentResultDTO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet"
   href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<script
	   src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
	<script
	   src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	
	<link rel="stylesheet" type="text/css"
	   href="/bigdataShop/jQCloud-master/jqcloud/jqcloud.css" />
	<script type="text/javascript"
	   src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.4/jquery.min.js"></script>
	<script type="text/javascript"
	   src="/bigdataShop/jQCloud-master/jqcloud/jqcloud-1.0.4.js"></script>
    
	<script type="text/javascript">
	$(document).ready(function() {
		var word_array = [];
		size=${commentresult.size()}
	
		<c:forEach var="commentResult" items="${commentResult}">
			word_array.push({
				text : "${commentResult.word}",
				weight : "${commentResult.count}"
			})
		</c:forEach>
		$(function() {
			$("#jQCloud").jQCloud(word_array);
		});
	});
	</script>
</head>
<body>
	<h1>상품댓글분석</h1>
		<div style="float: left; width: 30%;">
			<table class="table" style="border-collapse: collapse;">
				 <tr>
				 	<th>키워드</th>
				 	<th>반복회수</th>
				 </tr>
		 		<c:forEach var="cResult" items="${commentResult}">
		 			<tr>
		 				<td>${cResult.word}</td>
		 				<td>${cResult.count}</td>
		 			</tr>	
		 		</c:forEach>
				 
			</table>
		</div>
		
		<div id="jQCloud" style="float: left; width: 70%;">
		</div>
</body>
</html>