package https.github.com.FrancoBorba.util;

import https.github.com.FrancoBorba.exception.UnsupportedMathOpperationExcpetion;

public class NumberConverter {
            // converto o numero para double           
     public static Double convertToDouble(String strNumber) {
               if(strNumber == null || strNumber.isEmpty()){
               throw new UnsupportedMathOpperationExcpetion("Please set a numeric value!");
        }
        String number = strNumber.replace(",", "."); // converte todas as virgulas por ponto
         
        return Double.parseDouble(number);
          }



      // metodo para verificar se eh um numero 
      public static boolean isNumeric(String strNumber) {
          if(strNumber == null || strNumber.isEmpty()){
                return false;
          }
          String number = strNumber.replace(",", "."); // converte todas as virgulas por ponto
          
          // regex para aceitar numeros negativos e positivos e separador ponto 
         
           return number.matches("[-+]?[0-9]*\\.?[0-9]+"); // retorna o resultado da regex
      }
}
