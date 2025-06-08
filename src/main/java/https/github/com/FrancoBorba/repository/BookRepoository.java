package https.github.com.FrancoBorba.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import https.github.com.FrancoBorba.model.Book;




public interface BookRepoository extends JpaRepository<Book, Long> {


  
}
