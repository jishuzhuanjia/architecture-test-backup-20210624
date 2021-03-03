讨论Action中使用原生Servlet api及域操作。

1.通过ActionContext：用于操作三大域对象来存取数据，返回的都是域Map
1.1.获取ServletContext域map: ActionContext.getContext().getApplication()
1.2.获取request map： 直接ActionContext().getContext().put(key,value);
1.3.获取Session域： ActionContext().getContext().getSession();
1.4.获取PageContext：无

2.通过ServletActionContext
2.1.获取ServletContext - applicationScope
2.2.获取Session - sessionScope
2.3.获取HttpServletRequest - requestScope
2.4.获取PageContext- pageContext  -  在Action中获取为空。

