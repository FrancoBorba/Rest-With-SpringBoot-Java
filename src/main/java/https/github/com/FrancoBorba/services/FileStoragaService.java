package https.github.com.FrancoBorba.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import https.github.com.FrancoBorba.config.FileStorageConfig;
import https.github.com.FrancoBorba.exception.FIleStorageException;
import https.github.com.FrancoBorba.exception.FileNotFoundExcpetion;

@Service
public class FileStoragaService {
  
  private final Path fileStorageLocation;

   private static final Logger logger = LoggerFactory.getLogger(FileStoragaService.class);

  public FileStoragaService(FileStorageConfig fileStorageConfig ) {
    Path path = Paths.get(fileStorageConfig.getUploadDir()).toAbsolutePath().normalize(); // take the url of yml

    this.fileStorageLocation = path;

    try{
      Files.createDirectories(this.fileStorageLocation);
    }catch(Exception e){
      logger.error("Could not create the Directory where files we be storded");
      throw new FIleStorageException("Could not create the Directory where files we be storded",e);
    }
  }

  public String storeFile(MultipartFile file){
  
    String originalFilename = file.getOriginalFilename();

   
    if (originalFilename == null || originalFilename.isBlank()) {
        throw new FIleStorageException("File name cannot be null");
    }
      String fileName = StringUtils.cleanPath(originalFilename);

    try {
      if(fileName.contains("..")){
        logger.error("File name contains a invalid path sequence" + fileName);
        new FIleStorageException("File name contains a invalid path sequence" + fileName);
      }
    Path targetLocation = this.fileStorageLocation.resolve(fileName);
    Files.copy(file.getInputStream(), targetLocation , StandardCopyOption.REPLACE_EXISTING);

    return fileName;

    } catch (Exception e) {
      throw new FIleStorageException("Could not store file" + fileName + ". Please try again" , e);
    }
  }

  public Resource loadFileAsResource(String fileName){
    try {
    Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
    Resource urlResource = new UrlResource(filePath.toUri());
      if (urlResource.exists()) {
        return urlResource;
      }else{
        throw new FileNotFoundExcpetion("File not Found" + fileName);
      }
    } catch (Exception e) {
      throw new FileNotFoundExcpetion("File not Found" + fileName , e);
    }
  }
}
