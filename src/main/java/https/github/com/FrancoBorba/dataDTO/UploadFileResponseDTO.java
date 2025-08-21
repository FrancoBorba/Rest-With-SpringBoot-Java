package https.github.com.FrancoBorba.dataDTO;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;

public class UploadFileResponseDTO implements Serializable {
  
   
  private static final long serialVersionUID = 1L;

  @Schema(description = "Nome do arquivo que foi salvo.", example = "minha-foto.jpg")
  private String fileName;

  @Schema(description = "URL completa para realizar o download do arquivo.", example = "http://localhost:8080/api/file/v1/downloadFile/minha-foto.jpg")
  private String fileDownloadUri;

  @Schema(description = "O Content Type (MIME type) do arquivo.", example = "image/jpeg")
  private String fileType;

   @Schema(description = "Tamanho do arquivo em bytes.", example = "204800")
  private long size;

  
  public UploadFileResponseDTO() {
  }


  public UploadFileResponseDTO(String fileName, String fileDownloadUri, String fileTType, long size) {
    this.fileName = fileName;
    this.fileDownloadUri = fileDownloadUri;
    this.fileType = fileTType;
    this.size = size;
  }


  public static long getSerialversionuid() {
    return serialVersionUID;
  }


  public String getFileName() {
    return fileName;
  }


  public void setFileName(String fileName) {
    this.fileName = fileName;
  }


  public String getFileDownloadUri() {
    return fileDownloadUri;
  }


  public void setFileDownloadUri(String fileDownloadUri) {
    this.fileDownloadUri = fileDownloadUri;
  }


  public String getFileTType() {
    return fileType;
  }


  public void setFileTType(String fileTType) {
    this.fileType = fileTType;
  }


  public long getSize() {
    return size;
  }


  public void setSize(long size) {
    this.size = size;
  }
}
