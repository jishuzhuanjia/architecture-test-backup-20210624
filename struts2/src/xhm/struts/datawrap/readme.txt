Struts数据的封装 - 基础类型和简单引用类型


包装方式：属性注入 + 模型驱动

1.属性驱动：针对少量属性，不需要封装
通过setter方法直接对同名Action 属性进行赋值。

2.属性驱动：属性较多，需要封装对象
也需要对Pojo添加setter,同时在页面表单中注意name书写形式：pojoName.field

3.模型驱动： ModelDriven<T>
表单中只需要写属性名就可以