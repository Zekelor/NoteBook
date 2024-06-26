# ZooKeeper
### 1. CAP定理
### 2. ZAB协议
### 3. Leader选举算法和流程
# Redis
### 1. Redis的应用场景
### 2. Redis支持的数据类型（必考）
### 3. zset跳表的数据结构（必考）
### 4. Redis的数据过期策略（必考）
### 5. Redis的LRU过期策略的具体实现
### 6. 如何解决Redis缓存雪崩，缓存穿透问题
### 7. Redis的持久化机制（必考）
### 8. Redis的管道pipeline
# Mysql
1. 事务的基本要素
>  ACID，是指在可靠数据库管理系统（DBMS）中，事务(transaction)所应该具有的四个特性：
>
> * Automicity（原子性）
> 要么事务中的所有任务都必须发生，要么都不发生。
>
> * Consistency（一致性）
> 每个事务都必须保留数据库的完整性约束（已声明的一致性规则）。它不能使数据处于矛盾状态。在执行期间，一系列数据库操作不会违反任何完整性约束。
>
> * Isolation（隔离性）
> 两个同时进行的事务不能互相干扰。交易中的中间结果必须对其他交易不可见。其他一系列数据库操作无法看到一系列数据库操作的中间状态。
>
> * Durability（持久性）
> 已完成的事务以后不能中止或放弃其结果。它们必须在崩溃后通过（例如）重新启动DBMS持续存在。保证已提交的一系列数据库操作将永久保留

2. 事务隔离级别（必考）
> | 隔离级别 | 脏读 | 不可重复读 | 幻读 |
> | --- | --- | --- | --- |
> | 未提交读(Read Uncommitted) | 存在 | 存在 | 存在 |
> | 已提交读(Read Committed) | 不存在 | 存在 | 存在 |
> | 可重复读(Repeated Read) | 不存在 | 不存在 | 存在 |
> | 可串行读(Serializable) | 不存在 | 不存在 | 不存在 |
> 
> 提交读：Oracle等多数数据库默认都是该级别 (不重复读)
> 可重复读：InnoDB默认级别。在SQL标准中，该隔离级别消除了不可重复读，但是还存在幻象读
> 串行读：每次读都需要获得表级共享锁，读写相互都会阻塞
> 
> 可以使用*悲观锁机制*来处理这不可重复读和幻读，但是MySQL出于性能考虑，都是使用了以乐观锁为理论基础的MVCC（多版本并发控制）来避免这两种问题

3. 如何解决事务的并发问题(脏读，幻读)（必考）

4. MVCC多版本并发控制（必考）
> 在InnoDB中，会在每行数据后添加两个额外的隐藏的值来实现MVCC，这两个值一个记录这行数据何时被创建，另外一个记录这行数据何时过期（或者被删除）。 在实际操作中，**存储的并不是时间，而是事务的版本号**，每开启一个新事务，事务的版本号就会递增。 在可重读Repeatable reads事务隔离级别下：
> SELECT时，读取创建版本号<=当前事务版本号，删除版本号为空或>当前事务版本号。
> INSERT时，保存当前事务版本号为行的创建版本号
> DELETE时，保存当前事务版本号为行的删除版本号
> UPDATE时，插入一条新纪录，保存当前事务版本号为行创建版本号，同时保存当前事务版本号到原来删除的行

对于这种读取历史数据的方式，我们叫它<font color='#FF0000'>快照读 (snapshot read)</font>，而读取数据库当前版本数据的方式，叫当前读 (current read)。很显然，在MVCC中：

> * 快照读：
> 就是select
> 
> ```sql
> select * from table ….;
> ```
> 
> * 当前读：
> 特殊的读操作，插入/更新/删除操作，属于当前读，处理的都是当前的数据，需要加锁。
> 
> ```sql
> select * from table where ? lock in share mode;
> select * from table where ? for update; -- MariaDB Galera Cluster 无效
> insert;
> update ;
> delete;
> ```
> 
> 事务的隔离级别实际上都是定义了当前读的级别，MySQL为了减少锁处理（包括等待其它锁）的时间，提升并发能力，引入了<strong>快照读</strong>的概念，使得select不用加锁。而update、insert这些“当前读”，就需要另外的模块来解决了。
5. binlog,redolog,undolog都是什么，起什么作用
6. InnoDB的行锁/表锁
7. myisam和innodb的区别，什么时候选择myisam
8. 为什么选择B+树作为索引结构（必考）
9. 索引B+树的叶子节点都可以存哪些东西（必考）
10. 查询在什么时候不走（预期中的）索引（必考）
11. sql如何优化
12. explain是如何解析sql的
13. order by原理
# JVM
1. 运行时数据区域（内存模型）（必考）
  * 程序计数器（Program Counter Register）：
    * 程序计数器是内存里较小的一块区域，主要负责记录当前线程执行的位置，实现线程切换后能够恢复到正确的执行位置
  * Java虚拟机栈（Java Virtual Machine Stacks）：
    * Java虚拟机栈用于存储方法执行过程中的局部变量、操作数栈、方法出口等信息；
    * 每个方法在执行时都会创建一个对应的栈帧，栈帧包含了方法的局部变量表、操作数栈、动态链接、方法出口等信息。
  * 本地方法栈（Native Method Stack）：
    * 本地方法栈类似于Java虚拟机栈，但是它用于执行Native方法（使用JNI调用的方法）。
    * 与Java虚拟机栈类似，本地方法栈也会随着Native方法的调用和返回而入栈和出栈。
  * Java堆（Java Heap）：
    * Java堆是Java虚拟机中最大的一块内存区域，用于存储对象实例和数组；
    * 所有线程共享的Java堆用于存放所有的对象实例，包括程序员创建的对象和由Java虚拟机自动创建的对象（如字符串和数组）。
  * 方法区（Method Area）：
    * 方法区用于存储已加载的类信息、常量、静态变量、即时编译器编译后的代码等数据。
    * 在Java 8及之前的版本中，方法区通常被称为"永久代（Permanent Generation）"，但在Java 8之后，使用元空间（Metaspace）代替了永久代。
  * 直接内存区：
    * 直接内存并不是Java虚拟机运行时数据区的一部分，但是在NIO中经常被使用，可以通过ByteBuffer类来操作。
    * 直接内存是使用Native方法分配的内存，可以在堆外进行操作，常用于高性能IO操作。
  * 运行常量池
    * 运行时常量池是方法区的一部分，用于存放编译期生成的字面量和符号引用。
    * 在类加载时，常量池中的符号引用会被解析为直接引用，用于指向方法区中的方法、字段等信息。
2. 垃圾回收机制（必考）

3. 垃圾回收算法（必考）
4. Minor GC和Full GC触发条件
5. GC中Stop the world（STW）
6. 各垃圾回收器的特点及区别
7. 双亲委派模型
8. JDBC和双亲委派模型关系
9. JVM锁优化和锁膨胀过程
# Java基础
1. HashMap和ConcurrentHashMap区别（必考）
2. ConcurrentHashMap的数据结构（必考）
3. 高并发HashMap的环是如何产生的
4. volatile作用（必考）
5. Atomic类如何保证原子性（CAS操作）（必考）
6. synchronized和Lock的区别（必考）
7. ThreadLocal的原理和实现8. 为什么要使用线程池（必考）
9. 核心线程池ThreadPoolExecutor的参数（必考）
10. ThreadPoolExecutor的工作流程（必考）
11. 如何控制线程池线程的优先级
12. 线程之间如何通信
13. Boolean占几个字节
14. jdk1.8/jdk1.7都分别新增了哪些特性
15. Exception和Error16. Object类内的方法
# Spring
1. Spring的IOC/AOP的实现（必考）

引用 Spring 官方原文：This chapter covers the Spring Framework implementation of the

> ***Inversion of Control (IoC) [1] principle. IoC is also known as dependency injection (DI). It is a
  process whereby objects define their dependencies, that is, the other objects they work with, only
> through constructor arguments, arguments to a factory method, or properties that are set on the
> object instance after it is constructed or returned from a factory method. The container then injects
> those dependencies when it creates the bean. This process is fundamentally the inverse, hence the
> name Inversion of Control (IoC), of the bean itself controlling the instantiation or location of its
> dependencies by using direct construction of classes, or a mechanism such as the Service Locator
> pattern.***
> 
> ***“控制反转（IoC）”也称为“依赖注入（DI）”，是一个定义对象依赖的过程，对象只
> 和构造参数，工厂方法参数，对象实例属性或工厂方法返回相关。容器在创建这些 bean 的
> 时候注入这些依赖。这个过程是一个反向的过程，所以命名为依赖反转，对象实例的创建由
> 其提供的构造方法或服务定位机制来实现。
> IoC 最大的好处就是“ 解耦 ”*** 
> 
<font color='red'>简单来讲是通过xml/工厂类，xml中通过class反射对象实例，得到相应的对象进行反转控制</font>
```
第一步
<bean id="demoBean" class="com.zet.springioc.UserService">

第二步
public static UserService getService(){
  String classValue="class属性值";
  
  Class clazz =Class.forName(classValue);
  
  UserService service =clazz.newInstance();
  
  return service;
}

```

3. 动态代理的实现方式（必考）
4. Spring如何解决循环依赖（三级缓存）（必考）
5. Spring的后置处理器
6. Spring的@Transactional如何实现的（必考）
7. Spring的事务传播级别
8. BeanFactory和ApplicationContext的联系和区别
# 其他
1. 高并发系统的限流如何实现
2. 高并发秒杀系统的设计
3. 负载均衡如何设计
