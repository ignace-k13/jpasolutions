package be.abis.exercise.service;

import be.abis.exercise.exception.PersonAlreadyExistsException;
import be.abis.exercise.exception.PersonCanNotBeDeletedException;
import be.abis.exercise.exception.PersonNotFoundException;
import be.abis.exercise.model.Company;
import be.abis.exercise.model.Person;
import be.abis.exercise.repository.CompanyJpaRepository;
import be.abis.exercise.repository.PersonJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AbisPersonService implements PersonService {

    @Autowired
    CompanyJpaRepository companyRepository;
    @Autowired
    PersonJpaRepository personRepository;

    @Override
    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    @Override
    public Person findPerson(int id) throws PersonNotFoundException {
        return personRepository.findByPersonId(id);
    }

    @Override
    public Person findPerson(String emailAddress, String passWord) throws PersonNotFoundException {
        return personRepository.findByEmailAddressAndPassword(emailAddress,passWord);
    }

    @Override
    @Transactional
    public Person addPerson(Person p) throws PersonAlreadyExistsException {
        Company c = p.getCompany();
        if (c != null) {
            Company foundComp = companyRepository.getByNameAndTown(c.getName(), c.getAddress().getTown());
            if(foundComp!=null) p.setCompany(foundComp);
        }
        return personRepository.save(p);
    }

    @Override
    public void deletePerson(int id) throws PersonCanNotBeDeletedException {
        personRepository.deleteById(id);
    }

    @Override
    public Person changePassword(Person p, String newPswd)  {
        Person person = personRepository.findByPersonId(p.getPersonId());
        person.setPassword(newPswd);
        return personRepository.save(person);
    }

    @Override
    public List<Person> findPersonsByCompanyName(String compName) {
        return personRepository.getByCompanyName(compName.toLowerCase());
    }

    @Override
    public long count() {
        return personRepository.count();
    }
}
