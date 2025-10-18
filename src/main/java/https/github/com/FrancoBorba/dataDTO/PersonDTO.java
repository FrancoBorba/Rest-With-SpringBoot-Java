package https.github.com.FrancoBorba.dataDTO;

import java.io.Serializable;
/*
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
 */
import java.util.List;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import https.github.com.FrancoBorba.model.Book;
import https.github.com.FrancoBorba.serializer.GenderSerializer;
import jakarta.persistence.Column;


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

  private String urlProfile;

  private String urlPhoto;

  @JsonIgnore
  private List<Book> books;




  public String getUrlProfile() {
    return urlProfile;
  }
  public void setUrlProfile(String urlProfile) {
    this.urlProfile = urlProfile;
  }
  public String getUrlPhoto() {
    return urlPhoto;
  }
  public void setUrlPhoto(String urlPhoto) {
    this.urlPhoto = urlPhoto;
  }
  public List<Book> getBooks() {
    return books;
  }
  public void setBooks(List<Book> books) {
    this.books = books;
  }


  private String address;
 // @JsonIgnore // omite o atributo do json
 @JsonSerialize(using = GenderSerializer.class) // Serializer criado para gender
  private String gender;

  private Boolean enabled;

  public Boolean getEnabled() {
    return enabled;
  }
  public void setEnabled(Boolean enabled) {
    this.enabled = enabled;
  }
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

  // Retornar o firstName + lastName concatenados para o JasperReports
  @JsonIgnore
  public String getName(){
    return (firstName != null ? firstName : "") +
            (lastName != null ? lastName : "");
  }
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
    result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
    result = prime * result + ((urlProfile == null) ? 0 : urlProfile.hashCode());
    result = prime * result + ((urlPhoto == null) ? 0 : urlPhoto.hashCode());
    result = prime * result + ((books == null) ? 0 : books.hashCode());
    result = prime * result + ((address == null) ? 0 : address.hashCode());
    result = prime * result + ((gender == null) ? 0 : gender.hashCode());
    result = prime * result + ((enabled == null) ? 0 : enabled.hashCode());
    return result;
  }
  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (!super.equals(obj))
      return false;
    if (getClass() != obj.getClass())
      return false;
    PersonDTO other = (PersonDTO) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    if (firstName == null) {
      if (other.firstName != null)
        return false;
    } else if (!firstName.equals(other.firstName))
      return false;
    if (lastName == null) {
      if (other.lastName != null)
        return false;
    } else if (!lastName.equals(other.lastName))
      return false;
    if (urlProfile == null) {
      if (other.urlProfile != null)
        return false;
    } else if (!urlProfile.equals(other.urlProfile))
      return false;
    if (urlPhoto == null) {
      if (other.urlPhoto != null)
        return false;
    } else if (!urlPhoto.equals(other.urlPhoto))
      return false;
    if (books == null) {
      if (other.books != null)
        return false;
    } else if (!books.equals(other.books))
      return false;
    if (address == null) {
      if (other.address != null)
        return false;
    } else if (!address.equals(other.address))
      return false;
    if (gender == null) {
      if (other.gender != null)
        return false;
    } else if (!gender.equals(other.gender))
      return false;
    if (enabled == null) {
      if (other.enabled != null)
        return false;
    } else if (!enabled.equals(other.enabled))
      return false;
    return true;
  }


 
  
 
}
