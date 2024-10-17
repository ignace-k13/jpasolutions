package be.abis.exercise.mapper;

import be.abis.exercise.form.PersonForm;
import be.abis.exercise.dto.PersonDTO;
import be.abis.exercise.model.Address;
import be.abis.exercise.model.Company;
import be.abis.exercise.model.Person;

public class PersonMapper {

    public static PersonDTO toDTO(Person person) {
        Company c = person.getCompany();
        String companyName = null;
        String companyTown = null;
        if (c != null) {
            companyName = c.getName().trim();
            companyTown = c.getAddress().getTown().trim();
        }
        return new PersonDTO(person.getFirstName(),
                person.getLastName().trim(),
                person.getEmailAddress(),
                person.getBirthDate(),
                companyName,
                companyTown);
    }

    public static Person toPerson(PersonForm personForm) {
        Person p = new Person(personForm.getFirstName(), personForm.getLastName(), personForm.getBirthDate(), personForm.getEmailAddress(), personForm.getPassword(), personForm.getLanguage());
        if (personForm.getCompanyName()!=null && !personForm.getCompanyName().trim().isEmpty()) {
            Address a = new Address(personForm.getStreet(), personForm.getNr(), personForm.getZipcode(), personForm.getTown(), personForm.getCountryCode());
            Company c = new Company(personForm.getCompanyName(), personForm.getTelephoneNumber(), personForm.getVatNr(), a);
            p.setCompany(c);
        }
        return p;
    }


}
