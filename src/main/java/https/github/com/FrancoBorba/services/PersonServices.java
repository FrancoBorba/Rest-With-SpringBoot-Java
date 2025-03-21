package https.github.com.FrancoBorba.services;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import https.github.com.FrancoBorba.dataDTO.v1.PersonDTO;
import https.github.com.FrancoBorba.exception.ResourceNotFoundExcpetion;
import static https.github.com.FrancoBorba.mapper.ObjectMapper.parseListObjetc;
import static https.github.com.FrancoBorba.mapper.ObjectMapper.parseObjetc;
import https.github.com.FrancoBorba.model.Person;
import https.github.com.FrancoBorba.repository.PersonRepository;

@Service // permite a injencao de dependencia
public class PersonServices {


  @SuppressWarnings("unused")
  private final AtomicLong couter = new AtomicLong();

 private Logger logger = LoggerFactory.getLogger(PersonServices.class.getName());


  @Autowired
  PersonRepository repository;



  public PersonDTO findByID(Long id){  // achar o usuario pelo id
    logger.info("Find one person");

    var entity = repository.findById(id).orElseThrow(
      () -> new ResourceNotFoundExcpetion("No records found for this id")
    );
    return parseObjetc(entity, PersonDTO.class); // converte a entidade em PersonDTO
  }

  public List<PersonDTO> findAll(){ // criando um metodo de achar todos os usuarios
   
    logger.info("Fnding all peolpe"); 
      
        return parseListObjetc(repository.findAll() ,PersonDTO.class); // converte a lista de Person(entity) em uma lista PersonDTO
    }
      


        public PersonDTO create(PersonDTO person){ // end point  POST
        logger.info("Creating one Person");

       var entity = parseObjetc(person, Person.class); // converte de DTO para entity

         repository.save(entity); // salva a entidade


        return parseObjetc(entity, PersonDTO.class); // converte a entidade para DTO e retorna ela
        }

        

         public PersonDTO update(PersonDTO person){ // end point  POST
        logger.info("updating one Person");
        Person entity = repository.findById(person.getId()).orElseThrow(
      () -> new ResourceNotFoundExcpetion("No records found for this id"));

       
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

         repository.save(entity); // salva a entidade
         
          return parseObjetc(entity, PersonDTO.class); // converte a entidade para DTO e retorna ela
         }

        public void delete(Long id){
          logger.info("Deleting one person");

          Person entity = repository.findById(id).orElseThrow(
      () -> new ResourceNotFoundExcpetion("No records found for this id")
    );

        repository.delete(entity);
        }


}
