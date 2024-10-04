package be.abis.exercise.repository;

import be.abis.exercise.exception.PersonAlreadyExistsException;
import be.abis.exercise.exception.PersonCanNotBeDeletedException;
import be.abis.exercise.exception.PersonNotFoundException;
import be.abis.exercise.model.Address;
import be.abis.exercise.model.Company;
import be.abis.exercise.model.Person;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class FilePersonRepository implements PersonRepository {

	private List<Person> allPersons;
	private String fileLoc = "/temp/javacourses/personsJpa.csv";

	@PostConstruct
	public void init() {
		allPersons = new ArrayList<Person>();
		this.readFile();
	}

	@Override
	public List<Person> getAllPersons() {
		return allPersons;
	}

	public void readFile() {

		if (allPersons.size() != 0)
			allPersons.clear();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(fileLoc));
			String s = null;
			while ((s = br.readLine()) != null) {
				String[] vals = s.split(";");
				if (!vals[0].equals("")) {
					Address a = new Address();
					a.setStreet(!vals[10].equals("null") ? vals[10] : null);
					a.setNr(!vals[11].equals("null") ? vals[11] : "0");
					a.setZipcode(!vals[12].equals("null") ? vals[12] : null);
					a.setTown(!vals[13].equals("null") ? vals[13] : null);
					a.setCountryCode(!vals[14].equals("null") ? vals[14] : null);

					Company c = new Company();
					c.setName(!vals[7].equals("null") ? vals[7] : null);
					c.setTelephoneNumber(!vals[8].equals("null") ? vals[8] : null);
					c.setVatNr(!vals[9].equals("null") ? vals[9] : null);
					c.setAddress(a);

					Person p = new Person();
					p.setPersonId(!vals[0].equals("null") ? Integer.parseInt(vals[0]) : 0);
					p.setFirstName(!vals[1].equals("null") ? vals[1] : null);
					p.setLastName(!vals[2].equals("null") ? vals[2] : null);

					String bd = vals[3];
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
					LocalDate birthDate = LocalDate.parse(bd,formatter);
					p.setBirthDate(!vals[3].equals("null") ? birthDate : null);

					p.setEmailAddress(!vals[4].equals("null") ? vals[4] : null);
					p.setPassword(!vals[5].equals("null") ? vals[5] : null);
					p.setLanguage(!vals[6].equals("null") ? vals[6] : null);
					p.setCompany(c);

					allPersons.add(p);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public Person findPerson(String emailAddress, String passWord) throws PersonNotFoundException {
		if (emailAddress == null || passWord == null) {
			System.out.println("empty");
			throw new PersonNotFoundException("email/password incorrect");
		}

		this.readFile();
		// System.out.println("persons in PersonList" + allPersons);
		Iterator<Person> iter = allPersons.iterator();

		while (iter.hasNext()) {
			Person pers = iter.next();
			if (pers.getEmailAddress().equalsIgnoreCase(emailAddress) && pers.getPassword().equals(passWord)) {
				return pers;
			}
		}
		throw new PersonNotFoundException("email/password incorrect");
	}

	@Override
	public Person findPerson(int id) throws PersonNotFoundException {
		this.readFile();
		return allPersons.stream()
				.filter(p->p.getPersonId()==id).findFirst()
				.orElseThrow(()-> new PersonNotFoundException("person with id " + id + " not found"));
	}

	@Override
	public List<Person> findPersonsByCompanyName(String compName) {
		return allPersons.stream()
				.filter(p->p.getCompany().getName().equalsIgnoreCase(compName))
				.collect(Collectors.toList());
	}

	/*@Override
	public List<Person> findPersonsByCompanyName(String compName) {
		//this.readFile();
		List<Person> personsCompany = new ArrayList<>();
		for (Person p : allPersons){
			if (p.getCompany().getName().equalsIgnoreCase(compName)){
				personsCompany.add(p);
			}
		}
		return personsCompany;
	}*/


	@Override
	public void addPerson(Person p) throws PersonAlreadyExistsException {
		boolean b = false;
		this.readFile();
		Iterator<Person> iter = allPersons.iterator();
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(new FileWriter(fileLoc, true));
			while (iter.hasNext()) {
				Person pers = iter.next();
				if (pers.getEmailAddress().equalsIgnoreCase(p.getEmailAddress())) {
					throw new PersonAlreadyExistsException("you were already registered, login please");
				} else {
					b = true;
				}
			}
			if (b) {
				StringBuilder sb = this.parsePerson(p);
				// System.out.println(sb);

				pw.append("\n" + sb);
				allPersons.add(p);

			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		pw.close();
	}

	@Override
	public void deletePerson(int id) throws PersonCanNotBeDeletedException {
		Iterator<Person> iter = allPersons.iterator();
		boolean removed=false;
		while (iter.hasNext()) {
			Person pers = iter.next();
			if (pers.getPersonId()==id) {
				iter.remove();
				removed=true;
			}
		}
		if (removed){
			try {
				this.writePersons();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			throw new PersonCanNotBeDeletedException("person could not be removed");
		}

	}

	@Override
	public void changePassword(Person p, String newPswd) {
		Iterator<Person> iter = allPersons.iterator();
		while (iter.hasNext()) {
			Person pers = iter.next();
			if (pers.getEmailAddress().equals(p.getEmailAddress())) {
				pers.setPassword(newPswd);
			}
		}
		try {
			this.writePersons();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private StringBuilder parsePerson(Person p) {
		StringBuilder sb = new StringBuilder();
		LocalDate birthDate = p.getBirthDate();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String bd = formatter.format(birthDate);
		sb.append(p.getPersonId() + ";").append(p.getFirstName() + ";").append(p.getLastName() + ";")
				.append(bd + ";").append(p.getEmailAddress() + ";")
				.append(p.getPassword() + ";").append(p.getLanguage().toLowerCase() + ";")
				.append(p.getCompany().getName() + ";").append(p.getCompany().getTelephoneNumber() + ";")
				.append(p.getCompany().getVatNr() + ";").append(p.getCompany().getAddress().getStreet() + ";")
				.append(p.getCompany().getAddress().getNr()+ ";").append(p.getCompany().getAddress().getZipcode() + ";")
				.append(p.getCompany().getAddress().getTown()+ ";")
				.append(p.getCompany().getAddress().getCountryCode()+ ";");

		System.out.println(sb);
		return sb;
	}

	private void writePersons() throws IOException {
		PrintWriter pw = new PrintWriter(new FileWriter(fileLoc));

		for (Person pe : allPersons) {
			StringBuilder sb = this.parsePerson(pe);
			pw.println(sb);
		}

		pw.close();
	}

}
