package https.github.com.FrancoBorba.services;


import static https.github.com.FrancoBorba.mapper.ObjectMapper.parseListObjetc;
import static https.github.com.FrancoBorba.mapper.ObjectMapper.parseObjetc;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import https.github.com.FrancoBorba.controllerr.BookController;
import https.github.com.FrancoBorba.dataDTO.BookDTO;
import https.github.com.FrancoBorba.exception.RequiredObjectIsNullExcpetion;
import https.github.com.FrancoBorba.exception.ResourceNotFoundExcpetion;
import https.github.com.FrancoBorba.model.Book;
import https.github.com.FrancoBorba.repository.BookRepoository;




@Service
public class BookServices {
  
  @Autowired // injeta a dependencia de do repositorio de book
  BookRepoository repository;

  private Logger logger = LoggerFactory.getLogger(PersonServices.class.getName());


  public BookDTO findByID(Long id){
    logger.info("Find a book");
    
     var entity = repository.findById(id).orElseThrow(
       () -> new ResourceNotFoundExcpetion("No records found for this id")
     );

    var dto = parseObjetc(entity , BookDTO.class);

    addHatoasLinks(dto);

    return dto;
    
      }

  public List<BookDTO> findAll(){
    logger.info("Finding all books");

    var books = parseListObjetc(repository.findAll(), BookDTO.class);

    for(BookDTO book : books){
      addHatoasLinks(book);
    }

    return books;
    
  }

  public BookDTO create(BookDTO book){
    if(book == null){
        throw new RequiredObjectIsNullExcpetion(); // lança excessão seo objeto for null
    }
    logger.info("Create a book");

    var entity = parseObjetc(book, Book.class); // transforma o DTO em entidade

    repository.save(entity);

    var dto = parseObjetc(entity, BookDTO.class); // transforma a entidade em DTO para o retorno

    addHatoasLinks(dto);

    return dto;
  }

  public BookDTO update(BookDTO book){
    if(book == null){
        throw new RequiredObjectIsNullExcpetion(); // lança excessão seo objeto for null
    }
    logger.info("Updating a book");

    Book entity = repository.findById(book.getId()).orElseThrow( // recupera a entidade
    () -> new ResourceNotFoundExcpetion("No records found for this id"));
    
    // atualiza as colunas
    entity.setAuthor(book.getAuthor());
    entity.setLaunch_date(book.getLaunch_date());
    entity.setPrice(book.getPrice());
    entity.setTitle(book.getTitle());

    var dto = parseObjetc(entity, BookDTO.class); // converte para DTO de novo

    addHatoasLinks(dto);

    return dto;
  }

  public void delete(Long id){
    logger.info("Deleting a person");

    Book entity = repository.findById(id).orElseThrow(
      () -> new  ResourceNotFoundExcpetion("No records found for this id")
    );
    

   repository.delete(entity); 
  }

  public void addHatoasLinks(BookDTO book){

    book.add(linkTo(methodOn(BookController.class).findByID(book.getId())).withSelfRel().withType("GET"));

    book.add(linkTo(methodOn(BookController.class).findAll()).withRel("FindALL").withType("GET"));

    book.add(linkTo(methodOn(BookController.class).create(book)).withRel("create").withType("POST"));

    book.add(linkTo(methodOn(BookController.class).update(book)).withRel("update").withType("PUT"));

    book.add(linkTo(methodOn(BookController.class).delete(book.getId())).withRel("Delete").withType("DELETE"));
  }

  public Object findById(long l) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'findById'");
  }
}
