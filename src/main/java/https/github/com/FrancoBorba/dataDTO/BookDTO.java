package https.github.com.FrancoBorba.dataDTO;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.hateoas.RepresentationModel;

public class BookDTO extends RepresentationModel<BookDTO> implements Serializable {

  

  
  private Long id;
  private String author;
  private LocalDateTime launch_date;
  private BigDecimal price;
  private String title;

   public BookDTO(){
        
  }

  
  public Long getId() {
    return id;
  }
  public void setId(Long id) {
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
    int result = super.hashCode();
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result = prime * result + ((author == null) ? 0 : author.hashCode());
    result = prime * result + ((launch_date == null) ? 0 : launch_date.hashCode());
    result = prime * result + ((price == null) ? 0 : price.hashCode());
    result = prime * result + ((title == null) ? 0 : title.hashCode());
    return result;
  }
  
}
