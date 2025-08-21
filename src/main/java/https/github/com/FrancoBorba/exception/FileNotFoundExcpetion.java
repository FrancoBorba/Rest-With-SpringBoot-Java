package https.github.com.FrancoBorba.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND) // status code 

public class FileNotFoundExcpetion extends RuntimeException {

  public FileNotFoundExcpetion(String message){
    super(message);
  }

  public FileNotFoundExcpetion(String message , Throwable cause){
    super(message , cause);
  }
}
