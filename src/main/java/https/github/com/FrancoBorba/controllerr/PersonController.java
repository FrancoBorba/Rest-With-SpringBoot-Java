package https.github.com.FrancoBorba.controllerr;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import https.github.com.FrancoBorba.model.Person;
import https.github.com.FrancoBorba.services.PersonServices;

@RestController
@RequestMapping("/person")
public class PersonController {

  @Autowired
  private PersonServices service; // com a injencao de dependencia nao se precisa mais do new

  @GetMapping(value ="/{id}" ,
  produces = MediaType.APPLICATION_JSON_VALUE) 
  public Person findByID( // end point GET
    @PathVariable("id") Long id ){
      return service.findByID(id);
    }

  @GetMapping(  
  produces = MediaType.APPLICATION_JSON_VALUE)
  public List<Person> findAll( ){ // end point GET
    
      return service.findAll();
    }

  @PostMapping(
    consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE
  )
  public Person create(@RequestBody Person person){
    return service.create(person);
  }

  @PutMapping(
    consumes =  MediaType.APPLICATION_JSON_VALUE ,
    produces = MediaType.APPLICATION_JSON_VALUE
  )
  public Person put(@RequestBody Person person){
    return service.update(person);
  }

  @DeleteMapping(value = "/{id}")
  public ResponseEntity<?> delete(@PathVariable("id") Long id){
     service.delete(id);

     return ResponseEntity.noContent().build(); // retorna o status code correto(204)
  }



  
}
