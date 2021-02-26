<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/ssmtest/uploadTest.action" enctype="multipart/form-data">
	<label for="file1">请选择文件:</label>
	<input type="file" name="file1"><br>
	<input type="submit" value="上传文件">
</form>

</body>
</html>