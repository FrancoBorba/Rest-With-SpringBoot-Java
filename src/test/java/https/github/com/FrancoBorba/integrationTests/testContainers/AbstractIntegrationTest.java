package https.github.com.FrancoBorba.integrationTests.testContainers;

import java.util.Map;
import java.util.stream.Stream;

import org.hibernate.service.spi.Startable;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.lifecycle.Startables;
/*


A anotação @ContextConfiguration(initializers = AbstractIntegrationTest.Initializer.class) é usada no contexto de testes de integração no Spring. Aqui está uma explicação do que isso faz:

ContextConfiguration: A anotação @ContextConfiguration é utilizada para carregar o contexto do Spring durante os testes. Ela permite especificar como o contexto deve ser configurado, incluindo classes de configuração, arquivos de contexto ou inicializadores.

Inicializadores: Ao passar a classe AbstractIntegrationTest.Initializer.class como parâmetro, você está especificando que o Spring deve usar a classe Initializer para configurar o contexto. Isso é útil quando você precisa realizar alguma configuração adicional antes que o contexto seja completamente carregado.

Classe Inicializer:

Essa classe é geralmente uma classe interna estática que implementa a interface ApplicationContextInitializer<ConfigurableApplicationContext>.
O propósito dessa classe é inicializar recursos, como contêineres TestContainers (por exemplo, um contêiner de banco de dados MySQL), antes do contexto do Spring ser carregado.
No seu caso, ela pode conter métodos para iniciar o contêiner de banco de dados e configurar as propriedades de conexão para o Spring.
Exemplo de Uso: A estrutura de lógica geralmente envolve pular o início dos contêineres até que todas as dependências necessárias sejam resolvidas, permitindo que o ambiente de teste permaneça isolado e controlado.
 */
@ContextConfiguration(initializers = AbstractIntegrationTest.Initializer.class)
public class AbstractIntegrationTest {

  static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    static MySQLContainer<?> mysql = new MySQLContainer<>("mysql:8.0.32");

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
      startContainer();
      // Obtento as variaveis de ambiente do applicationContext(ex: yaml)
       ConfigurableEnvironment environment = applicationContext.getEnvironment();
       MapPropertySource testcontainers = new MapPropertySource("tescontainers", 
       createConnectionConfiruration());

       // Adiciona as "variaveis de ambiente" que pegou de tescontainers
       environment.getPropertySources().addFirst(testcontainers);
    }

   
    // Inicia um container unico com uma instancia do mysql
    // Busca o parametro passado via contrutor do MySQLContainer
    private static void startContainer() {
      Startables.deepStart(Stream.of(mysql)).join();
     
    }

     private static Map<String, Object> createConnectionConfiruration() {
      return Map.of(
        "spring.datasource.url" , mysql.getJdbcUrl(),
        "spring.datasource.username" , mysql.getUsername(),
        "spring.datasource.password" , mysql.getPassword()
        );
    }
  }


  

  
}