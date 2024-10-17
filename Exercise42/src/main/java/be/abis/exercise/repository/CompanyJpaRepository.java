package be.abis.exercise.repository;

import be.abis.exercise.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CompanyJpaRepository extends JpaRepository<Company,Integer> {

    @Query("select c from Company c  join fetch c.employees p where c.id=:id")
    Company findCompanyById(int id);

    Company findByNameStartsWith(String name);

    @Query("select c from Company c where c.name like :name% and c.address.town like :town%")
    Company getByNameAndTown(@Param("name") String name, @Param("town") String town);
}
