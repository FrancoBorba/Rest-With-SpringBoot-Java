package https.github.com.FrancoBorba.file.exporter.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import https.github.com.FrancoBorba.file.exporter.MediaTypes;
import https.github.com.FrancoBorba.file.exporter.contract.FileExporter;
import https.github.com.FrancoBorba.file.exporter.impl.CSVExporter;
import https.github.com.FrancoBorba.file.exporter.impl.PdfExporter;
import https.github.com.FrancoBorba.file.exporter.impl.XlsxExporter;
import https.github.com.FrancoBorba.file.importer.factory.FileImporterFactory;

@Component
public class FileExporterFactory {
    private Logger logger = LoggerFactory.getLogger(FileImporterFactory.class);

  @Autowired
  private ApplicationContext context;


  public FileExporter getExporter(String acceptHeader) throws Exception{
      

      if(acceptHeader.equalsIgnoreCase(MediaTypes.APPLICATION_CSV_VALUE)){
         logger.info("Exporting a file from type: csv"  );
        return context.getBean(CSVExporter.class);
       // return new CSVImporter();
      } else if(acceptHeader.equalsIgnoreCase(MediaTypes.APPLICATION_XLSX_VALUE)){
         logger.info("Exporting a file from type: xlsx"  );
        return  context.getBean(XlsxExporter.class);
       // return new XlsxImporter();
      }else if( acceptHeader.equalsIgnoreCase(MediaTypes.APPLICATION_PDF_VALUE)){
         logger.info("Exporting a file from type: pdf"  );
        return (FileExporter) context.getBean(PdfExporter.class);
      }
       else{
        throw new Exception("Unsuportted file");
      }
      
  }
}
