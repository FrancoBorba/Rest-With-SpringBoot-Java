package https.github.com.FrancoBorba.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

/*
A anotação @Configuration no Spring é usada para indicar que uma classe contém definições de beans do Spring — ou seja, configurações que devem ser carregadas no contexto da aplicação.

🔍 O que é um bean?
Um bean é um objeto que é instanciado, montado e gerenciado pelo container Spring. É com isso que o Spring faz injeção de dependência e configura automaticamente componentes da sua aplicação


 */
@Configuration
public class OpenAPIConfig  {
  
  @Bean
  OpenAPI customOpenAPI(){
    return new OpenAPI().info(
      new Info()
      .title("REST API'S RESTFULL FROM ZERO WITH JAVA SPRING BOOT , KUBERNETES AND DOCKER")
      .version("v1")
      .description("API developed during Professor Erudio's Spring Boot course")
      .termsOfService("https://github.com/FrancoBorba")
      .license(new License().name("Apache 2.0").url("https://github.com/FrancoBorba"))
    );
  }
}
