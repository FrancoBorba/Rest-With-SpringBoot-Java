package https.github.com.FrancoBorba.mapper;

import java.util.ArrayList;
import java.util.List;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;

public class ObjectMapper {
  

  private static Mapper mapper = DozerBeanMapperBuilder.buildDefault(); // mapeia entidade -> DTO
                                                                        //  e     DTO -> entidade

  public static <O,D> D parseObjetc(O origin , Class<D> destination){
    return mapper.map(origin, destination);
  }

  public static <O,D> List<D> parseListObjetc(List<O> origin , Class<D> destination){

    List<D> destinationObjects = new ArrayList<D>();
    for(Object o : origin){
      destinationObjects.add(mapper.map(o,destination));
    }
    
    return destinationObjects;
  }
}
