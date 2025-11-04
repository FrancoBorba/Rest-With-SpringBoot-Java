package https.github.com.FrancoBorba.controllerr.docs;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import https.github.com.FrancoBorba.dataDTO.request.EmailRequestDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Email" , description = "Controller to send emails")
public interface EmailControllerDocs {
    
        @Operation(
        summary = "Send an email" ,
        description = "sends a email by providing details , subject and body",
        tags = {"Email"},
        responses = {
        @ApiResponse(description = "Success" , responseCode = "200" ,content = @Content),
        @ApiResponse(description = "Bad Request" , responseCode = "400" , content = @Content),
        @ApiResponse(description = "Unautorizhed" , responseCode = "401" , content = @Content),
        @ApiResponse(description = "Internal Server Erros" , responseCode = "500" , content = @Content),
        }
    ) 
    ResponseEntity<String> sendEmail(EmailRequestDTO emailRequestDTO);

        @Operation(
        summary = "Send an email with attachment" ,
        description = "sends a email with attachment by providing details , subject and body",
        tags = {"Email"},
        responses = {
        @ApiResponse(description = "Success" , responseCode = "200" ,content = @Content),
        @ApiResponse(description = "Bad Request" , responseCode = "400" , content = @Content),
        @ApiResponse(description = "Unautorizhed" , responseCode = "401" , content = @Content),
        @ApiResponse(description = "Internal Server Erros" , responseCode = "500" , content = @Content),
        }
    ) 
    ResponseEntity<String> sendEmailWithAttachment(String emailRequestJson , MultipartFile multipartFile);

}
