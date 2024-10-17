package be.abis.exercise.repository;

import be.abis.exercise.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PersonJpaRepository extends JpaRepository<Person,Integer> {

    Person findByPersonId(int id);

    Person findByEmailAddress(String email);

    Person findByEmailAddressAndPassword(String email, String password);

    @Query("select p from Person p where lower(trim(p.company.name)) like %:name%")
    List<Person> getByCompanyName(@Param("name") String name);


    @Query("select size(p.hobbies) - 1 from Person p where p.personId=:personId")
    Integer getLastHnoForPerson(@Param("personId") int personId);

    @Modifying
    @Query(value = "insert into hobbies values (:id,:hno,:hobby)", nativeQuery = true)
    void addHobbyForPerson(@Param("id") int id, @Param("hno") int hno, @Param("hobby") String hobby);

}
