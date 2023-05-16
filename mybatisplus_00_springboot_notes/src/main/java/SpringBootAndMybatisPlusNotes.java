public class SpringBootAndMybatisPlusNotes {

/*
*
*
*  可以创建maven项目，然后在pom文件中导入对应的
*  SpringBoot项目快速启动：jar支持命令行启动需要依赖maven插件支持，请确认打包时是否具有SpringBoot对应的maven插件。
* 【步骤一】：对SpringBoot项目打包（执行Maven构建指令package）；
* 【步骤二】：到打完包的jar文件位置，在标识文件路径的那个框中输入cmd然后回车【这里其实就是方便的去欸的那个路径】，执行启动指令：java -jar ***.jar【java -jar  jar包的全名】# 项目的名称根据实际情况修改
*
*
*   SpringBoot简介
*           SpringBoot是由Pivotal团队提供的全新框架，其设计目的是用来简化Spring应用的初始搭建以及开发过程。
*           SpringBoot需要勾选添加pom文件中的坐标，手工制作控制器，不需要web3.0配置类和Spring/SpringMVC配置类。
*               SpringBoot程序优点：自动配置；起步依赖（简化依赖配置）；辅助功能（内置服务器，……）。
*           基于idea开发SpringBoot程序需要确保联网且能够加载到程序框架结构。
*
*
*       【SpringBoot起步依赖】
*               1.parent：【引入 Spring Boot Starter 父工程】所有SpringBoot项目要继承的项目，定义了若干个坐标版本号（依赖管理，而非依赖），以达到减少依赖冲突的目的。
*               2.starter：【spring-boot-starter、spring-boot-starter-test、mybatis-plus-boot-starter，数据库】
*                   SpringBoot中常见项目名称，定义了当前项目使用的所有项目坐标，以达到减少依赖配置的目的。
*               3.实际开发：使用任意坐标时，仅书写GAV中的G和A，V由SpringBoot提供；如发生坐标错误，再指定version（要小心版本冲突)。
*                   <dependency>
                        <groupId>junit</groupId>
                        <artifactId>junit</artifactId>
                        <version>${junit.version}</version>
                    </dependency>
*
*
*       【SpringBoot辅助功能】
*           1.SpringBoot程序启动;
*           2.SpringBoot在创建项目时，采用jar的打包方式;
*           3.SpringBoot的引导类是项目的入口，运行main方法就可以启动项目;
*           4.使用maven依赖管理变更起步依赖项;
*           5.Jetty比Tomcat更轻量级，可扩展性更强（相较于Tomcat），谷歌应用引擎（GAE）已经全面切换为Jetty.
*
*
*
*       SpringBoot基础配置
*               SpringBoot配置文件格式:*.properties,*.yml,*.yaml
*               修改服务器端口:见三个文件。
*                   【application.properties】:
*                   【application.yml】：
*                   【application.yaml】：
*               SpringBoot配置文件加载顺序：application.properties > application.yml > application.yaml
*               SpringBoot核心配置文件名为application；
*               SpringBoot内置属性过多，且所有属性集中在一起修改，在使用时，通过提示键+关键字修改属性。
*
*
*               SpringBoot自动提示功能消失解决方案。
*                       File->Project Structure->Facets->选择对应的springboot项目->【树叶+六角形】->勾选对应的配置文件。
*
*               SpringBoot的yaml格式配置文件
*                       YAML（YAML Ain't Markup Language），一种数据序列化格式：以数据为核心，重数据轻格式；
*                       YAML文件扩展名：【*.yml（主流）】，【*.yaml】。
*                       【yaml语法规则】
*                               大小写敏感；
*                               属性层级关系使用多行描述，每行结尾使用冒号结束；
*                               使用缩进表示层级关系，同层级左侧对齐，只允许使用空格（不允许使用Tab键）；
*                               属性值前面添加空格（属性名与属性值之间使用冒号+空格作为分隔）；
*                               #表示注释；
*                               核心规则：数据【属性值】前面要加空格与冒号隔开；
*
*                        【yaml数组数据】:数组数据在数据书写位置的下方使用减号作为数据开始符号，每行书写一个数据，减号与数据间空格分隔;
 *                       【yaml数据读取】：
 *                              【方式1】：使用@Value读取单个数据，属性名引用方式：${一级属性名.二级属性名……}；
 *                              【方式2】：封装全部数据到Environment对象【Environment对象是框架提供的，直接@Autowired注释引入Environment对象】；框架中常使用，是使用反射实现的；
 *                                      使用environment.getProperty("一级属性名.二级属性名……"))来获取去数据。
 *                              【方式3】：自定义对象封装指定数据【常用】（单独定义一个实体类来封装yaml中数据）。
 *                                      自定义对象封装数据警告解决方案：在pom文件中添加spring-boot-configuration-processor依赖。
 *                                      自定义的实体类使用@Component注解：封装yaml对象格式数据必须先声明当前实体类受Spring管控；
 *                                      使用@ConfigurationProperties注解定义当前实体类读取配置属性信息，通过prefix属性设置读取哪个数据；
 *                                      其实就是对于（一级属性名.二级属性名）的数据，将二级属性封装为一级属性对应的对象的属性，即二级属性是一级属性对应的类的成员变量。
 *                                      在一级属性对应的类上方使用@Component注解和@ConfigurationProperties(prefix=="一级属性名")。
 *                                      在使用该一级属性对应的类的对象的类中，使用 @Autowired注解该一级属性对应的类对象。
 *
 *
 *                SpringBoot配置文件分类
 *                      SpringBoot中4级配置文件
 *                          1级： file ：config/application.yml 【最高】
 *                          2级： file ：application.yml
 *                          3级：classpath：config/application.yml
 *                          4级：classpath：application.yml 【最低】
 *                              1级与2级留做系统打包后设置通用属性,，打包后在*.jar文件所在的目录下建立对应的yml文件，用于配置打包文件的属性；
 *                              3级与4级用于系统开发阶段设置通用属性。
 *
 *
*                【SpringBoot整合JUnit】
*                       【步骤一】添加整合junit起步依赖(可以直接勾选)：spring-boot-starter-test；
*                       【步骤二】编写测试类，默认自动生成了一个，在测试类上面使用@SpringBootTest注解。；
*                               @SpringBootTest：测试类注解；设置JUnit加载的SpringBoot启动类。
*
*
*               【基于SpringBoot实现SSM整合】
*                       SpringBoot整合Spring（不存在）；
*                       SpringBoot整合SpringMVC（不存在）；
*                       SpringBoot整合MyBatis（主要）；
*                       基于SpringBoot实现MyBatis整合步骤：
*                           【步骤一】创建新模块，选择Spring初始化，并配置模块相关基础信息；
*                           【步骤二】选择当前模块需要使用的技术集（SQL->MyBatis FrameWork、MySQL Driver）；
*                           【步骤三】设置数据源参数
*                               spring:
*                                 datasource:
*                                   driver-class-name: com.mysql.cj.jdbc.Driver
*                                   url: jdbc:mysql://localhost:3306/ssm_db?serverTimezone=UTC
*                                   username: root
*                                   password: 1234
*                                   type: com.alibaba.druid.pool.DruidDataSource
*                           【步骤四】定义数据层接口与映射配置，在Dao接口上使用@Mapper注解
*
*
*
*               【基于SpringBoot实现SSM整合步骤】：不需要Spring和SpringMVC配置类。
*                       【步骤一】创建SpringBoot工程：Spring Initializr->SQL->MyBatis FrameWork、MySQL Driver【JDBC】；
*                       【步骤二】设置pom文件：配置起步依赖，插件等；
*                       【步骤三】application.yml文件中设置数据源、端口、多环境启动配置等数据；
*                       【步骤四】数据层Dao接口使用@Mapper注解，
*
*
*
*
*  Lombok插件
*       Lombok插件：一个Java类库，提供了一组注解，简化POJO实体类开发。<artifactId>lombok</artifactId>
*       Lombok插件常用注解：
*           @Data注解：为当前实体类在编译期设置对应的get/set方法，toString方法，hashCode方法，equals方法等；
*           生成getter和setter方法：@Getter、@Setter；
*           生成toString方法：@ToString；
*           生成equals和hashcode方法：@EqualsAndHashCode；
*           @Data：对应的get/set方法，toString方法，hashCode方法，equals方法；
*           生成空参构造： @NoArgsConstructor；
*           生成全参构造： @AllArgsConstructor；
*           lombok还给我们提供了builder的方式创建对象,好处就是可以链式编程。 @Builder【扩展】；
*
*
*
*
* MyBatisPlus
*
*       MyBatis-Plus (opens new window)（简称 MP）是一个 MyBatis 的增强工具，在 MyBatis 的基础上只做增强不做改变，为简化开发、提高效率而生。
*       MyBatisPlus特性
*           无侵入：只做增强不做改变，不会对现有工程产生影响。
*           强大的 CRUD 操作：内置通用 Mapper，少量配置即可实现单表CRUD 操作。
*           支持 Lambda：编写查询条件无需担心字段写错。
*           支持主键自动生成。
*           内置分页插件。
*
*
*       开启MyBatisPlus日志：在yml文件中设置开启MyBatisPlus日志
*               # 1.开启mp的日志（输出到控制台）
*               mybatis-plus:
*                 configuration:
*                   log-impl: org.apache.ibatis.logging.stdout.StdOutImpl
*
*
*        解决MyBatisPlus日志打印过多问题
*               1.取消初始化spring日志打印
*                   做法：在resources下新建一个logback.xml文件，名称固定，内容如下：
*                           <?xml version="1.0" encoding="UTF-8"?>
*                           <configuration>
*                           </configuration>
*
*
*               2.取消SpringBoot启动banner图标：在yml文件中设置取消SpringBoot启动banner图标
*                   spring:
*                       main:
*                           banner-mode: off # 关闭SpringBoot启动图标(banner)
*
*
*               3.取消MybatisPlus启动banner图标：在yml文件中设置取消MybatisPlus启动banner图标
*                   # mybatis-plus日志控制台输出
*                   mybatis-plus:
*                       configuration:
*                           log-impl: org.apache.ibatis.logging.stdout.StdOutImpl
*                       global-config:
*                           banner: off # 关闭mybatisplus启动图标
*
*
*       SpringBoot整合MyBatisPlus整合步骤：
*           【步骤一】创建新模块，选择Spring初始化，并配置模块相关基础信息；
*           【步骤二】选择当前模块需要使用的技术集（SQL->MySQL Driver）；
*           【步骤三】在pom文件中手动添加MyBatisPlus起步依赖：mybatis-plus-boot-starter，druid；；
*           【步骤四】制作数据库表结构和实体类；
*           【步骤五】手动添加MyBatisPlus起步依赖：mybatis-plus-boot-starter，druid；
*           【步骤六】设置Jdbc参数【数据源参数】（application.yml）；
*               spring:
*                  datasource:
*                     driver-class-name: com.mysql.cj.jdbc.Driver
*                     url: jdbc:mysql://localhost:3306/ssm_db?serverTimezone=UTC
*                     username: root
*                     password: 1234
*                     type: com.alibaba.druid.pool.DruidDataSource
*           【步骤七】定义数据接口，继承BaseMapper，在Dao接口上使用@Mapper注解。
*
*
*
*       MyBatisPlus的 CRUD 接口
*           对象 Wrapper 为 条件构造器
*
*           MyBatisPlus的 Service CRUD 接口:接口中还有多种方法。具体参见文档： https://www.baomidou.com/pages/49cc81/#service-crud-%E6%8E%A5%E5%8F%A3
*               1.通用 Service CRUD 封装 IService 接口，进一步封装 CRUD 采用 get 查询单行 remove 删除 list 查询集合 page 分页 前缀命名方式区分 Mapper 层避免混淆；
*               2.泛型 T 为任意实体对象；
*               3.建议如果存在自定义通用 Service 方法的可能，请创建自己的 IBaseService 继承 Mybatis-Plus 提供的基类；
*               4.对象 Wrapper 为 条件构造器。可以使用接口中方法的就直接使用就可以，要是不能用就需要自己写。或者不想用就自己写。
*                       MyBatisPlus的 Service CRUD 接口中的方法已经已经是包含MyBatisPlus的Mapper CRUD 接口中的方法，也就是在 Service CRUD 接口中的方法在接口中调用MyBatisPlus的Mapper CRUD 接口中的方法。
*
*                       新增【插入】：save;
*                       修改插入：saveOrUpdate;
*                       删除：remove;
*                       查询：get;
*                       修改：update;
*                       查询全部：list;
*                       分页查询：page;
*                       计数：count;
*                       链式查询/更改：chain;
*                           链式查询：query;
*                           链式更改：update;
*
*
*
*           Mapper CRUD 接口: 接口中还有多种方法。具体参见文档： https://www.baomidou.com/pages/49cc81/#count
*               1.通用 CRUD 封装 BaseMapper 接口，为 Mybatis-Plus 启动时自动解析实体表关系映射转换为 Mybatis 内部对象注入容器;
*               2.泛型 T 为任意实体对象;
*               3.参数 Serializable 为任意类型主键 Mybatis-Plus 不推荐使用复合主键约定每一张表都有自己的唯一 id 主键;
*               4.对象 Wrapper 为 条件构造器。
*
*                       新增【插入】：insert;
*                       删除：delete，deleteById;
*                       查询：select,selectById,selectList,selectByMap,selectPage;
*                       修改：update,updateById;
*                       查询全部：selectList,selectByMap,selectMaps,;
*                       分页查询：selectPage,selectMapsPage;
*                       计数：selectCount;
*                       有批量查询和批量删除，分页查询等
*
*
*
*           条件构造器:其实就是SQl语句的条件在MybatisPlus这里自定义了一下。具体写法参见文档：https://www.baomidou.com/pages/10c804/#%E7%94%A8xml
*               eq:等于 =;
*               ne:不等于 <>;
*               gt：大于 >;
*               ge：大于等于 >=;
*               lt：小于 <;
*               le：小于等于 <=;
*               between：BETWEEN 值1 AND 值2;
*               notBetween：NOT BETWEEN 值1 AND 值2;
*               like：LIKE '%值%';
*               notLike：NOT LIKE '%值%';
*               isNull：字段 IS NULL;
*               isBNotNull：字段 IS NOT NULL;
*               in：字段 IN (value.get(0), value.get(1), ...);
*               isNull：字段 IS NULL;
*               groupBy：分组：GROUP BY 字段, ...;
*               orderByASC：排序：ORDER BY 字段, ... ASC;
*               orderByDesc：排序：ORDER BY 字段, ... DESC;
*               orderBy：排序：ORDER BY 字段, ...;
*               having：HAVING ( sql语句 );
*               or：拼接 OR【主动调用or表示紧接着下一个方法不是用and连接!(不调用or则默认为使用and连接)】;
*               and：排序：ORDER BY 字段, ...;
*
*
*           Mapper CRUD 接口中的条件查询方式：
*               条件查询方式一：按条件查询：QueryWrapper<User> qw=new QueryWrapper<>();
*               条件查询方式二：lambda格式按条件查询：QueryWrapper<User> qw=new QueryWrapper<>();qw.lambda().lt(User::getAge, 10);
*               条件查询方式三：lambda格式按条件查询（推荐）LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<User>();lqw.lt(User::getAge, 10);
*
*               组合条件：
*                   并且关系（and）：链式编程
*                   或者关系（or）：两个方法中间使用or()连接；lambdaQueryWrapper.lt(User::getAge, 10).or().gt(User::getAge, 30);
*                   NULL值处理方式1: if语句控制条件追加
*                   NULL值处理方式2: 条件参数控制  lambdaQueryWrapper.gt(minAge != null, User::getAge, minAge);
*                   NULL值处理方式3:条件参数控制（链式编程） lambdaQueryWrapper.gt(minAge != null, User::getAge, minAge).lt(maxAge != null, User::getAge, maxAge);
*
*
*               查询投影-设置【查询字段、分组、分页】：
*                   1.查询结果包含模型类中部分属性：查询语句中设置多个参数即可，
*                       方式1：LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();lambdaQueryWrapper.select(User::getId, User::getName, User::getAge);
*                       方式2：QueryWrapper<User> queryWrapper = new QueryWrapper<>();queryWrapper.select("id", "name", "age", "tel");
*
*                   查询投影-设置【查询字段、分组、分页】：
*
*
*       MyBatisPlus的数据库表的字段映射与表名映射
*
*               问题一：数据库的表字段与实体类编码属性设计不同步：
*                       在模型类【实体类】属性上方，使用@TableField属性注解，通过==value==属性，设置当前属性对应的数据库表中的字段关系。
*                       @TableField(value = "pwd",select = false)
*                       private String password;
*
*               问题二：实体类编码中添加了数据库中未定义的属性：
*                       在模型类【实体类】属性上方，使用@TableField注解，通过==exist==属性，设置属性在数据库表字段中是否存在，默认为true。此属性无法与value合并使用。
*                       @TableField(exist = false)//表示online字段不参与CRUD操作
*                       private Integer online;// 该属性在数据库字段中不存在
*
*               问题三：采用默认查询开放了更多的字段查看权限：
*                       在模型类【实体类】属性上方，使用@TableField注解，通过==select==属性：设置该属性是否参与查询。此属性与select()映射配置不冲突
*                           @TableField(value = "pwd",select = false)
*                           private String password;
*
*               问题四：表名与编码开发设计不同步:
*                       在模型类【实体类】上方，使用@TableName注解，通过==value==属性，设置当前类对应的数据库表名称。
*                       @TableName("tbl_student")//设置表名映射关系
*                       public class Student {}
*
*
*
*       MyBatisPlus的id生成策略控制（Insert）
*           不同的表应用不同的id生成策略
*               日志：自增（1,2,3,4，……）
*               购物订单：特殊规则（FQ23948AK3843）
*               外卖单：关联地区日期等信息（10 04 20200314 34 91）
*               关系表：可省略id
*
*               id生成策略控制（@TableId注解）
*                   @TableId注解：属性注解，模型类中用于表示主键的属性定义上方，设置当前类中主键属性的生成策略，
*                           相关属性：type：设置主键属性的生成策略，值参照IdType枚举值。
*                           AUTO(0）：使用数据库id自增策略控制id生成
*                           NONE(1)：不设置id生成策略
*                           INPUT(2)：用户手工输入id
*                           ASSIGN_ID(3)：雪花算法生成id（可兼容数值型与字符串型)
*                           ASSIGN_UUID(4)：以UUID生成算法作为id生成策略
*
*
*      MyBatisPlus的全局策略配置：application.yml文件中mybatis-plus的配置
*
*           mybatis-plus:
*             configuration:
*               log-impl: org.apache.ibatis.logging.stdout.StdOutImpl # 开启mp的日志（输出到控制台）
*
*           # 全局策略配置，也就是所有的实体类都有的属性
*             global-config:
*               db-config:
*                 id-type: auto # id-type：设置主键属性的生成策略全局配置，值参照IdType枚举值
*                 table-prefix: tbl_  # table-prefix表名前缀全局配置
*                 logic-delete-field: deleted # 逻辑删除字段名
*                 logic-not-delete-value: 0 # 逻辑删除字面值：未删除为0
*                 logic-delete-value: 1 # 逻辑删除字面值：删除为1
*           #    banner: off  # 关闭mybatisplus启动图标
*
*
*
*      MyBatisPlus的逻辑删除（Delete/Update）
*           删除操作业务问题：业务数据从数据库中丢弃;
*           逻辑删除：为数据设置是否可用状态字段，删除时设置状态字段为不可用状态，数据保留在数据库中;
*           逻辑删除本质：逻辑删除的本质其实是修改操作。如果加了逻辑删除字段，查询数据时也会自动带上逻辑删除字段。
*
*           【逻辑删除】
*               1.数据库表中添加逻辑删除标记字段， 例如 deleted；
*               2.实体类中添加对应字段，并设定当前字段为逻辑删除标记字段；
*                       @TableLogic（value="0",delval="1"）
*                       @TableLogic // 这个和MyBatisPlus的全局策略配置搭配使用
*                       private Integer deleted;
*               3.配置逻辑删除字面值；
*               1.数据库表中添加逻辑删除标记字段；
*
*
*
*
*     乐观锁（Update）
*           乐观锁假设数据一般情况下不会造成冲突，所以在数据进行提交更新的时候，才会正式对数据的冲突与否进行检测，如果发现冲突了，则返回给用户错误的信息，让用户决定如何去做。
*           使用乐观锁的流程：
*                  1.数据库表中添加锁标记字段 version；
*                  2.实体类中添加对应字段，并设定当前字段为逻辑删除标记字段；
*                           @Version
*                           private Integer version;
*                  3.配置乐观锁拦截器实现锁机制对应的动态SQL语句拼装；
*                           在MyBatisPlus配置类中设置Mp拦截器，并添加乐观锁拦截器；
*                           //1.定义Mp拦截器对象
*                            MybatisPlusInterceptor mpInterceptor = new MybatisPlusInterceptor();
*                           //2.添加具体的拦截器:添加乐观锁拦截器
*                           mpInterceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());
*                  4.使用乐观锁机制在修改前必须先获取到对应数据的verion方可正常进行，【也就是可以先获取待修改数据，获取了待修改数据，那么待修改数据的version也就获取了】；
*
*
*
*   快速开发-代码生成器
*       MyBatisPlus快速开发流程：工程搭建和基本代码编写
*           【步骤一】：创建SpringBoot工程，添加代码生成器相关依赖，其他依赖自行添加；
*                   代码生成器：<artifactId>mybatis-plus-generator</artifactId>；
*                   velocity模板引擎：<artifactId>velocity-engine-core</artifactId>
*           【步骤二】：编写代码生成器类【class CodeGenerator】，创建代码生成器对象【new AutoGenerator()】，执行生成代码操作；
*           【步骤三】：在代码生成器类中设置数据源相关配置：读取数据库中的信息，根据数据库表结构生成代码；
*                     在代码生成器类中设置全局配置；
*                     在代码生成器类中设置包名相关配置；
*                     在代码生成器类中设置策略；
*           【步骤四】：执行生成操作【autoGenerator.execute();】。
*
*
*
* */

}
