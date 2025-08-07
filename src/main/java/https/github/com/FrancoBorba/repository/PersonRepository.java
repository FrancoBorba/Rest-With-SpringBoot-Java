package https.github.com.FrancoBorba.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import https.github.com.FrancoBorba.model.Person;
/*Importa a interface JpaRepository, que fornece métodos prontos para CRUD (Create, Read, Update, Delete).
Aqui definimos um repositório para Person, herdando de JpaRepository<Person, Long>.

O que isso significa?

Person: A entidade gerenciada.
Long: O tipo do ID da entidade.
sso já traz métodos prontos, como:

findAll() → Retorna todas as pessoas.
findById(Long id) → Busca uma pessoa pelo ID.
save(Person p) → Salva ou atualiza uma pessoa.
deleteById(Long id) → Deleta uma pessoa pel

A classe PersonRepository não precisa ter métodos explícitos porque o Spring Data JPA já fornece vários métodos prontos.

No entanto, se precisar de buscas específicas, podemos adicionar métodos customizados dentro do repositório.



 */
                                                        // entidade e tipo do id
public interface PersonRepository extends JpaRepository<Person, Long> {
  

  @Modifying(clearAutomatically = true)
  @Query("UPDATE Person p SET p.enabled = false WHERE p.id =:id")
  void disablePerson(@Param("id") Long id);
}
