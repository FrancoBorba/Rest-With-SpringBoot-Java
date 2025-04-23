package https.github.com.FrancoBorba.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST) // status code 

public class RequiredObjectIsNullExcpetion extends RuntimeException {

    public   RequiredObjectIsNullExcpetion(){
     super("It is not allowed to persist a null object");
    }
}
