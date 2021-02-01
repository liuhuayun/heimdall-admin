#Mybatis 与 JPA

## 背景
一直以来，关于 Mybatis 和 JPA 的争论就没断过，各有各的道理，谁也说服不了谁。

JPA 的支持者，认为 JPA 是 Java Orm 规范，更贴合面向对象和 DDD 思想,而且这是 Spring 官方推荐默认 ORM 。
Mybatis 的支持呢，则认为 Mybatis 封装简单、上手容易、SQL Driven、可以方便的对 sql 语句进行调优。
骑墙派，则是各打五十大板，甩出一句：脱离实际需求场景谈技术选型就是耍流氓.....

## 选型
就我个人而言，更倾向于使用 Spring Data JPA，原因如下:

* ORM 规范。
* 个人更熟悉 Hibernate 使用，入手成本相对较低。
* 非常不喜欢把 sql 写在 xml 里，而且sql 里各种 if else 判断，没觉得比写在 java 代码里更有优势。
* JPA 生成的 SQL，不见得比手写的差多少，微服务环境下，复杂 SQL应该尽量避免。
* 配合Hibernate通用泛型DAO,直接原生 SQL 查询也不复杂，个别需要 DBA 优化的 SQL，可直接使用原生查询。
* Mybatis 的数据类型映射、结果映射，不习惯。


> 还是那句话：
请选择你自己熟悉的，且符合业务场景的 ORM。


本框架已经实现了 Mybatis Plus 和 JPA 模块。

## 实现
本例中，实现了两种风格的 JPA 示例：
- admin-server: 纯实体类关系映射风格的，自动建表，自动关联查询。
- jpa-admin: 实体类之间去各种关联关系，通过 sql 获取关联数据。

## 代码生成器
分别实现了 JPA 和 Mybatis 的代码生成器，Mybatis生成器采用 Mybatis Plus 实现。

代码生成器使用 FreeMarker 模板引擎生成代码，你可按照自己实际情况对生成模板进行修改。