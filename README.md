# maven-spring-boot-mongodb-transactional-configuration

Simple spring boot mongoDB (major to 4.0 version) project with transactional configuration, 
and also docker compose replica set.

### links of interest:
* https://onecompiler.com/posts/3uxxu2z5h/how-to-use-mongodb-transactions-in-spring-boot
* https://gist.github.com/mpowaga/e1f386efedc73837f4a7fac4ef3c3136
* https://docs.mongodb.com/manual/replication/
* https://www.mongodb.com/cloud/atlas (it has the replica set already configured)

## Start up from terminal
```bash
mvn clean package spring-boot:run -DskipTests=true -Dspring-boot.run.profiles=dev
```
or
```bash
./mvnw clean package spring-boot:run -DskipTests=true -Dspring-boot.run.profiles=dev
```

## Code style Guide

Follow official code conventions https://kotlinlang.org/docs/reference/coding-conventions.html.
Most of that rules can be done automatically by using IntelliJ code formatting tools. 
So before committing new code it's recommended to run 
[**Reformat Code**](https://www.jetbrains.com/help/idea/reformat-code-on-directory-dialog.html) action 
over the `src` directory