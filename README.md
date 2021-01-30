# Heimdall Admin 权限管理系统

>  Heimdall  Admin 权限管理系统是 Heimdall安全框架 的配套应用示例。
基于 SpringBoot +JPA 实现了通用增删改查和 BRAC 权限管理功能。配合精美的前端UI 框架，稍作修改就可实现自己的管理信息系统后台。

## 技术选型：

* Heimdall 权限管理框架
* Spring Boot  
* Spring Boot Data JPA
* Spring Boot Data Redis
* knife4j API 文档
* Element UI + Element UI Admin


## 实现功能
* 基础 RBAC 权限管理体系
* 支持:Restful资源授权、角色资源授权、权限标识符授权三种授权模式,任选其一。
* 动态授权
* 全局系统日志
* 登录尝试次数限制
* 多套风格的前端 UI

## 交流反馈,参与贡献
- Github

<a target="_blank" href="https://github.com/luterc/heimdall">**Heimdall 框架**</a>

<a target="_blank" href="https://github.com/luterc/heimdall-admin">**Heimdall-admin  权限管理系统** </a>

<a target="_blank" href="https://github.com/luterc/heimdall-admin-ui">**Heimdall-admin-ui  管理系统前端 UI** </a>

<a target="_blank" href="https://github.com/luterc/heimdall-admin-ui">**Heimdall-Starters 通用模块** </a>

**技术交流QQ群:	554290469**

欢迎fork，star，欢迎提需求，欢迎吐槽，支持共建！

*如果你感觉好用的话，支持刷火箭 !*

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
