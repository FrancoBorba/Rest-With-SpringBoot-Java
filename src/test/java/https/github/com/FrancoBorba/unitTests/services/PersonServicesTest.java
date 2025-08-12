package https.github.com.FrancoBorba.unitTests.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import https.github.com.FrancoBorba.dataDTO.PersonDTO;
import https.github.com.FrancoBorba.exception.RequiredObjectIsNullExcpetion;
import https.github.com.FrancoBorba.model.Person;
import https.github.com.FrancoBorba.repository.PersonRepository;
import https.github.com.FrancoBorba.services.PersonServices;
import https.github.com.FrancoBorba.unitTests.mapper.mocks.MockPerson;

@TestInstance(TestInstance.Lifecycle.PER_CLASS) // os mocks duram apenas para esta classe
@ExtendWith(MockitoExtension.class) // Diz ao JUnit 5 para usar a extensão do Mockito
public class PersonServicesTest {

  MockPerson input; // classe que gera os mocks

  @Mock
  PersonRepository repository; //  Cria um mock da classe PersonRepository, ou seja, uma versão falsa usada para simular seu comportamento nos testes. 




  @InjectMocks
  private PersonServices services; // Cria uma instância real de PersonServices e injeta automaticamente os mocks nas suas dependências (neste caso, o repository).

/*
Esse método roda antes de cada teste (@BeforeEach).

input = new MockPerson(); – cria um novo gerador de dados mockados para cada teste.

MockitoAnnotations.openMocks(this); – inicializa os objetos anotados com @Mock e @InjectMocks.

 */
  @BeforeEach 
  void setUp(){
    input = new MockPerson();
    MockitoAnnotations.openMocks(this);
  }


  @Test
  void testFindByID() {
    Person person = input.mockEntity(1); // cria o mock de Person
    person.setId(1L); // Define o ID da pessoa simulada como 1L
    when(repository.findById(1L)).thenReturn(Optional.of(person)); // Quando o método findById(1L) for chamado no repositório, retorne esse objeto person que criamos
    var result = services.findByID(1L); //Chama o método real da sua classe PersonServices, que está sendo testado. O resultado é armazenado na variável result

    // Agora faremos as verificações 

    assertNotNull(result);   // verifica se o objeto não é nulo
    assertNotNull(result.getId());   // verifica se o ID não é nulo
    assertNotNull(result.getLinks()); // verifica se os Links não são nulos


    //testando se o result possui os links certos,
    /*
    getRel() = tipo da relação (ex: self, findAll, delete, etc)
    getHref() = URL do link
    getType() = tipo de operação HTTP */

    //Link self para buscar essa mesma pessoa:
    assertNotNull(result.getLinks().stream().
    anyMatch(link -> link.getRel().value().equals("self") 
    &&  link.getHref().endsWith("/api/person/v1/1") 
    && link.getType().equals("GET")));

    //Link findAll para buscar todas as pessoas
    assertNotNull(result.getLinks().stream().
    anyMatch(link -> link.getRel().value().equals("findAll") 
    &&  link.getHref().endsWith("/api/person/v1") 
    && link.getType().equals("GET")));


    //Link post  para adicionar nova pessoa:
    assertNotNull(result.getLinks().stream().
    anyMatch(link -> link.getRel().value().equals("post") 
    &&  link.getHref().endsWith("/api/person/v1") 
    && link.getType().equals("CREATE")));

    //Link update
     assertNotNull(result.getLinks().stream().
    anyMatch(link -> link.getRel().value().equals("update") 
    &&  link.getHref().endsWith("/api/person/v1") 
    && link.getType().equals("PUT")));

    // Link Delete
    assertNotNull(result.getLinks().stream().
    anyMatch(link -> link.getRel().value().equals("delete") 
    &&  link.getHref().endsWith("/api/person/v1/1") 
    && link.getType().equals("DELETE")));
      
    
    // Verificação se os campos tem o retorno esperado (esperado/retornado)
    assertEquals("Address Test1", result.getAddress());
    assertEquals("First Name Test1", result.getFirstName());
    assertEquals("Last Name Test1", result.getLastName());
    assertEquals("Female", result.getGender());
    
     
  }


  @Test
  void testCreate() {
    Person person = input.mockEntity(1); // cria o mock de Person
    Person persisted = person;
    persisted.setId(1L); // Define o ID da pessoa simulada como 1L
    PersonDTO dto = input.mockDTO(1); // cria o Mock do DTO

    when(repository.save(person)).thenReturn(persisted); // Quando o método save for chamado no repositório, retorna persisted

    var result = services.create(dto); //Chama o método real da  classe PersonServices, que está sendo testado. O resultado é armazenado na variável result

    // Agora faremos as verificações 

    assertNotNull(result);   // verifica se o objeto não é nulo
    assertNotNull(result.getId());   // verifica se o ID não é nulo
    assertNotNull(result.getLinks()); // verifica se os Links não são nulos


    //testando se o result possui os links certos,
    /*
    getRel() = tipo da relação (ex: self, findAll, delete, etc)
    getHref() = URL do link
    getType() = tipo de operação HTTP */

    //Link self para buscar essa mesma pessoa:
    assertNotNull(result.getLinks().stream().
    anyMatch(link -> link.getRel().value().equals("self") 
    &&  link.getHref().endsWith("/api/person/v1/1") 
    && link.getType().equals("GET")));

    //Link findAll para buscar todas as pessoas
    assertNotNull(result.getLinks().stream().
    anyMatch(link -> link.getRel().value().equals("findAll") 
    &&  link.getHref().endsWith("/api/person/v1") 
    && link.getType().equals("GET")));


    //Link post  para adicionar nova pessoa:
    assertNotNull(result.getLinks().stream().
    anyMatch(link -> link.getRel().value().equals("post") 
    &&  link.getHref().endsWith("/api/person/v1") 
    && link.getType().equals("CREATE")));

    //Link update
     assertNotNull(result.getLinks().stream().
    anyMatch(link -> link.getRel().value().equals("update") 
    &&  link.getHref().endsWith("/api/person/v1") 
    && link.getType().equals("PUT")));

    // Link Delete
    assertNotNull(result.getLinks().stream().
    anyMatch(link -> link.getRel().value().equals("delete") 
    &&  link.getHref().endsWith("/api/person/v1/1") 
    && link.getType().equals("DELETE")));
      
    
    // Verificação se os campos tem o retorno esperado (esperado/retornado)
    assertEquals("Address Test1", result.getAddress());
    assertEquals("First Name Test1", result.getFirstName());
    assertEquals("Last Name Test1", result.getLastName());
    assertEquals("Female", result.getGender());

  }

    @Test
  void testCreateWithNullPerson() {
    Exception exception = assertThrows(RequiredObjectIsNullExcpetion.class, 
   () -> {
      services.update(null);
   });

   String expectedMessage = "It is not allowed to persist a null object";
   String actualMessage = exception.getMessage();

   assertTrue(expectedMessage.contains(actualMessage));
  }

  @Test
  void testDelete() {
    Person person = input.mockEntity(1);
    person.setId(1L); // na metodo mockEntity já se define o id com base no parametro então todos os setId foram redundantes , porém vou deixar pois estudando vi que o ideal seria criar o mockEntity sem o id
    
    when(repository.findById(1L)).thenReturn(Optional.of(person));

    services.delete(person.getId());

    // como não tem retorno não tem assert

    verify(repository, times(1)).findById(anyLong());
    verify(repository, times(1)).delete(any(Person.class));
   

  }

  


  @Test
  void testUpdate() {
    Person person = input.mockEntity(1); // cria o mock de Person
    Person persisted = person;
    persisted.setId(1L); // Define o ID da pessoa simulada como 1L
    PersonDTO dto = input.mockDTO(1); // cria o Mock do DTO

    when(repository.findById(1L)).thenReturn(Optional.of(person)); // Quando o método findById(1L) for chamado no repositório, retorne esse objeto person que criamos
    when(repository.save(person)).thenReturn(persisted); // Quando o método save for chamado no repositório, retorna persisted

    var result = services.update(dto); //Chama o método real da  classe PersonServices, que está sendo testado. O resultado é armazenado na variável result

    // Agora faremos as verificações 

    assertNotNull(result);   // verifica se o objeto não é nulo
    assertNotNull(result.getId());   // verifica se o ID não é nulo
    assertNotNull(result.getLinks()); // verifica se os Links não são nulos


    //testando se o result possui os links certos,
    /*
    getRel() = tipo da relação (ex: self, findAll, delete, etc)
    getHref() = URL do link
    getType() = tipo de operação HTTP */

    //Link self para buscar essa mesma pessoa:
    assertNotNull(result.getLinks().stream().
    anyMatch(link -> link.getRel().value().equals("self") 
    &&  link.getHref().endsWith("/api/person/v1/1") 
    && link.getType().equals("GET")));

    //Link findAll para buscar todas as pessoas
    assertNotNull(result.getLinks().stream().
    anyMatch(link -> link.getRel().value().equals("findAll") 
    &&  link.getHref().endsWith("/api/person/v1") 
    && link.getType().equals("GET")));


    //Link post  para adicionar nova pessoa:
    assertNotNull(result.getLinks().stream().
    anyMatch(link -> link.getRel().value().equals("post") 
    &&  link.getHref().endsWith("/api/person/v1") 
    && link.getType().equals("CREATE")));

    //Link update
     assertNotNull(result.getLinks().stream().
    anyMatch(link -> link.getRel().value().equals("update") 
    &&  link.getHref().endsWith("/api/person/v1") 
    && link.getType().equals("PUT")));

    // Link Delete
    assertNotNull(result.getLinks().stream().
    anyMatch(link -> link.getRel().value().equals("delete") 
    &&  link.getHref().endsWith("/api/person/v1/1") 
    && link.getType().equals("DELETE")));
      
    
    // Verificação se os campos tem o retorno esperado (esperado/retornado)
    assertEquals("Address Test1", result.getAddress());
    assertEquals("First Name Test1", result.getFirstName());
    assertEquals("Last Name Test1", result.getLastName());
    assertEquals("Female", result.getGender());

  }

    @Test
    void testUpdateWithNullPerson() {
    Exception exception = assertThrows(RequiredObjectIsNullExcpetion.class, 
   () -> {
      services.create(null);
   });

   String expectedMessage = "It is not allowed to persist a null object";
   String actualMessage = exception.getMessage();

   assertTrue(expectedMessage.contains(actualMessage));
  }
  
  @Test
  @Disabled("Reason: still under developement" )
  void testFindAll() {
       List<Person> list = input.mockEntityList();
        when(repository.findAll()).thenReturn(list);
        List<PersonDTO> people = new ArrayList<>();

        assertNotNull(people);
        assertEquals(14, people.size());

        var personOne = people.get(1);

        assertNotNull(personOne);
        assertNotNull(personOne.getId());
        assertNotNull(personOne.getLinks());

        assertNotNull(personOne.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("self")
                        && link.getHref().endsWith("/api/person/v1/1")
                        && link.getType().equals("GET")
                ));

        assertNotNull(personOne.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("findAll")
                        && link.getHref().endsWith("/api/person/v1")
                        && link.getType().equals("GET")
                )
        );

        assertNotNull(personOne.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("create")
                        && link.getHref().endsWith("/api/person/v1")
                        && link.getType().equals("POST")
                )
        );

        assertNotNull(personOne.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("update")
                        && link.getHref().endsWith("/api/person/v1")
                        && link.getType().equals("PUT")
                )
        );

        assertNotNull(personOne.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("delete")
                        && link.getHref().endsWith("/api/person/v1/1")
                        && link.getType().equals("DELETE")
                )
        );

        assertEquals("Address Test1", personOne.getAddress());
        assertEquals("First Name Test1", personOne.getFirstName());
        assertEquals("Last Name Test1", personOne.getLastName());
        assertEquals("Female", personOne.getGender());

        var personFour = people.get(4);

        assertNotNull(personFour);
        assertNotNull(personFour.getId());
        assertNotNull(personFour.getLinks());

        assertNotNull(personFour.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("self")
                        && link.getHref().endsWith("/api/person/v1/4")
                        && link.getType().equals("GET")
                ));

        assertNotNull(personFour.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("findAll")
                        && link.getHref().endsWith("/api/person/v1")
                        && link.getType().equals("GET")
                )
        );

        assertNotNull(personFour.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("create")
                        && link.getHref().endsWith("/api/person/v1")
                        && link.getType().equals("POST")
                )
        );

        assertNotNull(personFour.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("update")
                        && link.getHref().endsWith("/api/person/v1")
                        && link.getType().equals("PUT")
                )
        );

        assertNotNull(personFour.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("delete")
                        && link.getHref().endsWith("/api/person/v1/4")
                        && link.getType().equals("DELETE")
                )
        );

        assertEquals("Address Test4", personFour.getAddress());
        assertEquals("First Name Test4", personFour.getFirstName());
        assertEquals("Last Name Test4", personFour.getLastName());
        assertEquals("Male", personFour.getGender());

        var personSeven = people.get(7);

        assertNotNull(personSeven);
        assertNotNull(personSeven.getId());
        assertNotNull(personSeven.getLinks());

        assertNotNull(personSeven.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("self")
                        && link.getHref().endsWith("/api/person/v1/7")
                        && link.getType().equals("GET")
                ));

        assertNotNull(personSeven.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("findAll")
                        && link.getHref().endsWith("/api/person/v1")
                        && link.getType().equals("GET")
                )
        );

        assertNotNull(personSeven.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("create")
                        && link.getHref().endsWith("/api/person/v1")
                        && link.getType().equals("POST")
                )
        );

        assertNotNull(personSeven.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("update")
                        && link.getHref().endsWith("/api/person/v1")
                        && link.getType().equals("PUT")
                )
        );

        assertNotNull(personSeven.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("delete")
                        && link.getHref().endsWith("/api/person/v1/7")
                        && link.getType().equals("DELETE")
                )
        );

        assertEquals("Address Test7", personSeven.getAddress());
        assertEquals("First Name Test7", personSeven.getFirstName());
        assertEquals("Last Name Test7", personSeven.getLastName());
        assertEquals("Female", personSeven.getGender());
    }
  
  @Test
  void testAddHatoasLinks() {

  }
}
