package https.github.com.FrancoBorba.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class FIleStorageException extends RuntimeException {
  
  public FIleStorageException(String mensagem){
    super(mensagem);
  }

  public FIleStorageException(String mensagem, Throwable cause){
    super(mensagem, cause);
  }
}
