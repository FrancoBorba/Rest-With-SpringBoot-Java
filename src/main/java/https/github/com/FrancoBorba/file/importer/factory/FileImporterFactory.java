package https.github.com.FrancoBorba.file.importer.factory;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import https.github.com.FrancoBorba.file.importer.contract.FileImporter;
import https.github.com.FrancoBorba.file.importer.impl.CSVImporter;
import https.github.com.FrancoBorba.file.importer.impl.XlsxImporter;

@Component // Permit the class be injected
public class FileImporterFactory {
  
  private Logger logger = LoggerFactory.getLogger(FileImporterFactory.class);

  @Autowired
  private ApplicationContext context;


  public FileImporter getImporter(String fileName) throws Exception{


      if(fileName.endsWith(".csv")){
        return context.getBean(CSVImporter.class);
       // return new CSVImporter();
      } else if(fileName.endsWith(".xsls")){
        return  context.getBean(XlsxImporter.class);
       // return new XlsxImporter();
      } else{
        throw new Exception("Unsuportted file");
      }
      
  }
}       
