package https.github.com.FrancoBorba.services;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import https.github.com.FrancoBorba.exception.ResourceNotFoundExcpetion;
import https.github.com.FrancoBorba.model.Person;
import https.github.com.FrancoBorba.repository.PersonRepository;

@Service // permite a injencao de dependencia
public class PersonServices {


  private final AtomicLong couter = new AtomicLong();

  private Logger logger = Logger.getLogger(PersonServices.class.getName());


  @Autowired
  PersonRepository repository;

  public Person findByID(Long id){  // achar o usuario pelo id
    logger.info("Find one person");

    return repository.findById(id).orElseThrow(
      () -> new ResourceNotFoundExcpetion("No records found for this id")
    );

  }

  public List<Person> findAll(){ // criando um metodo de achar todos os usuarios
   

    logger.info("Fnding all peolpe");

    
      
          return repository.findAll();
    }
      


        public Person create(Person person){ // end point  POST
        logger.info("Creating one Person");
        return repository.save(person);
        }

         public Person update(Person person){ // end point  POST
        logger.info("updating one Person");
        Person entity = repository.findById(person.getId()).orElseThrow(
      () -> new ResourceNotFoundExcpetion("No records found for this id"));

       
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());
        return repository.save(entity);
    
         }

        public void delete(Long id){
          logger.info("Deleting one person");

          Person entity = repository.findById(id).orElseThrow(
      () -> new ResourceNotFoundExcpetion("No records found for this id")
    );

        repository.delete(entity);
        }


}
