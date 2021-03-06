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
值栈的使用：root中的不行不需要#，ContextMap需要#
<s:debug>
</s:debug>
<!-- 从添加的列表中获取数据 -->
<s:property value="userList[0].name"/><br>
<s:property value="userList[1].name"/><br>

<!-- 从set产生的Map中查找属性 -->
<s:property value="afterpush"/><br>
<s:property value="首富"/><br>

<!-- 从被压入栈的Action中获取属性：如果Map中有相同名值,则返回Map中的 -->
<s:property value="valueStackName"/><br>
<hr>

<!-- 1.OGNL中#不仅仅用来取值ContextMap,还能构建map,map值可以是字符串和数值整型double混合
            注意：当数值整数位大于7位就采用科学计数法 2.222222223E7 -->
<!-- 写在OGNL标签内的内容可以不写{#} -->
<!-- 1.1. 使用var遍历map必须使用# -->
1.1. <s:iterator var="entry" value="#{'name':'zhoujian','age':25}">
    <s:property value="#entry.key"/> --  <s:property value="#entry.value"/>
</s:iterator><hr/>

<!-- 1.2.不使用var遍历map，直接使用Entity的key和value属性
property中key和value都不能使用#,否则无法显示 -->
1.2. <s:iterator value="#{'name':'zhoujian','age':25,'money':2222222.23}">
    <s:property value="key"/> --  <s:property value="value"/>
</s:iterator><hr/> 
 
<!-- 2.OGNL中#的使用 ：构建List: 可以是字符串或整型或者double类型
property中写不写#都可以：效果一样
遍历数组需要定义变量的
对于变量带# 和 不带 都是一样的。-->
2.1 <s:iterator value="{'zhoujian',25,'马云',1.13,200}" var="i">
    <s:property value="i"/>--<s:property value="#i"/>
</s:iterator><hr>

<!-- 2.2不使用var的遍历pojo列表的方法：直接使用pojo的属性名-->
2.2<s:iterator value="userList">
   玩家：<s:property value="name"/><br>
     性别：<s:property value="sex"/><br>
</s:iterator>

<!-- 2.3. 不使用var遍历map：直接通过key和value得到键和值。-->
<!-- ognl标签是可以嵌套的，就像下面的遍历 -->
2.3 <s:iterator value="#request.userMap">
   key:<s:property value="key"/><br>
       玩家:<s:property value="value"/><br/>
    </s:iterator>

<!-- 3.从request域中取出属性 -->
3. <s:property value="#request.key2351s"/><br>

<!-- struts一个标签
优点是点击字体也会选择 -->
<s:radio list="{'男','女'}"  name="sex" label="性别"/>

<%--OGNL表达式解析原则：property标签需要在非Ognl标签中。
OGNL嵌套 :将ognl表达式用在property之外的ognl标签中会出现无法解析
<s:textfield name="name" value="#request.首富"/> 
文本域中显示#request.首富
总结:property标签不能嵌套在其他ognl标签中，但是可以嵌套在html元素中。
放在其他的html元素中，如input则可以正常解析。--%> 

<!-- 解决1：使用input标签 - OK -->
<input type="text" name="city" value="<s:property value="#request.city"/>">
<hr>

<!-- 怎样避免OGNL解析？  - 使用%{''}， 开发中基本没用 
一般是使用在property标签或者html标签中。-->
<%-- <s:property value="#request.city"/> - 会被正常解析--%>  
<!-- 注意：%{}中的内容如果不用''括起来，则认为是ognl表达式进行解析，此时%和{}不会
被显示出来,不要忘了写'' -->
4.<s:property value="%{'#request.city'}"/><hr>
 
 <%--  注意：除了在property中书写ognl表达式会被认识以外，
 其他的地方需要使用：${request.city} 这其实就是EL表达式 --%>
 
<%--注意：html注释中的El表达式会被jsp解析
处理jsp的Servlet会对jsp文件中的html注释中的EL表达式进行解析，
jsp注释的内容不会被解析 --%>
</body>
</html>