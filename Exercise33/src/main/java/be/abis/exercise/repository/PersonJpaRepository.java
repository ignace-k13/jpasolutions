package be.abis.exercise.repository;

import be.abis.exercise.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PersonJpaRepository extends JpaRepository<Person,Integer> {

    Person findByPersonId(int id);

    Person findByEmailAddressAndPassword(String email, String password);

    @Query("select p from Person p where lower(trim(p.company.name)) like %:name%")
    List<Person> getByCompanyName(@Param("name") String name);
}
