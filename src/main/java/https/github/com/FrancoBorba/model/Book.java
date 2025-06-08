package https.github.com.FrancoBorba.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity // diz que a classe eh uma entidade
@Table(name = "books")
public class Book implements Serializable {


  private static final long serialVersionUID = 1L;

  
  
  




  // contrutor
  public Book() {
  }

  @Id // chave primaria
  @GeneratedValue(strategy = GenerationType.IDENTITY) // gera automaticamente o id(AutoIncremento)
  private Long id;

  @Column(name = "author" , nullable = false , length = 100)
  private String author;

  @Column(name = "launch_date" , nullable = false)
  private LocalDateTime launch_date;

  @Column(name = "price" , nullable = false)
  private BigDecimal price;

  @Column(name = "title" , nullable = false , length = 100)
  private String title;

  public long getId() {
    return id;
  }
  public void setId(long id) {
    this.id = id;
  }
  public String getAuthor() {
    return author;
  }
  public void setAuthor(String author) {
    this.author = author;
  }
  public LocalDateTime getLaunch_date() {
    return launch_date;
  }
  public void setLaunch_date(LocalDateTime launch_date) {
    this.launch_date = launch_date;
  }
  public BigDecimal getPrice() {
    return price;
  }
  public void setPrice(BigDecimal price) {
    this.price = price;
  }
  public String getTitle() {
    return title;
  }
  public void setTitle(String title) {
    this.title = title;
  }
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((author == null) ? 0 : author.hashCode());
    result = prime * result + ((launch_date == null) ? 0 : launch_date.hashCode());
    result = prime * result + ((price == null) ? 0 : price.hashCode());
    result = prime * result + ((title == null) ? 0 : title.hashCode());
    result = prime * result + (int) (id ^ (id >>> 32));
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
    Book other = (Book) obj;
    if (author == null) {
      if (other.author != null)
        return false;
    } else if (!author.equals(other.author))
      return false;
    if (launch_date == null) {
      if (other.launch_date != null)
        return false;
    } else if (!launch_date.equals(other.launch_date))
      return false;
    if (price == null) {
      if (other.price != null)
        return false;
    } else if (!price.equals(other.price))
      return false;
    if (title == null) {
      if (other.title != null)
        return false;
    } else if (!title.equals(other.title))
      return false;
    if (id != other.id)
      return false;
    return true;
  }
  
 
}
