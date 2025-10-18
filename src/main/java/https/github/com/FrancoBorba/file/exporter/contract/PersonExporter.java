package https.github.com.FrancoBorba.file.exporter.contract;
import org.springframework.core.io.Resource;

import https.github.com.FrancoBorba.dataDTO.PersonDTO;

import java.util.List;
public interface PersonExporter {

    Resource exportPeople(List<PersonDTO> people) throws Exception;
    Resource exportPerson(PersonDTO person) throws Exception;
}