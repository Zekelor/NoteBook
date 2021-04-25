#ZooKeeper
1. CAP定理
2. ZAB协议
3. Leader选举算法和流程
#Redis
1. Redis的应用场景
2. Redis支持的数据类型（必考）
3. zset跳表的数据结构（必考）
4. Redis的数据过期策略（必考）
5. Redis的LRU过期策略的具体实现
6. 如何解决Redis缓存雪崩，缓存穿透问题
7. Redis的持久化机制（必考）
8. Redis的管道pipeline
#Mysql
1. 事务的基本要素
2. 事务隔离级别（必考）
3. 如何解决事务的并发问题(脏读，幻读)（必考）
4. MVCC多版本并发控制（必考）
5. binlog,redolog,undolog都是什么，起什么作用
6. InnoDB的行锁/表锁
7. myisam和innodb的区别，什么时候选择myisam
8. 为什么选择B+树作为索引结构（必考）
9. 索引B+树的叶子节点都可以存哪些东西（必考）
10. 查询在什么时候不走（预期中的）索引（必考）
11. sql如何优化
12. explain是如何解析sql的
13. order by原理
#JVM
1. 运行时数据区域（内存模型）（必考）
2. 垃圾回收机制（必考）
3. 垃圾回收算法（必考）
4. Minor GC和Full GC触发条件
5. GC中Stop the world（STW）
6. 各垃圾回收器的特点及区别
7. 双亲委派模型
8. JDBC和双亲委派模型关系
9. JVM锁优化和锁膨胀过程
#Java基础
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
#Spring
1. Spring的IOC/AOP的实现（必考）
2. 动态代理的实现方式（必考）
3. Spring如何解决循环依赖（三级缓存）（必考）
4. Spring的后置处理器
5. Spring的@Transactional如何实现的（必考）
6. Spring的事务传播级别
7. BeanFactory和ApplicationContext的联系和区别
#其他
1. 高并发系统的限流如何实现
2. 高并发秒杀系统的设计
3. 负载均衡如何设计
