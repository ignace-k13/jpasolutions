package be.abis.exercise.controller;

import be.abis.exercise.exception.PersonAlreadyExistsException;
import be.abis.exercise.exception.PersonCanNotBeDeletedException;
import be.abis.exercise.exception.PersonNotFoundException;
import be.abis.exercise.form.LoginForm;
import be.abis.exercise.model.Person;
import be.abis.exercise.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("persons")
public class PersonApiController {

    @Autowired
    PersonService personService;

    @Autowired
    PersonService ps;

    @GetMapping("")
    public List<Person> getAllPersons(){
        return ps.getAllPersons();
    }

    @GetMapping("{id}")
    public Person findPerson(@PathVariable("id") int id) throws PersonNotFoundException {
       return ps.findPerson(id);
    }

    @PostMapping("/login")
    public Person findPersonByMailAndPwd(@RequestBody LoginForm login) throws PersonNotFoundException {
        return ps.findPerson(login.getEmail(), login.getPassword());
    }

    @GetMapping(path="/compquery")
    public List<Person> findPersonsByCompanyName(@RequestParam("compname") String compName) {
        return ps.findPersonsByCompanyName(compName);
    }

    @PostMapping(path="")
    public void addPerson(@RequestBody Person p) throws PersonAlreadyExistsException {
        ps.addPerson(p);
    }

    @DeleteMapping("{id}")
    public void deletePerson(@PathVariable("id") int id) throws PersonCanNotBeDeletedException {
        ps.deletePerson(id);
    }

    @PutMapping("{id}")
    public void changePassword(@PathVariable("id") int id, @RequestBody Person person) throws PersonNotFoundException {
        System.out.println("changing password to newpswd= " + person.getPassword());
        ps.changePassword(person, person.getPassword());
    }





}
