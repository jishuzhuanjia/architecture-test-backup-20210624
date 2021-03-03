<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- scope:application, session, request, page, or action. -->
<!-- action就相当于request ,可以在request中找到-->
<s:set var="count" value="3" scope="action"/>
<!-- 不讨论struts ui标签库 -->

<!-- 1.判断标签 if,elseif,else-->
if,elseif,else :<br>
<s:if test="#request.count>5"> 
requeust.i   >  5  !!
</s:if> 
<s:elseif test="#request.count<5">
requeust.i   <  5  !! 
</s:elseif>
<s:else>
requeust.i   =  5  !!
</s:else><hr>iterator a list:<br>
<!-- 定义的数组不能字符串和数字混合
访问时有无引号都可以 -->
<s:iterator var="i" value="{2,2,3,4,5}"> 
    <s:property value="#i"/>
</s:iterator><br><hr/>iterator a map<br>

<!-- 遍历Map:双引号不能包括双引号
而且定义Map时候属性要用引号括起来表示字符串key，否则查找不到(因为不支持数值类型的key)
值可以视情况，如果是数值也可以不加引号。
访问时必须要#-->
<s:iterator var="i" value="#{'name':'zhoujian','age':24}"> 
    <s:property value="#i.key"/> --<s:property value="#i.value"/><br/>
</s:iterator><br>
遍历+过程条件判断：Ognl表达式添加#总是正确的<br>
<!-- status从1开始，表示当前是第几个数。 -->
<s:iterator var="i" begin="100" end="400" status="s" step="2">
    <!--  s必须添加# -->
    <s:if test="#s.count%5==0">
        <span style="color:red">
        [<s:property value="#s.count"/>]<s:property value="i"/>
        </span>
    </s:if>
    <s:else>
        <s:property value="#i"/>
    </s:else>
</s:iterator>

</body>
</html>