package https.github.com.FrancoBorba.services;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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


    public void setEmailWithAttachment(String emailRequestJson, MultipartFile attachment) {
        File tempFile = null;
        try {
            EmailRequestDTO emailRequest = new ObjectMapper().readValue(emailRequestJson, EmailRequestDTO.class);
            tempFile = File.createTempFile("attachment", attachment.getOriginalFilename());
            attachment.transferTo(tempFile);

            emailSender
                .To(emailRequest.getTo())
                .withSubject(emailRequest.getSubject())
                .withBody(emailRequest.getSubject())
                .attach(tempFile.getAbsolutePath())
                .send(emailConfig);

        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error parsing email request JSON!", e);
        } catch (IOException e) {
            throw new RuntimeException("Error processing the attachment!", e);
        } finally {
            if (tempFile != null && tempFile.exists()) tempFile.delete();
        }

    }
}