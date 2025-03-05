package https.github.com.FrancoBorba.controllerr;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import https.github.com.FrancoBorba.model.Greeting;

@RestController // Para ser reconhecido como um controller rest , permite que ela manipule requisicoes http
public class GreetingController {
  
  private static final String template = "Hello, %s! "; // Define a mensagem de saudacao
  private final AtomicLong counter = new AtomicLong(); // criacao do id (evita concorrencia)

    // http://localhost:8080/greeting?name=Franco
    // Query Parameters
  @RequestMapping("/greeting") // para ser reconhecido como um metodo exposto via http
  public Greeting greeting(
    @RequestParam(value = "name" , defaultValue = "World") // captura os parametros da url
    String name){
    return new Greeting(counter.incrementAndGet(), String.format(template, name));
  }
}
