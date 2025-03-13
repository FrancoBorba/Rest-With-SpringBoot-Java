package https.github.com.FrancoBorba.mapper;

import java.util.ArrayList;
import java.util.List;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;

//Essa classe é um mapeador genérico que converte objetos de um tipo para outro, usando a biblioteca Dozer (dozermapper). O objetivo principal dela é transformar entidades em DTOs e vice-versa.
public class ObjectMapper {
  

  private static Mapper mapper = DozerBeanMapperBuilder.buildDefault(); // mapeia entidade -> DTO
                                                                        //  e     DTO -> entidade

  // Converte um objeto generico origin para o tipo destination
  // o destino e a origem sao objetos genericos
  public static <O,D> D parseObjetc(O origin , Class<D> destination){
    return mapper.map(origin, destination);
  }

  /*
  Recebe uma lista de objetos (origin) e um tipo de destino (destination).
Cria uma nova lista (destinationObjects) onde serão armazenados os objetos convertidos.
Percorre a lista original, convertendo cada objeto individualmente usando mapper.map(). 
*/
  public static <O,D> List<D> parseListObjetc(List<O> origin , Class<D> destination){

    List<D> destinationObjects = new ArrayList<D>();
    for(Object o : origin){
      destinationObjects.add(mapper.map(o,destination));
    }
    
    return destinationObjects;
  }
}
