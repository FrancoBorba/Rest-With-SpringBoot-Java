package https.github.com.FrancoBorba.controllerr.docs;

import java.util.List;

import org.springframework.core.io.Resource; 
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import https.github.com.FrancoBorba.dataDTO.UploadFileResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;

@Tag(name = "File Endpoint", description = "Endpoints for managing file upload and download")
public interface FileControllerDocs {

@Operation(
    summary = "Upload a file",
    description = "Uploads a single file to the server.",
    responses = {
        @ApiResponse(
            description = "Success",
            responseCode = "200",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = UploadFileResponseDTO.class)
            )
        ),
        @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
        @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
        @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
    }
)
// A biblioteca vai olhar para "MultipartFile file" e criar o botão de upload automaticamente.
UploadFileResponseDTO uploadFile(MultipartFile file);

     @Operation(
        summary = "Upload multiple files",
        description = "Uploads multiple files to the server",
        responses = {
            @ApiResponse(
                description = "Success",
                responseCode = "200",
                // A resposta para múltiplos uploads deve ser uma LISTA do DTO.
                // Usamos ArraySchema para descrever isso.
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(type = "array", implementation = UploadFileResponseDTO.class)
                )
            ),
        @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
        @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
        @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
    }
    )
    List<UploadFileResponseDTO> uploadMultipleFile(MultipartFile[] files);

    @Operation(
        summary = "Download a file",
        description = "Downloads a file by its name",
        responses = {
            @ApiResponse(description = "Success", responseCode = "200",
                content = @Content(mediaType = "application/octet-stream",
                schema = @Schema(type = "string", format = "binary"))
            ),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
        }
    )
    ResponseEntity<Resource> downloadFile(String fileName, HttpServletRequest request);
}