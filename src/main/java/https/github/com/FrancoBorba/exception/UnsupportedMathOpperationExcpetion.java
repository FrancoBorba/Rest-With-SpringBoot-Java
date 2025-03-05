package https.github.com.FrancoBorba.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST) // status code 

public class UnsupportedMathOpperationExcpetion extends RuntimeException {



  public UnsupportedMathOpperationExcpetion(String message){
    super(message);
  }
}
