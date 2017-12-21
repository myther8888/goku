# goku
集中式，模块化工程脚手架
## 工程框架
### spring-容器，项目的基础
### spring mvc-MVC框架
### mybatis-数据库OOM框架
### druid-数据源
### redis-缓存系统
### shiro-权限控制框架
使用框架实现接口访问权限的控制
### hibernate validator-校验框架
前端参数统一验证，自定义对象中集合的元素有效性验证，用法如下
```xml
<constraint-mappings
        xmlns="http://jboss.org/xml/ns/javax/validation/mapping"
        version="1.1">
    <bean class="com.yongle.model.DemoVO" ignore-annotations="true">
        <field name="options">
            <constraint annotation="com.yongle.goku.base.validate.annotation.ValidateList">
                <element name="fieldsRegExp">
                    <value>content=\d</value>
                    <value>id=\d</value>
                </element>
                <element name="className">com.yongle.model.OptionVO</element>
            </constraint>
        </field>
    </bean>
</constraint-mappings>
```
### dshelper-读写分离
[具体查看](https://github.com/weinh/dynamic-datasource)
