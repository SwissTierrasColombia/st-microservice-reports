# st-microservice-reports

Report generation through JasperSoft.

## Running Development

```sh
$ mvn spring-boot:run
```

## Configuration 

### How to disable eureka client?

Modify the **enabled** property in st-microservice-reports/src/main/resources/**application.yml** file:

```yml
eureka:
  client:
    enabled: false
```

### How to disable config client?

Modify the **enabled** property in st-microservice-reports/src/main/resources/**bootstrap.yml** file:

```yml
spring:
  application:
    name: st-microservice-reports
  cloud:
    config:
      enabled: false
```

## Swagger Documentation?

See [http://localhost:9255/swagger-ui.html](http://localhost:7986/swagger-ui.html)

## License

[Agencia de Implementación - BSF Swissphoto - INCIGE](https://github.com/SwissTierrasColombia/st-microservice-reports/blob/master/LICENSE)
