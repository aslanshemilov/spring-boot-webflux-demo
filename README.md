# Spring-boot-webflux-demo

support 
## Annotation-based Programming Model 
  domain-repository-service-controller 
## Functional Programming Model
  domain-repository-service-router

dependency Spring-data-JPA

test in H2 database


目前Spring-data-JPA不是响应式的，所以我们的repository也不是响应式的。响应式Spring Data目前也在开发当中，它被作为Kay发布的一部分，代码在spring-data-commons 2.0.x分支上。现在已经有一个[里程碑版本](https://spring.io/blog/2016/11/28/going-reactive-with-spring-data)可以使用。
我们期待Kay的发布，然后把JPA那部分也可以换成完全响应式的。。。

