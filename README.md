# Spring-boot-webflux-demo

support 
## Annotation-based Programming Model 
  domain-repository-service-controller 
## Functional Programming Model
  domain-repository-service-router

dependency Spring-data-JPA

test in H2 database


目前Spring-data-JPA不是响应式的，所以我们的repository也不是响应式的。响应式Spring Data目前也在开发当中，它被作为Kay发布的一部分，代码在spring-data-commons 2.0.x分支上。现在已经有一个[里程碑版本](https://spring.io/blog/2016/11/28/going-reactive-with-spring-data)可以使用。


但是因为JPA是阻塞的，所以目前没有reactive的JPA。

下面是具体原因：
```
https://stackoverflow.com/questions/41483194/reactivecrudrepository-to-use-hibernate-in-spring
https://stackoverflow.com/questions/43559265/spring-data-commons-2-0-0-no-property-delete-found-for-type-t-reactivecrudrepo/43567893#43567893
```
```
We won't provide reactive support for Spring Data JPA. Exposing a reactive API creates the expectation of being reactive but that's the exact opposite for JPA. JPA is blocking, requires a transaction context, that is bound to a thread and the whole JDBC backend follows a blocking approach as well. Providing a reactive API for relational databases could still make sense in a context of reactive/asynchronous database drivers.
```

