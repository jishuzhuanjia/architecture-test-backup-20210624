<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib  uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
资源包测试：4种方式获取资源包属性：只有property标签需要使用方法，直接使用属性名返回''<br>
<!-- s:property -->
1.s:property:<s:property value="getText('username')"/>,<s:property value="getText('parent')"/> - 通过制定property属性getText('username')<br>
2.s:text:<s:text name="username"></s:text>-通过指定标签的name属性,name属性是资源包中的属性名<br>
3.struts2 UI组建标签： <s:textfield key="username"></s:textfield>  -  textfield是type=text的input元素，id和name都是username(不指定name),并添加label元素，通过指定textfield标签的key属性，值等于属性名，这个key就是文本框的label。<br>
4.国际化属性s:i18n，它可以通过指定name属性指定的资源包 age:
<s:i18n name="xhm.struts.resourcepackage.action.package">
	<s:text name="age"></s:text>
</s:i18n>
</body>
</html>