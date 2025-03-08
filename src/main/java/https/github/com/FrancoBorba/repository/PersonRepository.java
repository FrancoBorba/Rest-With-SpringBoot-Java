package https.github.com.FrancoBorba.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import https.github.com.FrancoBorba.model.Person;
                                                        // entidade e tipo do id
public interface PersonRepository extends JpaRepository<Person, Long> {
  
}
