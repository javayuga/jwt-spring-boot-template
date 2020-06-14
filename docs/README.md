# Spring Boot JWT demo

## Goals

A project to demonstrate:
 
- JWT authorization with Spring Boot REST API
- Externalized mySQL deployment via docker
- local deployment of Spring Boot app (for development purposes)
- Vuepress as a document building platform

Based on the excellent [Spring Boot tutorial](https://bezkoder.com/spring-boot-jwt-authentication/) by [Bezkoder](https://github.com/bezkoder)

Original Github repository [available here](https://github.com/bezkoder/spring-boot-spring-security-jwt-authentication)

## Ports

Services are listening on ports

| service | url:port | obs
| ---:|:---:|:---
| app      | [localhost:8082](http://localhost:8082) | either after docker-compose or .\mvnw spring:run
| Adminer      | [localhost:8081](http://localhost:8081)|after docker-compose
| mysql | [localhost:3306](http://localhost:3306)| after docker-compose
| vuepress | [localhost:8083](http://localhost:8083)| after yarn docs:dev
      


## JWT

From the [IETF definition](https://tools.ietf.org/html/rfc7519)

> JSON Web Token (JWT) is a compact, URL-safe means of representing claims to be transferred between two parties. 

In-depth information on Spring Security [can be found here](https://docs.spring.io/spring-security/site/docs/current/reference/html5/).


## Dockerized deployment

In Spring Boot, the most common initial approach is to use H2 (in-memory or disk-based) persistence.  That's just fine for prototyping and very small-scale projects.
  
As an exercise, however, I decided to use mySQL as a dockerized service.
  
With [Docker](https://docs.docker.com/docker-for-windows/) installed on your machine, there is little effort to run a docker-compose script.

```
# Use root/example as user/password credentials
version: '3.1'

services:

  db:
    image: mysql:8.0.20
    command: --default-authentication-plugin=mysql_native_password
    restart: always
    ports:
      - 3306:3306
    environment:
      MYSQL_DATABASE: jwt_template
      MYSQL_ROOT_PASSWORD: jwt_template

  adminer:
    image: adminer
    restart: always
    ports:
      - 8081:8080
```

[Adminer](https://www.adminer.org/) is a cute database management app written in PHP, which is a much needed replacement to the aging phpMyAdmin.

## Data initialization with Spring JPA

The data model for this project is quite simple, but still there are a few caveats.

Spring Boot data initialization runs **resources/data.sql**.  

Here's an excerpt from **resources/application.properties**

```{9,10}
spring.datasource.url= jdbc:mysql://localhost:3306/jwt_template
spring.datasource.username= root
spring.datasource.password= jwt_template

server.port=8082

spring.jpa.properties.hibernate.dialect= org.hibernate.dialect.MySQL5Dialect

spring.datasource.initialization-mode=always
spring.jpa.hibernate.ddl-auto= create-drop
``` 

As indicated by the highlights, data and schema will be refreshed after each restart.

This is good for an example, but might not be appropriate for most use cases.

Though not used here, [Spring Boot's @Sql annotation ](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/test/context/jdbc/Sql.html) could come in handy while developing tests, [as described here](https://www.baeldung.com/spring-boot-data-sql-and-schema-sql).


## Vuepress

[Vuepress](https://vuepress.vuejs.org/) is a static site generator for building documentation of technical projects.

Help for Vuepress configuration options [can be found here](https://vuepress.vuejs.org/config).

Markdown extensions are [described here](https://vuepress.vuejs.org/guide/markdown.htm).

## Testing with RESTer

Using [RESTer Chrome plugin](https://chrome.google.com/webstore/detail/rester/eejfoncpjfgmeleakejdcanedmefagga?hl=en) for testing the API.

Don't forget to set the Content-type to **application-json** on header

![RESTer content-type on header](/images/RESTerJWT_01.png)

### signup

url | method
:----|:----:
http://localhost:8082/api/auth/signup  | POST

payload
```json
{
    "username":"mod",
    "email":"mod@marcosilva.poa.br",
    "password":"123456789",
    "role":["mod","user"]
}
```
![RESTer signup ok](/images/RESTerJWT_02.png)

Using Spring Boot's **create-drop** strategy for data initialization, the signup method can be called once for each server session (restart of the project).

After the first call, the method will fail for the same username or email on the payload.

![RESTer signup error](/images/RESTerJWT_03.png)






 

