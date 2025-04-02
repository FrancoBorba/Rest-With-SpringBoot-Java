package https.github.com.FrancoBorba.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
//A anotação @Configuration no Spring indica que a classe contém definições de Beans e configurações para a aplicação.
public class WebConfig implements WebMvcConfigurer{


  public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {

    // Via QUERY PARAM http://localhost:8080/api/person/v1/1?mediaType=xml

 /*    configurer.favorParameter(true).// permite o cliente especificar o formato
                              parameterName("mediaType"). // define o nome do parametro
                              ignoreAcceptHeader(true) //Se true, o parâmetro de URL (mediaType) terá prioridade sobre o cabeçalho Accept.
                              .useRegisteredExtensionsOnly(false) //Se false, o Spring tentará identificar o formato mesmo que a extensão não esteja registrada.
                              .defaultContentType(MediaType.APPLICATION_JSON) //Se nenhum formato for especificado pelo cliente, a API retornará application/json.
                              .mediaType("json", MediaType.APPLICATION_JSON)
                              .mediaType("xml", MediaType.APPLICATION_XML);

                             Associa valores do parâmetro mediaType a tipos de mídia.

                                        "json" → application/json

                                          "xml" → application/xml
                               */

                        // Via HEADER PARAM
                               configurer.favorParameter(false)
                              .ignoreAcceptHeader(false)
                              .useRegisteredExtensionsOnly(false) 
                              .defaultContentType(MediaType.APPLICATION_JSON) 
                              .mediaType("json", MediaType.APPLICATION_JSON)
                              .mediaType("xml", MediaType.APPLICATION_XML)
                              .mediaType("yaml", MediaType.APPLICATION_YAML);
  }
}
