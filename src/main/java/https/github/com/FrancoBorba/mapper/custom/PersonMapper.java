package https.github.com.FrancoBorba.mapper.custom;

import java.util.Date;

import org.springframework.stereotype.Service;

import https.github.com.FrancoBorba.dataDTO.v2.PersonDTOV2;
import https.github.com.FrancoBorba.model.Person;

@Service // permite a injenção
public class PersonMapper {
  

  public PersonDTOV2 convertEntityToDTO(Person person){
    PersonDTOV2 dto = new PersonDTOV2();
      dto.setId(person.getId());
       dto.setFirstName(person.getFirstName());
       dto.setLastName(person.getLastName());
       dto.setBirthDay(new Date()); // a entity não possui birthDay
       dto.setAddress(person.getAddress());
       dto.setGender(person.getGender());

        return dto;
  }

  
  public Person convertDTOToEntity(Person person){
    Person entity = new Person();
      entity.setId(person.getId());
       entity.setFirstName(person.getFirstName());
       entity.setLastName(person.getLastName());
       entity.setAddress(person.getAddress());
       entity.setGender(person.getGender());

        return entity;
  }
}
