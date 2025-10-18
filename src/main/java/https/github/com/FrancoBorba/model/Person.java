package https.github.com.FrancoBorba.model;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "person") // quando o nome da tabela eh igual tanto na entidade como no bd eh opcional especificar
// esppecificado para fins de estudo
public class Person implements Serializable {
  
  private static final long serialVersionUID = 1L;

  public Person(){

  }

  @Id // mapeando os atributos , nao precisa especificar que eh uma coluna
  @GeneratedValue(strategy = GenerationType.IDENTITY) // forma de geracao do id
  private Long id;

  @Column(name = "first_name" , nullable = false , length = 80)
  private String firstName;

  @Column(name = "last_name" , nullable = false , length = 80)
  private String lastName;

  @Column( nullable = false , length = 100) // quando nao passa o nome ja se sabe que eh adsress
  private String address;

  @Column( nullable = false , length = 6) // quando nao passa o nome ja se sabe que eh gender
  private String gender;

  @Column( nullable = false)
  private Boolean enabled;

  @Column(name = "wikipedia_profile_url" , length = 255)
  private String urlProfile;

  @Column(name = "photo_url" , length = 255)
  private String urlPhoto;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
         name = "person_books",
         joinColumns = @JoinColumn(name = "person_id"),
         inverseJoinColumns = @JoinColumn(name = "book_id")
    )
  private List<Book> books;

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
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
    result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
    result = prime * result + ((address == null) ? 0 : address.hashCode());
    result = prime * result + ((gender == null) ? 0 : gender.hashCode());
    result = prime * result + ((enabled == null) ? 0 : enabled.hashCode());
    result = prime * result + ((urlProfile == null) ? 0 : urlProfile.hashCode());
    result = prime * result + ((urlPhoto == null) ? 0 : urlPhoto.hashCode());
    result = prime * result + ((books == null) ? 0 : books.hashCode());
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
    Person other = (Person) obj;
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
    return true;
  }
  
}
