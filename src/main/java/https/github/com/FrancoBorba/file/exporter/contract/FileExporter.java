package https.github.com.FrancoBorba.file.exporter.contract;

import java.util.List;

import org.springframework.core.io.Resource;

import https.github.com.FrancoBorba.dataDTO.PersonDTO;

public interface FileExporter {
  
  Resource exportFile(List<PersonDTO> people) throws Exception;
}
