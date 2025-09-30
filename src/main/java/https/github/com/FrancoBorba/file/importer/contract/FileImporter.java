package https.github.com.FrancoBorba.file.importer.contract;

import java.io.InputStream;
import java.util.List;

import https.github.com.FrancoBorba.dataDTO.PersonDTO;

public interface FileImporter {
  
  List<PersonDTO> importFile(InputStream inputStream) throws Exception;
}
