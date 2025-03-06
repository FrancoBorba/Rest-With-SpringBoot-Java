package https.github.com.FrancoBorba.controllerr;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import https.github.com.FrancoBorba.PersonServices;
import https.github.com.FrancoBorba.model.Person;

@RestController
@RequestMapping("/person")
public class PersonController {

  @Autowired
  private PersonServices service; // com a injencao de dependencia nao se precisa mais do new

  @RequestMapping(value ="/{id}" ,
  method = RequestMethod.GET ,
  produces = MediaType.APPLICATION_JSON_VALUE) 
  public Person findByID( // end point GET
    @PathVariable("id") String id ){
      return service.findByID(id);
    }

  @RequestMapping(
  method = RequestMethod.GET ,
  produces = MediaType.APPLICATION_JSON_VALUE)
  public List<Person> findAll( ){ // end point GET
    
      return service.findAll();
    }

  @RequestMapping(
    method = RequestMethod.POST ,
    consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE
  )
  public Person create(@RequestBody Person person){
    return service.create(person);
  }

  @RequestMapping(
    method = RequestMethod.PUT ,
    consumes =  MediaType.APPLICATION_JSON_VALUE ,
    produces = MediaType.APPLICATION_JSON_VALUE
  )
  public Person put(@RequestBody Person person){
    return service.update(person);
  }

  @RequestMapping(
    value = "/{id}",
    method = RequestMethod.DELETE 

  )
  public void delete(@PathVariable("id") String id){
  service.delete(id);
  }



  
}
