<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<form action="${pageContext.request.contextPath}/wrapper6Action">
<!-- 简单属性的书写方式 -->
<!-- 姓名：<input type="text" name="name"><br>
性别：<input type="text" name="sex" value="男"><br>
年龄：<input type="text" name="age" ><br> -->

<!-- pojo封装属性 + 多pojo封装-->
<!-- 姓名：<input type="text" name="user.name"><br>
性别：<input type="text" name="user.sex" value="男"><br>
年龄：<input type="text" name="user.age" ><br> 
入学时长：<input type="text" name="sinfo.time" ><br> 
年级：<input type="text" name="sinfo.grade" ><br> 
分数：<input type="text" name="sinfo.price" ><br> 
<input type="submit" value="提交"> -->

<!-- 模型驱动方式 -->
<!--  姓名：<input type="text" name="name"><br>
性别：<input type="text" name="sex" value="男"><br>
年龄：<input type="text" name="age" ><br>
<input type="submit" value="提交"> -->

<!-- 将参数封装到List<User> -->
<!-- 姓名：<input type="text" name="userList[0].name"><br>
性别：<input type="text" name="userList[0].sex" value="男"><br>
年龄：<input type="text" name="userList[0].age" ><br> 
姓名：<input type="text" name="userList[1].name"><br>
性别：<input type="text" name="userList[1].sex" value="男"><br>
年龄：<input type="text" name="userList[1].age" ><br> 
姓名：<input type="text" name="userList[2].name"><br>
性别：<input type="text" name="userList[2].sex" value="男"><br>
年龄：<input type="text" name="userList[2].age" ><br> -->

<!-- 将参数封装到Map<String,User> -->
<!-- 姓名：<input type="text" name="userMap['us er1'].name"><br>
性别：<input type="text" name="userMap['us er1'].sex" value="男"><br>
年龄：<input type="text" name="userMap['us er1'].age" ><br> 
姓名：<input type="text" name="userMap['user2'].name"><br>
性别：<input type="text" name="userMap['user2'].sex" value="男"><br>
年龄：<input type="text" name="userMap['user2'].age" ><br> 
姓名：<input type="text" name="userMap['user3'].name"><br>
性别：<input type="text" name="userMap['user3'].sex" value="男"><br>
年龄：<input type="text" name="userMap['user3'].age" ><br> 
姓名：<input type="text" name="userMap['user4'].name"><br>
性别：<input type="text" name="userMap['user4'].sex" value="男"><br>
年龄：<input type="text" name="userMap['user4 '].age" ><br> -->

姓名：<input type="text" name="user1.name"><br>
性别：<input type="text" name="user1.sex" value="男"><br>
年龄：<input type="text" name="user1.age"><br> 

<input type="submit" value="提交">
</form>
</body>
</html>