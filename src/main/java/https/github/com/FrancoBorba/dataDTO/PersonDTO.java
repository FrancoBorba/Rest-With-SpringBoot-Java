package https.github.com.FrancoBorba.dataDTO;

import java.io.Serializable;
/*
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
 */

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import https.github.com.FrancoBorba.serializer.GenderSerializer;


//@JsonPropertyOrder({"id" , "address" ,"first_name" , "last_name" ,  "gender"})
/*A anotação @JsonPropertyOrder é usada na serialização de objetos JSON para definir a ordem dos campos no JSON gerado. No seu caso: */
public class PersonDTO extends RepresentationModel<PersonDTO> implements Serializable {
  
  private static final long serialVersionUID = 1L;

  public PersonDTO(){

  }


  private Long id;
 // @JsonProperty("first_name") // permite mudar o json
  private String firstName;
  //@JsonProperty("last_name") // permite mudar o json
  @JsonInclude(JsonInclude.Include.NON_NULL) // só é renderizado se for diferente de null
  private String lastName;


 




  private String address;
 // @JsonIgnore // omite o atributo do json
 @JsonSerialize(using = GenderSerializer.class) // Serializer criado para gender
  private String gender;

  public Long getId() {
    return id;
  }
  public void setId(Long id) {
    this.id = id;
  }
  public String getFirstName() {
    return firstName;
  }
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }
  public String getLastName() {
    return lastName;
  }
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }
  public String getAddress() {
    return address;
  }
  public void setAddress(String address) {
    this.address = address;
  }
  public String getGender() {
    return gender;
  }
  public void setGender(String gender) {
    this.gender = gender;
  }




  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
    result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
    result = prime * result + ((address == null) ? 0 : address.hashCode());
    result = prime * result + ((gender == null) ? 0 : gender.hashCode());
    return result;
  }


 
  
 
}
