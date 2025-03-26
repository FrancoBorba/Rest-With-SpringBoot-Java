package https.github.com.FrancoBorba.controllerr;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api/test/v1")
public class TesteLogController {

  private Logger logger = LoggerFactory.getLogger(TesteLogController.class.getName());

  @GetMapping("/teste")
  public String testeLog(){
    logger.debug("This is an debug log");
    logger.info("This is an info log");
    logger.warn("This is a warn log");
    logger.error("This is a error log");
    return "Logs generated successfully";
  }
  
}
