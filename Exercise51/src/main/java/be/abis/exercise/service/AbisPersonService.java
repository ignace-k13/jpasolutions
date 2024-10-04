package be.abis.exercise.service;

import be.abis.exercise.exception.PersonAlreadyExistsException;
import be.abis.exercise.exception.PersonCanNotBeDeletedException;
import be.abis.exercise.exception.PersonNotFoundException;
import be.abis.exercise.model.Company;
import be.abis.exercise.model.Person;
import be.abis.exercise.repository.CompanyJpaRepository;
import be.abis.exercise.repository.PersonJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionSystemException;
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
        Person foundPerson = personRepository.findByPersonId(id);
        if (foundPerson==null) throw new PersonNotFoundException("person with id " + id + " could not be found.");
        return foundPerson;
    }

    @Override
    public Person findPerson(String emailAddress, String passWord) throws PersonNotFoundException {
        Person foundPerson = personRepository.findByEmailAddressAndPassword(emailAddress,passWord);
        if (foundPerson==null) throw new PersonNotFoundException("Problem logging in. Please check your email/password");
        return foundPerson;
    }

    @Override
    @Transactional(rollbackFor = PersonAlreadyExistsException.class)
    public Person addPerson(Person p) throws PersonAlreadyExistsException {
        Person foundPerson = personRepository.findByEmailAddress(p.getEmailAddress());
        if (foundPerson!=null) throw new PersonAlreadyExistsException("You are already registered. Please login");
        Company c = p.getCompany();
        if (c != null) {
            Company foundComp = companyRepository.getByNameAndTown(c.getName(), c.getAddress().getTown());
            if(foundComp!=null) p.setCompany(foundComp);
        }
        return personRepository.save(p);
    }

    @Override
    @Transactional(rollbackFor = PersonCanNotBeDeletedException.class)
    public void deletePerson(int id) throws PersonCanNotBeDeletedException {
       Person p = personRepository.findByPersonId(id);
        if (p==null) throw new PersonCanNotBeDeletedException("Person cannot be deleted since he does not exist.");
        try {
            personRepository.deleteById(id);
            personRepository.flush();  // Ensure the exception is thrown within the transaction boundary
            System.out.println("person deleted");
        } catch (DataIntegrityViolationException dve){
          throw new PersonCanNotBeDeletedException("You cannot delete this person, since there are still enrolments/sessions for him.");
        }
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
