package https.github.com.FrancoBorba.controllerr;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import https.github.com.FrancoBorba.controllerr.docs.PersonControllerDocs;
import https.github.com.FrancoBorba.dataDTO.PersonDTO;
import https.github.com.FrancoBorba.services.PersonServices;
import io.swagger.v3.oas.annotations.tags.Tag;

//@CrossOrigin("http://localhost:8080") Cross a nivel de controller
@RestController
@RequestMapping("/api/person/v1")
@Tag(name = "People" , description = "Endpoints for managing people")
public class PersonController implements PersonControllerDocs {

  @Autowired
  private PersonServices service; // com a injencao de dependencia nao se precisa mais do new

  @CrossOrigin("http://localhost:8080")
  @Override
  @GetMapping(value ="/{id}" ,
  produces = { 
      MediaType.APPLICATION_JSON_VALUE ,
      MediaType.APPLICATION_XML_VALUE ,
      MediaType.APPLICATION_YAML_VALUE})
  public PersonDTO findByID( // end point GET
    @PathVariable("id") Long id ){

      return service.findByID(id);
    }

  @Override
  @GetMapping(  
  produces = { 
      MediaType.APPLICATION_JSON_VALUE ,
      MediaType.APPLICATION_XML_VALUE ,
      MediaType.APPLICATION_YAML_VALUE})
  public ResponseEntity<PagedModel<EntityModel<PersonDTO>>> findAll(
    @RequestParam(value = "page" , defaultValue = "0") Integer page ,
    @RequestParam(value = "size" , defaultValue = "12") Integer size ,
    @RequestParam(value = "direction" , defaultValue = "asc") String direction
  ){ // end point GET
    
      var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
      Pageable pageable = PageRequest.of(page, size , Sort.by(sortDirection,"firstName"));
      return ResponseEntity.ok(service.findAll(pageable)) ;
    }

    @CrossOrigin({"http://localhost:8080" , "https://www.erudio.com.br"})
  @Override
  @PostMapping(
     consumes =  { 
      MediaType.APPLICATION_JSON_VALUE ,
      MediaType.APPLICATION_XML_VALUE ,
      MediaType.APPLICATION_YAML_VALUE} ,
    produces = { 
      MediaType.APPLICATION_JSON_VALUE ,
      MediaType.APPLICATION_XML_VALUE ,
      MediaType.APPLICATION_YAML_VALUE}
  )
  public PersonDTO create(@RequestBody PersonDTO person){
    return service.create(person);
  }

   

  @Override
  @PutMapping(
    consumes =  { 
      MediaType.APPLICATION_JSON_VALUE ,
      MediaType.APPLICATION_XML_VALUE ,
      MediaType.APPLICATION_YAML_VALUE} ,
    produces = { 
      MediaType.APPLICATION_JSON_VALUE ,
      MediaType.APPLICATION_XML_VALUE ,
      MediaType.APPLICATION_YAML_VALUE}
  )
  public PersonDTO put(@RequestBody PersonDTO person){
    return service.update(person);
  }

  @Override
  @DeleteMapping(value = "/{id}")
  public ResponseEntity<?> delete(@PathVariable("id") Long id){
     service.delete(id);

     return ResponseEntity.noContent().build(); // retorna o status code correto(204)
  }

  @Override
  @PatchMapping(value = {"/{id}"},
     produces = { 
      MediaType.APPLICATION_JSON_VALUE ,
      MediaType.APPLICATION_XML_VALUE ,
      MediaType.APPLICATION_YAML_VALUE}
  )
  public PersonDTO disablePerson(@PathVariable("id") Long id) {
    return service.disablePerson(id);
  }



  
}
