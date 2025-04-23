package https.github.com.FrancoBorba.services;

import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.stereotype.Service;

import https.github.com.FrancoBorba.controllerr.PersonController;
import https.github.com.FrancoBorba.dataDTO.PersonDTO;
import https.github.com.FrancoBorba.exception.RequiredObjectIsNullExcpetion;
import https.github.com.FrancoBorba.exception.ResourceNotFoundExcpetion;
import static https.github.com.FrancoBorba.mapper.ObjectMapper.parseListObjetc;
import static https.github.com.FrancoBorba.mapper.ObjectMapper.parseObjetc;
import https.github.com.FrancoBorba.model.Person;
import https.github.com.FrancoBorba.repository.PersonRepository;

@Service // permite a injencao de dependencia
public class PersonServices {


  @SuppressWarnings("unused")
  

 private Logger logger = LoggerFactory.getLogger(PersonServices.class.getName());


  @Autowired
  PersonRepository repository;



  public PersonDTO findByID(Long id){  // achar o usuario pelo id
    logger.info("Find one person");

    var entity = repository.findById(id).orElseThrow(
      () -> new ResourceNotFoundExcpetion("No records found for this id")
    );
    var dto = parseObjetc(entity, PersonDTO.class); // converte a entidade em PersonDTO
    addHatoasLinks(dto);
    return dto;
  }

 

  public List<PersonDTO> findAll(){ // criando um metodo de achar todos os usuarios
   
    logger.info("Fnding all peolpe"); 
      
        var persons = parseListObjetc(repository.findAll() ,PersonDTO.class); // converte a lista de Person(entity) em uma lista PersonDTO
          for (PersonDTO personDTO : persons) { // adiciona o link na lista
            addHatoasLinks(personDTO);
          }
        return persons;
    }
      


        public PersonDTO create(PersonDTO person){ // end point  POST

        if(person == null){
          throw new RequiredObjectIsNullExcpetion(); // lança excessão seo objeto for null
        }
        logger.info("Creating one Person");

       var entity = parseObjetc(person, Person.class); // converte de DTO para entity

         repository.save(entity); // salva a entidade


        var dto = parseObjetc(entity, PersonDTO.class); // converte a entidade para DTO e retorna ela

        addHatoasLinks(dto);

        return dto;
        }

        

         public PersonDTO update(PersonDTO person){ // end point  POST

          if(person == null){
          throw new RequiredObjectIsNullExcpetion(); // lança excessão seo objeto for null
        }
        
        logger.info("updating one Person");
        Person entity = repository.findById(person.getId()).orElseThrow(
      () -> new ResourceNotFoundExcpetion("No records found for this id"));

       
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

         repository.save(entity); // salva a entidade
         
          var dto =parseObjetc(entity, PersonDTO.class); // converte a entidade para DTO e retorna ela
          addHatoasLinks(dto);
          return  dto;
         }

        public void delete(Long id){
          logger.info("Deleting one person");

          Person entity = repository.findById(id).orElseThrow(
      () -> new ResourceNotFoundExcpetion("No records found for this id")
    );

        repository.delete(entity);
        }


 public  void addHatoasLinks( PersonDTO dto) {
    dto.add(linkTo(methodOn(PersonController.class).findByID(dto.getId())).withSelfRel().withType("GET"));

    dto.add(linkTo(methodOn(PersonController.class).findAll()).withRel("findAll").withType("GET"));

    dto.add(linkTo(methodOn(PersonController.class).create(dto)).withRel("create").withType("POST"));

    dto.add(linkTo(methodOn(PersonController.class).put(dto)).withRel("update").withType("PUT"));

    dto.add(linkTo(methodOn(PersonController.class).delete(dto.getId())).withRel("delete").withType("DELETE"));
  }

}
