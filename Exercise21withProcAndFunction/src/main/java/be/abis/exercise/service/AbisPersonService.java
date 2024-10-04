package be.abis.exercise.service;

import be.abis.exercise.exception.PersonAlreadyExistsException;
import be.abis.exercise.exception.PersonCanNotBeDeletedException;
import be.abis.exercise.exception.PersonNotFoundException;
import be.abis.exercise.model.Person;
import be.abis.exercise.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AbisPersonService implements PersonService {

    @Autowired
    PersonRepository personRepository;

    @Override
    public List<Person> getAllPersons() {
        return personRepository.getAllPersons();
    }

    @Override
    public Person findPerson(int id) throws PersonNotFoundException {
        return personRepository.findPerson(id);
    }

    @Override
    public Person findPerson(String emailAddress, String passWord) throws PersonNotFoundException {
        return personRepository.findPerson(emailAddress, passWord);
    }

    @Override
    public void addPerson(Person p) throws PersonAlreadyExistsException {
        personRepository.addPerson(p);
    }

    @Override
    public void deletePerson(int id) throws PersonCanNotBeDeletedException {
        personRepository.deletePerson(id);
    }

    @Override
    public void changePassword(Person p, String newPswd)  {
        personRepository.changePassword(p,newPswd);
    }

    @Override
    public List<Person> findPersonsByCompanyName(String compName) {
        return personRepository.findPersonsByCompanyName(compName);
    }
}
