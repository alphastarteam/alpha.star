# 该项目是一个论坛项目，采用微服务架构，前后端分离开发
## demo-article
 论坛帖子服务，提供发贴、回贴等帖子相关服务，目前没有任何实现，swagger地址：http://localhost:8763/swagger-ui.html

## demo-blog
论坛主站，属于消费端，因为一开始是想做博客，但没找到好的模板，就改做了论坛，但这个名字没改掉，不过这也不重要，swagger地址：http://localhost:8764/swagger-ui.html

## demo-springcloud
论坛服务注册中心、发现中心,地址：http://localhost:8761/

## demo-springzuul
服务网关

## demo-user
论坛用户服务，提供常见的用户服务操作，目前已实现的包括用户登录、注册、改密码、查询用户，swagger地址：http://localhost:8762/swagger-ui.html

## fly-3.0
前端项目

## 简易架构图
https://www.processon.com/view/link/5c09d4a6e4b0e4a51422d107

## 运行步骤
1. 设置jdk11，分别运行前五个项目，确保都启动成功
2. 分别上面提到的三个地址，确保都运行成功
3. 搭建前端项目，方式随便，然后访问http://你的地址/html/然后测试用户注册与用户登录功能，登录成功时会设置一个名为token的localStorage