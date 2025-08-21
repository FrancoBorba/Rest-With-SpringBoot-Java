package https.github.com.FrancoBorba.controllerr;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import https.github.com.FrancoBorba.controllerr.docs.FileControllerDocs;
import https.github.com.FrancoBorba.dataDTO.UploadFileResponseDTO;
import https.github.com.FrancoBorba.services.FileStoragaService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/file/v1")
public class FileController implements FileControllerDocs {

  @Autowired
  private FileStoragaService service;

  private static final Logger logger = LoggerFactory.getLogger(FileController.class);

  @Override
  @PostMapping(value = "/uploadFile" , consumes = "multipart/form-data")
  public UploadFileResponseDTO uploadFile(@RequestParam("file") MultipartFile file) {
    String fileName = service.storeFile(file);

    //http://localhost:8080/api/file/v1/downloadFile/fileName
    var fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                          .path("/api/file/v1/downloadFile/")
                          .path(fileName)
                          .toUriString();

    return new UploadFileResponseDTO(fileName , fileDownloadUri , file.getContentType() , file.getSize());
  }

  @Override
  @PostMapping(value = "/uploadMultipleFiles" , consumes = "multipart/form-data")
  public List<UploadFileResponseDTO> uploadMultipleFile(@RequestParam("files")MultipartFile[] files) {

    return Arrays.asList(files)
    .stream()
    .map(file -> uploadFile(file)).collect(Collectors.toList());
  }

  @Override
  @GetMapping(value = "/downloadFile/{filename:.+}")
  public ResponseEntity<Resource> downloadFile(@PathVariable("filename")String fileName, HttpServletRequest request) {
    Resource resource = service.loadFileAsResource(fileName);
    String contentType = null;

    try {
      contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
      
    } catch (Exception e) {
     logger.error("Could not determine file type");
    }

    if (contentType == null) {
      contentType = "application/octet-stream";
    }
     
     return ResponseEntity.ok()
     .contentType(MediaType.parseMediaType(contentType))
     .header(org.springframework.http.HttpHeaders.CONTENT_DISPOSITION , "attachment; filename=\""+ resource.getFilename() + "\"")
     .body(resource);
     
  }

    
}
