 File-->New-->Project... 2.1.1   ->  2.1.9
   Spring Initializr
   Maven Project
   Web
    Spring Web
   Core
    Lombok
    Aspects
    DevTools
   SQL
    mybatis+jdbc+mysql
	
	修改编码utf-8， 否则读取yml配置文件的时候，中文也会乱码

1.修改SpringBoot的数据源Druid(默认数据源是org.apache.tomcat.jdbc.pool.DataSource)

  1.1 项目地址

  https://github.com/alibaba/druid/tree/master/druid-spring-boot-starter

  1.2 引入依赖

  <dependency>
     <groupId>com.alibaba</groupId>
     <artifactId>druid-spring-boot-starter</artifactId>
     <version>1.1.10</version>
  </dependency>
  
  <!--用于明确表示编译版本配置有效-->
  <configuration>
	<fork>true</fork>
  <configuration>

  1.3 配置application.yml

  application.yml和application.properties区别？
  yml文件的好处，天然的树状结构，一目了然，实质上跟properties是差不多的。

  配置application.yml文件注意事项：
  1) 原有的key，例如spring.mybatis.xxx，按“.”分割，都变成树状的配置
  2) key后面的冒号，后面一定要跟一个空格
  3) 把原有的application.properties删掉。然后一定要执行一下  maven -X clean install

 
  1.4 启动SpringBoot项目访问druid(mysql修改为5.1.44)
  
  http://localhost:tomcat端口号/项目名称/druid/

  
2.配置Mybatis

  2.1 引入依赖
  
  <dependency>
     <groupId>org.mybatis.spring.boot</groupId>
     <artifactId>mybatis-spring-boot-starter</artifactId>
     <version>1.3.2</version>
  </dependency>

  MyBatis-Spring-Boot-Starter依赖将会提供如下：
  1) 自动检测现有的DataSource。
2) 将创建并注册SqlSessionFactory的实例，该实例使用SqlSessionFactoryBean将该DataSource作为输入进行传递。
3) 将创建并注册从SqlSessionFactory中获取的SqlSessionTemplate的实例。
4) 自动扫描您的mappers，将它们链接到SqlSessionTemplate并将其注册到Spring上下文，以便将它们注入到您的bean中。

  就是说，使用了该Starter之后，只需要定义一个DataSource即可（application.properties或application.yml中可配置），它会自动创建使用该DataSource的SqlSessionFactoryBean以及SqlSessionTemplate。会自动扫描你的Mappers，连接到SqlSessionTemplate，并注册到Spring上下文中。

  2.2 配置application.yml
  
  mybatis:
     #配置SQL映射文件路径
     mapper-locations: classpath:mapper/*.xml
     #配置别名
     type-aliases-package: com.zking.项目名.model
	 #驼峰命名
	 configuration:
		map-underscore-to-camel-case: true
  
  2.3 使用Mybatis-Generator插件生成代码

      2.3.1 导入并修改generatorConfig.xml和jdbc.properties（resources下）

      2.3.2 配置pom.xml文件
			<resources>
				<resource>
					<directory>src/main/java</directory>
					<includes>
						<include>**/*.xml</include>
					</includes>
				</resource>
				<resource>
					<directory>src/main/resources</directory>
					<includes>
						<include>*.properties</include>
						<include>*.xml</include>
						<include>*.yml</include>
					</includes>
				</resource>
			</resources>

      2.3.3 配置EditConfiguations的Maven启动方式

      命令：mybatis-generator:generate -e

  2.4 解决@Repository标签注解报错问题

      2.4.1 @Repository标签改为@Mapper标签
            
      添加@Mapper注解之后，这个接口在编译时会生成相应的实现类。
	  但请注意，这个接口中不可以定义同名的方法，因为会生成相同的id，因此这个接口不支持重载。
	  这样做虽然能解决问题，但以后都要为每个Dao层的接口添加@Mapper注解

      2.4.2 不修改@Repository注解，在启动类中添加@MapperScan(“xxxx”)注解，用于扫描Mapper类的包。

      扫描多个包：@MapperScan({”com.zking.dao”,”com.zking.pojo”})
      

3.配置PageHelper分页插件

  3.1 引入依赖

	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-aop</artifactId>
	</dependency>

	<dependency>
		<groupId>com.github.pagehelper</groupId>
		<artifactId>pagehelper-spring-boot-starter</artifactId>
		<version>1.2.3</version>
	</dependency>

  3.2 配置application.yml

  #pagehelper分页插件配置(默认设置，可以不配置)
  pagehelper:
     helperDialect: mysql
     reasonable: true
     supportMethodsArguments: true
     params: count=countSql

  3.3 创建分页AOP
      注：必须开启动态代理
	  
	  //启用事物管理器
	  @EnableTransactionManagement
	  //启用aop动态代理
	  @EnableAspectJAutoProxy

4.配置log日志
  
  Spring Boot在所有内部日志中使用Commons Logging，但是默认配置也提供了对常用日志的支持，如：Java Util Logging，Log4J, Log4J2和Logback。每种Logger都可以通过配置使用控制台或者文件输出日志内容。  

  4.1 配置application.yml

  #显示日志
  logging:
     level: 
       com.zking.springboot02.mapper: debug

  

5.其他
restful
delete  put
post get

作业：百度一下四个注解的意思
@GetMapping
@PostMapping
@PutMapping
@DeleteMapping
@PathVariable

 
  5.1 SpringBoot启动项配置：
  
  //自动扫描Mapper目录
  @MapperScan("com.zking.项目名.mapper")
  //启用事物管理器
  @EnableTransactionManagement
  //启用动态代理
  @EnableAspectJAutoProxy
  
  
  
  
  5.2 测试


