package https.github.com.FrancoBorba.controllerr;



import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpHeaders;
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
import org.springframework.web.multipart.MultipartFile;

import https.github.com.FrancoBorba.controllerr.docs.PersonControllerDocs;
import https.github.com.FrancoBorba.dataDTO.PersonDTO;
import https.github.com.FrancoBorba.file.exporter.MediaTypes;
import https.github.com.FrancoBorba.services.PersonServices;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;

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

      @Override
  @GetMapping(  
    value = "/findPeopleByName/{firstName}",
  produces = { 
      MediaType.APPLICATION_JSON_VALUE ,
      MediaType.APPLICATION_XML_VALUE ,
      MediaType.APPLICATION_YAML_VALUE})
 
  public ResponseEntity<PagedModel<EntityModel<PersonDTO>>> findByName(
    @PathVariable("firstName") String firstName,
    @RequestParam(value = "page" , defaultValue = "0") Integer page ,
    @RequestParam(value = "size" , defaultValue = "12") Integer size,
    @RequestParam(value = "direction" , defaultValue = "asc") String direction
  ){ // end point GET
    
      var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
      Pageable pageable = PageRequest.of(page, size , Sort.by(sortDirection,"firstName"));
      return ResponseEntity.ok(service.findByName(firstName,pageable)) ;
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

  @Override
  @PostMapping(
    value = {"/massCreation"} ,
      produces = { 
      MediaType.APPLICATION_JSON_VALUE ,
      MediaType.APPLICATION_XML_VALUE ,
      MediaType.APPLICATION_YAML_VALUE}
  )
  public List<PersonDTO> massCreation(@RequestParam("file") MultipartFile file) {
    try {
      return service.creationPeopleFromPlanilha(file);
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return null;
  }

 
 @GetMapping(value = "/exportPage", produces = {
            MediaTypes.APPLICATION_CSV_VALUE,
            MediaTypes.APPLICATION_XLSX_VALUE,
            MediaTypes.APPLICATION_PDF_VALUE
            })
    @Override
    public ResponseEntity<Resource> exportPage(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "12") Integer size,
            @RequestParam(value = "direction", defaultValue = "asc") String direction,
            HttpServletRequest request) {

        var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, "firstName"));

        // Recupera o Accept do header
        String acceptHeader = request.getHeader(HttpHeaders.ACCEPT);

        // Gera o arquivo na camada de serviço
        Resource file = service.exportPage(pageable, acceptHeader);

        Map<String , String> extensionMap = Map.of(
          MediaTypes.APPLICATION_CSV_VALUE , "csv",
           MediaTypes.APPLICATION_XLSX_VALUE , "xlsx",
            MediaTypes.APPLICATION_PDF_VALUE , "pdf"
        );

        // Define a extensão do arquivo com base no tipo de conteúdo
        String fileExtension = extensionMap.getOrDefault(acceptHeader, "");



        // Determina o tipo de conteúdo baseado no Accept Header
        String contentType = acceptHeader != null ? acceptHeader : "application/octet-stream";

        // Nome do arquivo padrão
        String fileName = "people_exported." + fileExtension;

        // Configura a resposta HTTP com o arquivo como anexo
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + fileName + "\"")
                .body(file);
    }

 @Override
 @GetMapping(
  value = {"/export/{id}"} ,
  produces = {
    MediaTypes.APPLICATION_PDF_VALUE
  }
 )
 public ResponseEntity<Resource> export(@PathVariable("id") Long id, HttpServletRequest request)  {
  
  String acceptHeader = request.getHeader(HttpHeaders.ACCEPT);

  Resource file;
  try {
    file = service.exportPerson(id, acceptHeader);
        return ResponseEntity.ok()
    .contentType(MediaType.parseMediaType(acceptHeader))
    .header(
      HttpHeaders.CONTENT_DISPOSITION ,
      "attachment; filename=peson.pdf"
    ).body(file);
  } catch (Exception e) {
   
    e.printStackTrace();
  }

return null;

 }




  
}
