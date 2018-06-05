# spring-boot
在springboot中整合springcloud搭建微服务平台。
其中 使用eureka来提供分布式服务，使用feign和ribbon来调用服务；

由于服务注册点和服务同时都定义于本项目中，所以服务返回的数据只要是对象，皆为xml格式数据被返回！
如果需要服务返回json格式数据，就不可将服务定义在注册点项目中，即引入了spring-cloud-starter-eureka-server依赖的项目！
