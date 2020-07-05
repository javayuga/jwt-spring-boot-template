FROM tomcat:latest
ENV spring.profiles.active=prod
ADD target/jwt-spring-boot-template.war /usr/local/tomcat/webapps/
EXPOSE 8080
CMD ["catalina.sh", "run"]
