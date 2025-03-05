package https.github.com.FrancoBorba.controllerr;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import https.github.com.FrancoBorba.exception.UnsupportedMathOpperationExcpetion;

@RestController
@RequestMapping("/math") // se aplica a todos os metodos da classe
public class MathController {

  // http://localhost:8080/math/sum/3/5
  // Path parameters
  // Metodo de soma
  @RequestMapping("/sum/{numberOne}/{numberTwo}") // se aplica a todos os metodos da classe
    public double sum(
     @PathVariable("numberOne") String numberOne , 
      @PathVariable("numberTwo")String numberTwo) throws Exception{

        if(!isNumeric(numberOne) || !isNumeric(numberTwo)){
                   throw new UnsupportedMathOpperationExcpetion("Please set a numeric value!");
                }
                return convertToDouble(numberOne) + convertToDouble(numberTwo);
          }


// http://localhost:8080/math/subtraction/3/5
// Metodo de Subtracao

  @RequestMapping("/subtraction/{numberOne}/{numberTwo}")
      public double subtratiction(
        @PathVariable("numberOne") String numberOne ,
        @PathVariable("numberTwo") String numberTwo) throws Exception{
          
          if(!isNumeric(numberOne) || !isNumeric(numberTwo)){
                   throw new UnsupportedMathOpperationExcpetion("Please set a numeric value!");
          }

          return convertToDouble(numberOne) - convertToDouble(numberTwo); // a ordem dos fatorer altera o resultado
        }


// http://localhost:8080/math/multiplication/3/5
// Metodo de Multiplicacao
  @RequestMapping("/multiplication/{numberOne}/{numberTwo}")
      public double multiplication(
        @PathVariable("numberOne") String numberOne ,
        @PathVariable("numberTwo") String numberTwo 
      ) throws Exception {
          
          if(!isNumeric(numberOne) || !isNumeric(numberTwo)){
                   throw new UnsupportedMathOpperationExcpetion("Please set a numeric value!");
          }

          return convertToDouble(numberOne) * convertToDouble(numberTwo); 
        }


  // http://localhost:8080/math/division/3/5
// Metodo de Divisao
  @RequestMapping("/division/{numberOne}/{numberTwo}")
      public double divisio(
        @PathVariable("numberOne") String numberOne ,
        @PathVariable("numberTwo") String numberTwo
      ) throws Exception{

         if(!isNumeric(numberOne) || !isNumeric(numberTwo)){
                   throw new UnsupportedMathOpperationExcpetion("Please set a numeric value!");
          }

          if(convertToDouble(numberTwo) == 0){
            throw new UnsupportedMathOpperationExcpetion("It is impossible make a division by 0");
          }
          
        
          return convertToDouble(numberOne) / convertToDouble(numberTwo); 
      }

    @RequestMapping("/mean/{numberOne}/{numberTwo}")
        public double mean(
          @PathVariable("numberOne") String numberOne ,
          @PathVariable("numberTwo") String numberTwo
        ) throws Exception{

          if(!isNumeric(numberOne) || !isNumeric(numberTwo)){
                   throw new UnsupportedMathOpperationExcpetion("Please set a numeric value!");
          }
           return (convertToDouble(numberOne) + convertToDouble(numberTwo))/2; 
        }

        
    @RequestMapping("/squareroot/{number}")
        public double squareroot(
          @PathVariable("number") String number
         ) throws Exception{

          if(!isNumeric(number)){
                   throw new UnsupportedMathOpperationExcpetion("Please set a numeric value!");
          }
            
           return Math.sqrt(convertToDouble(number));
        }






          // converto o numero para double           
     private Double convertToDouble(String strNumber) {
               if(strNumber == null || strNumber.isEmpty()){
               throw new UnsupportedMathOpperationExcpetion("Please set a numeric value!");
        }
        String number = strNumber.replace(",", "."); // converte todas as virgulas por ponto
         
        return Double.parseDouble(number);
          }








      // metodo para verificar se eh um numero 
      private boolean isNumeric(String strNumber) {
          if(strNumber == null || strNumber.isEmpty()){
                return false;
          }
          String number = strNumber.replace(",", "."); // converte todas as virgulas por ponto
          
          // regex para aceitar numeros negativos e positivos e separador ponto 
         
           return number.matches("[-+]?[0-9]*\\.?[0-9]+"); // retorna o resultado da regex
      }




  
  // http://localhost:8080/math/division/3/5


}
