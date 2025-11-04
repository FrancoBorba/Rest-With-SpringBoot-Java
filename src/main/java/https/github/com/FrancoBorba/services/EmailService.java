package https.github.com.FrancoBorba.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import https.github.com.FrancoBorba.config.EmailConfig;
import https.github.com.FrancoBorba.dataDTO.request.EmailRequestDTO;
import https.github.com.FrancoBorba.mail.EmailSender;

@Service
public class EmailService {

    
    @Autowired
    private EmailSender emailSender;

    @Autowired
    private EmailConfig emailConfig;


    public void sendSimpleEmail(EmailRequestDTO emailRequest){

        emailSender
        .To(emailRequest.getTo())
        .withSubject(emailRequest.getSubject())
        .withBody(emailRequest.getBody())
        .send(emailConfig);


    }

    
}