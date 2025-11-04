package https.github.com.FrancoBorba.controllerr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import https.github.com.FrancoBorba.controllerr.docs.EmailControllerDocs;
import https.github.com.FrancoBorba.dataDTO.request.EmailRequestDTO;
import https.github.com.FrancoBorba.services.EmailService;

@RestController
@RequestMapping("/api/email/v1")
public class EmailController implements EmailControllerDocs {

    @Autowired
    private EmailService emailService;

    @Override
    @PostMapping("/send")
    public ResponseEntity<String> sendEmail(@RequestBody EmailRequestDTO emailRequestDTO) {
        emailService.sendSimpleEmail(emailRequestDTO);
       return new ResponseEntity<>("Email send with success" , HttpStatus.OK);
    }

       @PostMapping(value = "/withAttachment", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Override
    public ResponseEntity<String> sendEmailWithAttachment(
            @RequestParam("emailRequest") String emailRequest,
            @RequestParam("attachment") MultipartFile attachment) {
        emailService.setEmailWithAttachment(emailRequest, attachment);
        return new ResponseEntity<>("e-Mail with attachment sent successfully!", HttpStatus.OK);
    }
    
}
