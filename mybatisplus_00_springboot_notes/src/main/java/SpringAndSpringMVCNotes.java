public class SpringAndSpringMVCNotes {

/**
 *
 * Spring和SpringMVC是不同的架构，对应简化不同的三个层。因此加载相应的Bean对象时一般要分开处理。
 * SpringMVC相关的bean(表现层bean【Controller】)
 * Spring相关的Bean(业务层bean【Service】,持久层Dao【@Repository】)
 *
 *
 *
 * Spring技术
 *      简化开发:
 *          IOC(反转控制),
 *          AOP(面向切面编程)
 *              事务处理
 *      框架整合:
 *
 *
 * Spring核心概念
 *      IOC（Inversion of Control）控制反转
 *          使用对象时，由主动new产生对象转换为由外部提供对象，此过程中对象创建控制权由程序转移到外部，此思想称为控制反转。
 *          通俗的讲就是“将new对象的权利交给Spring，我们从Spring中获取对象使用即可”。
 *      Spring技术对IOC思想进行了实现
 *          Spring提供了一个容器，称为IOC容器，用来充当IOC思想中的“外部”。
 *          IOC容器负责对象的创建、初始化等一系列工作，被创建或被管理的对象在IOC容器中统称为Bean。
 *      DI（Dependency Injection）依赖注入
 *          在容器中建立bean与bean之间的依赖关系的整个过程，称为依赖注入。
 *
 *      目标：充分解耦
 *          使用IOC容器管理bean(IOC)。
 *          在IOC容器内将有依赖关系的bean进行关系绑定（DI）。
 *      最终效果
 *          使用对象时不仅可以直接从IOC容器中获取，并且获取到的bean已经绑定了所有的依赖关系。
 *
 *
 *
 *      Spring3.0开启了纯注解开发模式，使用Java类替代配置文件，开启了Spring快速开发赛道。
 *
 *          @Configuration注解：类注解，用于设定当前类为配置类。
 *          @ComponentScan注解：类注解，用于设定扫描路径，此注解只能添加一次，多个数据请用数组格式。【不管是使用配置文件还是配置类，都必须进行对应的Spring注解包扫描才可以使用。】
 *              将第三方独立的配置类加入核心配置类：方式1：@Import注解导入式；或者方式2：@ComponentScan扫描式。
 *              读取Spring核心配置文件初始化容器对象切换为读取Java配置类初始化容器对象。
 *          @PropertySource注解：类注解，用于加载properties配置文件；
 *              @PropertySource()中加载多文件请使用数组格式配置，不允许使用通配符。
 *
 *
 *      Spring注解定义bean:
 *              @Component注解：类注解，用于说明当前类对象是Bean对象
 *                  如果@Component注解没有使用参数指定Bean的名称，那么类名首字母小写就是Bean在IOC容器中的默认名称。例如：BookServiceImpl对象在IOC容器中的名称是bookServiceImpl。
 *              Spring提供 @Component 注解的三个衍生注解，三个衍生注解只是方便标识是哪一个层级的数据。
 *                  @Controller注解：类注解，用于表现层bean定义；
 *                  @Service注解：类注解，用于业务层bean定义；
 *                  @Repository注解：类注解，用于数据层bean定义。
 *
 *
 *      Spring注解开发设置依赖注入
 *              变量注入三个注解：@Value，@Autowired，@Qualifier。
 *              使用注解注入简单类型变量
 *                  @Value注解：变量注解；
 *                  使用@Value进行简单类型变量注入；
 *                  如果注入的参数是使用EI表达式【"${accountDao.username}"】的，那么要创建对应的properties配置文件，并在在配置类中使用@PropertySource加载properties配置文件
 *              使用注解注入引用类型变量【类中对象的属性】
 *                  @Autowired注解：变量注解；
 *                  使用@Autowired注解开启引用类型变量自动装配模式，默认按类型装配；
 *                      注意：引用类型变量自动装配基于反射设计创建对象并暴力反射对应属性为私有属性初始化数据，因此无需提供setter方法；
 *                  @Qualifier注解：变量注解；
 *                  自动装配bean时按bean名称装配,@Qualifier注解无法单独使用，必须配合@Autowired注解使用；
 *                  使用@Qualifier注解指定要装配的bean名称，目的：解决IOC容器中同类型Bean有多个装配哪一个的问题。
 *
 *
 *      Spring注解开发管理第三方Bean
 *              @Bean：方法注解，表示当前方法的返回值是一个bean对象，添加到IOC容器中。
 *
 *              【步骤一】单独定义配置类【本质还是类】，在类中定义一个返回值类型就是要管理的对象的类型的方法，并且该方法使用@Bean进行注解。
 *                  @Bean表示该方法的返回值是一个Bean对象，添加到IOC容器中。
 *              【步骤二】将独立的配置类【第三方配置类】加入核心配置。
 *                  方式1：@Import注解导入式；@Import注解：类注解，用于导入配置类。
 *                  方式2：@ComponentScan扫描式；@ComponentScan注解：类注解，用于扫描配置类。
 *
 *          Spring注解开发为第三方Bean注入资源【重点】
 *                  定义了Bean对象之后，可能需要对Bean对象中传入参数【注入资源】：
 *                  注解开发为第三方Bean的简单类型依赖注入：@Value()。如果@Value()中使用了EL表达式读取properties属性文件中的内容，那么就需要加载properties属性文件。
 *                  注解开发为第三方Bean的引用类型依赖注入:引用类型注入只需要将引用类型变量设置为@Bean注解的方法的形参即可【@Bean注解的方法就是获取第三方Bean对象的方法】，容器会根据类型自动装配对象。
 *                      Spring会自动从IOC容器中找到对应引用类型对象赋值bean定义方法设置形参，如果没有就会报错。
 *
 *
 *      Spring整合其他技术【重点】
 *              Spring整合mybatis【重点】
 *                  【第一步】在pom配置文件中导入Spring整合Mybatis依赖：spring-jdbc；mybatis-spring；
 *                  【第二步】创建JdbcConfig配置类来配置DataSource数据源，如果传参使用了EI表达式，那么要创建properties文件；
 *                  【第三步】创建MybatisConfig配置类整合mybatis；
 *                  【第四步】创建SpringConfig主配置类进行包扫描和加载其他配置类。
 *
 *              Spring整合Junit单元测试【重点】
 *                  【第一步】在pom配置文件中导入Spring整合Junit依赖：junit，spring-test；
 *                  【第二步】在test文件夹的测试类中使用Spring整合Junit专用的类加载器【@RunWith(SpringJUnit4ClassRunner.class)//:类注解】；
 *                  【第三步】在test文件夹的测试类中加载配置文件或者配置类；
 *                          【测试DAO或者Service层,加载Spring对应的配置类。@ContextConfiguration(classes = {SpringConfig.class})// :类注解】；
 *                          【测试Controller层,加载SpringMVC对应的配置类。@ContextConfiguration(classes = {SpringMVCConfig.class})// :类注解】；
 *
 *
 *
 *      使用注解方式开发持久层接口【Dao层】：先连接数据库之后，才有提示
 *          在mybatis中针对CRUD一共有四个注解
 *              @Select @Insert @Update @Delete
 *
 *
 *
 *      Spring事务管理
 *           Spring事务作用
 *                  事务作用：在数据层保障一系列的数据库操作同成功同失败。
 *                  Spring事务作用：在数据层或业务层保障一系列的数据库操作同成功同失败。
 *                  1. Spring注解式事务通常添加在业务层接口中而不会添加到业务层实现类中，降低耦合。
 *                  2. Spring注解式事务可以添加到接口业务方法上表示当前方法开启事务，也可以添加到接口上表示当前接口所有方法开启事务。
 *                  对于RuntimeException类型异常或者Error错误，Spring事务能够进行回滚操作。
 *                  但是对于编译器异常，Spring事务是不进行回滚的，所以需要使用rollbackFor来设置要回滚的异常。
 *
 *          Spring事务管理步骤：
 *              【第一步】在业务层接口上添加Spring事务管理【@Transactional //表示所有方法进行事务管理】；
 *              【第二步】设置事务管理器(将事务管理器添加到IOC容器中，复制代码)；1. 事务管理器要根据实现技术进行选择；2. MyBatis框架使用的是JDBC事务。
 *              【第三步】开启注解式事务驱动。【在Spring配置类上使用@EnableTransactionManagement开启事务管理//开启注解式事务驱动】。
 *
 *          Spring事务角色
 *              Spring事务角色
 *                  事务管理员：发起事务方，在Spring中通常指代业务层开启事务的方法。
 *                  事务协调员：加入事务方，在Spring中通常指代数据层方法，也可以是业务层方法。
 *              Spring事务角色
 *
 *
 *
 *
 *
 *
 *  SpringMVC技术  与浏览器进行互动，就有请求与响应。
 *          SpringMVC是一种基于Java实现MVC模型的轻量级Web框架。
 *          设置tomcat服务器，加载web工程(tomcat插件）：tomcat7-maven-plugin；
 *          导入坐标（SpringMVC+Servlet）：javax.servlet-api（provided）；spring-webmvc。
 *          对于SpringMVC而言，Controller方法返回值默认表示要跳转的页面，没有对应的页面就会报错。如果不想跳转页面而是响应数据，那么就需要在方法上使用@ResponseBody注解。
 *
 *      SpringMVC常用注解：SpringMVC是Web框架，表现层框架，是与浏览器页面进行交互的。
 *
 *              @EnableWebMvc:配置类注解,SpringMVC配置类定义上方,开启SpringMVC多项辅助功能。根据类型匹配对应的类型转换器。
 *              @Controller注解：类注解，设定SpringMVC的核心控制器bean【就是表现层web层的Bean对象】。
 *              @RequestMapping：类/方法注解,设置当前控制器方法请求访问路径【设置映射路径为，即外部访问路径】，如果设置在类上统一设置当前控制器方法请求访问路径前缀。类上方配置的请求映射与方法上面配置的请求映射连接在一起，形成完整的请求映射路径。
 *              @RequestParam:形参注解【非json类型形参】,SpringMVC控制器方法形参定义前面,绑定请求参数与处理器方法形参间的关系。
 *              @RequestBody：形参注解【json类型形参】，SpringMVC控制器方法形参定义前面，将请求中请求体所包含的数据以json形式传递给请求参数，此注解一个处理器方法只能使用一次。
 *              @PathVariable：形参注解，SpringMVC控制器方法形参定义前面，用于接收路径参数【请求路径中使用】，使用{参数名称}描述路径参数。
 *              @ResponseBody注解：方法注解【响应】，SpringMVC控制器方法定义上方，设置当前控制器方法响应内容为当前返回值，无需解析。
 *
 *
 *              @RequestBody、@RequestParam与@PathVariable区别和应用
 *              区别：
 *                  @RequestParam表示用于接收url地址传参，表单传参【接收非json格式数据】；
 *                  @RequestBody表示用于接收json数据【json】；
 *                  @PathVariable用于接收路径参数【请求路径中使用】，使用{参数名称}描述路径参数；
 *              应用：
 *                  后期开发中，发送请求参数超过1个时，以json格式为主，@RequestBody应用较广；
 *                  如果发送非json格式数据，选用@RequestParam接收请求参数；
 *                  采用RESTful进行开发，当参数数量较少时，例如1个，可以采用@PathVariable接收请求路径变量，通常用于传递id值。
 *
 *
 *
 *
 *
 *      Spring和SpringMVC是不同的架构，对应简化不同的三个层。因此加载相应的Bean对象时一般要分开处理【分开处理时就不能相互访问了，类似权限处理】。
 *
 *      Spring配置类和SpringMVC配置类加载各自对应的Bean。
 *              SpringMVC相关的bean:(表现层bean【Controller】)
 *              Spring相关的bean:(业务层bean【Service】,持久层bean【Dao,Mapper。@Repository】)
 *              SpringMVC相关的bean(表现层bean【Controller】)加载控制:SpringMVC配置类只加载Controller控制器bean。
 *                      SpringMVC加载的bean设定扫描范围为精准范围，SpringMVC加载的bean对应的包均在*.*.controller。
 *                      【例如：com.wang.controller】包内。@ComponentScan("com.wang.controller")。
 *
 *              Spring相关的Bean(业务层bean【Service】,持久层bean【Dao,Mapper。@Repository】)的加载方式：
 *                      方式一：Spring加载的bean设定扫描范围包含Controller，Service和Repository三层，然后再排除Controller。【例如：Spring加载的bean设定扫描范围为com.itheima，排除掉controller包内的bean】
 *                      方式二：Spring加载的bean设定扫描范围为精准范围，例如service包、dao包等；
 *                      方式三：不区分Spring与SpringMVC的环境，加载到同一个环境中。
 *
 *
 *
 *      SpringMVC实现步骤
 *              SpringMVC实现步骤
 *                      【第一步】创建web工程（Maven结构）;
 *                      【第二步】在pom配置文件中设置tomcat服务器来加载web工程(tomcat插件<plugin>)和导入坐标(SpringMVC+Servlet的<dependency>)；
 *                      【第三步】在controller包中定义处理请求的功能类（XxxController），在XxxController中要配置请求访问路径；
 *                              对于SpringMVC而言，Controller方法返回值默认表示要跳转的页面，没有对应的页面就会报错。
 *                              如果不想跳转页面而是响应数据，那么就需要在方法上使用@ResponseBody注解。
 *                      【第四步】编写SpringMVC配置类【springmvc配置类，本质上还是一个spring配置类】，加载处理请求的Bean【@ComponentScan("com.wang.controller")】；
 *                      【第五步】创建web容器启动类，加载SpringMVC配置，并设置SpringMVC请求拦截路径【ServletContainersInitConfig extends AbstractAnnotationConfigDispatcherServletInitializer】。

 *           // 创建web容器启动类，加载SpringMVC配置，并设置SpringMVC请求拦截路径
 *           // web容器配置类后面用这个简化版的方便
 *           public class ServletContainersInitConfig extends AbstractAnnotationConfigDispatcherServletInitializer {
 *
 *               //getRootConfigClasses()加载Spring配置类
 *                @Override
 *                protected Class<?>[] getRootConfigClasses() {
 *                    return new Class[]{SpringConfig.class};
 *                    //return new Class[0];// 不加载配置类
 *                }


 *                //getServletConfigClasses()加载Springmvc配置类,产生springmvc容器（本质还是spring容器）
 *                @Override
 *                protected Class<?>[] getServletConfigClasses() {
 *                    return new Class[]{SpringMvcConfig.class};
 *                    //return new Class[0];// 不加载配置类
 *                }


 *                //getServletMappings()设置由Springmvc控制器处理的请求映射路径，设置为/表示拦截所有请求，任意请求都将转入到SpringMVC进行处理。
 *                @Override
 *                protected String[] getServletMappings() {
 *                    return new String[]{"/"};
 *                    //return new String[0];// 不加载配置类
 *                }


 *                //POST请求传递的参数中文乱码处理
 *                @Override
 *                protected Filter[] getServletFilters() {
 *                    CharacterEncodingFilter filter = new CharacterEncodingFilter();
 *                    filter.setEncoding("UTF-8");
 *                    return new Filter[]{filter};
 *                }
 *            }
  *
  *
  *
  *
  *      SpringMVC请求与响应：就是SpringMVC如何处理request和response。
  *
  *          SpringMVC请求和响应json数据需要添加jackson-databind依赖以及在SpringMvcConfig配置类上添加@EnableWebMvc注解。
  *          @EnableWebMvc:配置类注解,SpringMVC配置类定义上方,开启SpringMVC多项辅助功能。根据类型匹配对应的类型转换器。
 *
 *          SpringMVC请求映射路径
 *              对于SpringMVC而言，Controller方法返回值默认表示要跳转的页面，没有对应的页面就会报错。如果不想跳转页面而是响应数据，那么就需要在方法上使用@ResponseBody注解。
 *              @RequestMapping注解：方法注解/类注解，SpringMVC控制器方法定义上方，设置当前控制器方法请求访问路径【设置映射路径，即外部访问路径】，如果设置在类上统一设置当前控制器方法请求访问路径前缀。
 *                  类上方配置的请求映射与方法上面配置的请求映射连接在一起，形成完整的请求映射路径。
 *              @ResponseBody注解：方法注解，SpringMVC控制器方法定义上方，设置当前控制器方法响应内容为当前返回值，无需解析。
 *
 *
 *          SpringMVC请求参数
 *              SpringMVC发送普通类型参数【重点】
 *                  【SpringMVC的GET请求出现中文乱码问题】：
 *                          原因：tomcat 8.0版本之后GET请求就不再出现中文乱码问题，但是我们使用的是tomcat7插件，所以会出现GET请求中文乱码问题。
 *                          解决：在pom.xml添加tomcat7插件处配置UTF-8字符集，解决GET请求中文乱码问题。<uriEncoding>UTF-8</uriEncoding><!--访问路径编解码字符集-->
 *
 *                  【SpringMVC的POST请求出现中文乱码问题处理方式：在加载SpringMVC配置的配置类中指定字符过滤器】：
 *                          //SpringMVC的POST请求传递的参数中文乱码处理
 *                           @Override
 *                          protected Filter[] getServletFilters() {
 *                              CharacterEncodingFilter filter = new CharacterEncodingFilter();
 *                              filter.setEncoding("UTF-8");
 *                              return new Filter[]{filter};
 *                          }
 *
 *
 *              SpringMVC的GET请求传递普通参数：
 *                      普通参数：url地址传参，地址参数名与形参变量名相同，定义形参即可接收参数；
 *
 *              SpringMVC的POST请求传递普通参数：
 *                      普通参数：form表单post请求传参，表单参数名与形参变量名相同，定义形参即可接收参数；
 *
 *
 *              SpringMVC的Request请求的五种类型参数传递：
 *                      【类型1】：普通参数【其实就是基本类型参数和String类型变量】：
 *                              情况1：当请求参数与形参名称相同且一一对应即可完成参数传递；
 *                              情况2：当请求参数名与形参名称不同，使用@RequestParam绑定参数关系【建立请求参数名称和形参名称的映射】；
 *                       【类型2】：POJO参数【普通Java对象”（Plain Old Java Object），POJO其实就是没有使用任何框架的对象】
 *                              情况1：无嵌套POJO参数【即对象没有包装且对象的成员变量全是普通参数】：请求参数名与方法中POJO类型的形参对象的成员属性名相同即可接收参数。即请求形参与该方法的形参对应的POJO对象对应的类中成员变量名和类型对应。
 *                              【无嵌套的pojo对象的单个参数就可以看作普通参数，请求参数的名称要和POJO中属性的名称一致，否则无法封装】，否则无法封装。
 *                              情况2：嵌套POJO参数【POJO对象中包含POJO对象】：嵌套属性按照层次结构设定名称即可完成参数传递【例如：Person类有Pojo对象studet的属性,Student类有name属性，那么Person的属性student.name】。
 *                       【类型3】：数组保存普通参数：普通参数的请求参数名与数组形参对象属性名相同且请求参数为多个，定义数组类型即可接收参数。
 *                                      同名普通请求参数可以直接映射到对应名称的形参数组对象中【实参参数名就是形参数组名】。
 *                       【类型4】：集合保存普通参数：普通参数的请求参数名与集合形参集合对象名相同且请求参数为多个，使用@RequestParam绑定参数关系。
 *                                      同名普通请求参数可以使用@RequestParam注解映射到对应名称的集合对象中作为数据。
 *
 *                       【类型5】：json数据形式参数传递：【重点】
 *                              在方法中使用形参@RequestBody注解来表明是传递json数据；使用都需要开启json数据格式的自动转换，在配置类中开启@EnableWebMvc。
 *                              json数据参数传递流程：
 *                                      【步骤一】在pom配置文件中添加json数据转换相关坐标:jackson-databind；
 *                                      【步骤二】在SpringMVC配置类中开启json数据类型自动转换：@EnableWebMvc；
 *                                      【步骤三】在Controller中编写方法接收json参数：使用@RequestBody注解将外部传递的json数组数据映射到形参的集合对象中作为数据。
 *
 *
 *                              json数据的几种形式：json普通数组（["","","",...]）；json对象（{key:value,key:value,...}）；json对象数组（[{key:value,...},{key:value,...}]）。
 *                                  情况1：json格式普通数组:["","","",...]【就是多个普通参数，不是以键值对的形式存在的】： 如["game","music","travel"]。
 *                                          使用@RequestBody注解，形参以List<？>形式接收。泛型约束<？>的数据类型中就是多个普通参数的类型。
 *
 *                                  情况2：json对象:{key:value,key:value,...}：POJO参数：json数据与形参对象属性名相同，定义POJO类型形参即可接收参数。
 *                                          【就是实体类对象的实例成员变量以键值对形式传入，在一个大括号内使用键值对表示完一个对象的成员变量的变量名和属性值。】{"id":10,"name":"张三","money":100}。
 *                                          POJO参数：json数据与形参对象属性名相同，定义POJO类型形参即可接收参数。
 *
 *                                  情况3：json对象数组:[{key:value,key:value,...},{key:value,key:value,...}]：POJO集合参数：json数组数据与集合泛型属性名相同，定义List类型形参即可接收参数。
 *                                          就是以json形式的多个对象的组成的数组。
 *
 *                  SpringMVC日期类型参数传递
 *                          @DateTimeFormat：形参注解，SpringMVC控制器方法形参前面，设定日期时间型数据格式，pattern：指定日期时间格式字符串【按照业务要求写就可以】。
 *                          使用@DateTimeFormat注解设置日期类型数据格式，默认格式yyyy/MM/dd。
 *                          @DateTimeFormat注解：其内部依赖Converter接口
 *
 *
 *
 *          SpringMVC响应参数
 *                  SpringMVC响应json数据需要添加jackson-databind依赖以及在SpringMvcConfig配置类上添加@EnableWebMvc注解。
 *                  @EnableWebMvc:配置类注解,SpringMVC配置类定义上方,开启SpringMVC多项辅助功能。根据类型匹配对应的类型转换器。
 *                  @RequestBody：形参注解，SpringMVC控制器方法形参定义前面，将请求中请求体所包含的数据以json形式传递给请求参数，此注解一个处理器方法只能使用一次。
 *
 *                  对于SpringMVC而言，Controller方法返回值默认表示要跳转的页面，没有对应的页面就会报错。也就是跳转页面时不能使用@ResponseBody注解。
 *                  如果不想跳转页面而是响应数据，那么就需要在方法上使用@ResponseBody注解。
 *
 *                  @ResponseBody：方法注解，设置当前控制器[方法]返回值作为响应体。不同的响应体
 *                  【类型1】：响应页面/跳转页面：不使用@ResponseBody注解，设置Controller类中方法的返回值为String类型，设置返回值为页面名称，即可实现页面跳转；
 *                  【类型2】：响应文本数据：使用@ResponseBody注解，设置Controller类中方法的返回值为String类型，设置返回值为任意字符串信息，即可实现返回指定字符串信息；
 *                  【类型3】：响应json数据：
 *                              响应json形式POJO对象：返回值为实体类对象，设置返回值为实体类类型，即可实现返回对应对象的json数据，需要依赖@ResponseBody注解和@EnableWebMvc注解；
 *                              响应json形式POJO集合对象：返回值为集合对象，设置返回值为集合类型，集合的泛型约束<>内为集合中pojo对象的类型，即可实现返回对应集合的json数组数据，需要依赖@ResponseBody注解和@EnableWebMvc注解。
 *
 *
 *
 *
 *  REST风格：
 *          1.REST（Representational State Transfer），表现形式状态转换；隐藏资源的访问行为，无法通过地址得知对资源是何种操作，简化书写；
 *          2.根据REST风格对资源进行访问称为RESTful；约定不是规范，可以打破，所以称REST风格；
 *          3.REST风格访问资源时使用行为动作区分对资源进行了何种操作；【就是不是在路径中包含操作的信息】，修改为模块名+动作。【就是5说的形式】
 *          4.REST风格描述模块的名称通常使用复数，也就是加s的格式描述，表示此类资源，而非单个资源，例如：users、books；
 *          5.Rest风格如何通过路径传递参数：在Controller中定义方法时设定"http请求动作(请求方式)"和"设定请求参数（路径变量）"。
 *
 *
 *          Rest快速开发：简化前后对比
 *              0.REST风格操作简化前：使用@RequestMapping(value = "请求路径",method = RequestMethod.请求方式)来注解，设置当前方法的请求路径和REST风格的请求方式。
 *              1.在Controller类上使用@RequestMapping定义共同的访问路径。
 *              2.REST风格操作简化后：使用@GetMapping，@PostMapping， @PutMapping，@DeleteMapping代替@RequestMapping(method=RequestMethod.XXX)。
 *
 *                      方法注解，设置基于SpringMVC的RESTful开发当前控制器方法请求访问路径与请求动作，每种对应一个请求动作，例如@GetMapping对应GET请求。
 *                      设置当前请求方法为POST，表示REST风格中的添加操作为例：@PostMapping("请求路径")     《==》   @RequestMapping(value ="请求路径" ,method = RequestMethod.POST)
 *                      参数传递：
 *                          【方式一】如果请求路径中有单个参数就需要使用@PathVariable注解：@PathVariable：请求路径的形参注解，SpringMVC 控制器方法形参定义前面，绑定路径参数与处理器方法形参间的关系，要求路径参数名与形参名一一对应。
 *                              @PathVariable注解用于设置路径变量（路径参数），要求路径上设置对应的占位符{形参}，并且占位符名称与方法形参名称相同。如果名称不对应，那么就需要在@PathVariable中指定占位符的名称@PathVariable("占位符的名称")。
 *                          【方式二】@RequestBody：形参注解，SpringMVC控制器方法形参定义前面，将请求中请求体所包含的数据以json形式传递给请求参数，此注解一个处理器方法只能使用一次。
 *                      【添加】：@PostMapping("请求路径")     《==》   @RequestMapping(value ="请求路径" ,method = RequestMethod.POST)
 *                      【删除】：@DeleteMapping("请求路径")   《==》   @RequestMapping(value ="请求路径" ,method = RequestMethod.DELETE)
 *                      【查询】：@GetMapping("请求路径")      《==》   @RequestMapping(value ="请求路径" ,method = RequestMethod.GET)
 *                      【修改】：@PutMapping("请求路径")      《==》   @RequestMapping(value ="请求路径" ,method = RequestMethod.PUT)
 *
 *                      @GetMapping @PostMapping @PutMapping @DeleteMapping：
 *                              方法注解；基于SpringMVC的RESTful开发控制器方法定义上方；设置当前控制器方法请求访问路径与请求动作，每种对应一个请求动作，例如@GetMapping对应GET请求。
 *
 *
 *              3.@RestController：类注解，设置当前控制器类为RESTful风格，等同于@Controller与@ResponseBody两个注解组合功能。
 *
 *
 *
 *    SpringMVC设置对静态资源的访问放行。SpringMVC技术 与 浏览器进行互动，就有请求与响应，需要放行静态资源。
 *    *       //为了确保静态资源能够被访问到，需要设置静态资源过滤【设置对静态资源的访问放行】，
 *            @Configuration
 *            public class SpringMvcSupport extends WebMvcConfigurationSupport {
 *           //设置静态资源访问过滤，当前类需要设置为配置类，并被扫描加载
 *                @Override
 *                protected void addResourceHandlers(ResourceHandlerRegistry registry) {
 *                    //当访问/pages/????时候，从/pages目录下查找内容
 *                    registry.addResourceHandler("/pages/**").addResourceLocations("/pages/");
 *                    registry.addResourceHandler("/js/**").addResourceLocations("/js/");
 *                    registry.addResourceHandler("/css/**").addResourceLocations("/css/");
 *                    registry.addResourceHandler("/plugins/**").addResourceLocations("/plugins/");
 *                }
 *            }
  *
  *
  *
  *  SSM整合配置
  *      SSM整合:
  *          1.MyBatis:JdbcConfig;jdbc.properties;MybatisConfig；
  *          2.Spring:SpringConfig；
  *          3.SpringMVC:ServletConfig;SpringMvcConfig；
  *      功能模块:
  *          1.数据库与实体类;在MySQL数据库中创建数据库和表，在项目中定义实体类【pojo类】
  *          2.dao【数据层】（接口+自动代理）：数据层接口测试（整合JUnit）；
  *          3.service【业务层】（接口+实现类）：业务层接口测试（整合JUnit）；
  *          4.controller【表现层】（类）：表现层接口测试（PostMan或者浏览器）
  *
  *
  *     pom文件中插件和依赖：
  *          pom文件中插件：
  *              Tomcat插件：tomcat7-maven-plugin；<uriEncoding>UTF-8</uriEncoding><!--访问路径编解码字符集-->
  *              maven-compiler-plugin：<source>8</source> <!-- 源代码使用的JDK版本 -->；
  *                                     <target>8</target> <!-- 需要生成的目标class文件的编译版本 -->。
  *
  *          pom文件中依赖：
  *              Spring相关插件：spring-context;
  *              MyBatis相关插件：mysql-connector-java；spring-jdbc；druid；mybatis；mybatis-spring；
  *              Junit相关插件：junit；spring-test
  *              SpringMVC相关插件：javax.servlet-api【<scope>provided</scope>】;spring-webmvc;jackson-databind;
  *
  *
  *   SSM开发流程：
  *          【SSM整合配置步骤】：
  *              【步骤一】：创建工程，添加依赖和插件【pom文件中插件和依赖见上面】；
  *              【步骤二】：Spring整合Mybatis:
  *                      1.创建数据库和表;
  *                      2.jdbc.properties属性文件和JdbcConfig配置类【创建数据库连接池对象和Spring事务管理需要的平台事务管理器对象】；
  *                      3.MybatisConfig配置类【Mybatis是简化JDBC操作的：SqlSessionFactoryBean；MapperScannerConfigure】;
  *                      4.SpringConfig配置类;
  *              【步骤三】：Spring整合SpringMVC：
  *                      1.ServletConfig配置类实现AbstractAnnotationConfigDispatcherServletInitializer，加载SpringMvcConfig和SpringConfig配置类；
  *                          //POST请求传递的参数中文乱码处理：在加载SpringMVC配置的配置类中指定字符过滤器。
  *                          【Servlet是Java提供的一门动态web资源开发技术】;
  *                              getRootConfigClasses()加载Spring容器；
  *                              getServletConfigClasses()是加载SpringMvc容器;
  *                              SpringMvc容器可以访问Spring容器，Spring容器不可以访问SpringMvc容器。
  *                      2.SpringMvcConfig配置类;
  *
  *
  *
  *
  *          【功能模块开发步骤】：
  *                  注解开发的@Component和三个衍生注解都要注解在实现类上面 ，因为这几个注解都是表示Bean对象的，所以必须在能产生对象的类上面使用。
  *                  注意只有在数据层，业务层和表现层的实现类上面使用@Component来标识Bean对象。
  *
  *                  【步骤一】：数据层开发(XxxDao):接口+自动代理
  *                          Dao层的接口不使用@Repository。
  *                          实体类；数据层Dao【mappper】一般写成接口形式+注解形式【BookDao接口可以使用@Mapper注解】。
  *
  *                  【步骤二】：业务层开发(XxxService/XxxServiceImpl):
  *                          业务层Service一般写接口和实现类。
  *                          业务层Service接口中的方法一般要见名知意，并且要写文档注释。增删查改要有返回值。
  *                          业务层Service接口中使用注释@Transactional表示所有方法进行事务管理；事务管理要在Spring配置类上使用@EnableTransactionManagement开启Spring事务管理。
  *                          业务层Service接口的实现类要在类上使用@Service来表示Service层的Bean对象。
  *                          Service层实现类一般要操作数据层Dao的对象。
  *
  *                  【步骤三】：表现层开发(XxxController)【表现层开发使用RESTful风格开发】:
  *                          XxxController方法请求路径中含有参数，在请求路径中使用占位符{形参名}：在方法形参前使用@PathVariable；
  *                          XxxController方法形参是json类型：在方法形参前使用@RequestBody；
  *                          XxxController方法请求路径在方法或类上使用@RequestMapping("请求路径")；
  *                          表现层controller一般只有类，没有接口；
  *                          表现层controller调用业务层service的对象；
  *
  *          【接口测试】：应该写完一层测试一层；Spring整合Junit做测试时要注意test文件夹和main文件夹的结构层次相对应。
  *                  【步骤一】：数据层开发(XxxDao)写完了测试Dao层：数据层接口测试（整合JUnit）；
  *                  【步骤二】：业务层开发(XxxService)写完了测试Service层：业务层接口测试（整合JUnit）；
  *                  【步骤三】：表现层开发(BookController)写完了测试Controller层：表现层接口测试（使用postman或者直接使用网页进行测试）；
  *
  *
  *
  *      SSM开发表现层【Controller层】数据封装【重点】：核心是统一表现层响应数据格式
  *              【步骤一】：定义Result类封装响应结果【本质是统一表现层响应数据的格式，根据业务需要设定】；
  *                      定义Result类封装响应结果【一般可以包括Object data// 数据;Integer code// 状态码;String msg// 消息】；
  *                      定义Code类封装响应码；
  *              【步骤二】：表现层方法返回数据封装的Result对象。
  *
  *
  *
  *     SSM开发异常处理器
  *
  *          出现异常现象的常见位置与常见诱因如下：
  *                  1.框架内部抛出的异常：因使用不合规导致；
  *                  2.数据层抛出的异常：因外部服务器故障导致（例如：服务器访问超时）；
  *                  3.业务层抛出的异常：因业务逻辑书写错误导致（例如：遍历业务书写操作，导致索引异常等）；
  *                  4.表现层抛出的异常：因数据收集、校验等规则导致（例如：不匹配的数据类型间导致异常）；
  *                  5.工具类抛出的异常：因工具类书写不严谨不够健壮导致（例如：必要释放的连接长期未释放等）；
  *          项目各个层级均可能出现异常，将异常统一处理：把全部异常放在Controller层统一处理，在异常通知类中拦截并处理异常。
  *
  *
  *          项目异常处理代码实现步骤：
  *                  【根据异常分类自定义异常类】:自定义异常处理器，用于封装异常信息，对异常进行分类.
  *                      自定义系统级异常类继承RuntimeException【自定义异常处理器】，用于封装异常信息，对异常进行分类；
  *                      自定义业务级异常继承RuntimeException【自定义异常处理器】，用于封装异常信息，对异常进行分类；
  *                      设定异常的成员变量和成员方法，并设定构造器，当出现对应异常时，创建系统异常对象，可以上抛也可以捕获处理。
  *
  *
  *                  【编写异常处理器】:在异常通知类中拦截并处理异常;
  *                      @RestControllerAdvice：Rest风格类注解，此注解自带@ResponseBody注解与@Component注解，具备对应的功能;
 *                      @ExceptionHandler(异常类.class)：方法注解，专用于异常处理的控制器方法上方，设置指定异常的处理方案，功能等同于控制器方法，出现异常后终止原始控制器执行，并转入当前方法执行。
 *                              在异常通知类中可以根据处理的异常不同，制作多个异常处理方法分别处理对应的异常。
 *                      Result类有三个实例成员变量：Object data,Integer code,String msg。
 *
 *
 *          项目异常分类及其处理方案：
 *              【项目业务异常（BusinessException）】：
 *                  1.规范的用户行为产生的异常；
 *                  2.不规范的用户行为操作产生的异常。
 *                  【处理方案】：发送对应消息传递给用户，提醒规范操作。
 *
 *              【系统异常（SystemException）】：
 *                  1.项目运行过程中可预计且无法避免的异常；
 *                  【处理方案】：发送固定消息传递给用户，安抚用户；发送特定消息给运维人员，提醒维护；记录日志；
 *
 *              【其他异常（Exception）】：
 *                  1.编程人员未预期到的异常。
 *                  【处理方案】：发送固定消息传递给用户，安抚用户；发送特定消息给编程人员，提醒维护（纳入预期范围内）；记录日志。
 *
 *
 *
 *      SpringMVC拦截器【理解】
 *              拦截器（Interceptor）是一种动态拦截方法调用的机制，在SpringMVC中动态拦截控制器方法的执行，所以可以设置在controller包中。
 *                      作用：1. 在指定的方法调用前后执行预先设定的代码；2. 阻止原始方法的执行；3. 总结：增强。
 *                      核心原理：AOP思想。
 *
 *              拦截器和过滤器的区别
 *                      归属不同：Filter属于Servlet技术，Interceptor属于SpringMVC技术。
 *                      拦截内容不同：Filter对所有访问进行增强，Interceptor仅针对SpringMVC的访问进行增强。
 *                      Interceptor仅针对SpringMVC的访问进行增强。
 *                      拦截器一般是拦截controller层的，所以可以设置在controller包下的子包中。也可以单独设置一个包。
 *
 *              SpringMVC拦截器代码实现
 *                      【步骤一】定义拦截器类:定义一个类，实现HandlerInterceptor接口，需要使用@Component表示当前类必须受Spring容器控制。【拦截器是基于AOP思想实现的】
 *                      【步骤二】配置加载拦截器:配置加载拦截器的方式1和2是二选一的关系。
 *                              方式1：public class SpringMvcSupport extends WebMvcConfigurationSupport{}
 *                              方式2：public class SpringMvcConfig implements WebMvcConfigurer{}，定义SpringMvc配置类来实现标准接口WebMvcConfigurer简化开发（注意：侵入式较强）
 *
 *
 *                   定义拦截器类:定义一个类，实现HandlerInterceptor接口。详细情况：
 *                   【前置处理】：
 *                  //原始方法调用前执行的内容；返回值类型可以拦截控制的执行，true放行，false终止
 *                   public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object
 *                   handler) throws Exception {
 *                       System.out.println("preHandle..."+contentType);
 *                       return true;
 *                   }
 *                   参数：1. request:请求对象；2. response:响应对象；3. handler:被调用的处理器对象，本质上是一个方法对象，对反射技术中的Method对象进行了再包装。
 *                   返回值：返回值为false，被拦截的处理器将不执行。
 *
 *
 *                   【后置处理】：如果处理器方法出现异常了，该方法不会执行。
 *                   //原始方法调用后执行的内容；现在开发机制不需要这一个
 *                   public void postHandle(HttpServletRequest request, HttpServletResponse response, Object
 *                   handler, ModelAndView modelAndView) throws Exception {
 *                       System.out.println("postHandle...");
 *                   }
 *                   参数：modelAndView：如果处理器执行完成具有返回结果，可以读取到对应数据与页面信息，并进行跳转；
 *
 *
 *                   【完成后处理】：无论处理器方法内部是否出现异常，该方法都会执行。
 *                   //原始方法调用完成后执行的内容
 *                   public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object
 *                   handler, Exception ex) throws Exception {
 *                       System.out.println("afterCompletion...");
 *                   }
 *                   参数：ex:如果处理器执行过程中出现异常对象，可以针对异常情况进行单独处理。
 *
 *
 *
 *                   // 配置加载拦截器方式1：定义类继承WebMvcConfigurationSupport
 *                   @Configuration
 *                    public class SpringMvcSupport extends WebMvcConfigurationSupport {
 *                        //配置第一个拦截器
 *                        @Autowired
 *                        private ProjectInterceptor projectInterceptor;
 *                        //为了确保静态资源能够被访问到，需要设置静态资源过滤。addResourceHandlers
 *                        @Override
 *                        protected void addResourceHandlers(ResourceHandlerRegistry registry) {
 *                            registry.addResourceHandler("/pages/**").addResourceLocations("/pages/");
 *                        }
 *                        @Override
 *                        public void addInterceptors(InterceptorRegistry registry) {
 *                            //配置多拦截器
 *                            registry.addInterceptor(projectInterceptor).addPathPatterns("/books","/books/*");
 *                        }
 *                    }
*
*
*                     // 配置加载拦截器方式2：定义SpringMvc配置类来实现标准接口WebMvcConfigurer
*                     @Configuration
*                     @ComponentScan({"com.wang.controller"})
*                     @EnableWebMvc
*                     //实现WebMvcConfigurer接口可以简化开发，但具有一定的侵入性
*                     public class SpringMvcConfig implements WebMvcConfigurer {
*                         //配置第一个拦截器
*                         @Autowired
 *                        private ProjectInterceptor projectInterceptor;
*
 *                        //为了确保静态资源能够被访问到，需要设置静态资源过滤。addResourceHandlers
 *                        @Override
 *                        protected void addResourceHandlers(ResourceHandlerRegistry registry) {
 *                           registry.addResourceHandler("/pages/**").addResourceLocations("/pages/");
 *                       }
 *                        @Override
 *                        public void addInterceptors(InterceptorRegistry registry) {
 *                            //配置多拦截器
 *                            registry.addInterceptor(projectInterceptor).addPathPatterns("/books","/books/*");
 *                        }
 *                    }


 *               SpringMVC拦截器流程分析：
 *                    SpringMVC拦截器执行顺序：
 *                        1.先执行preHandle方法，若preHandle方法返回true，则执行controller->postHandle->afterCompletion
 *                        2.先执行preHandle方法，若preHandle方法返回false，则不执行执行（controller->postHandle->afterCompletion
 *                        ）这三个，也就是跳到该preHandle方法对应的afterCompletion之后。



 *                SpringMVC 拦截器链配置：
 *                    0.创建多个拦截器类；
 *                    1.当配置多个拦截器时，形成拦截器链；
 *                    2.拦截器链的运行顺序参照拦截器添加顺序为准；
 *                    3.当拦截器中出现对原始处理器的拦截，后面的拦截器均终止运行；
 *                    4.当拦截器运行中断，仅运行配置在前面的拦截器的afterCompletion操作。
 *                    5.拦截器链的运行顺序：
 *                        preHandle：与配置顺序相同，必定运行；
 *                        postHandle：与配置顺序相反，可能不运行；
 *                        afterCompletion：与配置顺序相反，可能不运行。


 *
 *
 */


}
