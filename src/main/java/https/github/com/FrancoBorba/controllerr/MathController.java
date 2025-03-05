package https.github.com.FrancoBorba.controllerr;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import https.github.com.FrancoBorba.exception.UnsupportedMathOpperationExcpetion;
import https.github.com.FrancoBorba.math.SimpleMath;
import https.github.com.FrancoBorba.util.NumberConverter;

@RestController
@RequestMapping("/math") // se aplica a todos os metodos da classe
public class MathController {

  SimpleMath simpleMath = new SimpleMath(); // cria o objeto para realizar as operacoes

  // http://localhost:8080/math/sum/3/5
  // Path parameters
  // Metodo de soma
  @RequestMapping("/sum/{numberOne}/{numberTwo}") // se aplica a todos os metodos da classe
    public double sum(
     @PathVariable("numberOne") String numberOne , 
      @PathVariable("numberTwo")String numberTwo) throws Exception{

        if(!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)){
                   throw new UnsupportedMathOpperationExcpetion("Please set a numeric value!");
                }
               
                return   simpleMath.sum(NumberConverter.convertToDouble(numberOne), NumberConverter.convertToDouble(numberTwo)) ;
          }


// http://localhost:8080/math/subtraction/3/5
// Metodo de Subtracao

  @RequestMapping("/subtraction/{numberOne}/{numberTwo}")
      public double subtratiction(
        @PathVariable("numberOne") String numberOne ,
        @PathVariable("numberTwo") String numberTwo) throws Exception{
          
          if(!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)){
                   throw new UnsupportedMathOpperationExcpetion("Please set a numeric value!");
          }

          return simpleMath.subtraction( NumberConverter.convertToDouble(numberOne) , NumberConverter.convertToDouble(numberTwo)); // a ordem dos fatorer altera o resultado
        }


// http://localhost:8080/math/multiplication/3/5
// Metodo de Multiplicacao
  @RequestMapping("/multiplication/{numberOne}/{numberTwo}")
      public double multiplication(
        @PathVariable("numberOne") String numberOne ,
        @PathVariable("numberTwo") String numberTwo 
      ) throws Exception {
          
          if(!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)){
                   throw new UnsupportedMathOpperationExcpetion("Please set a numeric value!");
          }

          return simpleMath.multiplication( NumberConverter.convertToDouble(numberOne),  NumberConverter.convertToDouble(numberTwo)); 
        }


  // http://localhost:8080/math/division/3/5
// Metodo de Divisao
  @RequestMapping("/division/{numberOne}/{numberTwo}")
      public double divisio(
        @PathVariable("numberOne") String numberOne ,
        @PathVariable("numberTwo") String numberTwo
      ) throws Exception{

         if(!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)){
                   throw new UnsupportedMathOpperationExcpetion("Please set a numeric value!");
          }

          if(NumberConverter.convertToDouble(numberTwo) == 0){
            throw new UnsupportedMathOpperationExcpetion("It is impossible make a division by 0");
          }
          
        
          return simpleMath.division(NumberConverter.convertToDouble(numberOne) , NumberConverter.convertToDouble(numberTwo)); 
      }

    @RequestMapping("/mean/{numberOne}/{numberTwo}")
        public double mean(
          @PathVariable("numberOne") String numberOne ,
          @PathVariable("numberTwo") String numberTwo
        ) throws Exception{

          if(!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)){
                   throw new UnsupportedMathOpperationExcpetion("Please set a numeric value!");
          }
           return simpleMath.mean(NumberConverter.convertToDouble(numberOne) , NumberConverter.convertToDouble(numberTwo)); 
        }

        
    @RequestMapping("/squareroot/{number}")
        public double squareroot(
          @PathVariable("number") String number
         ) throws Exception{

          if(!NumberConverter.isNumeric(number)){
                   throw new UnsupportedMathOpperationExcpetion("Please set a numeric value!");
          }
            
           return simpleMath.squareRoot(NumberConverter.convertToDouble(number));
        }


}
