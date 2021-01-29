# Heimdall Admin 权限管理系统

>  Heimdall  Admin 权限管理系统是 Heimdall安全框架 的配套应用示例。
基于 SpringBoot +JPA 实现了通用增删改查和 BRAC 权限管理功能。配合精美的前端UI 框架，稍作修改就可实现自己的管理信息系统后台。

## 技术选型：

* Heimdall  v1.0.2
* Spring Boot 2.4.1
* Spring Boot Data JPA
* Spring Boot Data Redis
* knife4j
* Element UI + Element UI Admin


## 实现功能
* 基础 RBAC 权限管理体系
* 支持:Restful资源授权、角色资源授权、权限标识符授权三种授权模式,任选其一。
* 动态授权
* 全局系统日志
* 登录尝试次数限制
* 多套风格的前端 UI

## 交流反馈,参与贡献

**仓库地址:**

- Heimdall 安全框架:https://github.com/luterc/heimdall
- Heimdall Admin后台 :https://github.com/luterc/heimdall-admin
- Heimdall Admin UI :https://github.com/luterc/heimdall-admin-ui

**技术交流QQ群:	554290469**

欢迎fork，star，欢迎提需求，欢迎吐槽，支持共建！

*如果你感觉好用的话，支持刷火箭 !*


## 快速开始
* 获取代码
    - Heimdall 安全框架:https://github.com/luterc/heimdall
    - Heimdall Admin后台 :https://github.com/luterc/heimdall-admin
    - Heimdall Admin UI :https://github.com/luterc/heimdall-admin-ui
* 配置
    1. 修改 pom.xml 中 dest.path 路径为自己本地打包文件输出目录
    2. 修改配置application-xxx.yml中的数据库链接地址和 redis 地址。
    3. mvn clean package
* 关于缓存
 系统支持两种缓存: Redis 、 Caffeine
 请根据自己实际情况选择，
 配置参见：CaffeineSecurityConfig、RedisSecurityConfig
 
##### 体验账号

* 用户名:zhangsanfeng ,密码:aaaaaa



## UI 效果
* 风格设置:支持纵向横向显示切换，支持整体组件大小和颜色选择
![](doc/images/720F144A-A681-4557-BFEB-B898D90D0C46.png)
![](doc/images/C80D864B-653B-4A19-B293-15EE0B2B6170.png)
* 登录注销
![](doc/images/3ED9934F-D531-45ED-8D7A-411B1C321CA5.png)
![](doc/images/F0A16653-4A83-40F1-AC51-28251E6D6AD0.png)
* 登录重试限制
![](doc/images/E6146418-9521-422E-A3AC-DB1CC0A0E820.png)
* 首页
![](doc/images/CFDF8B42-B325-4A33-ACEF-7970AFC24A2A.png)
* 用户管理:管理用户账户基本信息，启停设置等
![](doc/images/03596802-6CEA-4B55-A964-C4766BA967EB.png)
* 角色管理 :角色信息管理，角色授权
![](doc/images/20C928F8-6D3F-4A0C-8418-479AA24719A7.png)
![](doc/images/A67A7B9C-0401-4E29-B959-F66E47799584.png)
* 权限资源管理：管理系统菜单权限和操作权限
![](doc/images/00FA75FB-86BA-4C1D-A574-2A1338BC7E53.png)
![](doc/images/D84133CD-FEAF-4E72-85F8-DF106688F657.png)
* 系统日志管理:全局访问日志记录，基于注解拦截和 spring 事件通知
![](doc/images/45A55662-6674-4CB9-8655-F44CEFE8E4C6.png)
* 在线用户管理，在线用户列表查看、踢出等操作。
![](doc/images/BD842C68-9C02-4019-8CB3-8E92A8792E2F.png)