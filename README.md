# 基于SpringBoot的签到系统重构版
此项目是之前使用原生Servlet + JDBC实现的[签到系统](https://github.com/Dendiom/SignInWeb)的重构版。
新版本使用了SpringBoot + SpringMVC + JPA（Hibernate）的框架，功能与原来相同，复用了以前的数据库。
## 重构版与之前的版本对比
- SpringBoot简化了配置，不再需要web.xml以及spring的xml配置文件，并且有内置的Tomcat
- 不再需要写许多的Servlet，使用@Controller以及@RequestMapping可以轻松实现路由, 可读性也很强
- 使用Interceptor替代Filter，在Interceptor中可以直接利用@Autowired注入bean
- SprintBoot好像无法让浏览器直接访问.jsp文件，专门写了一个Controller来做此工作
- 使用ORM可以简化数据库操作，并且使用JPA不需要写Dao的实现类
- JPA默认实现是Hibernate, 但是由于封装的缘故，不太适合学习Hibernate
- 对比起Mybatis，Hibernate不需要写sql语句，这样有利也有弊，个人更喜欢Mybatis，感觉更灵活



