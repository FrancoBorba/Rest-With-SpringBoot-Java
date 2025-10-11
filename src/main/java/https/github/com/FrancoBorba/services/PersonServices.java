package https.github.com.FrancoBorba.services;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;

import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import https.github.com.FrancoBorba.controllerr.PersonController;
import https.github.com.FrancoBorba.dataDTO.PersonDTO;
import https.github.com.FrancoBorba.exception.RequiredObjectIsNullExcpetion;
import https.github.com.FrancoBorba.exception.ResourceNotFoundExcpetion;
import https.github.com.FrancoBorba.file.importer.contract.FileImporter;
import https.github.com.FrancoBorba.file.importer.factory.FileImporterFactory;

import static https.github.com.FrancoBorba.mapper.ObjectMapper.parseObjetc;
import https.github.com.FrancoBorba.model.Person;
import https.github.com.FrancoBorba.repository.PersonRepository;
import jakarta.transaction.Transactional;

@Service // permite a injencao de dependencia
public class PersonServices {


  @SuppressWarnings("unused")
  

 private Logger logger = LoggerFactory.getLogger(PersonServices.class.getName());


  @Autowired
  PersonRepository repository;

  @Autowired
  PagedResourcesAssembler<PersonDTO> assembler;

  @Autowired
  FileImporterFactory fileImporter;



  public PersonDTO findByID(Long id){  // achar o usuario pelo id
    logger.info("Find one person");

    var entity = repository.findById(id).orElseThrow(
      () -> new ResourceNotFoundExcpetion("No records found for this id")
    );
    var dto = parseObjetc(entity, PersonDTO.class); // converte a entidade em PersonDTO
    addHatoasLinks(dto);
    return dto;
  }

 

  public PagedModel<EntityModel<PersonDTO>> findAll(Pageable pageable){ // criando um metodo de achar todos os usuarios
   
    logger.info("Fnding all peolpe"); 

    var people = repository.findAll(pageable);

    return buildPagedModel(pageable, people);
    }

     public PagedModel<EntityModel<PersonDTO>> findByName(String firstName ,Pageable pageable){ // criando um metodo de achar todos os usuarios
   
    logger.info("Fnding  peolpe by name"); 

    var people = repository.findPeopleByName(firstName,pageable);

    return buildPagedModel(pageable, people);
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

         

  public List<PersonDTO> creationPeopleFromPlanilha(MultipartFile file) throws Exception{ 
    logger.info("Importing people from file"); 

    if(file.isEmpty()){
      throw new BadRequestException("Please set a valid file");
    }

    try (InputStream inputStream = file.getInputStream()) {
      String fileName = Optional.ofNullable(file.getOriginalFilename())
      .orElseThrow(()-> new BadRequestException("File name cannot be null"));

      FileImporter fileImporter = this.fileImporter.getImporter(fileName);

      List<Person> entities = fileImporter.importFile(inputStream).stream()
       .map( dto -> repository.save(parseObjetc(dto, Person.class)))
       .toList();

  return entities.stream().map(
    entity -> {
      var dto = parseObjetc(entity, PersonDTO.class);
      addHatoasLinks(dto);
      return dto;
    
 } ).toList();
  

    }
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
    
    @Transactional // Para implemenntar o ACID
    public PersonDTO disablePerson(Long id){
      logger.info("Disabling one person");

      Person entity = repository.findById(id)
      .orElseThrow( () -> new ResourceNotFoundExcpetion("No records found for this id"));

      repository.disablePerson(id);

      var dto = parseObjetc(entity, PersonDTO.class);

      addHatoasLinks(dto);

      return dto;
    }

    public void delete(Long id){
      logger.info("Deleting one person");

    Person entity = repository.findById(id).orElseThrow(
        () -> new ResourceNotFoundExcpetion("No records found for this id")
      );

    repository.delete(entity);
    }

      private PagedModel<EntityModel<PersonDTO>> buildPagedModel(Pageable pageable, Page<Person> people) {
    var peopleWithLink = people.map(person ->{
        var dto = parseObjetc(person, PersonDTO.class);
        addHatoasLinks(dto);
        return dto;
    });

    Link findAllLink = WebMvcLinkBuilder.linkTo(
      WebMvcLinkBuilder.methodOn(PersonController.class).findAll(
        pageable.getPageNumber() , pageable.getPageSize() , String.valueOf(pageable.getSort())))
        .withSelfRel();
      
    
        return assembler.toModel(peopleWithLink, findAllLink);
  }


 public  void addHatoasLinks( PersonDTO dto) {
      dto.add(linkTo(methodOn(PersonController.class).findAll(1, 12, "asc")).withRel("findAll").withType("GET"));
        dto.add(linkTo(methodOn(PersonController.class).findByName("", 1, 12, "asc")).withRel("findByName").withType("GET"));
        dto.add(linkTo(methodOn(PersonController.class).findByID(dto.getId())).withSelfRel().withType("GET"));
        dto.add(linkTo(methodOn(PersonController.class).create(dto)).withRel("create").withType("POST"));
        dto.add(linkTo(methodOn(PersonController.class)).slash("massCreation").withRel("massCreation").withType("POST"));
        dto.add(linkTo(methodOn(PersonController.class).put(dto)).withRel("update").withType("PUT"));
        dto.add(linkTo(methodOn(PersonController.class).disablePerson(dto.getId())).withRel("disable").withType("PATCH"));
        dto.add(linkTo(methodOn(PersonController.class).delete(dto.getId())).withRel("delete").withType("DELETE"));
  }

}
