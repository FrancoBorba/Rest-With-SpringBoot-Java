package https.github.com.FrancoBorba.dataDTO.v2;

import java.io.Serializable;
import java.util.Date;

public class PersonDTOV2 implements Serializable {
  
  private static final long serialVersionUID = 1L;

  public PersonDTOV2(){

  }


  private Long id;
  private String firstName;
  private String lastName;
  private Date birthDay;
  

  private String address;
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
  public Date getBirthDay() {
    return birthDay;
  }
  public void setBirthDay(Date birthDay) {
    this.birthDay = birthDay;
  }
  
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
    result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
    result = prime * result + ((birthDay == null) ? 0 : birthDay.hashCode());
    result = prime * result + ((address == null) ? 0 : address.hashCode());
    result = prime * result + ((gender == null) ? 0 : gender.hashCode());
    return result;
  }
  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    PersonDTOV2 other = (PersonDTOV2) obj;
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
    if (birthDay == null) {
      if (other.birthDay != null)
        return false;
    } else if (!birthDay.equals(other.birthDay))
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
    return true;
  }

  
 

}
