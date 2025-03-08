package https.github.com.FrancoBorba.exception.handler;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import https.github.com.FrancoBorba.exception.ExceptionResponse;
import https.github.com.FrancoBorba.exception.ResourceNotFoundExcpetion;




@RestController
@ControllerAdvice // sempre que precisar concentrar um tratamento que seria espalhado em todos os controllers
public class CustomEntityResponseHandler extends ResponseEntityExceptionHandler{
  
  @ExceptionHandler(Exception.class)
  public final ResponseEntity<ExceptionResponse> handleAllExceptions (Exception ex , WebRequest request){
    ExceptionResponse response = new ExceptionResponse(new Date(),
     ex.getMessage(), 
     request.getDescription(false));

     return new ResponseEntity<>(response , HttpStatus.INTERNAL_SERVER_ERROR);
  }

   @ExceptionHandler(ResourceNotFoundExcpetion.class)
  public final ResponseEntity<ExceptionResponse> handleNotFoundExceptions (Exception ex , WebRequest request){
    ExceptionResponse response = new ExceptionResponse(new Date(),
     ex.getMessage(), 
     request.getDescription(false));

     return new ResponseEntity<>(response , HttpStatus.NOT_FOUND);
  }
}
