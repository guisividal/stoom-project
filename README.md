# Teste de Qualificação Backend STOOM

## Technologies
* JAVA 11
* Spring Boot 2.4.x
* Spring Data JPA 2.4.x
* Postgres SQL 11.6.x
* Lombok 1.18.x
* Maven 3.6.x
* Docker 3.3.x

## Building and running

* `mvnw clean package`
* `docker build -t gsividal/stoom .`
* `docker run -p 8080:8080 -e "SPRING_DATASOURCE_URL=jdbc:postgresql://postgres:5432/stoom" --name="stoom_container" --network="stoom-project_default" gsividal/stoom`

## Running tests only

Needs Postgres and internet

* `mvnw test`