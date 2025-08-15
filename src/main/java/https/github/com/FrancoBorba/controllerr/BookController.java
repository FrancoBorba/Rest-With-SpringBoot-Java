package https.github.com.FrancoBorba.controllerr;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

   @GetMapping(produces = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_YAML_VALUE})
    @Override
    public ResponseEntity<PagedModel<EntityModel<BookDTO>>> findAll(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "12") Integer size,
            @RequestParam(value = "direction", defaultValue = "asc") String direction
    ) {
        var sortDirection = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, "title"));
        return ResponseEntity.ok(service.findAll(pageable));
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
