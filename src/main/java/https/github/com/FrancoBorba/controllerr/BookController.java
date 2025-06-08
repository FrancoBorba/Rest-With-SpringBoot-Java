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

import https.github.com.FrancoBorba.controllerr.docs.BookControllerDocs;
import https.github.com.FrancoBorba.dataDTO.BookDTO;
import https.github.com.FrancoBorba.services.BookServices;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/book/v1")
@Tag(name = "Books", description = "End point to books")
public class BookController implements BookControllerDocs {

  @Autowired
  BookServices service; // injetando dependencia de books
  

  @Override
  @GetMapping(
    value = "{id}",// variavel no Path
    produces = { // possiveis retornos
      MediaType.APPLICATION_JSON_VALUE ,
      MediaType.APPLICATION_XML_VALUE ,
      MediaType.APPLICATION_YAML_VALUE
    }
  )
  public BookDTO findByID(@PathVariable("id") Long id){
    return service.findByID(id);
  }

  @Override
  @GetMapping(
    produces = { 
      MediaType.APPLICATION_JSON_VALUE ,
      MediaType.APPLICATION_XML_VALUE ,
      MediaType.APPLICATION_YAML_VALUE}
  )
  public List<BookDTO> findAll(){
    return service.findAll();
  }
  
  @Override
  @PostMapping(
    consumes = {
      MediaType.APPLICATION_JSON_VALUE ,
      MediaType.APPLICATION_XML_VALUE ,
      MediaType.APPLICATION_YAML_VALUE},
    produces = {
      MediaType.APPLICATION_JSON_VALUE ,
      MediaType.APPLICATION_XML_VALUE ,
      MediaType.APPLICATION_YAML_VALUE} 
    )
  public BookDTO create(@RequestBody BookDTO book){
    return service.create(book);
  }

  @Override
  @PutMapping(
    consumes = {
      MediaType.APPLICATION_JSON_VALUE ,
      MediaType.APPLICATION_XML_VALUE ,
      MediaType.APPLICATION_YAML_VALUE},
    produces = {
      MediaType.APPLICATION_JSON_VALUE ,
      MediaType.APPLICATION_XML_VALUE ,
      MediaType.APPLICATION_YAML_VALUE} 
    )
  public BookDTO update(@RequestBody BookDTO book){
    return service.update(book);
  }

  @Override
  @DeleteMapping(value = "/{id}")
  public ResponseEntity<?> delete(@PathVariable("id") Long id){
    service.delete(id);
    return ResponseEntity.noContent().build(); // retorna o status code correto(204)
  }

}
