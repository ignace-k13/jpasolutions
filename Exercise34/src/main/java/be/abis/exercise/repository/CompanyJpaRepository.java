package be.abis.exercise.repository;

import be.abis.exercise.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CompanyJpaRepository extends JpaRepository<Company,Integer> {

    Company findCompanyById(int id);

    Company findByNameStartsWith(String name);

    //Company findByNameStartsWithAndAddress_Town(String name, String town);

    @Query("select c from Company c where c.name like :name% and trim(c.address.town) = :town")
    Company getByNameAndTown(@Param("name") String name, @Param("town") String town);
}
