# maven-spring-boot-mongodb

Simple spring boot mongoDB project paginating either simple queries (default pageable) 
as well as annotated queries and aggregations (custom pageable). 
The last case is not supported by default by Spring MongoRepository.

### links of interest:
* https://docs.spring.io/spring-data/mongodb/docs/current/reference/html/#mongodb.repositories.queries.aggregation
* https://docs.spring.io/spring-data/mongodb/docs/current/reference/html/#repositories.special-parameters
* https://docs.spring.io/spring-data/mongodb/docs/current/reference/html/#core.web.basic.paging-and-sorting

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