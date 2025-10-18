package https.github.com.FrancoBorba.file.exporter.impl;

import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import https.github.com.FrancoBorba.dataDTO.PersonDTO;
import https.github.com.FrancoBorba.file.exporter.contract.FileExporter;

@Component
public class CSVExporter implements FileExporter {

  @Override
  public Resource exportFile(List<PersonDTO> people) throws Exception {
  ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

   OutputStreamWriter writer = new OutputStreamWriter(outputStream , StandardCharsets.UTF_8);

   CSVFormat csvFormat = CSVFormat.Builder.create()
   .setHeader("ID" , "First Name" , "Last Name" , "Address", "Gender", "Enabled")
   .setSkipHeaderRecord(false)
   .build();

   try (CSVPrinter printer = new CSVPrinter(writer , csvFormat)) {

      for(PersonDTO person : people){
        printer.printRecord(
          person.getId(),
          person.getFirstName(),
          person.getLastName(),
          person.getAddress(),
          person.getGender(),
          person.getEnabled()
          );
      }
    
   } catch (Exception e) {
    // TODO: handle exception
   }
    return new ByteArrayResource(outputStream.toByteArray());
  }

  @Override
  public Resource exportPerson(PersonDTO person) throws Exception {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'exportPerson'");
  }
  
}
