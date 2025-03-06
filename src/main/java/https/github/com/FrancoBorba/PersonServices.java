package https.github.com.FrancoBorba;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import https.github.com.FrancoBorba.model.Person;

@Service // permite a injencao de dependencia
public class PersonServices {


  private final AtomicLong couter = new AtomicLong();

  private Logger logger = Logger.getLogger(PersonServices.class.getName());

  public Person findByID(String id){  // achar o usuario pelo id
    logger.info("Find one person");

    Person person = new Person();// mockando um usuario
    person.setId(couter.incrementAndGet());
    person.setFirstName("Franco");
    person.setLastName("Borba");
    person.setAddress("Vitória Da Conquista");
    person.setGender("Male");
    return person;

  }

  public List<Person> findAll(){ // criando um metodo de achar todos os usuarios
    List<Person> persons = new ArrayList<Person>();

    logger.info("Fnding all peolpe");

    for(int i = 0 ; i < 8 ; i++){ // criando um mock de usuarios
      Person person = mockPerson(i);
      persons.add(person);
    }
      
          return persons;
    }
      
        private Person mockPerson(int i) {
             Person person = new Person();// mockando um usuario
              person.setId(couter.incrementAndGet());
              person.setFirstName("FirstName: " + i);
              person.setLastName("LastName: " + i);
              person.setAddress("Some address in Brasil");
              person.setGender("Male");
              return person;
        }


        public Person create(Person person){ // end point  POST
        logger.info("Creating one Person");
        return person;
        }

         public Person update(Person person){ // end point  POST
        logger.info("updating one Person");
        return person;
        }

        public void delete(String id){
          logger.info("Deleting one person");

        }


}
