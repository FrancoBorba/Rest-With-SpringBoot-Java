package https.github.com.FrancoBorba.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND) // status code 

public class ResourceNotFoundExcpetion extends RuntimeException {



  public ResourceNotFoundExcpetion(String message){
    super(message);
  }
}
