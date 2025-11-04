package https.github.com.FrancoBorba.dataDTO.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public class EmailRequestDTO {
    
    @NotNull
    @Schema(name = "to" , description = "Who will recive the email" , example = "adryellenalves09@gmail.com")
    private String to;

    @NotNull
    @Schema(name = "subject" , description = "Subject of email" , example = "I am testing my email sender")
    private String subject;

    @NotNull
    @Schema(name = "body" , description = "Subject of email" , example = "Hiiiiiii , how are you")
    private String body;

    
    public EmailRequestDTO() {
    }
    public String getTo() {
        return to;
    }
    public void setTo(String to) {
        this.to = to;
    }
    public String getSubject() {
        return subject;
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }
    public String getBody() {
        return body;
    }
    public void setBody(String body) {
        this.body = body;
    }
}
