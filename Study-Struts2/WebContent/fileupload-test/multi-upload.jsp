<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<form action="${pageContext.request.contextPath}/test/multiFileUpload" enctype="multipart/form-data" method="post">
	<label for="uploadFile1">选择文件</label>
	<input type="file" name="file1s"><br>
	 <label for="uploadFile1">选择文件2</label>
	<input type="file" name="file1s"><br>
	 <label for="uploadFile1">选择文件3</label>
	<input type="file" name="file3"><br>
	
	<input type="submit" value="提交">xx
</form>
 
</body>
</html>