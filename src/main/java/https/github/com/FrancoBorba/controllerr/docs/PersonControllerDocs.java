package https.github.com.FrancoBorba.controllerr.docs;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import https.github.com.FrancoBorba.dataDTO.PersonDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

public interface PersonControllerDocs {

  @Operation(
    summary = "Find by id" ,
    description = "Finds a especification person by your ID",
    tags = {"People"},
    responses = {
      @ApiResponse(description = "Success" , responseCode = "200" ,
       content = @Content(
        schema = @Schema(implementation = PersonDTO.class)
       )),
      @ApiResponse(description = "No content" , responseCode = "204" , content = @Content),
      @ApiResponse(description = "Bad Request" , responseCode = "400" , content = @Content),
      @ApiResponse(description = "Unautorizhed" , responseCode = "401" , content = @Content),
      @ApiResponse(description = "Not found" , responseCode = "404" , content = @Content),
      @ApiResponse(description = "Internal Server Erros" , responseCode = "500" , content = @Content),
    }
  ) 
  PersonDTO findByID( // end point GET
      Long id);


  @Operation(
  summary = "Find all people",
  description = "Find all people" ,
  tags = {"People"},
  responses = {
    @ApiResponse(description = "Success" , responseCode = "200" ,
     content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
     array = @ArraySchema(schema = @Schema(implementation = PersonDTO.class)) )}),
    @ApiResponse(description = "No content" , responseCode = "204" , content = @Content),
    @ApiResponse(description = "Bad request" , responseCode = "400" , content = @Content),
    @ApiResponse(description = "Unautorizhed" , responseCode = "401" , content = @Content),
    @ApiResponse(description = "Not found" , responseCode = "404" , content = @Content),
    @ApiResponse(description = "Internal Server Erro" , responseCode = "500" , content = @Content)
  })
  List<PersonDTO> findAll();

 @Operation(
    summary = "Adds a new Person" ,
    description = "Adding a Person ",
    tags = {"People"},
    responses = {
      @ApiResponse(description = "Success" , responseCode = "200" ,
       content = @Content(
        schema = @Schema(implementation = PersonDTO.class)
       )),
      @ApiResponse(description = "No content" , responseCode = "204" , content = @Content),
      @ApiResponse(description = "Bad Request" , responseCode = "400" , content = @Content),
      @ApiResponse(description = "Unautorizhed" , responseCode = "401" , content = @Content),
      @ApiResponse(description = "Not found" , responseCode = "404" , content = @Content),
      @ApiResponse(description = "Internal Server Erros" , responseCode = "500" , content = @Content),
    }
  ) 
  PersonDTO create(PersonDTO person);

     @Operation(
    summary = "Update a person information" ,
    description = "Update a person information ",
    tags = {"People"},
    responses = {
      @ApiResponse(description = "Success" , responseCode = "200" ,
       content = @Content(
        schema = @Schema(implementation = PersonDTO.class)
       )),
      @ApiResponse(description = "No content" , responseCode = "204" , content = @Content),
      @ApiResponse(description = "Bad Request" , responseCode = "400" , content = @Content),
      @ApiResponse(description = "Unautorizhed" , responseCode = "401" , content = @Content),
      @ApiResponse(description = "Not found" , responseCode = "404" , content = @Content),
      @ApiResponse(description = "Internal Server Erros" , responseCode = "500" , content = @Content),
    }
  ) 
  PersonDTO put(PersonDTO person);

   @Operation(
    summary = "Delete a person" ,
    description = "Delete a person ",
    tags = {"People"},
    responses = {
      @ApiResponse(description = "Success" , responseCode = "200" ,
       content = @Content(
        schema = @Schema(implementation = PersonDTO.class)
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