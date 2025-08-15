package https.github.com.FrancoBorba.controllerr.docs;



import org.springframework.data.web.PagedModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import https.github.com.FrancoBorba.dataDTO.BookDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

public interface BookControllerDocs {

   @Operation(
    summary = "Find by id" ,
    description = "Finds a especification book by your ID",
    tags = {"Books"},
    responses = {
      @ApiResponse(description = "Success" , responseCode = "200" ,
       content = @Content(
        schema = @Schema(implementation = BookDTO.class)
       )),
      @ApiResponse(description = "No content" , responseCode = "204" , content = @Content),
      @ApiResponse(description = "Bad Request" , responseCode = "400" , content = @Content),
      @ApiResponse(description = "Unautorizhed" , responseCode = "401" , content = @Content),
      @ApiResponse(description = "Not found" , responseCode = "404" , content = @Content),
      @ApiResponse(description = "Internal Server Erros" , responseCode = "500" , content = @Content),
    }
  ) 
  BookDTO findByID(Long id);

    @Operation(
  summary = "Find all books",
  description = "Find all books" ,
  tags = {"Books"},
  responses = {
    @ApiResponse(description = "Success" , responseCode = "200" ,
     content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
     array = @ArraySchema(schema = @Schema(implementation = BookDTO.class)) )}),
    @ApiResponse(description = "No content" , responseCode = "204" , content = @Content),
    @ApiResponse(description = "Bad request" , responseCode = "400" , content = @Content),
    @ApiResponse(description = "Unautorizhed" , responseCode = "401" , content = @Content),
    @ApiResponse(description = "Not found" , responseCode = "404" , content = @Content),
    @ApiResponse(description = "Internal Server Erro" , responseCode = "500" , content = @Content)
  })
  ResponseEntity<org.springframework.hateoas.PagedModel<EntityModel<BookDTO>>> findAll(
    @RequestParam(value = "size" , defaultValue = "10") Integer size,
    @RequestParam(value = "page" , defaultValue = "0") Integer page,
    @RequestParam(value = "direction" , defaultValue = "asc") String direction
  );


  @Operation(
    summary = "Adds a new Book" ,
    description = "Adding a Book ",
    tags = {"Books"},
    responses = {
      @ApiResponse(description = "Success" , responseCode = "200" ,
       content = @Content(
        schema = @Schema(implementation = BookDTO.class)
       )),
      @ApiResponse(description = "No content" , responseCode = "204" , content = @Content),
      @ApiResponse(description = "Bad Request" , responseCode = "400" , content = @Content),
      @ApiResponse(description = "Unautorizhed" , responseCode = "401" , content = @Content),
      @ApiResponse(description = "Not found" , responseCode = "404" , content = @Content),
      @ApiResponse(description = "Internal Server Erros" , responseCode = "500" , content = @Content),
    }
  ) 
  BookDTO create(BookDTO book);

     @Operation(
    summary = "Update a book information" ,
    description = "Update a book information ",
    tags = {"People"},
    responses = {
      @ApiResponse(description = "Success" , responseCode = "200" ,
       content = @Content(
        schema = @Schema(implementation = BookDTO.class)
       )),
      @ApiResponse(description = "No content" , responseCode = "204" , content = @Content),
      @ApiResponse(description = "Bad Request" , responseCode = "400" , content = @Content),
      @ApiResponse(description = "Unautorizhed" , responseCode = "401" , content = @Content),
      @ApiResponse(description = "Not found" , responseCode = "404" , content = @Content),
      @ApiResponse(description = "Internal Server Erros" , responseCode = "500" , content = @Content),
    }
  ) 
  BookDTO update(BookDTO book);


  @Operation(
    summary = "Delete a book" ,
    description = "Delete a book ",
    tags = {"Books"},
    responses = {
      @ApiResponse(description = "Success" , responseCode = "200" ,
       content = @Content(
        schema = @Schema(implementation = BookDTO.class)
       )),
      @ApiResponse(description = "No content" , responseCode = "204" , content = @Content),
      @ApiResponse(description = "Bad Request" , responseCode = "400" , content = @Content),
      @ApiResponse(description = "Unautorizhed" , responseCode = "401" , content = @Content),
      @ApiResponse(description = "Not found" , responseCode = "404" , content = @Content),
      @ApiResponse(description = "Internal Server Erros" , responseCode = "500" , content = @Content),
    }
  ) 
  ResponseEntity<?> delete(Long id);

}